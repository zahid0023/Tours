package com.ghuddy.backendapp.tours.es.serviceImpl;

import com.ghuddy.backendapp.tours.es.dto.data.ESTourData;
import com.ghuddy.backendapp.tours.es.dto.response.ESSubscribedTourResponse;
import com.ghuddy.backendapp.tours.es.service.ESTourService;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.service.TourSubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class ESTourServiceImpl implements ESTourService {
    private final TourSubscriptionService tourSubscriptionService;

    public ESTourServiceImpl(TourSubscriptionService tourSubscriptionService) {
        this.tourSubscriptionService = tourSubscriptionService;
    }

    /**
     * @param subscribedTourId
     * @param requestId
     * @return
     * @throws TourNotFoundException
     */
    @Override
    public ESSubscribedTourResponse getAllAvailableTourPackages(Long subscribedTourId, String requestId) throws TourNotFoundException, EmptyListException {
        SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(subscribedTourId);
        ESTourData esTourData = new ESTourData(subscribedTourEntity);
        return new ESSubscribedTourResponse(esTourData);
    }
}
