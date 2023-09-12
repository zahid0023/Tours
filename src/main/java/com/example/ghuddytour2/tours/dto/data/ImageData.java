package com.example.ghuddytour2.tours.dto.data;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class ImageData {
    @Schema(description = "The file name of the image.", example = "image1.jpg")
    private String imageFileName;

    @Schema(description = "The URL of the image.", example = "https://example.com/images/image1.jpg")
    private String imageURL;

    private String imageCaption;
}

