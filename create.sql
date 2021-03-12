create table users (id bigint not null auto_increment, full_name varchar(50), user_contact varchar(255), user_mail varchar(255), user_name varchar(20) not null, user_password varchar(20), user_type varchar(255), primary key (id)) engine=InnoDB
alter table post_review add column date date
alter table posts add column date date
alter table posts add column is_shared bit
alter table posts add column shared_post_id bigint not null
alter table posts add column share_counter bigint not null
