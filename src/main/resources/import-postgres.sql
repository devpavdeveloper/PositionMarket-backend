BEGIN;
INSERT INTO user_profile(id, email, phone, price_distance)
VALUES ('a4486ad2-3879-4afd-98f6-7feafeafb109', 'jsdeveloper@yahoo.com', '+375299479630', 65);

INSERT INTO users (id, login, password, enabled, last_password_reset_date)
VALUES
('a4486ad2-3879-4afd-98f6-7feafeafb109', 'admin', '$2a$04$eq8a2soxlIEga/BrF20lO.HsSWXsjlpUTY0r6YiY.MARNLgCgc0pa',
 TRUE, '2018-11-13 17:16:30.802000');

INSERT INTO roles (id, title) VALUES
('15ab8ed6-651c-42b4-89d6-62cf99e6dbb4', 'ROLE_ADMIN');

INSERT INTO user_role (id_user, id_role) VALUES ('a4486ad2-3879-4afd-98f6-7feafeafb109', '15ab8ed6-651c-42b4-89d6-62cf99e6dbb4');
COMMIT;