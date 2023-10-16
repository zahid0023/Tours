package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.TransferPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferPackageRepository extends JpaRepository<TransferPackageEntity, Long> {
}