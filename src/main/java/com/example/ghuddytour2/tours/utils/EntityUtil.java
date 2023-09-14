package com.example.ghuddytour2.tours.utils;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Function;


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
}



