package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.model.DestinationLocationEntity;
import com.ghuddy.backendapp.service.DestinationLocationService;
import com.ghuddy.backendapp.tours.dao.TourDAO;
import com.ghuddy.backendapp.tours.dto.data.TourAddData;
import com.ghuddy.backendapp.tours.dto.request.tour.TourAddRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tour.AddedTourResponseList;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.LocationNotFoundException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.data.tour.AddedTourDataOptimized;
import com.ghuddy.backendapp.tours.model.entities.TourLocationEntity;
import com.ghuddy.backendapp.tours.repository.TourLocationRepository;
import com.ghuddy.backendapp.tours.service.TourLocationService;
import com.ghuddy.backendapp.tours.utils.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourLocationServiceImpl implements TourLocationService {
    private final TourLocationRepository tourLocationRepository;
    private final DestinationLocationService destinationLocationService;
    private final TourDAO tourDAO;

    public TourLocationServiceImpl(TourLocationRepository tourLocationRepository,
                                   DestinationLocationService destinationLocationService,
                                   TourDAO tourDAO) {
        this.tourLocationRepository = tourLocationRepository;
        this.destinationLocationService = destinationLocationService;
        this.tourDAO = tourDAO;
    }

    // added tour
    @Override
    public InsertAcknowledgeResponse<AddedTourDataOptimized> addTour(TourAddRequest tourAddRequest) throws LocationNotFoundException {
        DestinationLocationEntity destinationLocationEntity = destinationLocationService.getDestinationLocationEntityById(tourAddRequest.getLocationID());
        TourLocationEntity tourLocationEntity = new TourLocationEntity();
        tourLocationEntity.setTourName(StringUtil.tourName(destinationLocationEntity.getPlaceName(), tourAddRequest.getNumberOfDays(), tourAddRequest.getNumberOfNights()));
        tourLocationEntity.setDestinationLocationEntity(destinationLocationEntity);
        tourLocationEntity.setNumberOfDays(tourAddRequest.getNumberOfDays());
        tourLocationEntity.setNumberOfNights(tourAddRequest.getNumberOfNights());
        tourLocationEntity.setShortAddress(tourAddRequest.getShortAddress());
        tourLocationEntity.setTourTag(StringUtil.slugify(tourLocationEntity.getTourName(), tourAddRequest.getShortAddress()));
        tourLocationEntity = tourLocationRepository.save(tourLocationEntity); // tour is added!!
        return new InsertAcknowledgeResponse<>(new AddedTourDataOptimized(tourLocationEntity), tourAddRequest.getRequestId());
    }

    /**
     * @param addedTourEntityId the id of the added tour
     * @return TourLocationEntity
     * @throws TourNotFoundException when the added tour entity is not found
     */
    @Override
    public TourLocationEntity getAddedTourEntityById(Long addedTourEntityId) throws TourNotFoundException {
        return tourLocationRepository.findById(addedTourEntityId).orElseThrow(() -> new TourNotFoundException(ErrorCode.TOUR_NOT_FOUND));
    }

    /**
     * @param addedTourId the id of the added tour
     * @return AddedTourOptimized
     * @throws TourNotFoundException when the added tour by this id does not exist
     */
    @Override
    public AddedTourDataOptimized getAddedTourByAddedTourId(Long addedTourId) throws TourNotFoundException {
        TourLocationEntity tourLocationEntity = getAddedTourEntityById(addedTourId);
        return new AddedTourDataOptimized(tourLocationEntity);
    }

    @Override
    public AddedTourResponseList getAllAddedTours(String requestId) throws EmptyListException {
        List<TourAddData> tourAddDataList = tourDAO.getAllTours(0, 0);
        return new AddedTourResponseList(tourAddDataList, requestId);
    }

    @Override
    public AddedTourResponseList getAllAddedToursPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException {
        List<TourAddData> tourAddDataList = tourDAO.getAllTours(pageSize, pageNumber);
        return new AddedTourResponseList(tourAddDataList, requestId);
    }
}
