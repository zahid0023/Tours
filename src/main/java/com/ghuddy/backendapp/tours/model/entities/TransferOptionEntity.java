package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tour_transfer_option")
@Getter
@Setter
@NoArgsConstructor
public class TransferOptionEntity extends BaseEntity {

    @OneToMany(mappedBy = "transferOptionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransferPackageEntity> transferPackageEntities = new ArrayList<>();

    @Column(name = "active")
    private Boolean active;
    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "total_option_price")
    private BigDecimal perPersonTransferOptionPrice;

    @OneToMany(mappedBy = "transferOptionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourPackageOptionEntity> tourPackageOptionEntities = new ArrayList<>();

}
