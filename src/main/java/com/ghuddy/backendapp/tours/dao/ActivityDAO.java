package com.ghuddy.backendapp.tours.dao;

import com.ghuddy.backendapp.tours.dto.data.ActivityData;
import com.ghuddy.backendapp.tours.dto.data.ActivityTypeData;
import com.ghuddy.backendapp.tours.exception.EmptyListException;

import java.util.List;

public interface ActivityDAO {
    List<ActivityTypeData> getAllActivityTypes(Integer pageSize, Integer pageNumber) throws EmptyListException;

    List<ActivityData> getAllActivities(Integer pageSize, Integer pageNumber) throws EmptyListException;
}
