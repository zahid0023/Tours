package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.entities.DestinationLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<DestinationLocationEntity, Long> {
    Optional<DestinationLocationEntity> findLocationEntityById(Long locationID);
}
