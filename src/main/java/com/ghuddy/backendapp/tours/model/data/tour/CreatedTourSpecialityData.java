package com.ghuddy.backendapp.tours.model.data.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.TourSpecialityEntity;
import lombok.Data;

@Data
public class CreatedTourSpecialityData {
    @JsonProperty("created_tour_speciality_id")
    private Long createdTourSpecialityId;
    @JsonProperty("created_tour_speciality_title")
    private String createdTourSpecialityTitle;
    @JsonProperty("created_tour_speciality_description")
    private String createdTourSpecialityDescription;
    @JsonProperty("created_tour_speciality_icon_url")
    private String createdTourSpecialityIconUrl;

    public CreatedTourSpecialityData(TourSpecialityEntity tourSpecialityEntity) {
        this.createdTourSpecialityId = tourSpecialityEntity.getId();
        this.createdTourSpecialityTitle = tourSpecialityEntity.getTitle();
        this.createdTourSpecialityDescription = tourSpecialityEntity.getDescription();
        this.createdTourSpecialityIconUrl = tourSpecialityEntity.getIconUrl();
    }
}
