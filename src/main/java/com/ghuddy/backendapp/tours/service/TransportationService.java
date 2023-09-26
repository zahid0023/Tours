package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.transporation.*;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.transportation.TransportationBrandListResponse;
import com.ghuddy.backendapp.tours.dto.response.transportation.TransportationModeListResponse;
import com.ghuddy.backendapp.tours.dto.response.transportation.TransportationProviderListResponse;
import com.ghuddy.backendapp.tours.dto.response.transportation.TransportationRouteResponseList;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.entities.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TransportationService {
    AcknowledgeResponse addTransportationBrand(TransportationBrandAddRequest transportationBrand);

    AcknowledgeResponse addTransportationBrands(TransportationBrandListAddRequest transportationBrandListAddRequest);
    TransportationBrandListResponse getAllTransportationBrands() throws EmptyListException;

    TransportationBrandListResponse getAllTransportationBrandsPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    AcknowledgeResponse addTransportationMode(TransportationModeAddRequest transportationModeAddRequest);

    AcknowledgeResponse addTransportationModes(TransportationModeListAddRequest transportationModeListAddRequest);

    TransportationModeListResponse getAllTransportationModes() throws EmptyListException;

    TransportationModeListResponse getAllTransportationModesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    AcknowledgeResponse addTransportationProvider(TransportationProviderAddRequest transportationProviderAddRequest);

    AcknowledgeResponse addTransportationProviders(TransportationProviderListAddRequest transportationProviderListAddRequest);

    TransportationProviderListResponse getAllTransportationProviders() throws EmptyListException;

    TransportationProviderListResponse getAllTransportationProvidersPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

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
