package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.guide.GuideOptionRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.entities.guide.GuideOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.GuidePackageEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.GuidePackageRepository;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import com.ghuddy.backendapp.tours.repository.GuideOptionRepository;
import com.ghuddy.backendapp.tours.service.GuideService;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class GuideServiceImpl implements GuideService {
    private final GuideOptionRepository guideOptionRepository;
    private final GuidePackageRepository guidePackageRepository;

    public GuideServiceImpl(GuideOptionRepository guideOptionRepository,
                            GuidePackageRepository guidePackageRepository) {
        this.guideOptionRepository = guideOptionRepository;
        this.guidePackageRepository = guidePackageRepository;
    }

    /**
     * @return
     */
    @Override
    @Transactional
    public InsertAcknowledgeResponse addTourPackageGuideOption() {
        return null;
    }

    /**
     * @return
     */
    @Transactional
    @Override
    public InsertAcknowledgeListResponse addTourPackageGuideOptions() {
        return null;
    }

    /**
     * @param tourPackageEntity
     * @param guideOptionRequestList
     * @return
     */
    @Override
    public List<GuideOptionEntity> setTourPackageGuideOptions(TourPackageEntity tourPackageEntity, List<GuideOptionRequest> guideOptionRequestList) {
        return guideOptionRequestList.stream()
                .map(guideOptionRequest -> {
                    GuideOptionEntity guideOptionEntity = new GuideOptionEntity();
                    guideOptionEntity.setTourPackageEntity(tourPackageEntity);
                    List<GuidePackageEntity> guidePackageEntityList = guideOptionRequest.getGuidePackageRequestList().stream()
                            .map(guidePackageRequest -> {
                                GuidePackageEntity guidePackageEntity = new GuidePackageEntity();
                                guidePackageEntity.setGuideOptionEntity(guideOptionEntity);
                                guidePackageEntity.setDayNumber(guidePackageRequest.getDayNumber());
                                guidePackageEntity.setNumberOfGuideForDay(guidePackageRequest.getNumberOfGuidesForDay());
                                guidePackageEntity.setTotalGuidePriceForDay(guidePackageRequest.getPerDayGuidePrice());
                                return guidePackageEntity;
                            })
                            .toList();
                    guideOptionEntity.setGuidePackageEntities(guidePackageEntityList);
                    guideOptionEntity.setTotalOptionPrice(BigDecimal.valueOf(100));
                    return guideOptionEntity;
                })
                .toList();
    }

    /**
     * @param guidePackageIds
     * @return
     */
    @Override
    public Map<Long, GuidePackageEntity> getGuidePackageEntitiesById(Set<Long> guidePackageIds) {

        return EntityUtil.findEntitiesByIds(guidePackageIds, guidePackageRepository, GuidePackageEntity::getId, "GuidePackageEntity");
    }
}
