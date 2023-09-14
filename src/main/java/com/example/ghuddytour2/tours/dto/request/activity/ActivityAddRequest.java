package com.example.ghuddytour2.tours.dto.request.activity;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.ghuddytour2.tours.entities.ActivityEntity} entity
 */
@Data
public class ActivityAddRequest implements Serializable {
    private ActivityRequest activity;

}