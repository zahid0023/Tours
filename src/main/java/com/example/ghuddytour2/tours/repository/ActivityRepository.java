package com.example.ghuddytour2.tours.repository;

import com.example.ghuddytour2.tours.entities.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {

}