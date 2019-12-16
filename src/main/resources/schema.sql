create table user
(
    id        bigint auto_increment
        primary key,
    password  varchar(255) not null,
    user_name varchar(255) not null
);

create table city
(
    id              bigint auto_increment
        primary key,
    created_at      timestamp    null,
    description     varchar(255) not null,
    favourite_count int          null,
    name            varchar(255) not null,
    population      int          not null
);

create table city_like
(
    user_id bigint not null,
    city_id bigint not null,
    primary key (user_id, city_id),
    constraint FK5gj7x0nqpxej7ow00s6vf5ql2
        foreign key (user_id) references user (id),
    constraint FKkvmol5ornc0eqwfe4oe1tgk5r
        foreign key (city_id) references city (id)
);