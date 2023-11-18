package com.ghuddy.backendapp.tours.es.serviceImpl;

import com.ghuddy.backendapp.tours.es.dto.data.ESTourPackageData;
import com.ghuddy.backendapp.tours.es.service.ESTourPackageService;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ESTourPackageServiceImpl implements ESTourPackageService {
    /**
     * @param subscribedTourEntity
     * @param requestId
     * @return
     */
    @Override
    public List<ESTourPackageData> getTourPackagesBySubscribedTour(SubscribedTourEntity subscribedTourEntity, String requestId) {
       // List<TourPackageEntity> tourPackageEntityList = subscribedTourEntity.getTourPackageEntities();
       // List<ESTourPackageData> esTourPackageDataList = tourPackageEntityList.stream()
         //       .map(tourPackageEntity -> new ESTourPackageData(tourPackageEntity))
           //     .toList();
        return null;
    }
}
