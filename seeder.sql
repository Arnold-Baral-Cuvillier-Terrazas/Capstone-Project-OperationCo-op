
USE operationCoop_db;
INSERT INTO user(email, full_name, user_name, password, pronouns, birth_date)
VALUES ('bijit@gmail.com', 'Bijit' ,'bijit16', '12345', 'he/his', '2000/02/03 22:31:01'),
       ('binjita@gmail.com', 'binjita' ,'binjita', 'binjita','she/her', '1900/02/06 22:31:01');

USE operationCoop_db;
# USE capstone_db;
INSERT INTO tags (name)
VALUES
('foodie'),
('Cooking'),
('Animals'),
('Plants'),
('Vegan'),
('non-veg'),
('Art'),
('DIY'),
('Action'),
('Coder'),
('Dance'),
('Music'),
('acting'),
('traveling');


USE operationCoop_db;
Insert into user_rating( id, rated_user_id, rating, rating_user_id)
  VALUES (1,1,3,2),
         (2,2,5,3);


