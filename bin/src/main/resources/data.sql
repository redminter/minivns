INSERT INTO roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'USER');

INSERT INTO users (id, first_name, last_name, email, password, role_id) VALUES (5, 'Nick', 'Green', 'nick@mail.com', '$2a$10$QBIIErFNhGumQC5GpKOrKeriq7o6eip7pGCEJ7uhyXELsjt0MFgkO', 2);--password nick
INSERT INTO users (id, first_name, last_name, email, password, role_id) VALUES (6, 'Nora', 'White', 'nora@mail.com', '$2a$10$W.tYjC5j4z/A2JaRQNxSJuTqKdLbLNrdf5EEUEsxxjp6rlUUc8p3a', 2);--password nora
INSERT INTO users (id, first_name, last_name, email, password, role_id) VALUES (4, 'Mike', 'Brown', 'mike@mail.com', '$2a$10$2/MBbogaQUbNSWGN6TKTQOBGTgf2rSm5pzNTelDezC6ZIU7SLVyJy', 1);--password mike


INSERT INTO subjects (id, title,vns_url,lection_url,prakt_url,lab_url,at_monday,at_tuesday,at_wednesday,at_thursday,at_friday) VALUES (7, 'test_ТІК','vns_url','lection_url','prakt_url','lab_url' ,false,true,false,false,true);
INSERT INTO subjects (id, title,vns_url,lection_url,prakt_url,lab_url,at_monday,at_tuesday,at_wednesday,at_thursday,at_friday) VALUES (8, 'test_АК','vns_url','lection_url','prakt_url','lab_url'  ,true,false,false,false,false);
INSERT INTO subjects (id, title,vns_url,lection_url,prakt_url,lab_url,at_monday,at_tuesday,at_wednesday,at_thursday,at_friday) VALUES (9, 'test_СП','vns_url','lection_url','prakt_url','lab_url'  ,false,false,true,false,true);
INSERT INTO subjects (id, title,vns_url,lection_url,prakt_url,lab_url,at_monday,at_tuesday,at_wednesday,at_thursday,at_friday) VALUES (10, 'test_ІПЗ','vns_url','lection_url','prakt_url','lab_url',false,true,false,false,false);
INSERT INTO subjects (id, title,vns_url,lection_url,prakt_url,lab_url,at_monday,at_tuesday,at_wednesday,at_thursday,at_friday) VALUES (11, 'test_РК','vns_url','lection_url','prakt_url','lab_url' ,true,false,false,true,false);

INSERT INTO tasks (id, title, subject_id,link,deadline,user_id) VALUES (6, 'Task #2', 7,'linkedIN','2022-11-16 14:00:04.810221', 5);
INSERT INTO tasks (id, title, subject_id,link,deadline,user_id) VALUES (5, 'Task #1', 7,'linkedIN','2022-11-15 14:00:11.480271', 6);
INSERT INTO tasks (id, title, subject_id,link,deadline,user_id) VALUES (7, 'Task #3', 7,'linkedIN','2022-11-28 14:00:16.351238', 4);

--for changing first number of id autoincrementing
-- create sequence roles_start3;
-- create sequence users_start7;
-- create sequence subjects_start12;
-- create sequence tasks_start8;

-- change exactly in the database sequences

-- ---- auto-generated definition
-- create sequence roles_start3 start with 3 increment by 1;
--
-- alter sequence roles_start3 owner to postgres;
--
--
-- -- auto-generated definition
-- create sequence subjects_start12 start with 12 increment by 1;
--
-- alter sequence subjects_start12 owner to postgres;
--
-- -- auto-generated definition
-- create sequence tasks_start8 start with 8 increment by 1;
--
-- alter sequence tasks_start8 owner to postgres;
--
-- -- auto-generated definition
-- create sequence users_start7 start with 7 increment by 1;
--
-- alter sequence users_start7 owner to postgres;

