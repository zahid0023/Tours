package com.ghuddy.backendapp.tours.es.service;

import com.ghuddy.backendapp.tours.es.dto.data.ESTourPackageData;
import com.ghuddy.backendapp.tours.model.entities.SubscribedTourEntity;

import java.util.List;

public interface ESTourPackageService {
    List<ESTourPackageData> getTourPackagesBySubscribedTour(SubscribedTourEntity subscribedTourEntity, String requestId);
}
