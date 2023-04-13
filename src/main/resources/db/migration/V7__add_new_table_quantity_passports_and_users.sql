create table if not exists public.quantity_passports_and_users
(
    id                 serial
        primary key,

    quantity_passports integer,
    quantity_users     integer

);

