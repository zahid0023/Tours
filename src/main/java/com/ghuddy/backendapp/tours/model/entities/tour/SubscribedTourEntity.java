package com.ghuddy.backendapp.tours.model.entities.tour;

import com.ghuddy.backendapp.model.UserEntity;
import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "subscribed_tours")
public class SubscribedTourEntity extends BaseEntity {

    @Column(name = "active")
    private Boolean active = false;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tour_id", nullable = false)
    private TourEntity tourEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
    private UserEntity merchantEntity;

    @NotNull
    @Column(name = "tour_reporting_time", nullable = false)
    private LocalTime tourReportingTime;

    @Size(max = 100)
    @NotNull
    @Column(name = "tour_reporting_place", nullable = false, length = 100)
    private String tourReportingPlace;

    @OneToMany(mappedBy = "subscribedTourEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubscribedTourItineraryEntity> subscribedTourItineraryEntities = new ArrayList<>();

    @OneToMany(mappedBy = "subscribedTourEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourPackageEntity> tourPackageEntities = new LinkedList<>();

    @NotNull
    @Column(name = "number_of_reviews", nullable = false)
    private Integer numberOfReviews = 0;

    @NotNull
    @Column(name = "rating_in_stars", nullable = false)
    private Double ratingInStars = 0.0;

}