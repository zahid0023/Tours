package com.example.ghuddytour2.tours.dao;

import com.example.ghuddytour2.tours.dto.response.TourResponseList;
import com.example.ghuddytour2.tours.exception.EmptyListException;

public interface TourDAO {
    public TourResponseList getAllTours() throws EmptyListException;

    public TourResponseList getAllToursPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;
}
