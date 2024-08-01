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

-- TODO: in backend add constraint so no new semester can interfere with the interval: [dateFrom, dateTo]
-- of any other semester
CREATE TABLE Semester
(
    idSemester BIGSERIAL PRIMARY KEY,
    nameSemester VARCHAR(30) NOT NULL,
    dateFromSemester DATE NOT NULL,
    dateToSemester DATE,
    CONSTRAINT checkDates CHECK (dateToSemester IS NULL OR dateToSemester >= dateFromSemester)
);

-- CREATE OR REPLACE FUNCTION check_semester_overlap() RETURNS TRIGGER AS $$
-- BEGIN
--   IF EXISTS (
--     SELECT 1
--     FROM Semester
--     WHERE idSemester <> NEW.idSemester
--       AND daterange(dateFromSemester, dateToSemester, '[]') && daterange(NEW.dateFromSemester, NEW.dateToSemester, '[]')
--   ) THEN
--     RAISE EXCEPTION 'New semester interval overlaps with existing semester';
-- END IF;
-- RETURN NEW;
-- END;
-- $$ LANGUAGE plpgsql;
--
-- CREATE TRIGGER semester_overlap_trigger
-- BEFORE INSERT OR UPDATE ON Semester
-- FOR EACH ROW EXECUTE FUNCTION check_semester_overlap();

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

CREATE TABLE MyAttribute
(
    idAttribute BIGSERIAL PRIMARY KEY,
    nameAttribute VARCHAR(30) NOT NULL,
    datType VARCHAR(20) NOT NULL
);

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

CREATE TABLE News
(
    idNews BIGSERIAL PRIMARY KEY,
    title VARCHAR(80) NOT NULL,
    dateCreated DATE NOT NULL,
    dateEdited DATE,
    content VARCHAR(80) NOT NULL,
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
