INSERT INTO `users` (email,username,password,name,lastname,enabled) VALUES ("user@dp1.com","mat123",'$2a$10$w6MdQyzkHFUMxR/Lt4pmW.ip4SNChcP.h4yMaVcv9VdrO5ImICnMy',"Mateo","Rosales",true);

INSERT INTO `role` (description) VALUES ("ADMIN");
INSERT INTO `role` (description) VALUES ("USER");

INSERT INTO `user_role` (user_id, role_id) VALUES (1, 1);
