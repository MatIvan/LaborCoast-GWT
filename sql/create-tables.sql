-- user
CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT NOT NULL,
    login VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    mail VARCHAR(100) NULL,
    password VARCHAR(200) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

-- note_type
CREATE TABLE IF NOT EXISTS note_type (
    id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    CONSTRAINT pk_note_type PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

-- note
CREATE TABLE IF NOT EXISTS note (
    id BIGINT AUTO_INCREMENT NOT NULL,
    owner BIGINT NOT NULL,
    type_id BIGINT NOT NULL,
    note VARCHAR(250) NOT NULL,
    comment VARCHAR(250) NOT NULL,
    hours INT NOT NULL DEFAULT 0,
    day DATE NOT NULL,
    CONSTRAINT pk_note PRIMARY KEY (id),
    CONSTRAINT fk_note_owner FOREIGN KEY (owner) REFERENCES user(id),
    CONSTRAINT fk_note_type FOREIGN KEY (type_id) REFERENCES note_type(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;
