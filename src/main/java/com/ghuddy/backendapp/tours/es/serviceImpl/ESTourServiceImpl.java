package com.ghuddy.backendapp.tours.es.serviceImpl;

import com.ghuddy.backendapp.tours.es.dto.data.ESTourData;
import com.ghuddy.backendapp.tours.es.dto.response.ESSubscribedTourResponse;
import com.ghuddy.backendapp.tours.es.model.ESTourDocument;
import com.ghuddy.backendapp.tours.es.repository.ESTourDetailsRepository;
import com.ghuddy.backendapp.tours.es.service.ESTourService;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;
import com.ghuddy.backendapp.tours.service.TourService;
import com.ghuddy.backendapp.tours.service.TourSubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class ESTourServiceImpl implements ESTourService {
    private final TourSubscriptionService tourSubscriptionService;
    private final TourService tourService;
    private final ESTourDetailsRepository esTourDetailsRepository;

    public ESTourServiceImpl(TourSubscriptionService tourSubscriptionService,
                             TourService tourService,
                             ESTourDetailsRepository esTourDetailsRepository) {
        this.tourSubscriptionService = tourSubscriptionService;
        this.tourService = tourService;
        this.esTourDetailsRepository = esTourDetailsRepository;
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

    /**
     * @param createdTourId
     * @param requestId
     * @return
     */
    @Override
    public Boolean indexTourByTourId(Long createdTourId, String requestId) throws TourNotFoundException {
        TourEntity tourEntity = tourService.getCreatedTourEntityById(createdTourId);
        ESTourDocument esTourDocument = new ESTourDocument(tourEntity);
        esTourDetailsRepository.save(esTourDocument);
        return true;
    }
}
