create table users (id bigint not null auto_increment, full_name varchar(50), user_contact varchar(255), user_mail varchar(255), user_name varchar(20) not null, user_password varchar(20), user_type varchar(255), primary key (id)) engine=InnoDB