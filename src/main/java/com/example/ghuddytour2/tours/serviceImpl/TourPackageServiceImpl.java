package com.example.ghuddytour2.tours.serviceImpl;

import com.example.ghuddytour2.tours.dto.request.*;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.TourEntity;
import com.example.ghuddytour2.tours.entities.TourPackageEntity;
import com.example.ghuddytour2.tours.entities.TourPackageTypeEntity;
import com.example.ghuddytour2.tours.repository.TourPackageRepository;
import com.example.ghuddytour2.tours.repository.TourPackageTypeRepository;
import com.example.ghuddytour2.tours.service.TourPackageService;
import com.example.ghuddytour2.tours.service.TourService;
import com.example.ghuddytour2.tours.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourPackageServiceImpl implements TourPackageService {
    private final TourPackageTypeRepository tourPackageTypeRepository;
    private final TourPackageRepository tourPackageRepository;
    private final TourService tourService;

    public TourPackageServiceImpl(TourPackageTypeRepository tourPackageTypeRepository,
                                  TourPackageRepository tourPackageRepository,
                                  TourService tourService) {
        this.tourPackageTypeRepository = tourPackageTypeRepository;
        this.tourPackageRepository = tourPackageRepository;
        this.tourService = tourService;
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

    @Override
    public AcknowledgeResponse addTourPackage(TourPackageAddRequest tourPackageAddRequest) {
        TourEntity tourEntity = tourService.getTourByTourID(tourPackageAddRequest.getTourID());
        return addTourPackages(tourEntity, List.of(tourPackageAddRequest.getTourPackageRequest()));
    }

    @Override
    public AcknowledgeResponse addTourPackages(TourPackageListAddRequest tourPackageListAddRequest) {
        TourEntity tourEntity = tourService.getTourByTourID(tourPackageListAddRequest.getTourID());
        return addTourPackages(tourEntity, tourPackageListAddRequest.getTourPackages());
    }

    private AcknowledgeResponse addTourPackages(TourEntity tourEntity, List<TourPackageRequest> tourPackages) {
        List<TourPackageEntity> tourPackageEntities = tourPackages.stream()
                .map(tourPackageRequest -> {
                    TourPackageEntity tourPackageEntity = new TourPackageEntity();
                    tourPackageEntity.setTourEntity(tourEntity);

                    TourPackageTypeEntity tourPackageTypeEntity = getTourPackageTypeByPackageTypeID(tourPackageRequest.getTourPackageTypeID());
                    tourPackageEntity.setTourPackageType(tourPackageTypeEntity);

                    tourPackageEntity.setTourPackageName(StringUtil.tourPackageName(tourEntity.getTourName(), tourPackageTypeEntity.getPackageTypeName()));
                    tourPackageEntity.setDescription(tourPackageRequest.getDescription());

                    return tourPackageEntity;
                })
                .collect(Collectors.toList());
        tourPackageRepository.saveAll(tourPackageEntities);
        return new AcknowledgeResponse();
    }

    @Override
    public TourPackageTypeEntity getTourPackageTypeByPackageTypeID(Long tourPackageTypeID) {
        return tourPackageTypeRepository.findById(tourPackageTypeID).orElseThrow(() -> new EntityNotFoundException("TourPackageTypeEntity Not Found"));
    }

    @Override
    public TourPackageEntity getTourPackageByPackageID(Long tourPackageID) {
        return tourPackageRepository.findById(tourPackageID).orElseThrow(() -> new EntityNotFoundException("TourPackageEntity Not Found"));
    }
}
