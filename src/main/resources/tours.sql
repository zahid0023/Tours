-- Tour Table
create table if not exists tour
(
    id                      bigserial primary key,
    created_by              varchar(255),
    created_at              timestamp,
    deleted                 boolean,
    last_modified_by        varchar(255),
    updated_at              timestamp,
    version                 integer,
    active                  boolean,
    tour_name               varchar(255),
    destination_location_id bigint references location (id),
    number_of_days          integer,
    number_of_nights        integer,
    short_address           varchar(255),
    thumb_image_url         text,
    average_rating          numeric(2, 1),
    total_reviews           integer,
    tour_tag                varchar(255),
    title                   varchar(255),
    description             jsonb,
    UNIQUE (
               destination_location_id,
               number_of_days,
               number_of_nights
           )
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
    activity_type_name varchar(255) not null ,
    description        text,
    UNIQUE (
    activity_type_name
           )
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
    activity_type_id  bigint references activity_type (id) not null,
    short_location    varchar(255)                         not null,
    average_rating    numeric(2, 1),
    number_of_reviews int,
    UNIQUE (activity_name, short_location)
    );

-- This is the table where all the available tours will be stored.
-- A tour can not happen without any activities assigned to it so this table is the point of truth.
create table if not exists tour_availability
(
    id               bigserial primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    tour_id          bigint references tour (id),
    activity_id      bigint references activity (id),
    day_number       integer,
    start_time       time,
    end_time         time,
    itinerary_number integer,
    UNIQUE (tour_id, activity_id, day_number)
    );



-- Tour Speciality Table
-- Many Tour Specialities can belong to 1 Tour
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
    title            varchar(255),
    description      text,
    icon_url         text,
    update_request   boolean,
    rejection_reason text,
    tour_id          bigint references tour (id)
    );

-- Tour Images Table
create table tour_images
(
    id               bigserial primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    file_name        varchar(255),
    image_url        text,
    update_request   boolean,
    rejection_reason text,
    tour_id          bigint references tour (id)
);

