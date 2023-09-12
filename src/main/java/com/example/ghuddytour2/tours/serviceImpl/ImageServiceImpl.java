package com.example.ghuddytour2.tours.serviceImpl;

import com.example.ghuddytour2.tours.dto.data.ImageData;
import com.example.ghuddytour2.tours.dto.request.ImageAddRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.ActivityEntity;
import com.example.ghuddytour2.tours.entities.ActivityImageEntity;
import com.example.ghuddytour2.tours.repository.ActivityImageRepository;
import com.example.ghuddytour2.tours.service.ActivityService;
import com.example.ghuddytour2.tours.service.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {
    private final ActivityImageRepository activityImageRepository;

    public ImageServiceImpl(ActivityImageRepository activityImageRepository) {
        this.activityImageRepository = activityImageRepository;
    }

    @Override
    public AcknowledgeResponse addImage(ImageAddRequest imageAddRequest) {
        return null;
    }

    @Override
    public AcknowledgeResponse addActivityImages(ActivityEntity activityEntity, List<ImageData> activityImages) {

        List<ActivityImageEntity> activityImageEntities = activityImages.stream()
                .map(imageData -> {
                    ActivityImageEntity activityImageEntity = new ActivityImageEntity();
                    activityImageEntity.setFileName(imageData.getImageFileName());
                    activityImageEntity.setImageUrl(imageData.getImageURL());
                    activityImageEntity.setActivityEntity(activityEntity);
                    activityImageEntity.setCaption(imageData.getImageCaption());

                    return activityImageEntity;
                }).
                collect(Collectors.toList());
        activityImageRepository.saveAll(activityImageEntities);
        return new AcknowledgeResponse();
    }
}
