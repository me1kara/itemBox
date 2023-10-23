
create table quotes(
    id bigint primary key auto_increment,
    text varchar(255) not null
);

create table localfood(
    id bigint primary key auto_increment,
    region varchar2(20) not null,
    food varchar2(30) not null
);

