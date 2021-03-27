create table users (id bigint not null auto_increment, full_name varchar(50), user_contact varchar(255), user_mail varchar(255), user_name varchar(20) not null, user_password varchar(20), user_type varchar(255), primary key (id)) engine=InnoDB
alter table post_review add column date date
alter table posts add column date date
alter table posts add column is_shared bit
alter table posts add column shared_post_id bigint not null
alter table posts add column share_counter bigint not null
create table post_review (id bigint not null auto_increment, comment varchar(255), date date, rating integer not null, post_id bigint, reviewer_id bigint, primary key (id)) engine=InnoDB
create table posts (id bigint not null auto_increment, author varchar(255), blog varchar(255), cover_photo longblob, date date, edition varchar(255), is_shared bit, post_type varchar(255), share_counter bigint not null, shared_post_id bigint not null, title varchar(255), uploader_id bigint, primary key (id)) engine=InnoDB
create table users (id bigint not null auto_increment, full_name varchar(255), user_contact varchar(255), user_mail varchar(255), user_name varchar(255) not null, user_password varchar(255), user_type varchar(255), primary key (id)) engine=InnoDB
alter table post_review add constraint FK8p4mj2wpkdxwlpu5nhip2fvp4 foreign key (post_id) references posts (id)
alter table post_review add constraint FK4g4gkh532wcgqublvneqxv8bp foreign key (reviewer_id) references users (id)
alter table posts add constraint FKhqh2biylg4c8x16beuwso3873 foreign key (uploader_id) references users (id)
