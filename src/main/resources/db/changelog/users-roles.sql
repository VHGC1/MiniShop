insert into users (  password, email) values ( '$2a$10$cvJ1CtFWzh8Zq/maL3WR.O6hrfX5q2XVEMrcnMjWiAkb0M0KJNmxO', 'user@email.com.br');
insert into users (  password, email) values ( '$2a$10$cvJ1CtFWzh8Zq/maL3WR.O6hrfX5q2XVEMrcnMjWiAkb0M0KJNmxO', 'admin@email.com.br');

insert into roles (id, name) values (1, 'ROLE_BACKOFFICE_USER');
insert into roles (id, name) values (2, 'ROLE_ADMIN_USER');

insert into users_roles (user_id, role_id) values (1,1);
insert into users_roles (user_id, role_id) values (2,2);

