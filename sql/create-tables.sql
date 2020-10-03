-- user table
CREATE TABLE IF NOT EXISTS user (
    id BIGINT auto_increment NOT NULL,
    login varchar(100) NOT NULL,
    name varchar(100) NOT NULL,
    mail varchar(100) NULL,
    password VARCHAR(200) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

-- defaulr users
INSERT INTO user (login, name, mail, password )
VALUES ("test", "Test Tesov", "test@mail.ru", "40bd001563085fc35165329ea1ff5c5ecbdbbeef"); 
-- pass = 123
