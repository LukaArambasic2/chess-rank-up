-- Adding sections
INSERT INTO MySection (nameSection) VALUES ('Chess');
INSERT INTO MySection (nameSection) VALUES ('Biking');

-- Adding semesters
INSERT INTO Semester (nameSemester, dateFromSemester, dateToSemester) VALUES ('22/23 ZS', '2022-10-1', '2023-2-1');
INSERT INTO Semester (nameSemester, dateFromSemester, dateToSemester) VALUES ('22/23 LJS', '2023-2-20', '2023-7-1');
INSERT INTO Semester (nameSemester, dateFromSemester, dateToSemester) VALUES ('23/24 ZS', '2023-10-1', '2024-2-1');
INSERT INTO Semester (nameSemester, dateFromSemester, dateToSemester) VALUES ('23/24 ZS', '2024-2-20', '2024-7-1');

-- Adding ranks
INSERT INTO MyRank (nameRank, idSection) VALUES ('Pijun', 1);
INSERT INTO MyRank (nameRank, idSection) VALUES ('Konj', 1);
INSERT INTO MyRank (nameRank, idSection) VALUES ('Lovac', 1);
INSERT INTO MyRank (nameRank, idSection) VALUES ('Top', 1);
INSERT INTO MyRank (nameRank, idSection) VALUES ('Kraljica', 1);
INSERT INTO MyRank (nameRank, idSection) VALUES ('Kralj', 1);

INSERT INTO MyRank (nameRank, idSection) VALUES ('Pijun', 2);
INSERT INTO MyRank (nameRank, idSection) VALUES ('Kraljica', 2);
INSERT INTO MyRank (nameRank, idSection) VALUES ('Kralj', 2);

-- Adding members, some may be logged in
INSERT INTO MyMember (firstName, lastName, jmbag) VALUES ('Hrvoje', 'Horvat', '0006040945');
INSERT INTO MyMember (firstName, lastName, jmbag) VALUES ('Ivan', 'Ivanovic', '0036533665');
INSERT INTO MyMember (firstName, lastName, jmbag, email, isVerified, passwordHash, salt) VALUES ('Jan', 'Jankovic', '0036538219', 'jj56789@fer.hr', true, '{argon2id}$argon2id$v=19$m=65536,t=2,p=1$XuL67FD8TSTL/b7nbQkXig$j8YAoti2uZAPGrrbGfiy85Vn11u7dVWBQHeTQ4iPU8s', '7fOyhOOOXWTKbt1/k0u89TZqsy3106K0'); -- password = "password1"
INSERT INTO MyMember (firstName, lastName, jmbag, email, passwordHash, salt) VALUES ('Petar', 'Petrovic', '0036539669', 'pp53838@fer.hr', '{argon2id}$argon2id$v=19$m=65536,t=2,p=1$PxOGCMPzKXebWEkopYW6tA$T/Lxv64XwMGb3Bm4VfhOS3+b73ftAB5q+j9WM7B0wG4', '8vhrHT5sWd8E18xmY5vHvCtI07dJsDD0'); -- password = "password2"
INSERT INTO MyMember (firstName, lastName, jmbag, email, isVerified, passwordHash, salt) VALUES ('Jura', 'Juric', '0036540976', 'jj53890@pmf.hr', true, '{argon2id}$argon2id$v=19$m=65536,t=2,p=1$mdCRlrJdg5iotfsI43A+Tw$Vg9C2zbN7ppUA+wNzWSljfAPqdHgktb1zsW3E3cM8Vk', '/4pPIj/3ujPj/8/M5TubHLd7Ri9YsmFV'); -- password = "password3"
INSERT INTO MyMember (firstName, lastName, jmbag, email, isVerified, passwordHash, salt) VALUES ('Luka', 'Lukic', '0036542367', 'luka.lukic@pmf.hr', true, '{argon2id}$argon2id$v=19$m=65536,t=2,p=1$mdCRlrJdg5iotfsI43A+Tw$Vg9C2zbN7ppUA+wNzWSljfAPqdHgktb1zsW3E3cM8Vk', '/4pPIj/3ujPj/8/M5TubHLd7Ri9YsmFV'); -- password = "password3"
INSERT INTO MyMember (firstName, lastName, jmbag, email, isVerified, passwordHash, salt) VALUES ('Josip', 'Josipovic', '0036543684', 'josip.josipovic@pmf.hr', true, '{argon2id}$argon2id$v=19$m=65536,t=2,p=1$mdCRlrJdg5iotfsI43A+Tw$Vg9C2zbN7ppUA+wNzWSljfAPqdHgktb1zsW3E3cM8Vk', '/4pPIj/3ujPj/8/M5TubHLd7Ri9YsmFV'); -- password = "password3"
INSERT INTO MyMember (firstName, lastName, jmbag, email, isVerified, passwordHash, salt) VALUES ('Ana', 'Anic', '0016140662', 'ana.anic@pmf.hr', true, '{argon2id}$argon2id$v=19$m=65536,t=2,p=1$mdCRlrJdg5iotfsI43A+Tw$Vg9C2zbN7ppUA+wNzWSljfAPqdHgktb1zsW3E3cM8Vk', '/4pPIj/3ujPj/8/M5TubHLd7Ri9YsmFV'); -- password = "password3"
INSERT INTO MyMember (firstName, lastName, jmbag, email, isVerified, passwordHash, salt) VALUES ('Lea', 'Leakovic', '0036533670', 'lea.leakovic@fer.hr', true, '{argon2id}$argon2id$v=19$m=65536,t=2,p=1$mdCRlrJdg5iotfsI43A+Tw$Vg9C2zbN7ppUA+wNzWSljfAPqdHgktb1zsW3E3cM8Vk', '/4pPIj/3ujPj/8/M5TubHLd7Ri9YsmFV'); -- password = "password3"
INSERT INTO MyMember (firstName, lastName, jmbag, email, isVerified, passwordHash, salt) VALUES ('Marija', 'Marinovic', '0036538245', 'marija.marinovic@fer.hr', true, '{argon2id}$argon2id$v=19$m=65536,t=2,p=1$mdCRlrJdg5iotfsI43A+Tw$Vg9C2zbN7ppUA+wNzWSljfAPqdHgktb1zsW3E3cM8Vk', '/4pPIj/3ujPj/8/M5TubHLd7Ri9YsmFV'); -- password = "password3"

-- Adding section semesters

-- Semester 22/23 ZS
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 1, 1, 1, 1);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 1, 3, 1, 1);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 1, 4, 1, 1);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 1, 7, 1, 1);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 0, 8, 1, 1);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 0, 9, 1, 1);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 0, 10, 1, 1);

-- Semester 22/23 LJS
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 0, 1, 1, 2);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 1, 3, 1, 2);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 1, 4, 1, 2);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 1, 7, 1, 2);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 1, 8, 1, 2);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 1, 9, 1, 2);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 0, 10, 1, 2);

-- Semester 23/24 ZS
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 0, 1, 1, 3);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 0, 3, 1, 3);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 0, 4, 1, 3);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 1, 7, 1, 3);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 1, 8, 1, 3);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 1, 9, 1, 3);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 0, 10, 1, 3);

-- Semester 23/24 LJS
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 3, 1, 1, 4);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 11, 3, 1, 4);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 8, 4, 1, 4);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 6, 7, 1, 4);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 5, 8, 1, 4);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 5, 9, 1, 4);
INSERT INTO SectionSemester (threshold, points, idMember, idSection, idSemester) VALUES (12, 7, 10, 1, 4);

-- Adding members of sections
INSERT INTO SectionMember (idMember, idSection, idRank, pointsAll) VALUES (1, 1, 1, 4);
INSERT INTO SectionMember (idMember, idSection, idRank, pointsAll) VALUES (2, 2, 1, 0);
INSERT INTO SectionMember (idMember, idSection, idRank, pointsAll) VALUES (3, 1, 1, 13);
INSERT INTO SectionMember (idMember, idSection, idRank, pointsAll) VALUES (4, 1, 1, 10);
INSERT INTO SectionMember (idMember, idSection, idRank, pointsAll) VALUES (5, 2, 1, 0);
INSERT INTO SectionMember (idMember, idSection, idRank, pointsAll) VALUES (6, 2, 2, 0);
INSERT INTO SectionMember (idMember, idSection, idRank, pointsAll) VALUES (7, 1, 2, 9);
INSERT INTO SectionMember (idMember, idSection, idRank, pointsAll) VALUES (8, 1, 2, 7);
INSERT INTO SectionMember (idMember, idSection, idRank, pointsAll) VALUES (9, 1, 2, 7);
INSERT INTO SectionMember (idMember, idSection, idRank, pointsAll) VALUES (10, 1, 3, 7);

-- Add types of events
INSERT INTO EventType (nameEventType, defaultPoints) VALUES ('Ostalo', 1);
INSERT INTO EventType (nameEventType, defaultPoints) VALUES ('Sekcija', 1);
INSERT INTO EventType (nameEventType, defaultPoints) VALUES ('Online turnir', 1);
INSERT INTO EventType (nameEventType, defaultPoints) VALUES ('Uzivo turnir', 2);

-- Add events
INSERT INTO MyEvent (nameEvent, dateEvent, idSection, idEventType) VALUES ('Sekcija 1', '2022-11-11', 1, 2);
INSERT INTO MyEvent (nameEvent, dateEvent, idSection, idEventType) VALUES ('Sekcija 14', '2023-04-01', 1, 2);
INSERT INTO MyEvent (nameEvent, dateEvent, idSection, idEventType) VALUES ('Sekcija 1', '2023-11-11', 1, 2);
INSERT INTO MyEvent (nameEvent, dateEvent, idSection, idEventType) VALUES ('Sekcija 14', '2024-04-01', 1, 2);
INSERT INTO MyEvent (nameEvent, dateEvent, idSection, idEventType) VALUES ('Sekcija 15', '2024-04-08', 1, 2);
INSERT INTO MyEvent (nameEvent, dateEvent, idSection, idEventType) VALUES ('Lichess turnir 1', '2024-04-26', 1, 3);
INSERT INTO MyEvent (nameEvent, dateEvent, idSection, idEventType) VALUES ('FER Kup', '2024-04-29', 1, 4);

-- Add participations
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 1, 1);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 3, 1);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 4, 1);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 7, 1);

INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 3, 2);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 4, 2);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 7, 2);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 8, 2);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 9, 2);

INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 7, 3);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 8, 3);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 9, 3);

-- Last semester
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 1, 4);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 3, 4);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 4, 4);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 7, 4);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 8, 4);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 9, 4);

INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 3, 5);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 7, 5);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 8, 5);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 9, 5);

INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (3, 3, 6);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (2, 4, 6);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 7, 6);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 8, 6);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 9, 6);

INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 1, 7);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (3, 3, 7);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (2, 4, 7);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (1, 7, 7);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 8, 7);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (0, 9, 7);
INSERT INTO Participation (addPoints, idMember, idEvent) VALUES (5, 10, 7);
