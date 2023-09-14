package com.example.ghuddytour2.tours.serviceImpl;

import com.example.ghuddytour2.tours.dto.request.tour.TourSpecialityRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.TourEntity;
import com.example.ghuddytour2.tours.entities.TourSpecialityEntity;
import com.example.ghuddytour2.tours.repository.TourSpecialityRepository;
import com.example.ghuddytour2.tours.service.SpecialityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialityServiceImpl implements SpecialityService {
    private final TourSpecialityRepository tourSpecialityRepository;

    public SpecialityServiceImpl(TourSpecialityRepository tourSpecialityRepository) {
        this.tourSpecialityRepository = tourSpecialityRepository;
    }

    @Override
    public List<TourSpecialityEntity> setTourSpecialities(TourEntity tourEntity, List<TourSpecialityRequest> specialities) {
        List<TourSpecialityEntity> tourSpecialityEntities = specialities.stream()
                .map(specialityData -> {
                    TourSpecialityEntity tourSpecialityEntity = new TourSpecialityEntity();
                    tourSpecialityEntity.setTitle(specialityData.getTourSpecialityTitle());
                    tourSpecialityEntity.setDescription(specialityData.getTourSpecialityDescription());
                    tourSpecialityEntity.setIconUrl(specialityData.getTourSpecialityIconURL());
                    tourSpecialityEntity.setTourEntity(tourEntity);
                    return tourSpecialityEntity;
                })
                .collect(Collectors.toList());

        return tourSpecialityEntities;
    }
}
