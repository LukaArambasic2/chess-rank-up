-- Adding sections
INSERT INTO section (nameSection, descriptionSection) VALUES ('Chess', 'We are FER''s Chess section');
INSERT INTO section (nameSection, descriptionSection) VALUES ('Biking', 'We are Biking section');

-- Adding members, some may be logged in
INSERT INTO member (firstName, lastName, jmbag, isVerified) VALUES ('Hrvoje', 'Horvat', '1234567890', false);
INSERT INTO member (firstName, lastName, jmbag, isVerified) VALUES ('Ivan', 'Ivanovic', '0006040945', false);
INSERT INTO member (firstName, lastName, jmbag, email, isVerified) VALUES ('Jan', 'Jankovic', '6234567890', 'jj56789@fer.hr', false);
INSERT INTO member (firstName, lastName, jmbag, email, isVerified) VALUES ('Petar', 'Petrovic', '0036538383', 'pp53838@fer.hr', false);
INSERT INTO member (firstName, lastName, jmbag, email, isVerified) VALUES ('Jura', 'Juric', '0036538908', 'jj53890@pmf.hr', false);
