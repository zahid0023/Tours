package com.example.ghuddytour2.tours.service;

import com.example.ghuddytour2.tours.dto.request.tour.TourSpecialityRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.TourEntity;
import com.example.ghuddytour2.tours.entities.TourSpecialityEntity;

import java.util.List;

public interface SpecialityService {
    List<TourSpecialityEntity> setTourSpecialities(TourEntity tourEntity, List<TourSpecialityRequest> specialities);
}
