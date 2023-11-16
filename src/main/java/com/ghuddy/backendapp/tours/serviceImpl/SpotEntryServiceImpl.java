package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.spot.entry.SpotEntryRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.data.spot.entry.SpotEntryOptionData;
import com.ghuddy.backendapp.tours.model.entities.activity.ActivityEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import com.ghuddy.backendapp.tours.repository.SpotEntryPackageRepository;
import com.ghuddy.backendapp.tours.repository.TourPackageSpotEntryOptionRepository;
import com.ghuddy.backendapp.tours.service.ActivityService;
import com.ghuddy.backendapp.tours.service.SpotEntryService;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SpotEntryServiceImpl implements SpotEntryService {
    private final ActivityService activityService;
    private final TourPackageSpotEntryOptionRepository tourPackageSpotEntryOptionRepository;
    private final SpotEntryPackageRepository spotEntryPackageRepository;

    public SpotEntryServiceImpl(ActivityService activityService, TourPackageSpotEntryOptionRepository tourPackageSpotEntryOptionRepository,
                                SpotEntryPackageRepository spotEntryPackageRepository) {
        this.activityService = activityService;
        this.tourPackageSpotEntryOptionRepository = tourPackageSpotEntryOptionRepository;
        this.spotEntryPackageRepository = spotEntryPackageRepository;
    }

    /**
     * @param tourPackageEntity
     * @param spotEntryRequest
     * @param requestId
     * @return
     */
    @Override
    public InsertAcknowledgeResponse addTourPackageSpotEntry(TourPackageEntity tourPackageEntity, SpotEntryRequest spotEntryRequest, String requestId) {
        SpotEntryOptionEntity spotEntryOptionEntity = new SpotEntryOptionEntity();
        spotEntryOptionEntity.setTourPackageEntity(tourPackageEntity);
        SpotEntryPackageEntity spotEntryPackageEntity = setTourPackageSpotEntries(spotEntryOptionEntity, List.of(spotEntryRequest)).get(0);
        spotEntryOptionEntity.setSpotEntryPackageEntities(List.of(spotEntryPackageEntity));
        SpotEntryOptionData spotEntryOptionData = new SpotEntryOptionData(tourPackageSpotEntryOptionRepository.save(spotEntryOptionEntity));
        return new InsertAcknowledgeResponse(spotEntryOptionData, requestId);
    }

    /**
     * @param tourPackageEntity
     * @param spotEntryRequestList
     * @param requestId
     * @return
     */
    @Override
    public InsertAcknowledgeListResponse addTourPackageSpotEntries(TourPackageEntity tourPackageEntity, List<SpotEntryRequest> spotEntryRequestList, String requestId) {
        SpotEntryOptionEntity spotEntryOptionEntity = new SpotEntryOptionEntity();
        spotEntryOptionEntity.setTourPackageEntity(tourPackageEntity);
        List<SpotEntryPackageEntity> spotEntryPackageEntityList = setTourPackageSpotEntries(spotEntryOptionEntity, spotEntryRequestList);
        spotEntryOptionEntity.setSpotEntryPackageEntities(spotEntryPackageEntityList);
        spotEntryOptionEntity.setTotalOptionPricePerPerson(spotEntryOptionEntity.getSpotEntryPackageEntities().stream()
                .map(spotEntryPackageEntity -> spotEntryPackageEntity.getPricePerPerson())
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        SpotEntryOptionData spotEntryOptionData = new SpotEntryOptionData(tourPackageSpotEntryOptionRepository.save(spotEntryOptionEntity));
        return new InsertAcknowledgeListResponse(List.of(spotEntryOptionData), requestId);
    }

    /**
     * @param spotEntryRequestList
     * @return
     */
    @Override
    public List<SpotEntryPackageEntity> setTourPackageSpotEntries(SpotEntryOptionEntity spotEntryOptionEntity, List<SpotEntryRequest> spotEntryRequestList) {
        Set<Long> activityIds = spotEntryRequestList.stream()
                .map(spotEntryRequest -> spotEntryRequest.getActivityId())
                .collect(Collectors.toSet());
        Map<Long, ActivityEntity> activityEntityMap = activityService.getActivityEntityMapByIDs(activityIds);
        List<SpotEntryPackageEntity> spotEntryPackageEntityList = spotEntryRequestList.stream()
                .map(spotEntryRequest -> {
                    SpotEntryPackageEntity spotEntryPackageEntity = new SpotEntryPackageEntity();
                    spotEntryPackageEntity.setSpotEntryOptionEntity(spotEntryOptionEntity);
                    spotEntryPackageEntity.setActivityEntity(activityEntityMap.get(spotEntryRequest.getActivityId()));
                    spotEntryPackageEntity.setPricePerPerson(spotEntryRequest.getPricePerPerson());
                    spotEntryPackageEntity.setRemark(spotEntryRequest.getRemark());
                    spotEntryPackageEntity.setActive(true);
                    return spotEntryPackageEntity;
                })
                .toList();
        return spotEntryPackageEntityList;
    }

    /**
     * @param spotEntryPackageIds
     * @return
     */
    @Override
    public Map<Long, SpotEntryPackageEntity> getSpotEntryPackageEntitiesById(Set<Long> spotEntryPackageIds) {
        return EntityUtil.findEntitiesByIds(spotEntryPackageIds, spotEntryPackageRepository, SpotEntryPackageEntity::getId, "SpotEntryPackageEntity");
    }
}
