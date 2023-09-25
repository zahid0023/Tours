package com.ghuddy.backendapp.tours.dto.request.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ImageRequest extends BaseRequest {
    @Schema(description = "The filename of the uploaded image", example = "image1.jpg", required = true)
    @JsonProperty("file_name")
    private String fileName;
    @Schema(description = "The url of the uploaded image", example = "www.google.com/image1.jpg", required = true)
    @JsonProperty("image_url")
    private String imageURL;
    @Schema(description = "The caption that goes with the image", example = "A picture of tour", required = true)
    @JsonProperty("image_caption")
    private String imageCaption;

    @Override
    public void validate() throws AbstractException {

    }
}
