package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.spot.entry.SpotEntryRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.data.spot.entry.SpotEntryData;
import com.ghuddy.backendapp.tours.model.entities.activity.ActivityEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import com.ghuddy.backendapp.tours.repository.SpotEntryRepository;
import com.ghuddy.backendapp.tours.service.ActivityService;
import com.ghuddy.backendapp.tours.service.SpotEntryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SpotEntryServiceImpl implements SpotEntryService {
    private final ActivityService activityService;
    private final SpotEntryRepository spotEntryRepository;

    public SpotEntryServiceImpl(ActivityService activityService, SpotEntryRepository spotEntryRepository) {
        this.activityService = activityService;
        this.spotEntryRepository = spotEntryRepository;
    }

    /**
     * @param tourPackageEntity
     * @param spotEntryRequest
     * @param requestId
     * @return
     */
    @Override
    public InsertAcknowledgeResponse addTourPackageSpotEntry(TourPackageEntity tourPackageEntity, SpotEntryRequest spotEntryRequest, String requestId) {
        SpotEntryEntity spotEntryEntity = setTourPackageSpotEntries(tourPackageEntity, List.of(spotEntryRequest), requestId).get(0);
        SpotEntryData spotEntryData = new SpotEntryData(spotEntryRepository.save(spotEntryEntity));
        return new InsertAcknowledgeResponse(spotEntryData, requestId);
    }

    /**
     * @param tourPackageEntity
     * @param spotEntryRequestList
     * @param requestId
     * @return
     */
    @Override
    public InsertAcknowledgeListResponse addTourPackageSpotEntries(TourPackageEntity tourPackageEntity, List<SpotEntryRequest> spotEntryRequestList, String requestId) {
        List<SpotEntryEntity> spotEntryEntityList = setTourPackageSpotEntries(tourPackageEntity, spotEntryRequestList, requestId);
        List<SpotEntryData> spotEntryDataList = spotEntryRepository.saveAll(spotEntryEntityList).stream()
                .map(spotEntryEntity -> new SpotEntryData(spotEntryEntity))
                .toList();
        return new InsertAcknowledgeListResponse(spotEntryDataList, requestId);
    }

    /**
     * @param tourPackageEntity
     * @param spotEntryRequestList
     * @param requestId
     * @return
     */
    @Override
    public List<SpotEntryEntity> setTourPackageSpotEntries(TourPackageEntity tourPackageEntity, List<SpotEntryRequest> spotEntryRequestList, String requestId) {
        Set<Long> activityIds = spotEntryRequestList.stream()
                .map(spotEntryRequest -> spotEntryRequest.getActivityId())
                .collect(Collectors.toSet());
        Map<Long, ActivityEntity> activityEntityMap = activityService.getActivityEntityMapByIDs(activityIds);
        List<SpotEntryEntity> spotEntryEntityList = spotEntryRequestList.stream()
                .map(spotEntryRequest -> {
                    SpotEntryEntity spotEntryEntity = new SpotEntryEntity();
                    spotEntryEntity.setTourPackageEntity(tourPackageEntity);
                    spotEntryEntity.setActivityEntity(activityEntityMap.get(spotEntryRequest.getActivityId()));
                    spotEntryEntity.setPricePerPerson(spotEntryRequest.getPricePerPerson());
                    spotEntryEntity.setRemark(spotEntryRequest.getRemark());
                    spotEntryEntity.setActive(true);
                    return spotEntryEntity;
                })
                .toList();
        return spotEntryEntityList;
    }
}
