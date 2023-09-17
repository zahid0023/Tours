package com.ghuddy.backendapp.tours.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "activity_type")
public class ActivityTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "activity_type_name", nullable = false)
    private String activityTypeName;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "activityTypeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActivityEntity> activities = new ArrayList<>();

}