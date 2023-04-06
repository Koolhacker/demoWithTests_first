create table public.users
(
    id          bigint generated always as identity
        primary key,
    name        varchar,
    email       varchar,
    country     varchar,
    age         integer,
    gender      varchar,
    is_deleted  boolean,
    passport_id integer
        constraint fkddyjnd93b8x7gdng15k7g429p
            references public.passports
);
create table public.photos
(
    id          serial
        primary key,
    create_date timestamp,
    high        integer,
    is_visible  boolean,
    length      integer,
    link_photo  varchar(255),
    employee_id integer
        constraint fkbxq293jvxh5d4t6qrndovypyt
            references public.users
);