package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.transfer.TransferOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferOptionRepository extends JpaRepository<TransferOptionEntity, Long> {
}