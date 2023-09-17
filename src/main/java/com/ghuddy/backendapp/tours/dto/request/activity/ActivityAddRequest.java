package com.ghuddy.backendapp.tours.dto.request.activity;

import com.ghuddy.backendapp.tours.entities.ActivityEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ActivityEntity} entity
 */
@Data
public class ActivityAddRequest implements Serializable {
    private ActivityRequest activity;

}