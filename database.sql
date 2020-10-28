DROP DATABASE IF EXISTS operationCoop_db;
CREATE DATABASE IF NOT EXISTS operationCoop_db;


CREATE USER capstone_18@localhost IDENTIFIED BY 'capstone18';
GRANT ALL ON operationCoop_db.* TO capstone_18@localhost;


USE operationCoop_db;
# Drop TABLE user;
# create table user (
#      id INT UNSIGNED NOT NULL AUTO_INCREMENT,
#      username  VARCHAR(100) DEFAULT 'NONE',
#     fullName  VARCHAR(100) NOT NULL,
#      date datetime not null ,
#      PRIMARY KEY (id)
# );