package com.example.ghuddytour2.tours.daoImpl;

import com.example.ghuddytour2.enums.ErrorCode;
import com.example.ghuddytour2.tours.dao.ActivityDAO;
import com.example.ghuddytour2.tours.dto.data.ActivityData;
import com.example.ghuddytour2.tours.dto.data.ActivityTypeData;
import com.example.ghuddytour2.tours.dto.response.ActivityResponseList;
import com.example.ghuddytour2.tours.dto.response.ActivityTypeResponseList;
import com.example.ghuddytour2.tours.exception.EmptyListException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityDAOImpl implements ActivityDAO {
    private final JdbcTemplate jdbcTemplate;

    public ActivityDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ActivityTypeResponseList getAllActivityTypes() throws EmptyListException {
        String query = """
                SELECT id as activityTypeID,
                       activity_type_name as activityTypeName,
                       description as description
                FROM activity_type;""";

        List<ActivityTypeData> activityTypes = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ActivityTypeData.class));

        if (activityTypes.isEmpty()) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }

        return new ActivityTypeResponseList(activityTypes);
    }

    @Override
    public ActivityResponseList getAllActivities() throws EmptyListException {
        String query = """
                select a.id                  as activityID,
                       a.activity_name       as activityName,
                       a.short_location      as shortLocation,
                       at.activity_type_name as activityType
                from activity a
                         inner join activity_type at on at.id = a.activity_type_id;
                """;
        List<ActivityData> activities = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ActivityData.class));
        if (activities.isEmpty()) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
        return new ActivityResponseList(activities);
    }
}
