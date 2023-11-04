package com.ghuddy.backendapp.es.serviceImpl;

import com.ghuddy.backendapp.es.dto.data.ESTourPackageData;
import com.ghuddy.backendapp.es.service.ESTourPackageService;
import com.ghuddy.backendapp.tours.model.entities.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
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
        List<TourPackageEntity> tourPackageEntityList = subscribedTourEntity.getTourPackageEntities();
        List<ESTourPackageData> esTourPackageDataList = tourPackageEntityList.stream()
                .map(tourPackageEntity -> new ESTourPackageData(tourPackageEntity))
                .toList();
        return esTourPackageDataList;
    }
}
