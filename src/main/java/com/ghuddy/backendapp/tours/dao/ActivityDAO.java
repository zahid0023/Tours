package com.ghuddy.backendapp.tours.dao;

import com.ghuddy.backendapp.tours.dto.response.ActivityResponseList;
import com.ghuddy.backendapp.tours.dto.response.ActivityTypeResponseList;
import com.ghuddy.backendapp.tours.exception.EmptyListException;

public interface ActivityDAO {
    ActivityTypeResponseList getAllActivityTypes() throws EmptyListException;
    ActivityResponseList getAllActivities() throws EmptyListException;
}
