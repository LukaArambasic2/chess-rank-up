-- Adding sections
INSERT INTO section (nameSection, descriptionSection) VALUES ('Chess', 'We are FER''s Chess section');
INSERT INTO section (nameSection, descriptionSection) VALUES ('Biking', 'We are Biking section');

-- Adding members, some may be logged in
INSERT INTO member (firstName, lastName, jmbag) VALUES ('Hrvoje', 'Horvat', '1234567890');
INSERT INTO member (firstName, lastName, jmbag) VALUES ('Ivan', 'Ivanovic', '0006040945');
INSERT INTO member (firstName, lastName, jmbag, email) VALUES ('Jan', 'Jankovic', '6234567890', 'jj56789@fer.hr');
INSERT INTO member (firstName, lastName, jmbag, email) VALUES ('Petar', 'Petrovic', '0036538383', 'pp53838@fer.hr');
INSERT INTO member (firstName, lastName, jmbag, email) VALUES ('Jura', 'Juric', '0036538908', 'jj53890@pmf.hr');
