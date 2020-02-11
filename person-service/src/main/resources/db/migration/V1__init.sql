DROP TABLE IF EXISTS person;
CREATE TABLE person (
    id               LONG AUTO_INCREMENT PRIMARY KEY,
    first_name       VARCHAR(250) NOT NULL,
    last_name        VARCHAR(250) NOT NULL,
    age              INT,
    favourite_colour VARCHAR(250)
);

DROP TABLE IF EXISTS hobby;
CREATE TABLE hobby (
    id        LONG AUTO_INCREMENT PRIMARY KEY,
    person_id LONG REFERENCES person(id) ON DELETE CASCADE,
    name      VARCHAR(250) NOT NULL
);