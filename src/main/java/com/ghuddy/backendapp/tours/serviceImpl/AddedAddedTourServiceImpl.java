package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.model.DestinationLocationEntity;
import com.ghuddy.backendapp.service.DestinationLocationService;
import com.ghuddy.backendapp.tours.dao.TourDAO;
import com.ghuddy.backendapp.tours.model.data.tour.AddedTourData;
import com.ghuddy.backendapp.tours.dto.request.tour.TourAddRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tour.AddedTourResponseList;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.LocationNotFoundException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.data.tour.AddedTourDataOptimized;
import com.ghuddy.backendapp.tours.model.entities.AddedTourEntity;
import com.ghuddy.backendapp.tours.repository.AddedTourRespository;
import com.ghuddy.backendapp.tours.service.AddedTourService;
import com.ghuddy.backendapp.tours.utils.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddedAddedTourServiceImpl implements AddedTourService {
    private final AddedTourRespository addedTourRespository;
    private final DestinationLocationService destinationLocationService;
    private final TourDAO tourDAO;

    public AddedAddedTourServiceImpl(AddedTourRespository addedTourRespository,
                                     DestinationLocationService destinationLocationService,
                                     TourDAO tourDAO) {
        this.addedTourRespository = addedTourRespository;
        this.destinationLocationService = destinationLocationService;
        this.tourDAO = tourDAO;
    }

    // added tour
    @Override
    public InsertAcknowledgeResponse<AddedTourDataOptimized> addTour(TourAddRequest tourAddRequest) throws LocationNotFoundException {
        DestinationLocationEntity destinationLocationEntity = destinationLocationService.getDestinationLocationEntityById(tourAddRequest.getLocationID());
        AddedTourEntity addedTourEntity = new AddedTourEntity();
        addedTourEntity.setTourName(StringUtil.tourName(destinationLocationEntity.getPlaceName(), tourAddRequest.getNumberOfDays(), tourAddRequest.getNumberOfNights()));
        addedTourEntity.setDestinationLocationEntity(destinationLocationEntity);
        addedTourEntity.setNumberOfDays(tourAddRequest.getNumberOfDays());
        addedTourEntity.setNumberOfNights(tourAddRequest.getNumberOfNights());
        addedTourEntity.setShortAddress(tourAddRequest.getShortAddress());
        addedTourEntity.setTourTag(StringUtil.slugify(addedTourEntity.getTourName(), tourAddRequest.getShortAddress()));
        addedTourEntity = addedTourRespository.save(addedTourEntity); // tour is added!!
        return new InsertAcknowledgeResponse<>(new AddedTourDataOptimized(addedTourEntity), tourAddRequest.getRequestId());
    }

    /**
     * @param addedTourEntityId the id of the added tour
     * @return TourLocationEntity
     * @throws TourNotFoundException when the added tour entity is not found
     */
    @Override
    public AddedTourEntity getAddedTourEntityById(Long addedTourEntityId) throws TourNotFoundException {
        return addedTourRespository.findById(addedTourEntityId).orElseThrow(() -> new TourNotFoundException(ErrorCode.TOUR_NOT_FOUND));
    }

    /**
     * @param addedTourId the id of the added tour
     * @return AddedTourOptimized
     * @throws TourNotFoundException when the added tour by this id does not exist
     */
    @Override
    public AddedTourDataOptimized getAddedTourByAddedTourId(Long addedTourId) throws TourNotFoundException {
        AddedTourEntity addedTourEntity = getAddedTourEntityById(addedTourId);
        return new AddedTourDataOptimized(addedTourEntity);
    }

    @Override
    public AddedTourResponseList getAllAddedTours(String requestId) throws EmptyListException {
        List<AddedTourData> addedTourDataList = tourDAO.getAllAddedTours(0, 0);
        return new AddedTourResponseList(addedTourDataList, requestId);
    }

    @Override
    public AddedTourResponseList getAllAddedToursPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException {
        List<AddedTourData> addedTourDataList = tourDAO.getAllAddedTours(pageSize, pageNumber);
        return new AddedTourResponseList(addedTourDataList, requestId);
    }
}
