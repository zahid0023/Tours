package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.transporation.*;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.transportation.TransportationRouteResponseList;
import com.ghuddy.backendapp.tours.entities.*;
import com.ghuddy.backendapp.tours.exception.EmptyListException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TransportationService {
    AcknowledgeResponse addTransportationBrand(TransportationBrandAddRequest transportationBrand);

    AcknowledgeResponse addTransportationBrands(TransportationBrandListAddRequest transportationBrandListAddRequest);

    AcknowledgeResponse addTransportationMode(TransportationModeAddRequest transportationModeAddRequest);

    AcknowledgeResponse addTransportationModes(TransportationModeListAddRequest transportationModeListAddRequest);

    AcknowledgeResponse addTransportationProvider(TransportationProviderAddRequest transportationProviderAddRequest);

    AcknowledgeResponse addTransportationProviders(TransportationProviderListAddRequest transportationProviderListAddRequest);

    AcknowledgeResponse addTransportationRoute(TransportationRouteAddRequest transportationRouteAddRequest);

    AcknowledgeResponse addTransportationRoutes(TransportationRouteListAddRequest transportationRouteListAddRequest);

    TransportationRouteResponseList getAllTransportationRoutes() throws EmptyListException;

    TransportationRouteResponseList getAllTransportationRoutesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    Map<Long, TransportationRouteEntity> getTransportationRouteEntitiesByIDs(Set<Long> transportationRouteIDs);

    Map<Long, TransportationModeEntity> getTransportationModeEntitiesByIDs(Set<Long> transportationModeIDs);

    Map<Long, TransportationBrandEntity> getTransportationBrandEntitiesByIDs(Set<Long> transportationBrandIDs);

    Map<Long, TransportationProviderEntity> getTransportationProviderEntitiesByIDs(Set<Long> transportationProviderIDs);

    AcknowledgeResponse addTourPackageTransportation(TourPackageEntity tourPackageEntity, TourPackageTransportationRequest tourPackageTransportationRequest);

    AcknowledgeResponse addTourPackageTransportations(TourPackageEntity tourPackageEntity, TourPackageTransportationListAddRequest tourPackageTransportationListAddRequest);

    List<TourPackageTransportationEntity> setTourPackageTransportations(TourPackageEntity tourPackageEntity, List<TourPackageTransportationRequest> tourPackageTransportations);

}
