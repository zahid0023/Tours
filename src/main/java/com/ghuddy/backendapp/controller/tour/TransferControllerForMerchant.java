package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionAddRequest;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.TourPackageNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
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
//@Api(tags = "Tour - Transfer Controller For Merchant", description = "This controller is used to manager transfer packages by merchants.")
public class TransferControllerForMerchant {
    private final TourPackageService tourPackageService;
    private final TransferService transferService;

    public TransferControllerForMerchant(TourPackageService tourPackageService,
                                         TransferService transferService) {
        this.tourPackageService = tourPackageService;
        this.transferService = transferService;
    }

    @RequestMapping(path = "/transfer/option/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackageTransferPackage(@RequestBody TransferOptionAddRequest transferOptionAddRequest) {
        try {
            TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageEntityByPackageID(transferOptionAddRequest.getTourPackageId());
            return new ResponseEntity<>(transferService.addTourPackageTransferOption(tourPackageEntity, transferOptionAddRequest.getTransferOptionRequest(), transferOptionAddRequest.getRequestId()), HttpStatus.CREATED);
        } catch (TourPackageNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), transferOptionAddRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/transfer/option/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackageTransferPackages(@RequestBody TransferOptionListAddRequest transferOptionListAddRequest) {
        try {
            TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageEntityByPackageID(transferOptionListAddRequest.getTourPackageId());
            return new ResponseEntity<>(transferService.addTourPackageTransferOptions(tourPackageEntity, transferOptionListAddRequest.getTransferOptionRequestList(), transferOptionListAddRequest.getRequestId()), HttpStatus.CREATED);
        } catch (TourPackageNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), transferOptionListAddRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }
}
