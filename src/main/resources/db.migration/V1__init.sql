
CREATE TABLE  IMAGES (
    id INT  PRIMARY KEY SERIAL ,
    language VARCHAR(50),
    text VARCHAR(5000),
    url VARCHAR(255) not null unique,
    created_On timestamp,
    updated_On timestamp);

