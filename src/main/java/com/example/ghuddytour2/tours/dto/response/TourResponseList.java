package com.example.ghuddytour2.tours.dto.response;

import com.example.ghuddytour2.tours.dto.data.TourAddData;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class TourResponseList {
    private List<TourAddData> tours = new LinkedList<>();

    public TourResponseList(List<TourAddData> tours) {
        this.tours = tours;
    }
}
