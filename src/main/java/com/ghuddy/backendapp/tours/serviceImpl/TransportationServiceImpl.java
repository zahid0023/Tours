package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.model.DestinationLocationEntity;
import com.ghuddy.backendapp.service.DestinationLocationService;
import com.ghuddy.backendapp.tours.dao.TransportationDao;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.transportation.TransportationBrandListResponse;
import com.ghuddy.backendapp.tours.dto.response.transportation.TransportationModeListResponse;
import com.ghuddy.backendapp.tours.dto.response.transportation.TransportationProviderListResponse;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationBrandData;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationModeData;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationProviderData;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationRouteData;
import com.ghuddy.backendapp.tours.dto.request.transporation.*;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.transportation.TransportationRouteResponseList;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.entities.*;
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
    private final TransportationPackageRepository transportationPackageRepository;
    private final DestinationLocationService destinationLocationService;
    private final TransportationDao transportationDao;

    public TransportationServiceImpl(TransportationBrandRepository transportationBrandRepository,
                                     TransportationModeRepository transportationModeRepository,
                                     TransportationProviderRepository transportationProviderRepository,
                                     TransportationRouteRepository transportationRouteRepository,
                                     TransportationPackageRepository transportationPackageRepository,
                                     DestinationLocationService destinationLocationService,
                                     TransportationDao transportationDao) {
        this.transportationBrandRepository = transportationBrandRepository;
        this.transportationModeRepository = transportationModeRepository;
        this.transportationProviderRepository = transportationProviderRepository;
        this.transportationRouteRepository = transportationRouteRepository;
        this.transportationPackageRepository = transportationPackageRepository;
        this.destinationLocationService = destinationLocationService;
        this.transportationDao = transportationDao;
    }

    // transportation brand
    @Override
    public InsertAcknowledgeResponse addTransportationBrand(TransportationBrandAddRequest transportationBrandAddRequest) {
        TransportationBrandData transportationBrandData = addTransportationBrands(List.of(transportationBrandAddRequest.getTransportationBrand())).get(0);
        return new InsertAcknowledgeResponse(transportationBrandData, transportationBrandAddRequest.getRequestId());

    }

    @Override
    public InsertAcknowledgeListResponse addTransportationBrands(TransportationBrandListAddRequest transportationBrandListAddRequest) {
        List<TransportationBrandData> transportationBrandDataList = addTransportationBrands(transportationBrandListAddRequest.getTransportationBrands());
        return new InsertAcknowledgeListResponse(transportationBrandDataList, transportationBrandListAddRequest.getRequestId());
    }


    private List<TransportationBrandData> addTransportationBrands(List<TransportationBrandRequest> transportationBrands) {
        List<TransportationBrandEntity> transportationBrandEntities = transportationBrands.stream()
                .map(transportationBrandRequest -> {
                    TransportationBrandEntity transportationBrandEntity = new TransportationBrandEntity();
                    transportationBrandEntity.setBrandName(transportationBrandRequest.getBrandName());
                    return transportationBrandEntity;
                })
                .collect(Collectors.toList());
        return transportationBrandRepository.saveAll(transportationBrandEntities).stream()
                .map(transportationBrandEntity -> new TransportationBrandData(transportationBrandEntity))
                .collect(Collectors.toList());
    }

    /**
     * @return TransportationBrandListResponse
     * @throws EmptyListException
     */
    @Override
    public TransportationBrandListResponse getAllTransportationBrands() throws EmptyListException {
        List<TransportationBrandData> transportationBrandDataList = transportationDao.getAllTransportationBrands(0, 0);
        if (transportationBrandDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TransportationBrandListResponse(transportationBrandDataList);
    }

    /**
     * @param pageSize   the page size
     * @param pageNumber the page number
     * @return TransportationBrandListResponse the response
     * @throws EmptyListException when there are no elements in the list
     */
    @Override
    public TransportationBrandListResponse getAllTransportationBrandsPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException {
        List<TransportationBrandData> transportationBrandDataList = transportationDao.getAllTransportationBrands(pageSize, pageNumber);
        if (transportationBrandDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TransportationBrandListResponse(transportationBrandDataList);
    }

    // transportation mode
    @Override
    public InsertAcknowledgeResponse addTransportationMode(TransportationModeAddRequest transportationModeAddRequest) {
        TransportationModeData transportationModeData = addTransportationModes(List.of(transportationModeAddRequest.getTransportationMode())).get(0);
        return new InsertAcknowledgeResponse(transportationModeData, transportationModeAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addTransportationModes(TransportationModeListAddRequest transportationModeListAddRequest) {
        List<TransportationModeData> transportationModeDataList = addTransportationModes(transportationModeListAddRequest.getTransportationModes());
        return new InsertAcknowledgeListResponse(transportationModeDataList, transportationModeListAddRequest.getRequestId());
    }

    private List<TransportationModeData> addTransportationModes(List<TransportationModeRequest> transportationModes) {
        List<TransportationModeEntity> transportationModeEntities = transportationModes.stream()
                .map(transportationModeRequest -> {
                    TransportationModeEntity transportationModeEntity = new TransportationModeEntity();
                    transportationModeEntity.setModeName(transportationModeRequest.getModeName());
                    transportationModeEntity.setDescription(transportationModeRequest.getDescription());
                    transportationModeEntity.setIconUrl(transportationModeRequest.getIconUrl());
                    return transportationModeEntity;
                })
                .collect(Collectors.toList());
        return transportationModeRepository.saveAll(transportationModeEntities).stream()
                .map(transportationModeEntity -> new TransportationModeData(transportationModeEntity))
                .collect(Collectors.toList());
    }

    @Override
    public TransportationModeListResponse getAllTransportationModes() throws EmptyListException {
        List<TransportationModeData> transportationModeDataList = transportationDao.getAllTransportationModes(0, 0);
        if (transportationModeDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TransportationModeListResponse(transportationModeDataList);
    }

    @Override
    public TransportationModeListResponse getAllTransportationModesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException {
        List<TransportationModeData> transportationModeDataList = transportationDao.getAllTransportationModes(0, 0);
        if (transportationModeDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TransportationModeListResponse(transportationModeDataList);
    }

    // transportation providers
    @Override
    public InsertAcknowledgeResponse addTransportationProvider(TransportationProviderAddRequest transportationProviderAddRequest) {
        TransportationProviderData transportationProviderData = addTransportationProviders(List.of(transportationProviderAddRequest.getTransportationProvider())).get(0);
        return new InsertAcknowledgeResponse(transportationProviderData, transportationProviderAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addTransportationProviders(TransportationProviderListAddRequest transportationProviderListAddRequest) {
        List<TransportationProviderData> transportationProviderDataList = addTransportationProviders(transportationProviderListAddRequest.getTransportationProviders());
        return new InsertAcknowledgeListResponse(transportationProviderDataList, transportationProviderListAddRequest.getRequestId());
    }

    private List<TransportationProviderData> addTransportationProviders(List<TransportationProviderRequest> transportationProviders) {
        List<TransportationProviderEntity> transportationProviderEntities = transportationProviders.stream()
                .map(transportationProviderRequest -> {
                    TransportationProviderEntity transportationProviderEntity = new TransportationProviderEntity();
                    transportationProviderEntity.setTransportationProviderName(transportationProviderRequest.getTransportationProviderName());
                    transportationProviderEntity.setHotlineNumber(transportationProviderRequest.getHotlineNumber());
                    return transportationProviderEntity;
                })
                .collect(Collectors.toList());
        return transportationProviderRepository.saveAll(transportationProviderEntities).stream()
                .map(transportationProviderEntity -> new TransportationProviderData(transportationProviderEntity))
                .collect(Collectors.toList());
    }

    @Override
    public TransportationProviderListResponse getAllTransportationProviders() throws EmptyListException {
        List<TransportationProviderData> transportationProviderDataList = transportationDao.getAllTransportationProviders(0, 0);
        if (transportationProviderDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TransportationProviderListResponse(transportationProviderDataList);
    }

    @Override
    public TransportationProviderListResponse getAllTransportationProvidersPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException {
        List<TransportationProviderData> transportationProviderDataList = transportationDao.getAllTransportationProviders(pageSize, pageNumber);
        if (transportationProviderDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TransportationProviderListResponse(transportationProviderDataList);
    }

    // transportation routes
    @Override
    public InsertAcknowledgeResponse addTransportationRoute(TransportationRouteAddRequest transportationRouteAddRequest) {
        TransportationRouteData transportationRouteData = addTransportationRoutes(List.of(transportationRouteAddRequest.getTransportationRoute())).get(0);
        return new InsertAcknowledgeResponse(transportationRouteData, transportationRouteAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addTransportationRoutes(TransportationRouteListAddRequest transportationRouteListAddRequest) {
        List<TransportationRouteData> transportationRouteDataList = addTransportationRoutes(transportationRouteListAddRequest.getTransportationRoutes());
        return new InsertAcknowledgeListResponse(transportationRouteDataList, transportationRouteListAddRequest.getRequestId());
    }

    private List<TransportationRouteData> addTransportationRoutes(List<TransportationRouteRequest> transportationRoutes) {
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
        return transportationRouteRepository.saveAll(transportationRouteEntities).stream()
                .map(transportationRouteEntity -> new TransportationRouteData(transportationRouteEntity))
                .collect(Collectors.toList());
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


    // tour package transportation
    @Transactional
    @Override
    public AcknowledgeResponse addTourPackageTransportation(TourPackageEntity tourPackageEntity, TourPackageTransportationRequest tourPackageTransportationRequest) {
        List<TransportationPackageEntity> tourPackageTransportationEntities = setTourPackageTransportations(tourPackageEntity, List.of(tourPackageTransportationRequest));
        transportationPackageRepository.saveAll(tourPackageTransportationEntities);
        return new AcknowledgeResponse();
    }

    @Transactional
    @Override
    public AcknowledgeResponse addTourPackageTransportations(TourPackageEntity tourPackageEntity, TourPackageTransportationListAddRequest tourPackageTransportationListAddRequest) {
        List<TransportationPackageEntity> tourPackageTransportationEntities = setTourPackageTransportations(tourPackageEntity, tourPackageTransportationListAddRequest.getTourPackageTransportations());
        transportationPackageRepository.saveAll(tourPackageTransportationEntities);
        return new AcknowledgeResponse();
    }

    @Override
    public List<TransportationPackageEntity> setTourPackageTransportations(TourPackageEntity tourPackageEntity, List<TourPackageTransportationRequest> tourPackageTransportations) {
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

        List<TransportationPackageEntity> tourPackageTransportationEntities = tourPackageTransportations.stream()
                .map(tourPackageTransportationRequest -> {
                    TransportationPackageEntity transportationPackageEntity = new TransportationPackageEntity();
                    transportationPackageEntity.setTourPackageEntity(tourPackageEntity);
                    transportationPackageEntity.setTransportationRouteEntity(transportationRouteEntityMap.get(tourPackageTransportationRequest.getTransportationRouteID()));
                    transportationPackageEntity.setTransportationModeEntity(transportationModeEntityMap.get(tourPackageTransportationRequest.getTransportationModeID()));
                    transportationPackageEntity.setTransportationBrandEntity(transportationBrandEntityMap.get(tourPackageTransportationRequest.getTransportationBrandID()));
                    transportationPackageEntity.setTransportationProviderEntity(transportationProviderEntityMap.get(tourPackageTransportationRequest.getTransportationProviderID()));
                    transportationPackageEntity.setIsAc(tourPackageTransportationRequest.getIsAC());
                    transportationPackageEntity.setTripType(tourPackageTransportationRequest.getTripType());
                    transportationPackageEntity.setUnitPrice(tourPackageTransportationRequest.getUnitPrice());
                    transportationPackageEntity.setQuantity(tourPackageTransportationRequest.getQuantity());
                    transportationPackageEntity.setNetPrice(tourPackageTransportationRequest.getNetPrice());
                    transportationPackageEntity.setAddedPrice(tourPackageTransportationRequest.getAddedPrice());
                    transportationPackageEntity.setTotalTransportationPackagePrice(tourPackageTransportationRequest.getTotalTransportationPackagePrice());
                    transportationPackageEntity.setIsIncluded(tourPackageTransportationRequest.getIsDefault());
                    return transportationPackageEntity;
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
