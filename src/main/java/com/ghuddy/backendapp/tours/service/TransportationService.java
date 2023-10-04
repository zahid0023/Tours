package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.transporation.*;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
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
    // transportation brand
    InsertAcknowledgeResponse addTransportationBrand(TransportationBrandAddRequest transportationBrand);

    InsertAcknowledgeListResponse addTransportationBrands(TransportationBrandListAddRequest transportationBrandListAddRequest);
    TransportationBrandListResponse getAllTransportationBrands() throws EmptyListException;

    TransportationBrandListResponse getAllTransportationBrandsPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    Map<Long, TransportationBrandEntity> getTransportationBrandEntitiesByIDs(Set<Long> transportationBrandIDs);

    // transportation mode
    InsertAcknowledgeResponse addTransportationMode(TransportationModeAddRequest transportationModeAddRequest);

    InsertAcknowledgeListResponse addTransportationModes(TransportationModeListAddRequest transportationModeListAddRequest);

    TransportationModeListResponse getAllTransportationModes() throws EmptyListException;

    TransportationModeListResponse getAllTransportationModesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    Map<Long, TransportationModeEntity> getTransportationModeEntitiesByIDs(Set<Long> transportationModeIDs);

    // transportation provider
    InsertAcknowledgeResponse addTransportationProvider(TransportationProviderAddRequest transportationProviderAddRequest);

    InsertAcknowledgeListResponse addTransportationProviders(TransportationProviderListAddRequest transportationProviderListAddRequest);

    TransportationProviderListResponse getAllTransportationProviders() throws EmptyListException;

    TransportationProviderListResponse getAllTransportationProvidersPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    Map<Long, TransportationProviderEntity> getTransportationProviderEntitiesByIDs(Set<Long> transportationProviderIDs);

    // transportation route
    InsertAcknowledgeResponse addTransportationRoute(TransportationRouteAddRequest transportationRouteAddRequest);

    InsertAcknowledgeListResponse addTransportationRoutes(TransportationRouteListAddRequest transportationRouteListAddRequest);

    TransportationRouteResponseList getAllTransportationRoutes() throws EmptyListException;

    TransportationRouteResponseList getAllTransportationRoutesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    Map<Long, TransportationRouteEntity> getTransportationRouteEntitiesByIDs(Set<Long> transportationRouteIDs);

    // tour package transportation
    AcknowledgeResponse addTourPackageTransportation(TourPackageEntity tourPackageEntity, TourPackageTransportationRequest tourPackageTransportationRequest);

    AcknowledgeResponse addTourPackageTransportations(TourPackageEntity tourPackageEntity, TourPackageTransportationListAddRequest tourPackageTransportationListAddRequest);

    List<TransportationPackageEntity> setTourPackageTransportations(TourPackageEntity tourPackageEntity, List<TourPackageTransportationRequest> tourPackageTransportations);

}
