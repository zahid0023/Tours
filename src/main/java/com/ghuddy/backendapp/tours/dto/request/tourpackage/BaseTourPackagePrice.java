package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import com.ghuddy.backendapp.tours.dto.request.guide.GuidePackageRequest;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferPackageRequest;
import lombok.Data;

@Data
public class BaseTourPackagePrice {
    private TransferPackageRequest transferPackageRequest;
    private GuidePackageRequest guidePackageRequest;
}
