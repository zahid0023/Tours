package com.ghuddy.backendapp.tours.dto.request.accommodation;

import lombok.Data;

import java.util.List;

@Data
public class RoomTypeListAddRequest {
    private List<RoomTypeRequest> roomTypes;
}
