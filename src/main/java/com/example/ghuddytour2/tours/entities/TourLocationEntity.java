package com.example.ghuddytour2.tours.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "tour_location_mapping")
public class TourLocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "tour_name")
    private String tourName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destination_location_id", nullable = false)
    private LocationEntity destinationLocationEntity;

    @NotNull
    @Column(name = "number_of_days", nullable = false)
    private Integer numberOfDays;

    @NotNull
    @Column(name = "number_of_nights", nullable = false)
    private Integer numberOfNights;

    @Size(max = 255)
    @Column(name = "short_address")
    private String shortAddress;

    @Size(max = 255)
    @Column(name = "tour_tag")
    private String tourTag;

}