insert into sec_user (id, login_name, cname, email, password, salt, status, group_id) values(1,'admin','管理员','admin@springside.org.cn','691b14d79bf0fa2215f155235df5e670b64394cc','7efbd59d9741d34f','E',NULL);
insert into sec_user (id, login_name, cname, email, password, salt, status, group_id) values(2,'user','温江春','user@springside.org.cn','2488aa0c31c624687bd9928e0a5d29e7d1ed520b','6d65d24122c30500','E',NULL);

insert into sec_role (id, name,cname, status) values(1,'Admin','超级管理员','E');
insert into sec_role (id, name,cname, status) values(2,'User','普通用户','E');

insert into sec_user_role (user_id, role_id) values(1,1);
insert into sec_user_role (user_id, role_id) values(2,2);