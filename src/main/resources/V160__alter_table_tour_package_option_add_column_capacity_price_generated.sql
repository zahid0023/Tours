ALTER TABLE IF EXISTS tour_package_option
    ADD COLUMN IF NOT EXISTS capacity_price_generated boolean;