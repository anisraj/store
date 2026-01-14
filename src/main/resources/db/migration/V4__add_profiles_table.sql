create table profiles
(
    id             bigint                 not null
        primary key,
    bio            text                   not null,
    phone_number   varchar(15)            not null,
    date_of_birth  date                   not null,
    loyalty_points int unsigned default 0 not null,
    constraint profiles_users_id_fk
        foreign key (id) references users (id)
);