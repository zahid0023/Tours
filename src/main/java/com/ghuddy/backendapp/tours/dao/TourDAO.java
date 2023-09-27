package com.ghuddy.backendapp.tours.dao;

import com.ghuddy.backendapp.tours.dto.data.TourAddData;
import com.ghuddy.backendapp.tours.exception.EmptyListException;

import java.util.List;

public interface TourDAO {
    public List<TourAddData> getAllTours(Integer pageSize, Integer pageNumber) throws EmptyListException;
}
