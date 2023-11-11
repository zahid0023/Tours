package com.ghuddy.backendapp.tours.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.tour.TourSpecialityEntity;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
public class ESTourSpecialityData {
    @JsonProperty("tour_speciality_id")
    @Field(name = "tour_speciality_id")
    private Long tourSpecialityId;
    @JsonProperty("tour_speciality_title")
    @Field(name = "tour_speciality_title")
    private String tourSpecialityTitle;
    @JsonProperty("tour_speciality_description")
    @Field(name = "tour_speciality_description")
    private String tourSpecialityDescription;
    @JsonProperty("tour_speciality_icon_url")
    @Field(name = "tour_speciality_icon_url")
    private String tourSpecialityIconUrl;

    public ESTourSpecialityData(TourSpecialityEntity tourSpecialityEntity) {
        this.tourSpecialityId = tourSpecialityEntity.getId();
        this.tourSpecialityTitle = tourSpecialityEntity.getTitle();
        this.tourSpecialityDescription = tourSpecialityEntity.getDescription();
        this.tourSpecialityIconUrl = tourSpecialityEntity.getIconUrl();
    }
}
