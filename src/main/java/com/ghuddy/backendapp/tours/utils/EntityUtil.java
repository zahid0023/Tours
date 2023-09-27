package com.ghuddy.backendapp.tours.utils;

import com.ghuddy.backendapp.tours.dto.data.LimitOffsetData;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import lombok.experimental.UtilityClass;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@UtilityClass
public class EntityUtil {

    public static <T, Long> Map<Long, T> findEntitiesByIds(Set<Long> ids,
                                                           JpaRepository<T, Long> repository,
                                                           Function<T, Long> idExtractor,
                                                           String entityName) {

        List<T> entities = repository.findAllById(ids);

        Map<Long, T> entityMap = entities.stream()
                .collect(Collectors.toMap(
                        idExtractor,
                        entity -> entity
                ));

        if (entities.size() < ids.size()) {
            List<Long> unmatchedIds = new ArrayList<>(ids);
            unmatchedIds.removeAll(entityMap.keySet());
            throw new IllegalArgumentException("Some " + entityName + " entities were not found for the given IDs.");
        }
        return entityMap;
    }

    public static LimitOffsetData getLimitOffsetClause(Integer pageSize, Integer pageNumber) {
        int offset = (pageNumber - 1) * pageSize;
        return new LimitOffsetData(" limit ? offset ?", offset);
    }

    // for getting all entities(data classes) without any pagination call this method with pageSize = 0 and pageNumber = 0
    public static <T> List<T> getAllEntitiesPaginated(String query, Integer pageSize, Integer pageNumber, Class<T> entityClass, JdbcTemplate jdbcTemplate) throws EmptyListException {
        try {
            if (pageNumber == 0 && pageSize == 0) {
                return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(entityClass));
            } else {
                LimitOffsetData limitOffsetData = getLimitOffsetClause(pageSize, pageNumber);
                String paginatedQuery = query + limitOffsetData.appendSql();
                Integer offset = limitOffsetData.offset();
                return jdbcTemplate.query(paginatedQuery, BeanPropertyRowMapper.newInstance(entityClass), pageSize, offset);
            }
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }
}



