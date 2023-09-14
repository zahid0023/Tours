package com.example.ghuddytour2.tours.service;

import com.example.ghuddytour2.tours.dto.request.image.ImageRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.ActivityEntity;

import java.util.List;

public interface ImageService {
    AcknowledgeResponse addImage(ImageRequest imageRequest);

    AcknowledgeResponse addActivityImages(ActivityEntity activityEntity, List<ImageRequest> activityImages);
}
