INSERT INTO roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'USER');

INSERT INTO users (id, first_name, last_name, email, password, role_id) VALUES (5, 'Nick', 'Green', 'nick@mail.com', '$2a$10$CJgEoobU2gm0euD4ygru4ukBf9g8fYnPrMvYk.q0GMfOcIDtUhEwC', 2);
INSERT INTO users (id, first_name, last_name, email, password, role_id) VALUES (6, 'Nora', 'White', 'nora@mail.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0e1Yv1KEKeqUlYfLZQ1OQvyUrnEcX/rOy', 2);
INSERT INTO users (id, first_name, last_name, email, password, role_id) VALUES (4, 'Mike', 'Brown', 'mike@mail.com', '$2a$10$CdEJ2PKXgUCIwU4pDQWICuiPjxb1lysoX7jrN.Y4MTMoY9pjfPALO', 1);


INSERT INTO subjects (id, title,vns_url,lection_url,prakt_url,lab_url) VALUES (7, 'Mike''s To-Do #1','vns_url','lection_url','prakt_url','lab_url');
INSERT INTO subjects (id, title,vns_url,lection_url,prakt_url,lab_url) VALUES (8, 'Mike''s To-Do #2','vns_url','lection_url','prakt_url','lab_url');
INSERT INTO subjects (id, title,vns_url,lection_url,prakt_url,lab_url) VALUES (9, 'Mike''s To-Do #3','vns_url','lection_url','prakt_url','lab_url');
INSERT INTO subjects (id, title,vns_url,lection_url,prakt_url,lab_url) VALUES (10, 'Nick''s To-Do #1','vns_url','lection_url','prakt_url','lab_url');
INSERT INTO subjects (id, title,vns_url,lection_url,prakt_url,lab_url) VALUES (11, 'Nick''s To-Do #2','vns_url','lection_url','prakt_url','lab_url');

INSERT INTO tasks (id, title, subject_id,link,deadline,state_id,user_id) VALUES (6, 'Task #2', 7,'linkedIN','2022-11-16 14:00:04.810221', 5, 5);
INSERT INTO tasks (id, title, subject_id,link,deadline,state_id,user_id) VALUES (5, 'Task #1', 7,'linkedIN','2022-11-15 14:00:11.480271', 5, 6);
INSERT INTO tasks (id, title, subject_id,link,deadline,state_id,user_id) VALUES (7, 'Task #3', 7,'linkedIN','2022-11-28 14:00:16.351238', 6, 4);

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

