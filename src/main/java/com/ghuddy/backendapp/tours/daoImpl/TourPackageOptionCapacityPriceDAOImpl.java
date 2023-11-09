package com.ghuddy.backendapp.tours.daoImpl;

import com.ghuddy.backendapp.tours.dao.TourPackageOptionCapacityPriceDAO;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageOptionCapacityPriceDailyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class TourPackageOptionCapacityPriceDAOImpl implements TourPackageOptionCapacityPriceDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int updateTourPackageOptionCapacityPriceDaily(long packageOptionId) {
        String sql = """
                        with temp as (SELECT tpocp.id                  as price_id,
                                             tpocpf.id                 as filter_id,
                                             tpocp.tour_package_option_id,
                                             tour_date                 AS date,
                                             to_char(tour_date, 'DAY') as day,
                                             tpocpf.delta_total_discount_percent_point,
                                             tpocpf.delta_ghuddy_website_red_price,
                                             tpocpf.delta_total_seats,
                                             tpocpf.delta_bookable_seats,
                                             tpocpf.delta_rack_rate,
                                             tpocpf.policy_type,
                                             case
                                                 when policy_type = 'SPECIAL' then 1
                                                 when policy_type = 'WEEKEND' then 2
                                                 when policy_type = 'SEASONAL' then 3
                                                 when policy_type = 'BASE' then 4
                                                 when policy_type = 'ONE_TIME' then 5
                                                 else 6
                                                 end                   as priority
                        
                                      from tour_package_option_capacity_price_filter tpocpf
                                               left join tour_package_option_capacity_price tpocp on tpocpf.capacity_price_id = tpocp.id
                                               left join tour_package_option tpo on tpocp.tour_package_option_id = tpo.id
                                      where tpocpf.tour_date < now() + interval '180 days'
                                        and tour_package_option_id = ?
                                        and tpocpf.deleted = FALSE
                                        and tpo.deleted = FALSE),
                             ranked as (select price_id,
                                               filter_id,
                                               tour_package_option_id,
                                               date,
                                               day,
                                               delta_total_discount_percent_point,
                                               delta_ghuddy_website_red_price,
                                               policy_type,
                                               delta_total_seats,
                                               delta_bookable_seats,
                                               delta_rack_rate,
                                               priority,
                                               RANK() OVER (
                                                   PARTITION BY price_id,
                                                       tour_package_option_id,
                                                       date
                                                   ORDER BY priority asc , filter_id desc
                                                   ) priority_rank
                                        from temp),
                             delta as (select price_id,
                                              filter_id,
                                              tour_package_option_id,
                                              date,
                                              day,
                                              policy_type,
                                              delta_total_discount_percent_point,
                                              delta_ghuddy_website_red_price,
                                              delta_rack_rate,
                                              delta_total_seats,
                                              delta_bookable_seats
                                       from ranked
                                       where priority_rank = 1),
                        
                             base as (select generate_series(now(), now() + interval '180 days', interval '1 day')::date AS date,
                                             tour_package_option_id,
                                             id                                                                          as price_id,
                                             total_seats,
                                             bookable_seats,
                                             rack_rate,
                                             ghuddy_website_red_price,
                                             total_discount_percent
                                      FROM tour_package_option_capacity_price
                                      where tour_package_option_id = ?
                                        and deleted = false),
                             master as (SELECT bs.tour_package_option_id,
                                               bs.price_id,
                                               bs.date,
                                               case
                                                   when dl.date is null then 0
                                                   else bs.total_seats + coalesce(dl.delta_total_seats, 0) end              as total_seats,
                                               case
                                                   when dl.date is null then 0
                                                   else bs.bookable_seats + COALESCE(dl.delta_bookable_seats, 0) end        as bookable_seats,
                                               bs.rack_rate + COALESCE(dl.delta_rack_rate, 0)                               AS black_price,
                                               bs.ghuddy_website_red_price + COALESCE(dl.delta_ghuddy_website_red_price, 0) AS red_price,
                                               bs.total_discount_percent +
                                               COALESCE(dl.delta_total_discount_percent_point, 0)                           AS discount_percent,
                                               COALESCE(dl.policy_type, 'BASE')                                             AS policy_type
                                        FROM base bs
                                                 LEFT JOIN delta dl ON dl.price_id = bs.price_id AND dl.date = bs.date)
                        
                        INSERT
                        INTO tour_package_option_capacity_price_daily (package_option_id,
                                                                       price_id,
                                                                       date,
                                                                       total_seats,
                                                                       bookable_seats,
                                                                       black_price,
                                                                       red_price,
                                                                       discount_percent,
                                                                       policy_type)
                        select tour_package_option_id,
                               price_id,
                               date,
                               total_seats,
                               bookable_seats,
                               black_price,
                               red_price,
                               discount_percent,
                               policy_type
                        from master
                        where bookable_seats > 0
                        ON CONFLICT (price_id, date) DO UPDATE SET total_seats      = EXCLUDED.total_seats,
                                                                   bookable_seats   = EXCLUDED.bookable_seats,
                                                                   black_price      = EXCLUDED.black_price,
                                                                   red_price        = EXCLUDED.red_price,
                                                                   discount_percent = EXCLUDED.discount_percent,
                                                                   policy_type      = EXCLUDED.policy_type
                """;

      return  jdbcTemplate.update(sql, packageOptionId, packageOptionId);
    }

    @Override
    public List<TourPackageOptionCapacityPriceDailyData> getRoomCategoryAvailabilityPriceDailyList(
            long packageOptionId, LocalDate startDate, LocalDate endDate) {

        return jdbcTemplate.query(
                """
                        SELECT package_option_id,
                        price_id,
                        date,
                        total_seats,
                        bookable_seats,
                        black_price,
                        red_price,
                        discount_percent,
                        policy_type
                         FROM tour_package_option_capacity_price_daily WHERE package_option_id = ? and date BETWEEN ? AND ?""",
                new Object[]{packageOptionId, startDate, endDate},
                (rs, rowNum) -> {
                    TourPackageOptionCapacityPriceDailyData availability = new TourPackageOptionCapacityPriceDailyData();
                    availability.setPackageOptionId(rs.getLong("package_option_id"));
                    availability.setPriceId(rs.getLong("price_id"));
                    availability.setDate(rs.getDate("date").toLocalDate());
                    availability.setTotalSeats(rs.getLong("total_seats"));
                    availability.setBookableSeats(rs.getLong("bookable_seats"));
                    availability.setBlackPrice(rs.getDouble("black_price"));
                    availability.setRedPrice(rs.getDouble("red_price"));
                    availability.setDiscountPercent(rs.getDouble("discount_percent"));
                    availability.setPolicyType(rs.getString("policy_type"));
                    return availability;
                });
    }

}
