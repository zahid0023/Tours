package com.example.ghuddytour2.tours.daoImpl;

import com.example.ghuddytour2.enums.ErrorCode;
import com.example.ghuddytour2.tours.dao.TourDAO;
import com.example.ghuddytour2.tours.dto.data.TourData;
import com.example.ghuddytour2.tours.dto.response.TourResponseList;
import com.example.ghuddytour2.tours.exception.EmptyListException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TourDAOImpl implements TourDAO {

    private final JdbcTemplate jdbcTemplate;

    public TourDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TourResponseList getAllTours() throws EmptyListException {
        String sql = """
                select t.id                              as tourID,
                       t.tour_name                       as tourName,
                       l.name                            as destinationLocation,
                       t.number_of_days                  as numberOfDays,
                       t.number_of_nights                as numberOfNights,
                       t.short_address                   as shortAddress,
                       t.thumb_image_url                 as thumbImageURL,
                       (t.description ->> 'title')       as title,
                       (t.description ->> 'description') as description
                from tour t
                         inner join
                     location l
                     on
                         l.id = t.destination_location_id;
                """;
        List<TourData> tours = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(TourData.class));
        if (tours.isEmpty()) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
        return new TourResponseList(tours);
    }
}
