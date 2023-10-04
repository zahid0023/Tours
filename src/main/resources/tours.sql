create table place_near_by
(
    id               bigserial primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    place_name       varchar(255)
);

create table users
(
    id        bigserial primary key,
    user_name varchar(255)
);

-- Tour Add Table.
-- Here we map tour with a destination location and
-- number of days and number of nights
create table added_tours
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
    destination_location_id bigint not null references place_near_by (id)
);

-- Activity Type Table
-- auto-generated definition
create table activity_type
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
create table activity
(
    id                bigserial primary key,
    created_by        varchar(255),
    created_at        timestamp,
    deleted           boolean,
    last_modified_by  varchar(255),
    updated_at        timestamp,
    version           integer,
    active            boolean,
    activity_name     varchar(255) not null,
    average_rating    numeric(2, 1),
    number_of_reviews bigint,
    short_location    varchar(255) not null,
    activity_type_id  bigint       not null references activity_type (id)
);

-- Activity Image Table
create table activity_images
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
    file_name        varchar(255) not null,
    image_url        text         not null,
    is_display_image boolean default false,
    rejection_reason text,
    update_request   boolean,
    activity_id      bigint       not null references activity (id)
);


-- Tour Table
-- When we assign activities to added tour then the tour is created
-- It will be created by admin by mapping added tours with activities
create table tour
(
    id               bigserial primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    description      text         not null,
    thumb_image_url  text,
    title            varchar(255) not null,
    added_tour_id    bigint       not null references added_tours (id)
);


-- Tour Itinerary Table
-- mapping between tour and activity
create table tour_itinerary
(
    id               bigserial primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    activity_id      bigint not null references activity (id),
    tour_id          bigint not null references tour (id)
);

-- Tour Speciality Table
-- Belongs to created Tour
create table tour_speciality
(
    id               bigserial primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    description      text         not null,
    icon_url         text         not null,
    title            varchar(255) not null,
    update_request   boolean default false,
    tour_id          bigint       not null references tour (id)
);

-- Subscribed Tour
-- Will be populated by merchants
-- This table is point of truth for all the things merchant does in regard to a tour
create table subscribed_tours
(
    id                   bigserial primary key,
    created_by           varchar(255),
    created_at           timestamp,
    deleted              boolean,
    last_modified_by     varchar(255),
    updated_at           timestamp,
    version              integer,
    active               boolean,
    tour_id              bigint       not null references tour (id),
    merchant_id          bigint       not null references users (id),
    tour_start_date      date         not null,
    tour_end_date        date         not null,
    tour_reporting_time  time         not null,
    tour_reporting_place varchar(100) not null
);

-- Subscribed Tour Itinerary
-- Merchant will set day number, start time and end time for all the activities that must be provided with this tour
create table subscribed_tour_itinerary
(
    id                  bigserial primary key,
    created_by          varchar(255),
    created_at          timestamp,
    deleted             boolean,
    last_modified_by    varchar(255),
    updated_at          timestamp,
    version             integer,
    active              boolean,
    subscribed_tour_id  bigint  not null references subscribed_tours (id),
    activity_id         bigint  not null references activity (id),
    activity_day_number integer not null,
    activity_start_time time    not null,
    activity_end_time   time    not null
);

-- Tour Package Table
-- It holds the tour package type
-- ex : Solo Package, Couple Package, Family Package, Group Package 1 - 12 Persons, Group Package 2 - 20 Persons
create table tour_package_type
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
    id                         bigserial primary key,
    created_by                 varchar(255),
    created_at                 timestamp,
    deleted                    boolean,
    last_modified_by           varchar(255),
    updated_at                 timestamp,
    version                    integer,
    active                     boolean,
    tour_package_name          varchar(255) not null,
    subscribed_tour_id         bigint       not null references subscribed_tours (id),
    tour_package_type_id       bigint       not null references tour_package_type (id),
    description                text         not null,
    is_food_included           boolean      not null,
    is_accommodation_included  boolean      not null,
    is_transportation_included boolean      not null,
    is_transfer_included       boolean      not null,
    net_price                  numeric(10, 2),
    added_price                numeric(10, 2), -- merchant may want to add/subtract some price on top of the calculated price
    total_package_price        numeric(10, 2)
);

-- Food Item Table
-- This table stores all the food items.
create table food_item
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
    meal_type_name   varchar(255) not null
);

-- Meal Package Table
-- Each meal package belongs to a tour package.
-- A tour package may have many meal packages
create table tour_package_meal_package
(
    id                       bigserial primary key,
    created_by               varchar(255),
    created_at               timestamp,
    deleted                  boolean,
    last_modified_by         varchar(255),
    updated_at               timestamp,
    version                  integer,
    active                   boolean,
    meal_package_name        varchar(255)   not null,
    meal_type_id             bigint         not null references meal_type (id),
    tour_package_id          bigint         not null references tour_package (id),
    unit_price               numeric(10, 2) not null,
    quantity                 integer        not null,
    net_price                numeric(10, 2),
    added_price              numeric(10, 2), -- merchant may want to add/subtract some price on top of the calculated price
    total_meal_package_price numeric(10, 2),
    is_included              boolean        not null
);

-- Meal Package Contents
-- This table maps between meal package and food items
create table meal_package_food_item_mapping
(
    meal_package_id bigint not null references tour_package_meal_package (id),
    food_item_id    bigint not null references food_item (id),
    primary key (meal_package_id, food_item_id)
);


-- Accommodation Type Table
create table tour_accommodation_type
(
    id                      bigserial primary key,
    created_by              varchar(255),
    created_at              timestamp,
    deleted                 boolean,
    last_modified_by        varchar(255),
    updated_at              timestamp,
    version                 integer,
    active                  boolean,
    accommodation_type_name varchar(255) not null
);

-- Accommodation Table
create table tour_accommodation
(
    id                    bigserial primary key,
    created_by            varchar(255),
    created_at            timestamp,
    deleted               boolean,
    last_modified_by      varchar(255),
    updated_at            timestamp,
    version               integer,
    active                boolean,
    accommodation_name    varchar(255) not null,
    accommodation_type_id bigint       not null references tour_accommodation_type (id),
    accommodation_address varchar(255) not null,
    average_rating        numeric(3, 1),
    total_reviews         integer
);

-- Room Category Table
create table tour_room_category
(
    id                 bigserial primary key,
    created_by         varchar(255),
    created_at         timestamp,
    deleted            boolean,
    last_modified_by   varchar(255),
    updated_at         timestamp,
    version            integer,
    active             boolean,
    room_category_name varchar(255) not null,
    description        text
);

-- Room Type Table
-- Example: Single, Couple
create table tour_room_type
(
    id               bigserial primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    room_type_name   varchar(255) not null,
    description      text
);

-- Tour Package Accommodation Table
-- This table stores the room types of a specific tour package. Each package may have zero or more room types associated with it.
-- We call this table tour package accommodation because this table stores the data about accommodation of a tour package.
create table tour_package_accommodation_package
(
    id                                bigserial primary key,
    created_by                        varchar(255),
    created_at                        timestamp,
    deleted                           boolean,
    last_modified_by                  varchar(255),
    updated_at                        timestamp,
    version                           integer,
    active                            boolean,
    room_category_id                  bigint         not null references tour_room_category (id),
    room_type_id                      bigint         not null references tour_room_type (id),
    accommodation_id                  bigint         not null references tour_accommodation (id),
    tour_package_id                   bigint         not null references tour_package (id),
    is_shareable                      boolean,
    suitable_for_persons              integer        not null,
    bed_count                         integer,
    bed_configuration                 varchar(100),
    unit_price                        numeric(10, 2) not null,
    quantity                          integer        not null,
    net_price                         numeric(10, 2) not null,
    added_price                       numeric(10, 2) not null, -- merchant may want to add/subtract some price on top of the calculated price
    total_accommodation_package_price numeric(10, 2) not null,
    is_included                       boolean        not null
);

-- Transportation Route Table
create table if not exists transportation_route
(
    id                      bigserial primary key,
    created_by              varchar(255),
    created_at              timestamp,
    deleted                 boolean,
    last_modified_by        varchar(255),
    updated_at              timestamp,
    version                 integer,
    active                  boolean,
    source_location_id      bigint references place_near_by (id) not null,
    destination_location_id bigint references place_near_by (id) not null
);

-- Transportation Mode Table
create table if not exists transportation_mode
(
    id               bigserial primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    mode_name        varchar(255) not null,
    description      text,
    icon_url         text         not null
);

-- Transportation Brand Table
create table if not exists transportation_brand
(
    id               bigserial primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    brand_name       varchar(255) not null
);

-- Transportation Provider Table
create table if not exists transportation_provider
(
    id                           bigserial primary key,
    created_by                   varchar(255),
    created_at                   timestamp,
    deleted                      boolean,
    last_modified_by             varchar(255),
    updated_at                   timestamp,
    version                      integer,
    active                       boolean,
    transportation_provider_name varchar(255),
    hotline_number               varchar(20),
    rating                       numeric(2, 1),
    total_reviews                integer
);

-- Create Transportation Table
create table tour_package_transportation_package
(
    id                         bigserial primary key,
    created_by                 varchar(255),
    created_at                 timestamp,
    deleted                    boolean,
    last_modified_by           varchar(255),
    updated_at                 timestamp,
    version                    integer,
    active                     boolean,
    tour_package_id            bigint         not null references tour_package (id),
    transportation_route_id    bigint         not null references transportation_route (id),
    transportation_mode_id     bigint         not null references transportation_mode (id),
    transportation_brand_id    bigint         not null references transportation_brand (id),
    transportation_provider_id bigint         not null references transportation_provider (id),
    trip_type                  varchar(20)    not null,
    is_ac                      boolean        not null,
    unit_price                 numeric(10, 2) not null,
    quantity                   integer        not null,
    net_price                  numeric(10, 2) not null,
    added_price                numeric(10, 2) not null, -- merchant may want to add/subtract some price on top of the calculated price
    total_package_price        numeric(10, 2) not null,
    is_included                boolean        not null
);



