package com.ghuddy.backendapp.tours.es.serviceImpl;

import com.ghuddy.backendapp.tours.es.model.entities.ESTourComponentOptionCombinationDocument;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourPackageDocument;
import com.ghuddy.backendapp.tours.es.repository.ESTourComponentOptionCombinationRepository;
import com.ghuddy.backendapp.tours.es.repository.ESTourPackageRepository;
import com.ghuddy.backendapp.tours.es.service.ESTourPackageService;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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
        List<ESTourPackageDocument> esTourPackageDocumentList = getAvailableTourPackagesByTourId(tourEntity);
        esTourPackageRepository.saveAll(esTourPackageDocumentList);
        return true;
    }

    /**
     * @param tourEntity
     * @return
     */
    @Override
    public List<ESTourPackageDocument> getAvailableTourPackagesByTourId(TourEntity tourEntity) {
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

    /**
     * @param tourPackageEntity
     * @return
     */
    @Override
    public List<ESTourComponentOptionCombinationDocument> getAllComponentOptionsCombinations(TourPackageEntity tourPackageEntity) {
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
}
