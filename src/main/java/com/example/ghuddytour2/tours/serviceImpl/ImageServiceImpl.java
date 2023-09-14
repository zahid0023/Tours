package com.example.ghuddytour2.tours.serviceImpl;

import com.example.ghuddytour2.tours.dto.request.image.ImageRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.ActivityEntity;
import com.example.ghuddytour2.tours.repository.ActivityImageRepository;
import com.example.ghuddytour2.tours.service.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    private final ActivityImageRepository activityImageRepository;

    public ImageServiceImpl(ActivityImageRepository activityImageRepository) {
        this.activityImageRepository = activityImageRepository;
    }

    @Override
    public AcknowledgeResponse addImage(ImageRequest imageRequest) {
        return null;
    }

    @Override
    public AcknowledgeResponse addActivityImages(ActivityEntity activityEntity, List<ImageRequest> activityImages) {

        return new AcknowledgeResponse();
    }
}
