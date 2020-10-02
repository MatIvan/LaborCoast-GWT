-- user table
CREATE TABLE IF NOT EXISTS laborcoast.`user` (
    ID BIGINT auto_increment NOT NULL,
    LOGIN varchar(100) NOT NULL,
    NAME varchar(100) NOT NULL,
    MAIL varchar(100) NULL,
    password VARCHAR(200) NOT NULL,
    CONSTRAINT PK_USER PRIMARY KEY (ID)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

-- defaulr users
INSERT INTO `user` (LOGIN, NAME, MAIL, PASSWORD )
VALUES ("test", "Test Tesov", "test@mail.ru", "40bd001563085fc35165329ea1ff5c5ecbdbbeef"); -- pass = 123
