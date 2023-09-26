package com.ghuddy.backendapp.tours.daoImpl;

import com.ghuddy.backendapp.tours.dao.TourDAO;
import com.ghuddy.backendapp.tours.dto.data.TourAddData;
import com.ghuddy.backendapp.tours.dto.response.tour.TourResponseList;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
@Slf4j
public class TourDAOImpl implements TourDAO {

    private final JdbcTemplate jdbcTemplate;

    public TourDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TourResponseList getAllTours() throws EmptyListException {
        List<TourAddData> tours = fetchToursFromDatabase(0, 0);
        return new TourResponseList(tours);
    }

    @Override
    public TourResponseList getAllToursPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException {
        List<TourAddData> tours = fetchToursFromDatabase(pageSize, pageNumber);
        return new TourResponseList(tours);
    }

    private List<TourAddData> fetchToursFromDatabase(Integer pageSize, Integer pageNumber) throws EmptyListException {
        // Calculate the offset based on pageSize and pageNumber
        int offset = (pageNumber - 1) * pageSize;

        StringBuilder sql = new StringBuilder("""
                SELECT tlm.id AS tour_id,
                       tlm.tour_name AS tour_name,
                       l.place_name AS destination_location,
                       tlm.number_of_days AS number_of_days,
                       tlm.number_of_nights AS number_of_nights,
                       tlm.short_address AS short_address,
                       tlm.tour_tag AS tour_tag
                FROM tour_location_mapping tlm
                INNER JOIN location l
                ON l.id = tlm.destination_location_id
                """);
        List<TourAddData> tours;
        // pagination
        if (pageSize != 0) {
            sql.append(" LIMIT ? OFFSET ?");
            log.info(sql.toString());

            tours = jdbcTemplate.query(
                    sql.toString(),
                    new Object[]{pageSize, offset},
                    new int[]{Types.INTEGER, Types.INTEGER},
                    BeanPropertyRowMapper.newInstance(TourAddData.class)
            );
        } else {
            tours = jdbcTemplate.query(
                    sql.toString(),
                    BeanPropertyRowMapper.newInstance(TourAddData.class)
            );
        }

        if (tours.isEmpty()) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
        return tours;
    }

}
