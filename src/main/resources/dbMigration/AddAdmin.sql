insert into usr (id, username,first_name,last_name, password, active,created_at)
    values (1, 'admin','Nikita','Barkhatov', 'qqq3', true,'06:16:18_19.08.2019');

insert into user_role (user_id, roles)
    values  (1, 'ADMIN');