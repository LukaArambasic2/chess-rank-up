-- Adding sections
INSERT INTO MySection (nameSection) VALUES ('Chess');
INSERT INTO MySection (nameSection) VALUES ('Biking');

-- Adding members, some may be logged in
INSERT INTO MyMember (firstName, lastName, jmbag) VALUES ('Hrvoje', 'Horvat', '0006040945');
INSERT INTO MyMember (firstName, lastName, jmbag) VALUES ('Ivan', 'Ivanovic', '0036533665');
INSERT INTO MyMember (firstName, lastName, jmbag, email, isVerified, passwordHash, salt) VALUES ('Jan', 'Jankovic', '0036538219', 'jj56789@fer.hr', true, '{argon2id}$argon2id$v=19$m=65536,t=2,p=1$XuL67FD8TSTL/b7nbQkXig$j8YAoti2uZAPGrrbGfiy85Vn11u7dVWBQHeTQ4iPU8s', '7fOyhOOOXWTKbt1/k0u89TZqsy3106K0'); -- password = "password1"
INSERT INTO MyMember (firstName, lastName, jmbag, email, passwordHash, salt) VALUES ('Petar', 'Petrovic', '0036539669', 'pp53838@fer.hr', '{argon2id}$argon2id$v=19$m=65536,t=2,p=1$PxOGCMPzKXebWEkopYW6tA$T/Lxv64XwMGb3Bm4VfhOS3+b73ftAB5q+j9WM7B0wG4', '8vhrHT5sWd8E18xmY5vHvCtI07dJsDD0'); -- password = "password2"
INSERT INTO MyMember (firstName, lastName, jmbag, email, passwordHash, salt) VALUES ('Jura', 'Juric', '0036540976', 'jj53890@pmf.hr', '{argon2id}$argon2id$v=19$m=65536,t=2,p=1$mdCRlrJdg5iotfsI43A+Tw$Vg9C2zbN7ppUA+wNzWSljfAPqdHgktb1zsW3E3cM8Vk', '/4pPIj/3ujPj/8/M5TubHLd7Ri9YsmFV'); -- password = "password3"

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

-- Adding members of sections
INSERT INTO SectionMember (idMember, idSection, idRank) VALUES (1, 1, 1);
INSERT INTO SectionMember (idMember, idSection, idRank) VALUES (2, 2, 1);
INSERT INTO SectionMember (idMember, idSection, idRank) VALUES (3, 1, 1);
INSERT INTO SectionMember (idMember, idSection, idRank) VALUES (4, 1, 1);
INSERT INTO SectionMember (idMember, idSection, idRank) VALUES (5, 2, 1);
