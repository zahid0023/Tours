package com.ghuddy.backendapp.tours.dao;

import com.ghuddy.backendapp.tours.model.data.accommodation.TourPackageAccommodationData;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourPackageAccommodationTypeData;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourPackageRoomCategoryData;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourPackageRoomTypeData;
import com.ghuddy.backendapp.tours.exception.EmptyListException;

import java.util.List;

public interface AccommodationDao {
    List<TourPackageRoomCategoryData> getTourRoomCategories(Integer pageSize, Integer pageNumber) throws EmptyListException;

    List<TourPackageRoomTypeData> getTourRoomTypes(Integer pageSize, Integer pageNumber) throws EmptyListException;

    List<TourPackageAccommodationTypeData> getTourAccommodationTypes(Integer pageSize, Integer pageNumber) throws EmptyListException;

    List<TourPackageAccommodationData> getTourAccommodations(Integer pageSize, Integer pageNumber) throws EmptyListException;
}
