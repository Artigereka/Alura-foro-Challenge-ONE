CREATE TABLE topics (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    body TEXT (5000) NOT NULL,
    author BIGINT (100) NOT NULL,
    course BIGINT (100) NOT NULL,
    PRIMARY KEY (id)
);