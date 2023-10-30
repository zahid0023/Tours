package com.ghuddy.backendapp.es;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ESTourPackage {
    @JsonProperty("tour_package_id")
    private Long tourPackageId;
    @JsonProperty("tour_package_name")
    private String tourPackageName;
    @JsonProperty("suitable_for_persons")
    private Integer suitableForPersons;
    @JsonProperty("package_image_urls")
    private List<String> packageImageUrls;
    @JsonProperty("package_thumb_image_url")
    private String packageThumbImageUrl;
    @JsonProperty("accommodation_options")
    private List<ESAccommodationOption> accommodationOptionList;
    @JsonProperty("food_options")
    private List<ESFoodOption> foodOptionList;
    @JsonProperty("transfer_options")
    private List<ESTransferOption> transferOptionList;
    @JsonProperty("transfer_packages")
    private List<ESTransportPackage> transportPackageList;

}
