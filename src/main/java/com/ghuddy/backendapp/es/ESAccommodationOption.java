package com.ghuddy.backendapp.es;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class ESAccommodationOption extends ESOption{
    @JsonProperty("accommodation_packages")
    private List<ESAccommodationPackage> accommodationPackageList;

}
