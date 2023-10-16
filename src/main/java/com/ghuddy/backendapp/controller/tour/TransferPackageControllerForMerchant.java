package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionAddRequest;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionListAddRequest;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import com.ghuddy.backendapp.tours.service.TourPackageService;
import com.ghuddy.backendapp.tours.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/merchant")
public class TransferPackageControllerForMerchant {
    private final TourPackageService tourPackageService;
    private final TransferService transferService;

    public TransferPackageControllerForMerchant(TourPackageService tourPackageService,
                                                TransferService transferService) {
        this.tourPackageService = tourPackageService;
        this.transferService = transferService;
    }

    @RequestMapping(path = "/transfer/package/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackageTransferPackage(@RequestBody TransferOptionAddRequest transferOptionAddRequest) {
        TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageEntityByPackageID(transferOptionAddRequest.getTourPackageId());
        return new ResponseEntity<>(transferService.addTourPackageTransferOption(tourPackageEntity, transferOptionAddRequest.getTransferOptionRequest(), transferOptionAddRequest.getRequestId()), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/transfer/packages/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackageTransferPackages(@RequestBody TransferOptionListAddRequest transferOptionListAddRequest) {
        TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageEntityByPackageID(transferOptionListAddRequest.getTourPackageId());
        return new ResponseEntity<>(transferService.addTourPackageTransferOptions(tourPackageEntity, transferOptionListAddRequest.getTransferOptionRequestList(), transferOptionListAddRequest.getRequestId()), HttpStatus.CREATED);
    }
}
