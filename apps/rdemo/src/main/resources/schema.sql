CREATE TABLE claim
(
    id       SERIAL PRIMARY KEY,
    sender   VARCHAR(255),
    receiver VARCHAR(255),
    message  VARCHAR(255)
);