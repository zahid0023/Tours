package com.example.ghuddytour2.tours.entities;

import com.example.ghuddytour2.tours.dto.data.TourDescriptionData;
import com.example.ghuddytour2.tours.dto.request.TourAddRequest;
import com.example.ghuddytour2.tours.utils.StringUtil;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Entity
@Table(name = "tour")
@Getter
@Setter
@NoArgsConstructor
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class TourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tour_name")
    private String tourName;

    @ManyToOne
    @JoinColumn(name = "destination_location_id", referencedColumnName = "id")
    private LocationEntity destinationLocation;

    @Column(name = "number_of_days")
    private Integer numberOfDays;

    @Column(name = "number_of_nights")
    private Integer numberOfNights;

    @Column(name = "short_address")
    private String shortAddress;

    @Column(name = "thumb_image_url")
    private String thumbImageUrl;

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "total_reviews")
    private Integer totalReviews;

    @Column(name = "tour_tag")
    private String tourTag;

    @Type(type = "jsonb")
    @Column(name = "description", columnDefinition = "jsonb")
    private TourDescriptionData description;

    public TourEntity(TourAddRequest tourAddRequest) {
        this.tourName = tourAddRequest.getTourName();
        this.numberOfDays = tourAddRequest.getNumberOfDays();
        this.numberOfNights = tourAddRequest.getNumberOfNights();
        this.shortAddress = tourAddRequest.getShortAddress();
        this.thumbImageUrl = tourAddRequest.getThumbImageUrl();
        this.description = tourAddRequest.getTourDescriptionData();
        this.tourTag = StringUtil.tagify(this.tourName, this.shortAddress);
    }
}

