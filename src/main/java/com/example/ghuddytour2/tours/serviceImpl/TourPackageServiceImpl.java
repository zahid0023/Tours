package com.example.ghuddytour2.tours.serviceImpl;

import com.example.ghuddytour2.tours.dto.request.TourPackageTypeAddRequest;
import com.example.ghuddytour2.tours.dto.request.TourPackageTypeListAddRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.TourPackageTypeEntity;
import com.example.ghuddytour2.tours.repository.TourPackageTypeRepository;
import com.example.ghuddytour2.tours.service.TourPackageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourPackageServiceImpl implements TourPackageService {
    private final TourPackageTypeRepository tourPackageTypeRepository;

    public TourPackageServiceImpl(TourPackageTypeRepository tourPackageTypeRepository) {
        this.tourPackageTypeRepository = tourPackageTypeRepository;
    }

    @Override
    public AcknowledgeResponse addTourPackageType(TourPackageTypeAddRequest tourPackageTypeAddRequest) {
        return addTourPackageTypes(List.of(tourPackageTypeAddRequest));
    }

    @Override
    public AcknowledgeResponse addTourPackageTypes(TourPackageTypeListAddRequest tourPackageTypeListAddRequest) {
        return addTourPackageTypes(tourPackageTypeListAddRequest.getTourPackageTypes());
    }

    private AcknowledgeResponse addTourPackageTypes(List<TourPackageTypeAddRequest> tourPackages) {
        List<TourPackageTypeEntity> tourPackageTypeEntities = tourPackages.stream()
                .map(tourPackageTypeAddRequest -> {
                    TourPackageTypeEntity tourPackageTypeEntity = new TourPackageTypeEntity();
                    tourPackageTypeEntity.setPackageTypeName(tourPackageTypeAddRequest.getTourPackageTypeName());
                    tourPackageTypeEntity.setDescription(tourPackageTypeAddRequest.getTourPackageTypeDescription());
                    tourPackageTypeEntity.setSuitableFor(tourPackageTypeAddRequest.getSuitableForPersons());
                    return tourPackageTypeEntity;
                })
                .collect(Collectors.toList());
        tourPackageTypeRepository.saveAll(tourPackageTypeEntities);
        return new AcknowledgeResponse();
    }
}
