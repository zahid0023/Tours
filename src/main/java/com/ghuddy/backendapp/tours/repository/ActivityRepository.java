package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.entities.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {

}