package com.ghuddy.backendapp.tours.es.serviceImpl;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.es.dto.data.ESSubscribedTourItineraryData;
import com.ghuddy.backendapp.tours.es.service.ESTourItineraryService;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.entities.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.SubscribedTourItineraryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ESTourItineraryServiceImpl implements ESTourItineraryService {
    /**
     * @param subscribedTourEntity
     * @param requestId
     * @return
     * @throws EmptyListException
     */
    @Override
    public List<ESSubscribedTourItineraryData> getESTourItineraryBySubscribedTourEntity(SubscribedTourEntity subscribedTourEntity, String requestId) throws EmptyListException {
        List<SubscribedTourItineraryEntity> subscribedTourItineraryEntityList = subscribedTourEntity.getSubscribedTourItineraryEntities();
        if (subscribedTourItineraryEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return subscribedTourItineraryEntityList.stream()
                .map(subscribedTourItineraryEntity -> new ESSubscribedTourItineraryData(subscribedTourItineraryEntity))
                .toList();
    }
}
