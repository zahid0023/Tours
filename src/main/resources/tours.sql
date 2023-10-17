create table if not exists users
(
    id        bigserial
        primary key,
    user_name varchar(255)
);

create table if not exists place_near_by
(
    id               bigserial
        primary key,
    place_name       varchar(255),
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer
);

create table if not exists added_tours
(
    id                      bigserial
        primary key,
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
    destination_location_id bigint not null
        references place_near_by
);

create table if not exists activity_type
(
    id                 bigserial
        primary key,
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

create table if not exists activity
(
    id                bigserial
        primary key,
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
    activity_type_id  bigint       not null
        references activity_type
);

create table if not exists tour
(
    id               bigserial
        primary key,
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
    added_tour_id    bigint       not null
        references added_tours
);

create table if not exists tour_itinerary
(
    id               bigserial
        primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    activity_id      bigint not null
        references activity,
    tour_id          bigint not null
        references tour
);

create table if not exists tour_speciality
(
    id               bigserial
        primary key,
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
    tour_id          bigint       not null
        references tour
);

create table if not exists subscribed_tours
(
    id                   bigserial
        primary key,
    created_by           varchar(255),
    created_at           timestamp,
    deleted              boolean,
    last_modified_by     varchar(255),
    updated_at           timestamp,
    version              integer,
    active               boolean,
    tour_id              bigint       not null
        references tour,
    merchant_id          bigint       not null
        references users,
    tour_reporting_time  time         not null,
    tour_reporting_place varchar(100) not null
);

create table if not exists subscribed_tour_itinerary
(
    id                  bigserial
        primary key,
    created_by          varchar(255),
    created_at          timestamp,
    deleted             boolean,
    last_modified_by    varchar(255),
    updated_at          timestamp,
    version             integer,
    active              boolean,
    subscribed_tour_id  bigint  not null
        references subscribed_tours,
    activity_id         bigint  not null
        references activity,
    activity_day_number integer not null,
    activity_start_time time    not null,
    activity_end_time   time    not null
);

create table if not exists tour_package_type
(
    id                bigserial
        primary key,
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

create table if not exists tour_package
(
    id                         bigserial
        primary key,
    created_by                 varchar(255),
    created_at                 timestamp,
    deleted                    boolean,
    last_modified_by           varchar(255),
    updated_at                 timestamp,
    version                    integer,
    active                     boolean,
    tour_package_name          varchar(255) not null,
    subscribed_tour_id         bigint       not null
        references subscribed_tours,
    tour_package_type_id       bigint       not null
        references tour_package_type,
    description                text         not null,
    is_food_included           boolean      not null,
    is_accommodation_included  boolean      not null,
    is_transportation_included boolean      not null,
    is_transfer_included       boolean      not null,
    package_price_per_person   numeric(10, 2),
    total_package_price        numeric
);

create table if not exists food_item
(
    id               bigserial
        primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    food_item_name   varchar(255) not null
);

create table if not exists meal_type
(
    id               bigserial
        primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    meal_type_name   varchar(255) not null
);

create table if not exists tour_accommodation_type
(
    id                      bigserial
        primary key,
    created_by              varchar(255),
    created_at              timestamp,
    deleted                 boolean,
    last_modified_by        varchar(255),
    updated_at              timestamp,
    version                 integer,
    active                  boolean,
    accommodation_type_name varchar(255) not null
);

create table if not exists tour_accommodation
(
    id                    bigserial
        primary key,
    created_by            varchar(255),
    created_at            timestamp,
    deleted               boolean,
    last_modified_by      varchar(255),
    updated_at            timestamp,
    version               integer,
    active                boolean,
    accommodation_name    varchar(255) not null,
    accommodation_type_id bigint       not null
        references tour_accommodation_type,
    accommodation_address varchar(255) not null,
    average_rating        numeric(3, 1),
    total_reviews         integer
);

create table if not exists tour_room_category
(
    id                 bigserial
        primary key,
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

create table if not exists tour_room_type
(
    id               bigserial
        primary key,
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

create table if not exists transportation_brand
(
    id               bigserial
        primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    brand_name       varchar(255) not null
);

create table if not exists transportation_mode
(
    id               bigserial
        primary key,
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

create table if not exists transportation_provider
(
    id                           bigserial
        primary key,
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

create table if not exists transportation_route
(
    id                      bigserial
        primary key,
    created_by              varchar(255),
    created_at              timestamp,
    deleted                 boolean,
    last_modified_by        varchar(255),
    updated_at              timestamp,
    version                 integer,
    active                  boolean,
    source_location_id      bigint not null
        references place_near_by,
    destination_location_id bigint not null
        references place_near_by
);

create table if not exists transportation_packages
(
    id                                      bigint default nextval('tour_package_transportation_package_id_seq'::regclass) not null
        constraint tour_package_transportation_package_pkey
            primary key,
    created_by                              varchar(255),
    created_at                              timestamp,
    deleted                                 boolean,
    last_modified_by                        varchar(255),
    updated_at                              timestamp,
    version                                 integer,
    active                                  boolean,
    tour_package_id                         bigint                                                                         not null
        constraint tour_package_transportation_package_tour_package_id_fkey
            references tour_package,
    transportation_route_id                 bigint                                                                         not null
        constraint tour_package_transportation_packag_transportation_route_id_fkey
            references transportation_route,
    transportation_mode_id                  bigint                                                                         not null
        constraint tour_package_transportation_package_transportation_mode_id_fkey
            references transportation_mode,
    transportation_brand_id                 bigint                                                                         not null
        constraint tour_package_transportation_packag_transportation_brand_id_fkey
            references transportation_brand,
    transportation_provider_id              bigint                                                                         not null
        constraint tour_package_transportation_pac_transportation_provider_id_fkey
            references transportation_provider,
    trip_type                               varchar(20)                                                                    not null,
    is_ac                                   boolean                                                                        not null,
    unit_price                              numeric(10, 2)                                                                 not null,
    quantity                                integer                                                                        not null,
    per_person_transportation_package_price numeric(10, 2)                                                                 not null,
    is_included                             boolean                                                                        not null
);

create table if not exists activity_images
(
    id               bigserial
        primary key,
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
    activity_id      bigint       not null
        references activity
);

create table if not exists tour_accommodation_option
(
    id               bigserial
        primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    tour_package_id  bigint
        references tour_package,
    is_default       boolean default false
);

create table if not exists accommodation_packages
(
    id                                     bigint default nextval('tour_package_accommodation_package_id_seq'::regclass) not null
        constraint tour_package_accommodation_package_pkey
            primary key,
    created_by                             varchar(255),
    created_at                             timestamp,
    deleted                                boolean,
    last_modified_by                       varchar(255),
    updated_at                             timestamp,
    version                                integer,
    active                                 boolean,
    room_category_id                       bigint                                                                        not null
        constraint tour_package_accommodation_package_room_category_id_fkey
            references tour_room_category,
    room_type_id                           bigint                                                                        not null
        constraint tour_package_accommodation_package_room_type_id_fkey
            references tour_room_type,
    accommodation_id                       bigint                                                                        not null
        constraint tour_package_accommodation_package_accommodation_id_fkey
            references tour_accommodation,
    is_shareable                           boolean,
    suitable_for_persons                   integer                                                                       not null,
    bed_count                              integer,
    bed_configuration                      varchar(100),
    per_night_room_price                   numeric(10, 2)                                                                not null,
    per_person_accommodation_package_price numeric(10, 2)                                                                not null,
    accommodation_option_id                bigint
        constraint acommodation_packages_accommodation_option_id_fkey
            references tour_accommodation_option,
    night_numbers                          integer[]
);

create table if not exists tour_food_option
(
    id               bigserial
        primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    tour_package_id  bigint
        references tour_package,
    is_default       boolean default false
);

create table if not exists meal_packages
(
    id                          bigint default nextval('tour_package_meal_package_id_seq'::regclass) not null
        constraint tour_package_meal_package_pkey
            primary key,
    created_by                  varchar(255),
    created_at                  timestamp,
    deleted                     boolean,
    last_modified_by            varchar(255),
    updated_at                  timestamp,
    version                     integer,
    active                      boolean,
    meal_package_name           varchar(255)                                                         not null,
    meal_type_id                bigint                                                               not null
        constraint tour_package_meal_package_meal_type_id_fkey
            references meal_type,
    per_meal_price              numeric(10, 2)                                                       not null,
    per_person_number_of_meals  integer                                                              not null,
    per_person_total_meal_price numeric(10, 2),
    food_option_id              bigint
        references tour_food_option
);

create table if not exists meal_package_food_item_mapping
(
    meal_package_id bigint not null
        references meal_packages,
    food_item_id    bigint not null
        references food_item,
    primary key (meal_package_id, food_item_id)
);

create table if not exists tour_transfer_option
(
    id               bigserial
        primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    is_default       boolean,
    tour_package_id  bigint
        references tour_package
);

create table if not exists transfer_packages
(
    id                                bigint default nextval('tour_package_transfer_package_id_seq'::regclass) not null
        constraint tour_package_transfer_package_pkey
            primary key,
    created_by                        varchar(255),
    created_at                        timestamp,
    deleted                           boolean,
    last_modified_by                  varchar(255),
    updated_at                        timestamp,
    version                           integer,
    active                            boolean,
    transportation_mode_id            bigint                                                                   not null
        constraint tour_package_transfer_package_transportation_mode_id_fkey
            references transportation_mode,
    transportation_provider_id        bigint                                                                   not null
        constraint tour_package_transfer_package_transportation_provider_id_fkey
            references transportation_provider,
    is_ac                             boolean                                                                  not null,
    maximum_number_of_travellers      integer                                                                  not null,
    per_day_price                     numeric(10, 2)                                                           not null,
    number_of_vehicles                integer                                                                  not null,
    per_person_transfer_package_price numeric(10, 2)                                                           not null,
    tour_package_id                   bigint
        references tour_package,
    day_numbers                       integer[],
    transfer_route                    text,
    tour_transfer_option_id           bigint
        references tour_transfer_option
);

