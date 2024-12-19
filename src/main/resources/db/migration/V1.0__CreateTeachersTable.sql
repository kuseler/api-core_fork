create table teachers
(
    id            bigserial primary key,
    first_name    text not null,
    last_name     text not null,
    creation_date timestamp(6) default now()
);

alter sequence teachers_id_seq RESTART 1000;