
CREATE TABLE  IMAGES (
    id  INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    language VARCHAR(50),
    text VARCHAR(5000),
    url VARCHAR(255) not null unique,
    created_On timestamp,
    updated_On timestamp);

