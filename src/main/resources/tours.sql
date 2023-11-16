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
    id                   bigserial
        primary key,
    created_by           varchar(255),
    created_at           timestamp,
    deleted              boolean,
    last_modified_by     varchar(255),
    updated_at           timestamp,
    version              integer,
    active               boolean,
    tour_package_name    varchar(255) not null,
    subscribed_tour_id   bigint       not null
        references public.subscribed_tours,
    tour_package_type_id bigint       not null
        references public.tour_package_type,
    description          text         not null,
    thumb_image_url      text
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
    is_active                               boolean,
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
    id                 bigserial
        primary key,
    created_by         varchar(255),
    created_at         timestamp,
    deleted            boolean,
    last_modified_by   varchar(255),
    updated_at         timestamp,
    version            integer,
    tour_package_id    bigint
        references public.tour_package,
    total_option_price numeric(10, 2),
    is_active          boolean
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
    day_number          integer,
    number_of_meals     integer,
    number_of_breakfast integer,
    number_of_lunch     integer,
    number_of_dinner    integer,
    total_option_price  numeric(10, 2),
    tour_package_id     bigint
        references public.tour_package,
    is_active           boolean
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
    total_option_price numeric(10, 2),
    tour_package_id    bigint
        references public.tour_package,
    is_active          boolean
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

create table if not exists public.spot_entry
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
    tour_package_id  bigint         not null
        constraint sport_entry_tour_package_id_fkey
            references public.tour_package,
    activity_id      bigint         not null
        constraint sport_entry_activity_id_fkey
            references public.activity,
    price_per_person numeric(10, 2) not null,
    remark           text
);

alter table public.spot_entry_packages
    owner to postgres;

create table if not exists public.tour_package_guide_options
(
    id                 bigserial
        primary key,
    created_by         varchar(255),
    created_at         timestamp,
    deleted            boolean,
    last_modified_by   varchar(255),
    updated_at         timestamp,
    version            integer,
    tour_package_id    bigint not null
        references public.tour_package,
    total_option_price numeric(10, 2)
);

alter table public.tour_package_guide_options
    owner to postgres;

create table if not exists public.guide_packages
(
    id                        bigserial
        primary key,
    created_by                varchar(255),
    created_at                timestamp,
    deleted                   boolean,
    last_modified_by          varchar(255),
    updated_at                timestamp,
    version                   integer,
    day_number                integer not null,
    number_of_guide_for_day   integer,
    total_guide_price_for_day numeric(10, 2),
    guide_option_id           bigint  not null
        references public.tour_package_guide_options
);

alter table public.guide_packages
    owner to postgres;

create table if not exists public.tour_package_availability
(
    id                              bigserial
        primary key,
    created_by                      varchar(255),
    created_at                      timestamp,
    deleted                         boolean,
    last_modified_by                varchar(255),
    updated_at                      timestamp,
    version                         integer,
    tour_package_id                 bigint
        references public.tour_package,
    default_accommodation_option_id bigint
        references public.tour_accommodation_option,
    default_food_option_id          bigint
        references public.tour_food_option,
    default_transfer_option_id      bigint
        references public.tour_transfer_option,
    default_guide_option_id         bigint
        references public.tour_package_guide_options,
    default_spot_entry_id           bigint
        references public.spot_entry_packages,
    tour_start_date                 date    not null,
    total_seats                     integer not null,
    bookable_seats                  integer not null
);

alter table public.tour_package_availability
    owner to postgres;

create table if not exists public.availability_generated_transportation_packages
(
    id                                      bigserial
        primary key,
    created_by                              varchar(255),
    created_at                              timestamp,
    deleted                                 boolean,
    last_modified_by                        varchar(255),
    updated_at                              timestamp,
    version                                 integer,
    transportation_package_id               bigint         not null
        constraint availability_generated_transport_transportation_package_id_fkey
            references public.meal_packages,
    transportation_package_price_per_person numeric(10, 2) not null,
    tour_package_availability_id            bigint         not null
        constraint availability_generated_transp_tour_package_availability_id_fkey
            references public.tour_package_availability,
    is_active                               boolean
);

alter table public.availability_generated_transportation_packages
    owner to postgres;

create table if not exists public.availability_generated_accommodation_options
(
    id                            bigserial
        primary key,
    created_by                    varchar(255),
    created_at                    timestamp,
    deleted                       boolean,
    last_modified_by              varchar(255),
    updated_at                    timestamp,
    version                       integer,
    tour_package_availability_id  bigint         not null
        constraint availability_generated_accom_tour_package_availability_id_fkey1
            references public.tour_package_availability,
    total_option_price_per_person numeric(10, 2) not null,
    is_active                     boolean
);

alter table public.availability_generated_accommodation_options
    owner to postgres;

create table if not exists public.availability_generated_accommodation_packages
(
    id                                             bigserial
        primary key,
    created_by                                     varchar(255),
    created_at                                     timestamp,
    deleted                                        boolean,
    last_modified_by                               varchar(255),
    updated_at                                     timestamp,
    version                                        integer,
    accommodation_package_id                       bigint         not null
        constraint availability_generated_accommodat_accommodation_package_id_fkey
            references public.accommodation_packages,
    per_night_room_price                           numeric(10, 2) not null,
    availability_generated_accommodation_option_id bigint         not null
        constraint availability_generated_accomm_availability_generated_accom_fkey
            references public.availability_generated_accommodation_options
);

alter table public.availability_generated_accommodation_packages
    owner to postgres;

create table if not exists public.availability_generated_food_options
(
    id                            bigserial
        primary key,
    created_by                    varchar(255),
    created_at                    timestamp,
    deleted                       boolean,
    last_modified_by              varchar(255),
    updated_at                    timestamp,
    version                       integer,
    tour_package_availability_id  bigint         not null
        constraint availability_generated_food_o_tour_package_availability_id_fkey
            references public.tour_package_availability,
    total_option_price_per_person numeric(10, 2) not null,
    is_active                     boolean
);

alter table public.availability_generated_food_options
    owner to postgres;

create table if not exists public.availability_generated_meal_packages
(
    id                                    bigserial
        primary key,
    created_by                            varchar(255),
    created_at                            timestamp,
    deleted                               boolean,
    last_modified_by                      varchar(255),
    updated_at                            timestamp,
    version                               integer,
    meal_package_id                       bigint         not null
        references public.meal_packages,
    meal_package_price                    numeric(10, 2) not null,
    availability_generated_food_option_id bigint         not null
        constraint availability_generated_meal_p_availability_generated_food__fkey
            references public.availability_generated_food_options
);

alter table public.availability_generated_meal_packages
    owner to postgres;

create table if not exists public.availability_generated_transfer_options
(
    id                            bigserial
        primary key,
    created_by                    varchar(255),
    created_at                    timestamp,
    deleted                       boolean,
    last_modified_by              varchar(255),
    updated_at                    timestamp,
    version                       integer,
    tour_package_availability_id  bigint         not null
        constraint availability_generated_trans_tour_package_availability_id_fkey1
            references public.tour_package_availability,
    total_option_price_per_person numeric(10, 2) not null,
    is_active                     boolean
);

alter table public.availability_generated_transfer_options
    owner to postgres;

create table if not exists public.availability_generated_transfer_packages
(
    id                                        bigserial
        primary key,
    created_by                                varchar(255),
    created_at                                timestamp,
    deleted                                   boolean,
    last_modified_by                          varchar(255),
    updated_at                                timestamp,
    version                                   integer,
    transfer_package_id                       bigint         not null
        constraint availability_generated_transfer_packag_transfer_package_id_fkey
            references public.transfer_packages,
    per_vehicle_per_trip_price                numeric(10, 2) not null,
    availability_generated_transfer_option_id bigint         not null
        constraint availability_generated_transf_availability_generated_trans_fkey
            references public.availability_generated_transfer_options
);

alter table public.availability_generated_transfer_packages
    owner to postgres;

create table if not exists public.availability_generated_guide_options
(
    id                            bigserial
        primary key,
    created_by                    varchar(255),
    created_at                    timestamp,
    deleted                       boolean,
    last_modified_by              varchar(255),
    updated_at                    timestamp,
    version                       integer,
    tour_package_availability_id  bigint         not null
        constraint availability_generated_guide_tour_package_availability_id_fkey1
            references public.tour_package_availability,
    total_option_price_per_person numeric(10, 2) not null,
    is_active                     boolean
);

alter table public.availability_generated_guide_options
    owner to postgres;

create table if not exists public.availability_generated_guide_packages
(
    id                                     bigserial
        primary key,
    created_by                             varchar(255),
    created_at                             timestamp,
    deleted                                boolean,
    last_modified_by                       varchar(255),
    updated_at                             timestamp,
    version                                integer,
    guide_package_id                       bigint         not null
        references public.guide_packages,
    day_number                             integer        not null,
    number_of_guides                       integer        not null,
    total_guide_package_price              numeric(10, 2) not null,
    availability_generated_guide_option_id bigint         not null
        constraint availability_generated_guide__availability_generated_guide_fkey
            references public.availability_generated_guide_options
);

alter table public.availability_generated_guide_packages
    owner to postgres;

create table if not exists public.availability_generated_spot_entries
(
    id                           bigserial
        primary key,
    created_by                   varchar(255),
    created_at                   timestamp,
    deleted                      boolean,
    last_modified_by             varchar(255),
    updated_at                   timestamp,
    version                      integer,
    spot_entry_id                bigint         not null
        references public.spot_entry_packages,
    tour_package_availability_id bigint         not null
        constraint availability_generated_spot_e_tour_package_availability_id_fkey
            references public.tour_package_availability,
    spot_entry_price_per_person  numeric(10, 2) not null,
    is_active                    boolean
);

alter table public.availability_generated_spot_entries
    owner to postgres;

create table if not exists public.availability_generated_tour_package_all_options
(
    id                                               bigserial
        primary key,
    created_by                                       varchar(255),
    created_at                                       timestamp,
    deleted                                          boolean,
    last_modified_by                                 varchar(255),
    updated_at                                       timestamp,
    version                                          integer,
    availability_generated_tour_package_id           bigint
        references public.availability_generated_tour_packages,
    availability_generated_accommodation_option_id   bigint
        references public.availability_generated_accommodation_options,
    availability_generated_food_option_id            bigint
        references public.availability_generated_food_options,
    availability_generated_transfer_option_id        bigint
        references public.availability_generated_transfer_options,
    availability_generated_transportation_package_id bigint
        references public.availability_generated_transportation_packages,
    availability_generated_guide_option_id           bigint
        references public.availability_generated_guide_options,
    availability_generated_spot_entry_id             bigint
        references public.availability_generated_spot_entries,
    ghuddy_platform_calculated_option_price        numeric(10, 2) not null default 0,
    merchant_subsidy_amount                        numeric(10, 2) not null default 0,
    net_option_price_after_merchant_subsidy        numeric(10, 2) not null default 0,
    ghuddy_platform_commission_amount              numeric(10, 2) not null default 0,
    net_option_price_after_ghuddy_commission       numeric(10, 2) not null default 0,
    ghuddy_website_black_price                     numeric(10, 2) not null default 0,
    ghuddy_subsidy_amount                          numeric(10, 2) not null default 0,
    net_option_price_after_ghuddy_subsidy          numeric(10, 2) not null default 0,
    ghuddy_website_red_price                       numeric(10, 2) not null default 0,
    payment_gateway_amount                         numeric(10, 2) not null default 0
);



create table if not exists public.availability_generated_tour_package_inclusive_options
(
    id                                             bigserial
        primary key,
    created_by                                     varchar(255),
    created_at                                     timestamp,
    deleted                                        boolean,
    last_modified_by                               varchar(255),
    updated_at                                     timestamp,
    version                                        integer,
    availability_generated_tour_package_id         bigint
        references public.availability_generated_tour_packages not null ,
    availability_generated_accommodation_option_id bigint
        references public.availability_generated_accommodation_options,
    availability_generated_food_option_id          bigint
        references public.availability_generated_food_options,
    availability_generated_transfer_option_id      bigint
        references public.availability_generated_transfer_options,
    availability_generated_guide_option_id         bigint
        references public.availability_generated_guide_options,
    availability_generated_spot_entry_id           bigint
        references public.availability_generated_spot_entries,
    ghuddy_platform_calculated_option_price        numeric(10, 2) not null default 0,
    merchant_subsidy_amount                        numeric(10, 2) not null default 0,
    net_option_price_after_merchant_subsidy        numeric(10, 2) not null default 0,
    ghuddy_platform_commission_amount              numeric(10, 2) not null default 0,
    net_option_price_after_ghuddy_commission       numeric(10, 2) not null default 0,
    ghuddy_website_black_price                     numeric(10, 2) not null default 0,
    ghuddy_subsidy_amount                          numeric(10, 2) not null default 0,
    net_option_price_after_ghuddy_subsidy          numeric(10, 2) not null default 0,
    ghuddy_website_red_price                       numeric(10, 2) not null default 0,
    payment_gateway_amount                         numeric(10, 2) not null default 0
);

alter table public.availability_generated_tour_package_options_wo_transportation
    owner to postgres;

