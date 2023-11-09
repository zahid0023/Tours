package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "activity")
public class ActivityEntity extends BaseEntity {

    @Column(name = "activity_name", nullable = false)
    private String activityName;

    @Column(name = "short_location", nullable = false)
    private String shortLocation;

    @Column(name = "average_rating", precision = 2, scale = 1)
    private BigDecimal averageRating;

    @Column(name = "number_of_reviews")
    private Integer numberOfReviews;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_type_id")
    private ActivityTypeEntity activityTypeEntity;

    @OneToMany(mappedBy = "activityEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActivityImageEntity> activityImageEntities = new ArrayList<>();

    @OneToMany(mappedBy = "activityEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SpotEntryEntity> spotEntryEntities = new ArrayList<>();

}