package com.ghuddy.backendapp.tours.dao;

import com.ghuddy.backendapp.tours.dto.response.tour.TourResponseList;
import com.ghuddy.backendapp.tours.exception.EmptyListException;

public interface TourDAO {
    public TourResponseList getAllTours() throws EmptyListException;

    public TourResponseList getAllToursPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;
}
