package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.transporation.*;
import com.ghuddy.backendapp.tours.service.TransportationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/")
//@Api(tags = "Tour - Tour Transportation Controller For Admin", description = "This controller is used to manage tour transportations by admins.")
public class TransportationControllerForAdmin {
    private final TransportationService transportationService;

    public TransportationControllerForAdmin(TransportationService transportationService) {
        this.transportationService = transportationService;
    }

    // transportation brand
    @RequestMapping(path = "/admin/transportation/brand/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTransportationBrand(@RequestBody TransportationBrandAddRequest transportationBrandAddRequest) {
        return new ResponseEntity<>(transportationService.addTransportationBrand(transportationBrandAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/admin/transportation/brand/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTransportationBrands(@RequestBody TransportationBrandListAddRequest transportationBrandListAddRequest) {
        return new ResponseEntity<>(transportationService.addTransportationBrands(transportationBrandListAddRequest), HttpStatus.CREATED);
    }

    // transportation mode
    @RequestMapping(path = "/admin/transportation/mode/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTransportationMode(@RequestBody TransportationModeAddRequest transportationModeAddRequest) {
        return new ResponseEntity<>(transportationService.addTransportationMode(transportationModeAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/admin/transportation/mode/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTransportationModes(@RequestBody TransportationModeListAddRequest transportationModeListAddRequest) {
        return new ResponseEntity<>(transportationService.addTransportationModes(transportationModeListAddRequest), HttpStatus.CREATED);
    }

    // transportation providers
    @RequestMapping(path = "/admin/transportation/provider/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTransportationProvider(@RequestBody TransportationProviderAddRequest transportationProviderAddRequest) {
        return new ResponseEntity<>(transportationService.addTransportationProvider(transportationProviderAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/admin/transportation/provider/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTransportationProviders(@RequestBody TransportationProviderListAddRequest transportationProviderListAddRequest) {
        return new ResponseEntity<>(transportationService.addTransportationProviders(transportationProviderListAddRequest), HttpStatus.CREATED);
    }

    // transportation routes
    @RequestMapping(path = "/admin/transportation/route/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTransportationRoute(@RequestBody TransportationRouteAddRequest transportationRouteAddRequest) {
        return new ResponseEntity<>(transportationService.addTransportationRoute(transportationRouteAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/admin/transportation/route/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTransportationRoutes(@RequestBody TransportationRouteListAddRequest transportationRouteListAddRequest) {
        return new ResponseEntity<>(transportationService.addTransportationRoutes(transportationRouteListAddRequest), HttpStatus.CREATED);
    }
}
