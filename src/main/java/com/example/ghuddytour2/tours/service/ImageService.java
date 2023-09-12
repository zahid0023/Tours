package com.example.ghuddytour2.tours.service;

import com.example.ghuddytour2.tours.dto.data.ImageData;
import com.example.ghuddytour2.tours.dto.request.ImageAddRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.ActivityEntity;
import com.example.ghuddytour2.tours.entities.ActivityImageEntity;

import java.util.List;
import java.util.Map;

public interface ImageService {
    AcknowledgeResponse addImage(ImageAddRequest imageAddRequest);

    AcknowledgeResponse addActivityImages(ActivityEntity activityEntity, List<ImageData> activityImages);
}
