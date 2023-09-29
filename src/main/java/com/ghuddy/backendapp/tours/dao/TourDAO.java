package com.ghuddy.backendapp.tours.dao;

import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.tour.AddedTourData;
import com.ghuddy.backendapp.tours.model.data.tour.AddedTourDataOptimized;
import com.ghuddy.backendapp.tours.model.data.tour.CreatedTourData;

import java.util.List;

public interface TourDAO {
    public List<AddedTourData> getAllAddedTours(Integer pageSize, Integer pageNumber) throws EmptyListException;

    public List<CreatedTourData> getAllCreatedTours(Integer pageSize, Integer pageNumber) throws EmptyListException;
}
