package com.ghuddy.backendapp.tours.daoImpl;

import com.ghuddy.backendapp.tours.dao.TransportationDao;
import com.ghuddy.backendapp.tours.dto.data.TransportationRouteData;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransportationDaoImpl implements TransportationDao {
    private final JdbcTemplate jdbcTemplate;

    public TransportationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TransportationRouteData> getAllTransportationRoutes(Integer pageSize, Integer pageNumber) throws EmptyListException {
        String query = """
                select tr.id               AS transportation_route_id,
                       src_loc.place_name  AS transportation_route_source_location,
                       dest_loc.place_name AS transportation_route_destinations_location
                from transportation_route tr
                         inner join location src_loc ON tr.source_location_id = src_loc.id
                         inner join location dest_loc ON tr.destination_location_id = dest_loc.id
                """;
        try {
            List<TransportationRouteData> transportationRoutes = EntityUtil.getAllEntitiesPaginated(query, pageSize, pageNumber, TransportationRouteData.class, jdbcTemplate);
            return transportationRoutes;
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }
}
