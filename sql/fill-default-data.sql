-- default users
INSERT INTO user (login, name, mail, password )
VALUES ("test", "Test Tesov", "test@mail.ru", "40bd001563085fc35165329ea1ff5c5ecbdbbeef"); 
-- pass = 123

-- default note_types
INSERT INTO note_type (id, name)
VALUES  (1, "ticket"),
        (2, "miting"),
        (3, "vacation");

-- default notes
INSERT INTO note (owner, type_id, note, comment, `day`)
VALUES (1, 1, "Hellow World.", "first comment", DATE '2020-12-31');
