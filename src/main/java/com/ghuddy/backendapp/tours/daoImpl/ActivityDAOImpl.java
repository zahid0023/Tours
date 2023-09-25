package com.ghuddy.backendapp.tours.daoImpl;

import com.ghuddy.backendapp.tours.dao.ActivityDAO;
import com.ghuddy.backendapp.tours.dto.data.ActivityData;
import com.ghuddy.backendapp.tours.dto.data.ActivityTypeData;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityDAOImpl implements ActivityDAO {
    private final JdbcTemplate jdbcTemplate;

    public ActivityDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // used both for paginated and non paginated
    public List<ActivityTypeData> getAllActivityTypes(Integer pageSize, Integer pageNumber) throws EmptyListException {
        String sql = """
                      select id as activity_type_id,
                             activity_type_name as activity_type_name,
                             description as description
                      from activity_type
                """;
        try {
            List<ActivityTypeData> activityTypes = EntityUtil.getAllEntitiesPaginated(sql, pageSize, pageNumber, ActivityTypeData.class, jdbcTemplate);
            return activityTypes;
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }

    // used both for paginated and non paginated
    @Override
    public List<ActivityData> getAllActivities(Integer pageSize, Integer pageNumber) throws EmptyListException {
        String query = """
                select a.id                  as activity_id,
                       a.activity_name       as activity_name,
                       a.short_location      as short_location,
                       at.activity_type_name as activity_type
                from activity a
                         inner join activity_type at on at.id = a.activity_type_id
                """;
        try {
            List<ActivityData> activities = EntityUtil.getAllEntitiesPaginated(query, pageSize, pageNumber, ActivityData.class, jdbcTemplate);
            return activities;
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }
}
