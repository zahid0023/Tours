package com.ghuddy.backendapp.tours.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ESTourPackageData {
    @JsonProperty("tour_package_id")
    @Field(name = "tour_package_id")
    private Long tourPackageId;
    @JsonProperty("tour_package_type_id")
    @Field(name = "tour_package_type_id")
    private Long tourPackageTypeId;
    @JsonProperty("tour_package_name")
    @Field(name = "tour_package_name")
    private String tourPackageName;
    @JsonProperty("suitable_for_persons")
    @Field(name = "suitable_for_persons")
    private Integer suitableForPersons;
    @JsonProperty("tour_package_thumb_image_url")
    @Field(name = "tour_package_thumb_image_url")
    private String packageThumbImageUrl;
    @JsonProperty("tour_package_options")
    @Field(name = "component_options")
    private List<ESComponentCombinationData> esComponentCombinationDataList;
    @JsonProperty("tour_package_transportation_packages")
    @Field(name = "transportation_packages")
    private List<ESTransportationPackageData> esTransportationPackageDataList;

    public ESTourPackageData(TourPackageEntity tourPackageEntity) {
        this.tourPackageId = tourPackageEntity.getId();
        this.tourPackageTypeId = tourPackageEntity.getTourPackageType().getId();
        this.tourPackageName = tourPackageEntity.getTourPackageName();
        this.suitableForPersons = tourPackageEntity.getTourPackageType().getSuitableFor();
        this.esComponentCombinationDataList = tourPackageEntity.getTourPackageOptionEntities().stream()
                .map(tourPackageOptionEntity -> new ESComponentCombinationData(tourPackageOptionEntity))
                .toList();
        this.esTransportationPackageDataList = tourPackageEntity.getTransportationPackageEntities().stream()
                .map(transportationPackageEntity -> new ESTransportationPackageData(transportationPackageEntity))
                .toList();
    }

}
