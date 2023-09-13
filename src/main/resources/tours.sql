-- Location Table
create table if not exists location
(
    id   bigserial primary key,
    name varchar(255)
    );


-- Tour Add Table.
-- Here we map tour with a destination location and
-- number of days and number of nights
create table if not exists tour_location_mapping
(
    id                      bigserial primary key,
    created_by              varchar(255),
    created_at              timestamp,
    deleted                 boolean,
    last_modified_by        varchar(255),
    updated_at              timestamp,
    version                 integer,
    active                  boolean,
    number_of_days          bigint not null,
    number_of_nights        bigint not null,
    short_address           varchar(255),
    tour_name               varchar(255),
    tour_tag                varchar(255),
    destination_location_id bigint not null references location (id)
    );

-- Activity Type Table
create table if not exists activity_type
(
    id                 bigserial primary key,
    created_by         varchar(255),
    created_at         timestamp,
    deleted            boolean,
    last_modified_by   varchar(255),
    updated_at         timestamp,
    version            integer,
    active             boolean,
    activity_type_name varchar(255) not null,
    description        text
    );

-- Activity Table
create table if not exists activity
(
    id                bigserial primary key,
    created_by        varchar(255),
    created_at        timestamp,
    deleted           boolean,
    last_modified_by  varchar(255),
    updated_at        timestamp,
    version           integer,
    active            boolean,
    activity_name     varchar(255)                         not null,
    average_rating    numeric(2, 1),
    number_of_reviews bigint,
    short_location    varchar(255)                         not null,
    activity_type_id  bigint references activity_type (id) not null
    );

-- Activity Image Table
create table if not exists activity_images
(
    id               bigserial primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    caption          text,
    file_name        varchar(255)                    not null,
    image_url        text                            not null,
    is_display_image boolean default false,
    rejection_reason text,
    update_request   boolean,
    activity_id      bigint references activity (id) not null
    );


-- Tour Table
-- When we assign activities to added tour then the tour is created
create table if not exists tour
(
    id               bigserial primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    tour_name        varchar(255)                                 not null,
    description      text                                         not null,
    thumb_image_url  text,
    title            varchar(255)                                 not null,
    tour_location_id bigint references tour_location_mapping (id) not null
    );


-- Tour Itinerary Table
-- mapping between tour and activity
create table if not exists tour_itinerary
(
    id               bigserial primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    day_number       integer                         not null,
    end_time         time                            not null,
    start_time       time                            not null,
    activity_id      bigint references activity (id) not null,
    tour_id          bigint references tour (id)     not null
    );

-- Tour Speciality Table
-- Belongs to created Tour
create table if not exists tour_speciality
(
    id               bigserial primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    description      text                        not null,
    icon_url         text                        not null,
    title            varchar(255)                not null,
    update_request   boolean default false,
    tour_id          bigint references tour (id) not null
    );

-- Tour Package Table
-- It holds the tour package type
-- ex : Solo Package, Couple Package, Family Package, Group Package 1 - 12 Persons, Group Package 2 - 20 Persons
create table if not exists tour_package_type
(
    id                bigserial primary key,
    created_by        varchar(255),
    created_at        timestamp,
    deleted           boolean,
    last_modified_by  varchar(255),
    updated_at        timestamp,
    version           integer,
    active            boolean,
    package_type_name varchar(255) not null,
    description       text         not null,
    suitable_for      integer      not null
    );


-- Tour Package table
-- In this table we will store all the package belonging to a tour
-- This table is the point of truth for the rest of the components
create table tour_package
(
    id                   bigserial primary key,
    created_by           varchar(255),
    created_at           timestamp,
    deleted              boolean,
    last_modified_by     varchar(255),
    updated_at           timestamp,
    version              integer,
    active               boolean,
    tour_package_name    varchar(255)                             not null,
    tour_id              bigint references tour (id)              not null,
    tour_package_type_id bigint references tour_package_type (id) not null,
    description          text                                     not null
);

-- Food Item Table
-- This table stores all the food items.
create table if not exists food_item
(
    id               bigserial primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    food_item_name   varchar(255) not null
    );

-- Meal Type Table
-- Meal type means Breakfast or Lunch or Dinner
create table meal_type
(
    id               bigserial primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    meal_type_name   varchar(255) not null unique
);

-- Meal Package Table
-- Each meal package belongs to a tour package.
-- A tour package may have many meal packages
create table meal_package
(
    id                bigserial primary key,
    created_by        varchar(255),
    created_at        timestamp,
    deleted           boolean,
    last_modified_by  varchar(255),
    updated_at        timestamp,
    version           integer,
    active            boolean,
    meal_package_name varchar(255)                        not null,
    meal_type_id      bigint references meal_type (id)    not null,
    tour_package_id   bigint references tour_package (id) not null
    -- merchant_id as different merchant can provide different meal package for a specific tour package
    -- meal type combination
);

-- Meal Package Contents
-- This table maps between meal package and food items
create table meal_package_food_item_mapping
(
    meal_package_id bigint references meal_package (id),
    food_item_id    bigint references food_item (id),
    primary key (meal_package_id, food_item_id)
);