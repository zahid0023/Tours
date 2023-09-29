package com.ghuddy.backendapp.tours.daoImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghuddy.backendapp.tours.dao.TourDAO;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.tour.AddedTourData;
import com.ghuddy.backendapp.tours.model.data.tour.CreatedTourData;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class TourDAOImpl implements TourDAO {

    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    public TourDAOImpl(JdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<AddedTourData> getAllAddedTours(Integer pageSize, Integer pageNumber) throws EmptyListException {
        String query = """
                select at.id               AS added_tour_id,
                       at.tour_name        AS added_tour_name,
                       pnb.place_name      AS destination_location,
                       at.number_of_days   AS number_of_days,
                       at.number_of_nights AS number_of_nights,
                       at.short_address    AS short_address,
                       at.tour_tag         AS added_tour_tag
                from added_tours at
                         inner join place_near_by pnb on pnb.id = at.destination_location_id;
                                """;
        try {
            return EntityUtil.getAllEntitiesPaginated(query, pageSize, pageNumber, AddedTourData.class, jdbcTemplate);
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }

    /**
     * @param pageSize
     * @param pageNumber
     * @return
     * @throws EmptyListException
     */
    public List<CreatedTourData> getAllCreatedTours(Integer pageSize, Integer pageNumber) {
        String sql = """
                -- Your SQL Query Here
                """;

        if (pageNumber == 0 && pageSize == 0) {
            // No pagination required
            return jdbcTemplate.query(sql, createdTourDataRowMapper());
        } else {
            // Paginated query
            int offset = (pageNumber - 1) * pageSize;
            return jdbcTemplate.query(sql + " LIMIT ? OFFSET ?", createdTourDataRowMapper(), pageSize, offset);
        }
    }

    private RowMapper<CreatedTourData> createdTourDataRowMapper() {
        // Implement your RowMapper logic here to map the ResultSet to CreatedTourData
        return (rs, rowNum) -> {
            CreatedTourData createdTourData = new CreatedTourData();
            // Map the ResultSet columns to createdTourData fields here
            createdTourData.setCreatedTourId(rs.getLong("created_tour_id"));
            createdTourData.setCreatedTourName(rs.getString("created_tour_name"));
            createdTourData.setCreatedTourThumbImageUrl(rs.getString("created_tour_thumb_image_url"));
            createdTourData.setCreatedTourTitle(rs.getString("created_tour_title"));
            createdTourData.setCreatedTourDescription(rs.getString("created_tour_description"));
            // Set other fields as needed
            return createdTourData;
        };
    }
}
