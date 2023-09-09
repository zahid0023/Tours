package com.example.ghuddytour2.tours.repository;

import com.example.ghuddytour2.tours.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    Optional<LocationEntity> findLocationEntityById(Long locationID);
}
