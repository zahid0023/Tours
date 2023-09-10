package com.example.ghuddytour2.tours.dao;

import com.example.ghuddytour2.tours.dto.response.ActivityResponseList;
import com.example.ghuddytour2.tours.dto.response.ActivityTypeResponseList;
import com.example.ghuddytour2.tours.exception.EmptyListException;

public interface ActivityDAO {
    ActivityTypeResponseList getAllActivityTypes() throws EmptyListException;
    ActivityResponseList getAllActivities() throws EmptyListException;
}
