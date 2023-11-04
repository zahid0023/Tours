package com.ghuddy.backendapp.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.ActivityImageEntity;
import lombok.Data;

@Data
public class ESImageData {
    @JsonProperty("image_id")
    private Long imageId;
    @JsonProperty("image_file_name")
    private String imageFileName;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("image_caption")
    private String imageCaption;

    public ESImageData(ActivityImageEntity activityImageEntity) {
        this.imageId = activityImageEntity.getId();
        this.imageFileName = activityImageEntity.getFileName();
        this.imageUrl = activityImageEntity.getImageUrl();
        this.imageCaption = activityImageEntity.getCaption();
    }
}
