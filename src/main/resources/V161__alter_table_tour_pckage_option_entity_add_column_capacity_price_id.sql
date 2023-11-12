ALTER TABLE IF EXISTS tour_package_option
    ADD COLUMN IF NOT EXISTS capacity_price_id bigint references tour_package_option_capacity_price (id);