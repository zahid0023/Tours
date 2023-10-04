package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.TransportationPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportationPackageRepository extends JpaRepository<TransportationPackageEntity, Long> {
}