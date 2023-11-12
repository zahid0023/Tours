alter table tour_package
    add column if not exists thumb_image_url text;

alter table tour_accommodation_option
    drop column if exists tour_package_id;

alter table tour_transfer_option
    drop column if exists tour_package_id;

alter table tour_food_option
    drop column if exists tour_package_id;

alter table meal_packages
    drop column if exists meal_package_name;

alter table subscribed_tours
    add column if not exists number_of_reviews integer default 0 not null;

alter table subscribed_tours
    add column if not exists rating_in_stars double precision default 0.0 not null;

alter table tour_package
    alter column package_price_per_person set not null;

alter table tour_package
    alter column package_price_per_person set default 0.0;

alter table tour_package
    alter column total_package_price set not null;

alter table tour_package
    alter column total_package_price set default 0.0;

alter table tour_package
    alter column default_food_option_price set not null;

alter table tour_package
    alter column default_food_option_price set default 0.0;

alter table tour_package
    alter column default_accommodation_option_price set not null;

alter table tour_package
    alter column default_accommodation_option_price set default 0.0;

alter table tour_package
    alter column default_transfer_option_price set not null;

alter table tour_package
    alter column default_transfer_option_price set default 0.0;

alter table tour_package
    alter column guide_price set not null;

alter table tour_package
    alter column guide_price set default 0.0;

create table if not exists spot_entry
(
    id               bigserial
        primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean default true,
    tour_package_id  bigint references tour_package (id) not null,
    activity_id      bigint references activity (id)     not null,
    price_per_person numeric(10, 2)                      not null,
    remark           text                                not null
);


