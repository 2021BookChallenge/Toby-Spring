create database tobyspring;
create table tobyspring.users
(
    id       varchar(10) primary key,
    name     varchar(20) not null,
    password varchar(10) not null
);
