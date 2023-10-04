package com.ghuddy.backendapp.repository;

import com.ghuddy.backendapp.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<UserEntity, Long> {
}