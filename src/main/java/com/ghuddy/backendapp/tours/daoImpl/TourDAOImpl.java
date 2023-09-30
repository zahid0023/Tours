package com.ghuddy.backendapp.tours.daoImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghuddy.backendapp.tours.dao.TourDAO;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.tour.AddedTourData;
import com.ghuddy.backendapp.tours.model.data.tour.TourData;
import com.ghuddy.backendapp.tours.model.data.tour.TourItineraryData;
import com.ghuddy.backendapp.tours.model.data.tour.TourSpecialityData;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
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
     * @param pageSize the page size
     * @param pageNumber the number of page
     * @return List of TourData
     */
    public List<TourData> getAllCreatedTours(Integer pageSize, Integer pageNumber) {
        String query = getAllCreatedToursQuery();

        if (pageNumber == 0 && pageSize == 0) {
            // No pagination required
            return jdbcTemplate.query(query, tourDataRowMapper());
        } else {
            // Paginated query
            int offset = (pageNumber - 1) * pageSize;
            return jdbcTemplate.query(query + " LIMIT ? OFFSET ?", tourDataRowMapper(), pageSize, offset);
        }
    }

    private RowMapper<TourData> tourDataRowMapper() {
        // Implement your RowMapper logic here to map the ResultSet to CreatedTourData
        return (rs, rowNum) -> {
            TourData tourData = new TourData();
            // Map the ResultSet columns to createdTourData fields here
            tourData.setTourId(rs.getLong("tour_id"));
            tourData.setTourName(rs.getString("tour_name"));
            tourData.setTourDescription(rs.getString("tour_thumb_image_url"));
            tourData.setTourTitle(rs.getString("tour_title"));
            tourData.setTourDescription(rs.getString("tour_description"));
            // Set other fields as needed
            String tourActivities = rs.getString("tour_itinerary");
            if (tourActivities != null && !tourActivities.isEmpty())
                try {
                    List<TourItineraryData> tourItinerary = objectMapper.readValue(tourActivities, new TypeReference<List<TourItineraryData>>() {
                    });
                    tourData.setItinerary(tourItinerary);
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
            else tourData.setItinerary(new LinkedList<>());

            String tourSpecialities = rs.getString("tour_specialities");
            if (tourSpecialities != null && !tourSpecialities.isEmpty()) {
                try {
                    List<TourSpecialityData> tourSpecialityDataList = objectMapper.readValue(tourSpecialities, new TypeReference<List<TourSpecialityData>>() {
                    });
                    tourData.setTourSpecialityDataList(tourSpecialityDataList);
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
            } else tourData.setTourSpecialityDataList(new LinkedList<>());

            return tourData;
        };
    }

    private String getAllCreatedToursQuery() {
        return """
                with tour_specialities_agg as (select tour_specialities.tour_id           as tour_id,
                                                      json_agg(json_build_object(
                                                              'tour_speciality_id',
                                                              tour_specialities.tour_speciality_id,
                                                              'tour_speciality_title',
                                                              tour_specialities.tour_speciality_title,
                                                              'tour_speciality_description',
                                                              tour_specialities.tour_speciality_description,
                                                              'tour_speciality_icon_url',
                                                              tour_specialities.tour_speciality_icon_url,
                                                              'tour_id',
                                                              tour_specialities.tour_id)) as tour_specialities_agg_column
                                               from (select ts.id          as tour_speciality_id,
                                                            ts.title       as tour_speciality_title,
                                                            ts.description as tour_speciality_description,
                                                            ts.icon_url    as tour_speciality_icon_url,
                                                            ts.tour_id     as tour_id
                                                     from tour_speciality ts) as tour_specialities
                                               group by tour_specialities.tour_id),
                                
                     tour_activities_agg as (select tour_activities.tour_id,
                                                    json_agg(json_build_object(
                                                            'tour_activity_id',
                                                            tour_activities.tour_activity_id,
                                                            'tour_activity_name',
                                                            tour_activities.tour_activity_name,
                                                            'tour_activity_day_number',
                                                            tour_activities.tour_activity_day_number,
                                                            'tour_activity_start_time',
                                                            tour_activities.tour_activity_start_time,
                                                            'tour_activity_end_time',
                                                            tour_activities.tour_activity_end_time)) as tour_activities_agg_column
                                             from (select ti.id           as tour_activity_id,
                                                          a.activity_name as tour_activity_name,
                                                          ti.day_number   as tour_activity_day_number,
                                                          ti.start_time   as tour_activity_start_time,
                                                          ti.end_time     as tour_activity_end_time,
                                                          ti.tour_id      as tour_id
                                                   from tour_itinerary ti
                                                            inner join activity a on a.id = ti.activity_id) as tour_activities
                                             group by tour_activities.tour_id)
                select t.id                             as tour_id,
                       at.tour_name                     as tour_name,
                       t.title                          as tour_title,
                       t.description                    as tour_description,
                       t.thumb_image_url                as tour_thumb_image_url,
                       taa.tour_activities_agg_column   as tour_itinerary,
                       tsa.tour_specialities_agg_column as tour_specialities
                from tour as t
                         inner join added_tours at on t.added_tour_id = at.id
                         left join tour_activities_agg taa on taa.tour_id = t.id
                         left join tour_specialities_agg tsa on tsa.tour_id = t.id
                """;
    }
}
