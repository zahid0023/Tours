package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.tour.TourSubscriptionRequest;
import com.ghuddy.backendapp.tours.dto.response.AddressResponse;
import com.ghuddy.backendapp.tours.dto.response.tour.SubscribedTourListResponse;
import com.ghuddy.backendapp.tours.dto.response.tour.TourSubscriptionResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.SubscribedTourEntity;

import javax.persistence.EntityNotFoundException;

public interface TourSubscriptionService {
    TourSubscriptionResponse subscribeTour(TourSubscriptionRequest tourSubscriptionRequest, String requestId) throws TourNotFoundException, EntityNotFoundException;

    SubscribedTourEntity getSubscribedTourEntityById(Long id) throws TourNotFoundException;

    SubscribedTourListResponse getAllSubscribedToursByMerchantId(Long merchantId, String requestId) throws EmptyListException;

    SubscribedTourListResponse getAllSubscribedToursPaginatedByMerchantId(Integer pageSize, Integer pageNumber, Long merchantId, String requestId) throws EmptyListException;

    AddressResponse getSubscribedTourRelatedAddress(Long merchantId, Long subscribedTourId, String requestId) throws TourNotFoundException;

}
