package com.ghuddy.backendapp.tours.dao;

import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.accommodation.AccommodationData;
import com.ghuddy.backendapp.tours.model.data.accommodation.AccommodationTypeData;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourPackageRoomCategoryData;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourPackageRoomTypeData;

import java.util.List;

public interface AccommodationDao {
    List<TourPackageRoomCategoryData> getTourRoomCategories(Integer pageSize, Integer pageNumber) throws EmptyListException;

    List<TourPackageRoomTypeData> getTourRoomTypes(Integer pageSize, Integer pageNumber) throws EmptyListException;

    List<AccommodationTypeData> getTourAccommodationTypes(Integer pageSize, Integer pageNumber) throws EmptyListException;

    List<AccommodationData> getTourAccommodations(Integer pageSize, Integer pageNumber) throws EmptyListException;
}
