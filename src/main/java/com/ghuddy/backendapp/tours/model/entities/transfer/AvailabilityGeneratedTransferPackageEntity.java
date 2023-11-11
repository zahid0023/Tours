package com.ghuddy.backendapp.tours.model.entities.transfer;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "availability_generated_transfer_packages")
public class AvailabilityGeneratedTransferPackageEntity extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transfer_package_id")
    private TransferPackageEntity transferPackageEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "availability_generated_transfer_option_id")
    private AvailabilityGeneratedTransferOptionEntity availabilityGeneratedTransferOptionEntity;
    @NotNull
    @Column(name = "per_vehicle_per_trip_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal perVehiclePerTripPrice;
}