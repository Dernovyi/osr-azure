DROP TABLE if exists IMAGES;
CREATE TABLE IMAGES (id BIGINT(20) primary key auto_increment, language VARCHAR(50), text VARCHAR(5000), url VARCHAR(255) not null unique, created_On datetime, updated_On datetime);

