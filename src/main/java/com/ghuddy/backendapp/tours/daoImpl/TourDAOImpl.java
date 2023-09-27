package com.ghuddy.backendapp.tours.daoImpl;

import com.ghuddy.backendapp.tours.dao.TourDAO;
import com.ghuddy.backendapp.tours.dto.data.TourAddData;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class TourDAOImpl implements TourDAO {

    private final JdbcTemplate jdbcTemplate;

    public TourDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TourAddData> getAllTours(Integer pageSize, Integer pageNumber) throws EmptyListException {
        String query = """
                select tlm.id               AS tour_id,
                       tlm.tour_name        AS tour_name,
                       pnb.place_name       AS destination_location,
                       tlm.number_of_days   AS number_of_days,
                       tlm.number_of_nights AS number_of_nights,
                       tlm.short_address    AS short_address,
                       tlm.tour_tag         AS tour_tag
                from tour_location_mapping tlm
                         inner join place_near_by pnb on pnb.id = tlm.destination_location_id
                """;
        try {
            return EntityUtil.getAllEntitiesPaginated(query, pageSize, pageNumber, TourAddData.class, jdbcTemplate);
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }
}
