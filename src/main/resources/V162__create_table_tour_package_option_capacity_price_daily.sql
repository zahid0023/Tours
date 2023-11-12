create table if not exists tour_package_option_capacity_price_daily
(
    package_option_id bigint,
    price_id          bigint,
    date              date,
    total_seats       bigint,
    bookable_seats    bigint,
    black_price       numeric,
    red_price         numeric,
    discount_percent  numeric,
    policy_type       varchar(50),
    constraint tour_package_option_capacity_price_daily_pk
        unique (price_id, date)
);