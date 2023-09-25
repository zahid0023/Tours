package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.model.DestinationLocationEntity;
import com.ghuddy.backendapp.service.DestinationLocationService;
import com.ghuddy.backendapp.tours.dao.TransportationDao;
import com.ghuddy.backendapp.tours.dto.data.TransportationRouteData;
import com.ghuddy.backendapp.tours.dto.request.transporation.*;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.transportation.TransportationRouteResponseList;
import com.ghuddy.backendapp.tours.entities.*;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.repository.*;
import com.ghuddy.backendapp.tours.service.TransportationService;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransportationServiceImpl implements TransportationService {
    private final TransportationBrandRepository transportationBrandRepository;
    private final TransportationModeRepository transportationModeRepository;
    private final TransportationProviderRepository transportationProviderRepository;
    private final TransportationRouteRepository transportationRouteRepository;
    private final TourPackageTransportationRepository tourPackageTransportationRepository;
    private final DestinationLocationService destinationLocationService;
    private final TransportationDao transportationDao;

    public TransportationServiceImpl(TransportationBrandRepository transportationBrandRepository,
                                     TransportationModeRepository transportationModeRepository,
                                     TransportationProviderRepository transportationProviderRepository,
                                     TransportationRouteRepository transportationRouteRepository,
                                     TourPackageTransportationRepository tourPackageTransportationRepository,
                                     DestinationLocationService destinationLocationService,
                                     TransportationDao transportationDao) {
        this.transportationBrandRepository = transportationBrandRepository;
        this.transportationModeRepository = transportationModeRepository;
        this.transportationProviderRepository = transportationProviderRepository;
        this.transportationRouteRepository = transportationRouteRepository;
        this.tourPackageTransportationRepository = tourPackageTransportationRepository;
        this.destinationLocationService = destinationLocationService;
        this.transportationDao = transportationDao;
    }

    // transportation brand
    @Override
    public AcknowledgeResponse addTransportationBrand(TransportationBrandAddRequest transportationBrand) {
        return addTransportationBrands(List.of(transportationBrand.getTransportationBrand()));
    }

    @Override
    public AcknowledgeResponse addTransportationBrands(TransportationBrandListAddRequest transportationBrandListAddRequest) {
        return addTransportationBrands(transportationBrandListAddRequest.getTransportationBrands());
    }

    private AcknowledgeResponse addTransportationBrands(List<TransportationBrandRequest> transportationBrands) {
        List<TransportationBrandEntity> transportationBrandEntities = transportationBrands.stream()
                .map(transportationBrandRequest -> {
                    TransportationBrandEntity transportationBrandEntity = new TransportationBrandEntity();
                    transportationBrandEntity.setBrandName(transportationBrandRequest.getBrandName());
                    return transportationBrandEntity;
                })
                .collect(Collectors.toList());
        transportationBrandRepository.saveAll(transportationBrandEntities);
        return new AcknowledgeResponse();
    }

    // transportation mode
    @Override
    public AcknowledgeResponse addTransportationMode(TransportationModeAddRequest transportationModeAddRequest) {
        return addTransportationModes(List.of(transportationModeAddRequest.getTransportationMode()));
    }

    @Override
    public AcknowledgeResponse addTransportationModes(TransportationModeListAddRequest transportationModeListAddRequest) {
        return addTransportationModes(transportationModeListAddRequest.getTransportationModes());
    }

    private AcknowledgeResponse addTransportationModes(List<TransportationModeRequest> transportationModes) {
        List<TransportationModeEntity> transportationModeEntities = transportationModes.stream()
                .map(transportationModeRequest -> {
                    TransportationModeEntity transportationModeEntity = new TransportationModeEntity();
                    transportationModeEntity.setModeName(transportationModeRequest.getModeName());
                    transportationModeEntity.setDescription(transportationModeRequest.getDescription());
                    transportationModeEntity.setIconUrl(transportationModeRequest.getIconUrl());
                    return transportationModeEntity;
                })
                .collect(Collectors.toList());
        transportationModeRepository.saveAll(transportationModeEntities);
        return new AcknowledgeResponse();
    }

    // transportation providers
    @Override
    public AcknowledgeResponse addTransportationProvider(TransportationProviderAddRequest transportationProviderAddRequest) {
        return addTransportationProviders(List.of(transportationProviderAddRequest.getTransportationProvider()));
    }

    @Override
    public AcknowledgeResponse addTransportationProviders(TransportationProviderListAddRequest transportationProviderListAddRequest) {
        return addTransportationProviders(transportationProviderListAddRequest.getTransportationProviders());
    }

    private AcknowledgeResponse addTransportationProviders(List<TransportationProviderRequest> transportationProviders) {
        List<TransportationProviderEntity> transportationProviderEntities = transportationProviders.stream()
                .map(transportationProviderRequest -> {
                    TransportationProviderEntity transportationProviderEntity = new TransportationProviderEntity();
                    transportationProviderEntity.setTransportationProviderName(transportationProviderRequest.getTransportationProviderName());
                    transportationProviderEntity.setHotlineNumber(transportationProviderRequest.getHotlineNumber());
                    return transportationProviderEntity;
                })
                .collect(Collectors.toList());
        transportationProviderRepository.saveAll(transportationProviderEntities);
        return new AcknowledgeResponse();
    }

    // transportation routes
    @Override
    public AcknowledgeResponse addTransportationRoute(TransportationRouteAddRequest transportationRouteAddRequest) {
        return addTransportationRoutes(List.of(transportationRouteAddRequest.getTransportationRoute()));
    }

    @Override
    public AcknowledgeResponse addTransportationRoutes(TransportationRouteListAddRequest transportationRouteListAddRequest) {
        return addTransportationRoutes(transportationRouteListAddRequest.getTransportationRoutes());
    }

    @Override
    public TransportationRouteResponseList getAllTransportationRoutes() throws EmptyListException {
        List<TransportationRouteData> transportationRouteDataList = transportationDao.getAllTransportationRoutes(0, 0);
        if (transportationRouteDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TransportationRouteResponseList(transportationRouteDataList);
    }

    @Override
    public TransportationRouteResponseList getAllTransportationRoutesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException {
        List<TransportationRouteData> transportationRouteDataList = transportationDao.getAllTransportationRoutes(pageSize, pageNumber);
        if (transportationRouteDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TransportationRouteResponseList(transportationRouteDataList);
    }

    private AcknowledgeResponse addTransportationRoutes(List<TransportationRouteRequest> transportationRoutes) {
        Set<Long> locationIDs = transportationRoutes.stream()
                .flatMap(route -> Stream.of(route.getSourceLocationID(), route.getDestinationLocationID()))
                .collect(Collectors.toSet());
        Map<Long, DestinationLocationEntity> destinationLocationEntityMap = destinationLocationService.getDestinationLocationByIDs(locationIDs);
        List<TransportationRouteEntity> transportationRouteEntities = transportationRoutes.stream()
                .map(transportationRouteRequest -> {
                    TransportationRouteEntity transportationRouteEntity = new TransportationRouteEntity();
                    transportationRouteEntity.setSourceLocation(destinationLocationEntityMap.get(transportationRouteRequest.getSourceLocationID()));
                    transportationRouteEntity.setDestinationLocation(destinationLocationEntityMap.get(transportationRouteRequest.getDestinationLocationID()));
                    return transportationRouteEntity;
                })
                .collect(Collectors.toList());
        transportationRouteRepository.saveAll(transportationRouteEntities);
        return new AcknowledgeResponse();
    }

    @Transactional
    @Override
    public AcknowledgeResponse addTourPackageTransportation(TourPackageEntity tourPackageEntity, TourPackageTransportationRequest tourPackageTransportationRequest) {
        List<TourPackageTransportationEntity> tourPackageTransportationEntities = setTourPackageTransportations(tourPackageEntity, List.of(tourPackageTransportationRequest));
        tourPackageTransportationRepository.saveAll(tourPackageTransportationEntities);
        return new AcknowledgeResponse();
    }

    @Transactional
    @Override
    public AcknowledgeResponse addTourPackageTransportations(TourPackageEntity tourPackageEntity, TourPackageTransportationListAddRequest tourPackageTransportationListAddRequest) {
        List<TourPackageTransportationEntity> tourPackageTransportationEntities = setTourPackageTransportations(tourPackageEntity, tourPackageTransportationListAddRequest.getTourPackageTransportations());
        tourPackageTransportationRepository.saveAll(tourPackageTransportationEntities);
        return new AcknowledgeResponse();
    }

    @Override
    public List<TourPackageTransportationEntity> setTourPackageTransportations(TourPackageEntity tourPackageEntity, List<TourPackageTransportationRequest> tourPackageTransportations) {
        Map<String, Set<Long>> idMaps = new HashMap<>();
        tourPackageTransportations.forEach(tourPackageTransportationRequest -> {
            idMaps.computeIfAbsent("route", key -> new HashSet<>())
                    .add(tourPackageTransportationRequest.getTransportationRouteID());
            idMaps.computeIfAbsent("mode", key -> new HashSet<>())
                    .add(tourPackageTransportationRequest.getTransportationModeID());
            idMaps.computeIfAbsent("brand", key -> new HashSet<>())
                    .add(tourPackageTransportationRequest.getTransportationBrandID());
            idMaps.computeIfAbsent("provider", key -> new HashSet<>())
                    .add(tourPackageTransportationRequest.getTransportationProviderID());
        });
        Map<Long, TransportationRouteEntity> transportationRouteEntityMap = getTransportationRouteEntitiesByIDs(idMaps.get("route"));
        Map<Long, TransportationModeEntity> transportationModeEntityMap = getTransportationModeEntitiesByIDs(idMaps.get("mode"));
        Map<Long, TransportationBrandEntity> transportationBrandEntityMap = getTransportationBrandEntitiesByIDs(idMaps.get("brand"));
        Map<Long, TransportationProviderEntity> transportationProviderEntityMap = getTransportationProviderEntitiesByIDs(idMaps.get("provider"));

        List<TourPackageTransportationEntity> tourPackageTransportationEntities = tourPackageTransportations.stream()
                .map(tourPackageTransportationRequest -> {
                    TourPackageTransportationEntity tourPackageTransportationEntity = new TourPackageTransportationEntity();
                    tourPackageTransportationEntity.setTourPackageEntity(tourPackageEntity);
                    tourPackageTransportationEntity.setTransportationRouteEntity(transportationRouteEntityMap.get(tourPackageTransportationRequest.getTransportationRouteID()));
                    tourPackageTransportationEntity.setTransportationModeEntity(transportationModeEntityMap.get(tourPackageTransportationRequest.getTransportationModeID()));
                    tourPackageTransportationEntity.setTransportationBrand(transportationBrandEntityMap.get(tourPackageTransportationRequest.getTransportationBrandID()));
                    tourPackageTransportationEntity.setTransportationProviderEntity(transportationProviderEntityMap.get(tourPackageTransportationRequest.getTransportationProviderID()));
                    tourPackageTransportationEntity.setIsAc(tourPackageTransportationRequest.getIsAC());
                    tourPackageTransportationEntity.setTripType(tourPackageTransportationRequest.getTripType());
                    return tourPackageTransportationEntity;
                })
                .collect(Collectors.toList());

        return tourPackageTransportationEntities;
    }

    @Override
    public Map<Long, TransportationRouteEntity> getTransportationRouteEntitiesByIDs(Set<Long> transportationRouteIDs) {
        return EntityUtil.findEntitiesByIds(transportationRouteIDs, transportationRouteRepository, TransportationRouteEntity::getId, "TransportationRouteEntity");
    }

    @Override
    public Map<Long, TransportationModeEntity> getTransportationModeEntitiesByIDs(Set<Long> transportationModeIDs) {
        return EntityUtil.findEntitiesByIds(transportationModeIDs, transportationModeRepository, TransportationModeEntity::getId, "TransportationModeEntity");
    }

    @Override
    public Map<Long, TransportationBrandEntity> getTransportationBrandEntitiesByIDs(Set<Long> transportationBrandIDs) {
        return EntityUtil.findEntitiesByIds(transportationBrandIDs, transportationBrandRepository, TransportationBrandEntity::getId, "TransportationBrandEntity");
    }

    @Override
    public Map<Long, TransportationProviderEntity> getTransportationProviderEntitiesByIDs(Set<Long> transportationProviderIDs) {
        return EntityUtil.findEntitiesByIds(transportationProviderIDs, transportationProviderRepository, TransportationProviderEntity::getId, "TransportationProviderEntity");
    }
}
