package com.ghuddy.backendapp.tours.es.serviceImpl;

import com.ghuddy.backendapp.tours.es.dto.data.ESSubscribedTourItineraryData;
import com.ghuddy.backendapp.tours.es.dto.data.ESTourPackageData;
import com.ghuddy.backendapp.tours.es.dto.response.ESSubscribedTourResponse;
import com.ghuddy.backendapp.tours.es.dto.data.ESSubscribedTourData;
import com.ghuddy.backendapp.tours.es.repository.ESTourRepository;
import com.ghuddy.backendapp.tours.es.service.ESTourItineraryService;
import com.ghuddy.backendapp.tours.es.service.ESTourPackageService;
import com.ghuddy.backendapp.tours.es.service.ESTourService;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.service.TourSubscriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ESTourServiceImpl implements ESTourService {
    private final TourSubscriptionService tourSubscriptionService;
    private final ESTourPackageService esTourPackageService;
    private final ESTourItineraryService esTourItineraryService;
    private final ESTourRepository esTourRepository;

    public ESTourServiceImpl(TourSubscriptionService tourSubscriptionService,
                             ESTourPackageService esTourPackageService,
                             ESTourItineraryService esTourItineraryService,
                             ESTourRepository esTourRepository) {
        this.tourSubscriptionService = tourSubscriptionService;
        this.esTourPackageService = esTourPackageService;
        this.esTourItineraryService = esTourItineraryService;
        this.esTourRepository = esTourRepository;
    }

    /**
     * @param subscribedTourId
     * @param requestId
     * @return
     * @throws TourNotFoundException
     */
    @Override
    public ESSubscribedTourResponse getSubscribedTourById(Long subscribedTourId, String requestId) throws TourNotFoundException, EmptyListException {
        SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(subscribedTourId);
        ESSubscribedTourData esSubscribedTourData = new ESSubscribedTourData(subscribedTourEntity);
        List<ESTourPackageData> esTourPackageDataList = esTourPackageService.getTourPackagesBySubscribedTour(subscribedTourEntity, requestId);
        List<ESSubscribedTourItineraryData> esSubscribedTourItineraryDataList = esTourItineraryService.getESTourItineraryBySubscribedTourEntity(subscribedTourEntity, requestId);
        return new ESSubscribedTourResponse(esSubscribedTourData, esSubscribedTourItineraryDataList, esTourPackageDataList);
    }

    /**
     * @param subscribedTourId
     * @param requestId
     */
    @Override
    public void indexESSubscribedTour(Long subscribedTourId, String requestId) throws TourNotFoundException {
        SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(subscribedTourId);
        ESSubscribedTourData esSubscribedTourData = new ESSubscribedTourData(subscribedTourEntity);
        esTourRepository.save(esSubscribedTourData);
    }
}
