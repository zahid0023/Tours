create table if not exists public.users
(
    id        bigserial
        primary key,
    user_name varchar(255)
);

alter table public.users
    owner to postgres;

create table if not exists public.place_near_by
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

alter table public.place_near_by
    owner to postgres;

create table if not exists public.added_tours
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
        references public.place_near_by
);

alter table public.added_tours
    owner to postgres;

create table if not exists public.activity_type
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

alter table public.activity_type
    owner to postgres;

create table if not exists public.activity
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
        references public.activity_type
);

alter table public.activity
    owner to postgres;

create table if not exists public.tour
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
        references public.added_tours
);

alter table public.tour
    owner to postgres;

create table if not exists public.tour_itinerary
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
        references public.activity,
    tour_id          bigint not null
        references public.tour
);

alter table public.tour_itinerary
    owner to postgres;

create table if not exists public.tour_speciality
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
        references public.tour
);

alter table public.tour_speciality
    owner to postgres;

create table if not exists public.subscribed_tours
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
    tour_id              bigint                       not null
        references public.tour,
    merchant_id          bigint                       not null
        references public.users,
    tour_reporting_time  time                         not null,
    tour_reporting_place varchar(100)                 not null,
    number_of_reviews    integer          default 0   not null,
    rating_in_stars      double precision default 0.0 not null
);

alter table public.subscribed_tours
    owner to postgres;

create table if not exists public.subscribed_tour_itinerary
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
        references public.subscribed_tours,
    activity_id         bigint  not null
        references public.activity,
    activity_day_number integer not null,
    activity_start_time time    not null,
    activity_end_time   time    not null
);

alter table public.subscribed_tour_itinerary
    owner to postgres;

create table if not exists public.tour_package_type
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

alter table public.tour_package_type
    owner to postgres;

create table if not exists public.tour_package
(
    id                                 bigserial
        primary key,
    created_by                         varchar(255),
    created_at                         timestamp,
    deleted                            boolean,
    last_modified_by                   varchar(255),
    updated_at                         timestamp,
    version                            integer,
    active                             boolean,
    tour_package_name                  varchar(255) not null,
    subscribed_tour_id                 bigint       not null
        references public.subscribed_tours,
    tour_package_type_id               bigint       not null
        references public.tour_package_type,
    description                        text         not null,
    package_price_per_person           numeric(10, 2),
    total_package_price                numeric,
    default_food_option_price          numeric(10, 2),
    default_accommodation_option_price numeric(10, 2),
    default_transfer_option_price      numeric(10, 2),
    guide_price                        numeric(10, 2),
    thumb_image_url                    text
);

alter table public.tour_package
    owner to postgres;

create table if not exists public.food_item
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

alter table public.food_item
    owner to postgres;

create table if not exists public.meal_type
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

alter table public.meal_type
    owner to postgres;

create table if not exists public.tour_accommodation_type
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

alter table public.tour_accommodation_type
    owner to postgres;

create table if not exists public.tour_accommodation
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
        references public.tour_accommodation_type,
    accommodation_address varchar(255) not null,
    average_rating        numeric(3, 1),
    total_reviews         integer
);

alter table public.tour_accommodation
    owner to postgres;

create table if not exists public.tour_room_category
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

alter table public.tour_room_category
    owner to postgres;

create table if not exists public.tour_room_type
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

alter table public.tour_room_type
    owner to postgres;

create table if not exists public.transportation_brand
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

alter table public.transportation_brand
    owner to postgres;

create table if not exists public.transportation_mode
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

alter table public.transportation_mode
    owner to postgres;

create table if not exists public.transportation_provider
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

alter table public.transportation_provider
    owner to postgres;

create table if not exists public.transportation_route
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
        references public.place_near_by,
    destination_location_id bigint not null
        references public.place_near_by
);

alter table public.transportation_route
    owner to postgres;

create table if not exists public.transportation_packages
(
    id                                      bigserial
        primary key,
    created_by                              varchar(255),
    created_at                              timestamp,
    deleted                                 boolean,
    last_modified_by                        varchar(255),
    updated_at                              timestamp,
    version                                 integer,
    active                                  boolean,
    tour_package_id                         bigint         not null
        constraint tour_package_transportation_package_tour_package_id_fkey
            references public.tour_package,
    transportation_route_id                 bigint         not null
        constraint tour_package_transportation_packag_transportation_route_id_fkey
            references public.transportation_route,
    transportation_mode_id                  bigint         not null
        constraint tour_package_transportation_package_transportation_mode_id_fkey
            references public.transportation_mode,
    transportation_brand_id                 bigint         not null
        constraint tour_package_transportation_packag_transportation_brand_id_fkey
            references public.transportation_brand,
    transportation_provider_id              bigint         not null
        constraint tour_package_transportation_pac_transportation_provider_id_fkey
            references public.transportation_provider,
    trip_type                               varchar(20)    not null,
    is_ac                                   boolean        not null,
    per_person_transportation_package_price numeric(10, 2) not null
);

alter table public.transportation_packages
    owner to postgres;

create table if not exists public.activity_images
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
        references public.activity
);

alter table public.activity_images
    owner to postgres;

create table if not exists public.tour_accommodation_option
(
    id                            bigserial
        primary key,
    created_by                    varchar(255),
    created_at                    timestamp,
    deleted                       boolean,
    last_modified_by              varchar(255),
    updated_at                    timestamp,
    version                       integer,
    active                        boolean,
    is_default                    boolean default false,
    total_option_price_per_person numeric(10, 2)
);

alter table public.tour_accommodation_option
    owner to postgres;

create table if not exists public.accommodation_packages
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
    room_category_id        bigint         not null
        constraint tour_package_accommodation_package_room_category_id_fkey
            references public.tour_room_category,
    room_type_id            bigint         not null
        constraint tour_package_accommodation_package_room_type_id_fkey
            references public.tour_room_type,
    accommodation_id        bigint         not null
        constraint tour_package_accommodation_package_accommodation_id_fkey
            references public.tour_accommodation,
    is_shareable            boolean,
    suitable_for_persons    integer        not null,
    bed_count               integer,
    bed_configuration       varchar(100),
    per_night_room_price    numeric(10, 2) not null,
    accommodation_option_id bigint
        constraint acommodation_packages_accommodation_option_id_fkey
            references public.tour_accommodation_option,
    night_number            integer
);

alter table public.accommodation_packages
    owner to postgres;

create table if not exists public.tour_food_option
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
    is_default          boolean default false,
    day_number          integer,
    number_of_meals     integer,
    number_of_breakfast integer,
    number_of_lunch     integer,
    number_of_dinner    integer,
    total_option_price  numeric(10, 2)
);

alter table public.tour_food_option
    owner to postgres;

create table if not exists public.tour_transfer_option
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
    is_default         boolean,
    total_option_price numeric(10, 2)
);

alter table public.tour_transfer_option
    owner to postgres;

create table if not exists public.meal_packages
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
    meal_type_id     bigint         not null
        constraint tour_package_meal_package_meal_type_id_fkey
            references public.meal_type,
    per_meal_price   numeric(10, 2) not null,
    food_option_id   bigint
        references public.tour_food_option
);

alter table public.meal_packages
    owner to postgres;

create table if not exists public.meal_package_food_item_mapping
(
    meal_package_id bigint not null
        references public.meal_packages,
    food_item_id    bigint not null
        references public.food_item,
    primary key (meal_package_id, food_item_id)
);

alter table public.meal_package_food_item_mapping
    owner to postgres;

create table if not exists public.transfer_packages
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
    transportation_mode_id     bigint         not null
        constraint tour_package_transfer_package_transportation_mode_id_fkey
            references public.transportation_mode,
    transportation_provider_id bigint         not null
        constraint tour_package_transfer_package_transportation_provider_id_fkey
            references public.transportation_provider,
    is_ac                      boolean        not null,
    suitable_for_persons       integer        not null,
    per_vehicle_per_trip_price numeric(10, 2) not null,
    transfer_route             text,
    tour_transfer_option_id    bigint
        references public.tour_transfer_option,
    trip_type                  varchar(20)
);

alter table public.transfer_packages
    owner to postgres;

create table if not exists public.tour_package_option
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
    tour_package_id         bigint
        references public.tour_package,
    food_option_id          bigint
        references public.tour_food_option,
    accommodation_option_id bigint
        references public.tour_accommodation_option,
    transfer_option_id      bigint
        references public.tour_transfer_option
);

alter table public.tour_package_option
    owner to postgres;

