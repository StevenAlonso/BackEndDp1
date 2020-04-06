INSERT INTO `users` (email,username,password,lastname,enabled) VALUES ("user@dp1.com","Mateo",'$2a$10$w6MdQyzkHFUMxR/Lt4pmW.ip4SNChcP.h4yMaVcv9VdrO5ImICnMy',"Rosales",true);

INSERT INTO `role` (role_name) VALUES ("ADMIN");
INSERT INTO `role` (role_name) VALUES ("USER");

INSERT INTO `user_role` (user_id, role_id) VALUES (1, 1);
INSERT INTO `user_role` (user_id, role_id) VALUES (2, 2);
INSERT INTO `user_role` (user_id, role_id) VALUES (3, 2);
INSERT INTO `user_role` (user_id, role_id) VALUES (4, 2);
INSERT INTO `user_role` (user_id, role_id) VALUES (5, 2);
