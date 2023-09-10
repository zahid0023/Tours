package com.example.ghuddytour2.tours.dto.response;

import com.example.ghuddytour2.tours.dto.data.TourData;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class TourResponseList {
    private List<TourData> tours = new LinkedList<>();

    public TourResponseList(List<TourData> tours) {
        this.tours = tours;
    }
}
