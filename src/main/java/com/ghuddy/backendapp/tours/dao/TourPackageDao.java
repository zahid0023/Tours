package com.ghuddy.backendapp.tours.dao;

import com.ghuddy.backendapp.tours.dto.data.TourPackageTypeData;
import com.ghuddy.backendapp.tours.exception.EmptyListException;

import java.util.List;

public interface TourPackageDao {
    List<TourPackageTypeData> getTourPackageTypes(Integer pageSize, Integer pageNumber) throws EmptyListException;
}
