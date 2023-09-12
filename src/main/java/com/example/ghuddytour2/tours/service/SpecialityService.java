package com.example.ghuddytour2.tours.service;

import com.example.ghuddytour2.tours.dto.data.TourSpecialityData;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.TourEntity;

import java.util.List;

public interface SpecialityService {
    AcknowledgeResponse setTourSpecialities(TourEntity tourEntity, List<TourSpecialityData> specialities);
}
