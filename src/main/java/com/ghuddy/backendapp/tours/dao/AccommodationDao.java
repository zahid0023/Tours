package com.ghuddy.backendapp.tours.dao;

import com.ghuddy.backendapp.tours.model.data.accommodation.TourAccommodationData;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourAccommodationTypeData;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourRoomCategoryData;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourRoomTypeData;
import com.ghuddy.backendapp.tours.exception.EmptyListException;

import java.util.List;

public interface AccommodationDao {
    List<TourRoomCategoryData> getTourRoomCategories(Integer pageSize, Integer pageNumber) throws EmptyListException;

    List<TourRoomTypeData> getTourRoomTypes(Integer pageSize, Integer pageNumber) throws EmptyListException;

    List<TourAccommodationTypeData> getTourAccommodationTypes(Integer pageSize, Integer pageNumber) throws EmptyListException;

    List<TourAccommodationData> getTourAccommodations(Integer pageSize, Integer pageNumber) throws EmptyListException;
}
