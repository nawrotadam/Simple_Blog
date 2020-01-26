CREATE SEQUENCE hibernate_sequence;


CREATE TABLE profile
(
    id BIGSERIAL NOT NULL,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    firstname VARCHAR NOT NULL,
    lastname VARCHAR NOT NULL,
    dateofbirth VARCHAR NOT NULL,

    PRIMARY KEY (id)
);


CREATE TABLE article
(
    id BIGSERIAL NOT NULL,
    title VARCHAR NOT NULL,
    content VARCHAR NOT NULL,
    date VARCHAR NOT NULL,

    author_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES profile (id)
);