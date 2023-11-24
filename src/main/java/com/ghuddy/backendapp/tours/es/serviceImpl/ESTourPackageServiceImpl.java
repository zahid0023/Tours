package com.ghuddy.backendapp.tours.es.serviceImpl;

import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.es.dto.response.ESTourPackageResponse;
import com.ghuddy.backendapp.tours.es.model.data.ESTourPackageData;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourComponentOptionCombinationDocument;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourPackageDocument;
import com.ghuddy.backendapp.tours.es.repository.ESTourComponentOptionCombinationRepository;
import com.ghuddy.backendapp.tours.es.repository.ESTourPackageRepository;
import com.ghuddy.backendapp.tours.es.service.ESTourPackageService;
import com.ghuddy.backendapp.tours.exception.TourPackageNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ESTourPackageServiceImpl implements ESTourPackageService {
    private final ESTourPackageRepository esTourPackageRepository;
    private final ESTourComponentOptionCombinationRepository esTourComponentOptionCombinationRepository;

    public ESTourPackageServiceImpl(ESTourPackageRepository esTourPackageRepository,
                                    ESTourComponentOptionCombinationRepository esTourComponentOptionCombinationRepository) {
        this.esTourPackageRepository = esTourPackageRepository;
        this.esTourComponentOptionCombinationRepository = esTourComponentOptionCombinationRepository;
    }

    /**
     * @param tourEntity
     * @param requestId
     * @return
     */
    @Override
    public Boolean indexAvailableTourPackages(TourEntity tourEntity, String requestId) {
        List<ESTourPackageDocument> esTourPackageDocumentList = getAvailableTourPackagesByTourEntity(tourEntity);
        esTourPackageRepository.saveAll(esTourPackageDocumentList);
        return true;
    }


    private List<ESTourPackageDocument> getAvailableTourPackagesByTourEntity(TourEntity tourEntity) {
        List<ESTourPackageDocument> esTourPackageDocumentList = tourEntity.getSubscribedTourEntities().stream()
                .flatMap(subscribedTourEntity -> subscribedTourEntity.getTourPackageEntities().stream()
                        .flatMap(tourPackageEntity -> tourPackageEntity.getAvailabilityGeneratedTourPackages().stream()
                                .map(availabilityGeneratedTourPackageEntity -> new ESTourPackageDocument(availabilityGeneratedTourPackageEntity))))
                .toList();
        return esTourPackageDocumentList;
    }

    /**
     * @param tourEntity
     * @param requestId
     * @return
     */
    @Override
    public Boolean indexAvailableTourPackagesOptionsCombinations(TourEntity tourEntity, String requestId) {
        List<ESTourComponentOptionCombinationDocument> esTourComponentOptionCombinationDocumentList = tourEntity.getSubscribedTourEntities().stream()
                .flatMap(subscribedTourEntity -> subscribedTourEntity.getTourPackageEntities().stream()
                        .flatMap(tourPackageEntity -> getAllComponentOptionsCombinations(tourPackageEntity).stream()))
                .collect(Collectors.toList());
        esTourComponentOptionCombinationRepository.saveAll(esTourComponentOptionCombinationDocumentList);
        return true;
    }


    private List<ESTourComponentOptionCombinationDocument> getAllComponentOptionsCombinations(TourPackageEntity tourPackageEntity) {
        List<ESTourComponentOptionCombinationDocument> allOptions = tourPackageEntity.getAvailabilityGeneratedTourPackages().stream()
                .flatMap(availabilityGeneratedTourPackageEntity -> availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedTourPackageAllOptionEntities().stream()
                        .map(availabilityGeneratedTourPackageAllOptionEntity -> new ESTourComponentOptionCombinationDocument(availabilityGeneratedTourPackageAllOptionEntity)))
                .collect(Collectors.toList());
        List<ESTourComponentOptionCombinationDocument> inclusiveOptions = tourPackageEntity.getAvailabilityGeneratedTourPackages().stream()
                .flatMap(availabilityGeneratedTourPackageEntity -> availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedTourPackageInclusiveOptionEntities().stream()
                        .map(availabilityGeneratedTourPackageInclusiveOptionEntity -> new ESTourComponentOptionCombinationDocument(availabilityGeneratedTourPackageInclusiveOptionEntity)))
                .collect(Collectors.toList());
        allOptions.addAll(inclusiveOptions);
        return allOptions.stream().distinct().toList();
    }

    /**
     * @param esTourPackageDocumentList
     * @param requestId
     * @return
     */
    @Override
    public Map<Long, List<ESTourPackageData>> organizeAvailableTourPackagesByTourPackageType(List<ESTourPackageDocument> esTourPackageDocumentList, String requestId) {
        // Organize the packages by their tourPackageTypeId using computeIfAbsent
        Map<Long, List<ESTourPackageData>> idTourPackagesMap = new HashMap<>();

        esTourPackageDocumentList.forEach(esTourPackageDocument -> {
            ESTourPackageData esTourPackageData = new ESTourPackageData(esTourPackageDocument);
            idTourPackagesMap
                    .computeIfAbsent(esTourPackageData.getTourPackageTypeId(), key -> new LinkedList<>())
                    .add(esTourPackageData);
        });
        return idTourPackagesMap;
    }

    /**
     * @param availableTourPackageId
     * @return
     * @throws TourPackageNotFoundException
     */
    @Override
    public ESTourPackageData getAvailableTourPackageDetailsById(Long availableTourPackageId) throws TourPackageNotFoundException {
        ESTourPackageDocument esTourPackageDocument = esTourPackageRepository.findById(availableTourPackageId).orElseThrow(() -> new TourPackageNotFoundException(ErrorCode.TOUR_PACKAGE_NOT_FOUND));
        return new ESTourPackageData(esTourPackageDocument);
    }

    /**
     * @param esTourPackageDocumentList
     * @param requestId
     * @return
     */
    @Override
    public Map<Long, List<Long>> organizeAvailableTourPackagesIdsByTourType(List<ESTourPackageDocument> esTourPackageDocumentList, String requestId) {
        // Organize the packages by their tourPackageTypeId using computeIfAbsent
        Map<Long, List<Long>> idTourPackagesMap = new HashMap<>();

        esTourPackageDocumentList.forEach(esTourPackageDocument -> {
            idTourPackagesMap
                    .computeIfAbsent(esTourPackageDocument.getTourPackageTypeId(), key -> new LinkedList<>())
                    .add(esTourPackageDocument.getTourPackageTypeId());
        });
        return idTourPackagesMap;
    }

    /**
     * @param tourId
     * @param requestId
     * @return
     */
    @Override
    public List<ESTourPackageDocument> getAllAvailableTourPackagesByTourId(Long tourId, String requestId) {
        return esTourPackageRepository.findAllByTourId(tourId);
    }

    /**
     * @param tourId
     * @param requestId
     * @return
     */
    @Override
    public ESTourPackageResponse getAvailableTourPackagesByTourId(Long tourId, String requestId) {
        return new ESTourPackageResponse(organizeAvailableTourPackagesByTourPackageType(getAllAvailableTourPackagesByTourId(tourId, requestId), requestId), requestId);
    }
}
