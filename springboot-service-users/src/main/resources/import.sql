
INSERT INTO `roles` (name) VALUES ('ROLE_USER');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `states_user` (name) VALUES ('CREADO');
INSERT INTO `states_user` (name) VALUES ('BLOQUEADO');

INSERT INTO `users` (username, password, enabled, name, last_name, mail, state_id) VALUES ('andres','$2a$10$ykhXmCAam5FUEF9GN.4Z8OwwWJidvMii6VFYe77cmS2X6oF6p4W86',1, 'Andres',  'Guzman','profesor@bolsadeideas.com', 2);
INSERT INTO `users` (username, password, enabled, name, last_name, mail, state_id) VALUES ('admin','$2a$10$qGyDfZLBB.SgLv7GCP3uZe3oM38fVtr58T1iZ1LNOvO61loNUAAaK',1, 'John', 'Doe','jhon.doe@bolsadeideas.com', 1);


INSERT INTO `users_roles` (user_id, role_id) VALUES (1, 1);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 2);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 1);
