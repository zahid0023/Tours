package com.example.ghuddytour2.tours.repository;

import com.example.ghuddytour2.tours.entities.ActivityTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityTypeRepository extends JpaRepository<ActivityTypeEntity, Long> {

}