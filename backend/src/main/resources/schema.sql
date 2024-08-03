DROP TABLE IF EXISTS MyMember CASCADE;
DROP TABLE IF EXISTS Verification CASCADE;
DROP TABLE IF EXISTS MySection CASCADE;
DROP TABLE IF EXISTS Semester CASCADE;
DROP TABLE IF EXISTS EventType CASCADE;
DROP TABLE IF EXISTS MyEvent CASCADE;
DROP TABLE IF EXISTS Participation CASCADE;
DROP TABLE IF EXISTS MyRank CASCADE;
DROP TABLE IF EXISTS MyAttribute CASCADE;
DROP TABLE IF EXISTS SectionSemester CASCADE;
DROP TABLE IF EXISTS News CASCADE;
DROP TABLE IF EXISTS SectionMember CASCADE;
DROP TABLE IF EXISTS MemberInfo CASCADE;

CREATE TABLE MyMember
(
    idMember BIGSERIAL PRIMARY KEY,
    firstName VARCHAR(30) NOT NULL,
    lastName VARCHAR(30) NOT NULL,
    -- jmbag CHAR(10) NOT NULL,
    jmbag VARCHAR(10) NOT NULL CHECK (LENGTH(jmbag) = 10),
    email VARCHAR(50),

    -- 2 bytes for '{' and '}', 10 bytes for name of encoder
    -- 128 bytes for actual hash (the biggest length is for 'argon2')
    -- 32 bytes for salt and 32 bytes for pepper
    -- bigger size is for future use
    passwordHash VARCHAR(140),

    salt VARCHAR(32),
    isVerified BOOLEAN NOT NULL DEFAULT FALSE,
    UNIQUE (jmbag),
    UNIQUE (email)
);

CREATE TABLE Verification
(
    idVerification BIGSERIAL PRIMARY KEY,
    url VARCHAR(80) NOT NULL,
    expirationTime TIMESTAMP NOT NULL,
    idMember BIGINT NOT NULL,
    FOREIGN KEY (idMember) REFERENCES MyMember(idMember) ON DELETE CASCADE,
    UNIQUE (url)
);

CREATE TABLE MySection
(
    idSection BIGSERIAL PRIMARY KEY,
    nameSection VARCHAR(30) NOT NULL,
    descriptionSection VARCHAR(80),
    logo VARCHAR(80),
    isOpen BOOLEAN NOT NULL DEFAULT TRUE,
    UNIQUE (nameSection)
);

-- Creates default ranks that are Chess' section easter egg :D.
CREATE OR REPLACE FUNCTION createDefaultRanks()
RETURNS TRIGGER AS $$
BEGIN
    -- Insert default ranks for the new section.
    INSERT INTO MyRank (nameRank, idSection) VALUES 
        ('Pijun', NEW.idSection),
        ('Konj', NEW.idSection),
        ('Lovac', NEW.idSection),
        ('Top', NEW.idSection),
        ('Kraljica', NEW.idSection),
        ('Kralj', NEW.idSection);
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER insertDefaultRanks
AFTER INSERT ON MySection
FOR EACH ROW
EXECUTE FUNCTION createDefaultRanks();

CREATE OR REPLACE FUNCTION validateSemesterName(nameSemester VARCHAR)
RETURNS BOOLEAN AS $$
DECLARE
    year1 INTEGER;
    year2 INTEGER;
    semester VARCHAR;
BEGIN
    -- Check if the format matches the required pattern
    IF nameSemester !~ '^[0-9]+/[0-9]+ (LJS|ZS)$' THEN
        RETURN FALSE;
    END IF;

    -- Extract the years and type
    year1 := SUBSTRING(nameSemester from '^[0-9]+')::INTEGER;
    year2 := SUBSTRING(nameSemester from '/([0-9]+)')::INTEGER;
    semester := SUBSTRING(nameSemester from '(LJS|ZS)$');

    -- Validate the years
    IF year2 != year1 + 1 THEN
        RETURN FALSE;
    END IF;

    RETURN TRUE;
END;
$$ LANGUAGE plpgsql;

CREATE TABLE Semester
(
    idSemester BIGSERIAL PRIMARY KEY,
    nameSemester VARCHAR(30) NOT NULL,
    dateFromSemester DATE NOT NULL,
    dateToSemester DATE,
	UNIQUE (nameSemester),
    CONSTRAINT checkDates CHECK (dateToSemester IS NULL OR dateToSemester >= dateFromSemester),
    CONSTRAINT checkNameSemesterFormat CHECK (validateSemesterName(nameSemester))
);

CREATE OR REPLACE FUNCTION checkSemesterDateOverlap() 
RETURNS TRIGGER AS $$
BEGIN
    IF EXISTS (
        SELECT 1 
        FROM Semester 
        WHERE 
            (NEW.dateFromSemester <= dateToSemester AND (NEW.dateToSemester IS NULL OR NEW.dateToSemester >= dateFromSemester))
    ) THEN
        RAISE EXCEPTION 'Semester dates overlap with an existing semester';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER semesterDateOverlapTrigger
BEFORE INSERT OR UPDATE ON Semester
FOR EACH ROW EXECUTE FUNCTION checkSemesterDateOverlap();

CREATE TABLE MyRank
(
    idRank BIGSERIAL PRIMARY KEY,
    nameRank VARCHAR(30) NOT NULL,
    image VARCHAR(80),
    pointsModifier INT NOT NULL DEFAULT 0,
    pointsRequired INT,  -- NULL if it is impossible to get that rank with the points alone
    idSection BIGINT NOT NULL,
    FOREIGN KEY (idSection) REFERENCES MySection(idSection) ON DELETE CASCADE
);

CREATE TABLE EventType
(
    idEventType BIGSERIAL PRIMARY KEY,
    nameEventType VARCHAR(30) NOT NULL,
    defaultPoints INT NOT NULL DEFAULT 0
);

CREATE TABLE MyEvent
(
    idEvent BIGSERIAL PRIMARY KEY,
    nameEvent VARCHAR(30) NOT NULL,
    dateEvent DATE NOT NULL,
    descriptionEvent VARCHAR(80),
    idSection BIGINT NOT NULL,
    idEventType BIGINT NOT NULL,
    FOREIGN KEY (idSection) REFERENCES MySection(idSection) ON DELETE CASCADE,
    FOREIGN KEY (idEventType) REFERENCES EventType(idEventType) ON DELETE CASCADE
);

-- Event date must be between [dateFromSemester, dateToSemester] of some semester.
-- If that is not the case then Event is invalid and we return error.
CREATE OR REPLACE FUNCTION validateEventDate()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 
        FROM Semester 
        WHERE NEW.dateEvent BETWEEN dateFromSemester AND dateToSemester
    ) THEN
        RAISE EXCEPTION 'Event date must be within a valid semester period';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER validateEventDateTrigger
BEFORE INSERT OR UPDATE ON MyEvent
FOR EACH ROW
EXECUTE FUNCTION validateEventDate();

CREATE TABLE SectionSemester
(
    idSectionSemester BIGSERIAL PRIMARY KEY,
    threshold INT NOT NULL,
    points INT NOT NULL DEFAULT 0,
    idSemester BIGINT NOT NULL,
    idSection BIGINT NOT NULL,
    idMember BIGINT NOT NULL,
    FOREIGN KEY (idSemester) REFERENCES Semester(idSemester) ON DELETE CASCADE,
    FOREIGN KEY (idSection) REFERENCES MySection(idSection) ON DELETE CASCADE,
    FOREIGN KEY (idMember) REFERENCES MyMember(idMember) ON DELETE CASCADE,
    UNIQUE (idMember, idSemester, idSection)
);

CREATE TABLE Participation
(
    idParticipation BIGSERIAL PRIMARY KEY,
    addPoints INT NOT NULL DEFAULT 0,
    idMember BIGINT NOT NULL,
    idEvent BIGINT NOT NULL,
    FOREIGN KEY (idMember) REFERENCES MyMember(idMember) ON DELETE CASCADE,
    FOREIGN KEY (idEvent) REFERENCES MyEvent(idEvent) ON DELETE CASCADE,
    UNIQUE(idMember, idEvent)
);

CREATE OR REPLACE FUNCTION handleParticipationChanges()
RETURNS TRIGGER AS $$
DECLARE
    default_points INT;
    event_date DATE;
    semester_id BIGINT;
    section_id BIGINT;
    points_diff INT;
BEGIN
    -- Determine the action: INSERT, UPDATE, or DELETE.
    IF (TG_OP = 'DELETE') THEN
        -- Fetch default points from EventType for the event.
        SELECT defaultPoints INTO default_points
        FROM EventType
        WHERE idEventType = (SELECT idEventType FROM MyEvent WHERE idEvent = OLD.idEvent);

        -- Fetch event date and section.
        SELECT dateEvent, idSection INTO event_date, section_id
        FROM MyEvent
        WHERE idEvent = OLD.idEvent;

        -- Find the corresponding semester.
        SELECT idSemester INTO semester_id
        FROM Semester
        WHERE event_date BETWEEN dateFromSemester AND dateToSemester;

        -- Update points in SectionSemester.
        UPDATE SectionSemester
        SET points = points - (OLD.addPoints + default_points)
        WHERE idMember = OLD.idMember AND idSemester = semester_id AND idSection = section_id;

        -- Update pointsAll in SectionMember.
        UPDATE SectionMember
        SET pointsAll = pointsAll - (OLD.addPoints + default_points)
        WHERE idMember = OLD.idMember AND idSection = section_id;

    ELSE
        -- Fetch default points from EventType for the event.
        SELECT defaultPoints INTO default_points
        FROM EventType
        WHERE idEventType = (SELECT idEventType FROM MyEvent WHERE idEvent = NEW.idEvent);

        -- Fetch event date and section.
        SELECT dateEvent, idSection INTO event_date, section_id
        FROM MyEvent
        WHERE idEvent = NEW.idEvent;

        -- Find the corresponding semester.
        SELECT idSemester INTO semester_id
        FROM Semester
        WHERE event_date BETWEEN dateFromSemester AND dateToSemester;

        IF semester_id IS NULL THEN
            RAISE EXCEPTION 'Event date does not fall within any valid semester';
        END IF;

        IF (TG_OP = 'INSERT') THEN
            points_diff = NEW.addPoints + default_points;

            -- Insert or update SectionSemester.
            INSERT INTO SectionSemester (threshold, points, idSemester, idSection, idMember)
            VALUES (0, points_diff, semester_id, section_id, NEW.idMember)
            ON CONFLICT (idMember, idSemester, idSection) DO UPDATE
            SET points = SectionSemester.points + EXCLUDED.points;

            -- Update pointsAll in SectionMember.
            UPDATE SectionMember
            SET pointsAll = pointsAll + points_diff
            WHERE idMember = NEW.idMember AND idSection = section_id;

        ELSIF (TG_OP = 'UPDATE') THEN
            -- Calculate the difference in points for the update.
            points_diff = (NEW.addPoints + default_points) - (OLD.addPoints + default_points);

            -- Update SectionSemester.
            UPDATE SectionSemester
            SET points = points + points_diff
            WHERE idMember = NEW.idMember AND idSemester = semester_id AND idSection = section_id;

            -- Update pointsAll in SectionMember.
            UPDATE SectionMember
            SET pointsAll = pointsAll + points_diff
            WHERE idMember = NEW.idMember AND idSection = section_id;
        END IF;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger to handle participation changes.
CREATE TRIGGER participationChangesTrigger
AFTER INSERT OR UPDATE OR DELETE ON Participation
FOR EACH ROW
EXECUTE FUNCTION handleParticipationChanges();


CREATE TABLE MyAttribute
(
    idAttribute BIGSERIAL PRIMARY KEY,
    nameAttribute VARCHAR(30) NOT NULL,
    datType VARCHAR(20) NOT NULL
);

CREATE TABLE News
(
    idNews BIGSERIAL PRIMARY KEY,
    title VARCHAR(80) NOT NULL,
    dateCreated DATE NOT NULL,
    dateEdited DATE,
    myContent VARCHAR(80) NOT NULL,
    images VARCHAR(80),
    idSection BIGINT NOT NULL,
    idAuthor BIGINT NOT NULL,
    FOREIGN KEY (idSection) REFERENCES MySection(idSection) ON DELETE CASCADE,
    FOREIGN KEY (idAuthor) REFERENCES MyMember(idMember) ON DELETE CASCADE
);

CREATE TABLE SectionMember
(
    idSectionMember BIGSERIAL PRIMARY KEY,
    isActive BOOLEAN NOT NULL DEFAULT TRUE,
    pointsAll INT NOT NULL DEFAULT 0,
    idMember BIGINT NOT NULL,
    idSection BIGINT NOT NULL,
    idRank BIGINT NOT NULL,
    FOREIGN KEY (idMember) REFERENCES MyMember(idMember) ON DELETE CASCADE,
    FOREIGN KEY (idSection) REFERENCES MySection(idSection) ON DELETE CASCADE,
    FOREIGN KEY (idRank) REFERENCES MyRank(idRank) ON DELETE CASCADE,
    UNIQUE (idMember, idSection)
);

CREATE TABLE MemberInfo
(
    idInfo BIGSERIAL PRIMARY KEY,
    stringValue VARCHAR(40), -- can be added later
    showOnProfile BOOLEAN NOT NULL DEFAULT FALSE,
    idSection BIGINT NOT NULL,
    idAttribute BIGINT NOT NULL,
    idMember BIGINT NOT NULL,
    FOREIGN KEY (idSection) REFERENCES MySection(idSection) ON DELETE CASCADE,
    FOREIGN KEY (idAttribute) REFERENCES MyAttribute(idAttribute) ON DELETE CASCADE,
    FOREIGN KEY (idMember) REFERENCES MyMember(idMember) ON DELETE CASCADE,
    UNIQUE (idSection, idMember, idAttribute)
);
