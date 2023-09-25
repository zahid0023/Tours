package com.ghuddy.backendapp.tours.dao;

import com.ghuddy.backendapp.tours.dto.data.TransportationRouteData;
import com.ghuddy.backendapp.tours.exception.EmptyListException;

import java.util.List;

public interface TransportationDao {
    List<TransportationRouteData> getAllTransportationRoutes(Integer pageSize, Integer pagNumber) throws EmptyListException;
}
