package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.enums.AvailabilityPricePolicyTypeEnum;
import com.ghuddy.backendapp.tours.dao.TourPackageOptionCapacityPriceDAO;
import com.ghuddy.backendapp.tours.dto.data.TourPackageOptionCapacityPriceFilterData;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageOptionCapacityPriceFilterAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageOptionCapacityPriceSetRequest;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageOptionCapacityPriceDailyDataListResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageOptionCapacityPriceFilterListResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageOptionCapacityPriceFilterResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageOptionCapacityPriceResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.TourPackageOptionCapacityPriceNotFoundException;
import com.ghuddy.backendapp.tours.exception.TourPackageOptionNotFoundException;
import com.ghuddy.backendapp.tours.model.data.availability.TourPackageOptionCapacityPriceData;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageOptionCapacityPriceDailyData;
import com.ghuddy.backendapp.tours.model.entities.TourPackageOptionCapacityPriceEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageOptionCapacityPriceFilterEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageOptionEntity;
import com.ghuddy.backendapp.tours.repository.TourPackageOptionCapacityPriceFilterRepository;
import com.ghuddy.backendapp.tours.repository.TourPackageOptionCapacityPriceRepository;
import com.ghuddy.backendapp.tours.repository.TourPackageOptionRepository;
import com.ghuddy.backendapp.tours.service.TourPackageOptionCapacityPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TourPackageOptionCapacityPriceServiceImpl implements TourPackageOptionCapacityPriceService {

    private final TourPackageOptionCapacityPriceRepository tourPackageOptionCapacityPriceRepository;

    private final TourPackageOptionRepository tourPackageOptionRepository;

    private final TourPackageOptionCapacityPriceFilterRepository tourPackageOptionCapacityPriceFilterRepository;

    private final TourPackageOptionCapacityPriceDAO tourPackageOptionCapacityPriceDAO;

    TourPackageOptionCapacityPriceServiceImpl(TourPackageOptionCapacityPriceRepository tourPackageOptionCapacityPriceRepository,
                                              TourPackageOptionRepository tourPackageOptionRepository,
                                              TourPackageOptionCapacityPriceFilterRepository tourPackageOptionCapacityPriceFilterRepository,
                                              TourPackageOptionCapacityPriceDAO tourPackageOptionCapacityPriceDAO) {
        this.tourPackageOptionCapacityPriceRepository = tourPackageOptionCapacityPriceRepository;
        this.tourPackageOptionRepository = tourPackageOptionRepository;
        this.tourPackageOptionCapacityPriceFilterRepository = tourPackageOptionCapacityPriceFilterRepository;
        this.tourPackageOptionCapacityPriceDAO = tourPackageOptionCapacityPriceDAO;
    }


    @Override
    @Transactional
    // 1. set price first
    public TourPackageOptionCapacityPriceResponse setCapacityPrice(TourPackageOptionCapacityPriceSetRequest request) throws TourPackageOptionNotFoundException {
        TourPackageOptionEntity tourPackageOptionEntity = tourPackageOptionRepository.findByIdAndDeleted(request.getTourPackageOptionId(), false);

        if (tourPackageOptionEntity == null)
            throw new TourPackageOptionNotFoundException(ErrorCode.TOUR_PACKAGE_OPTION_DOES_NOT_EXIST);

        TourPackageOptionCapacityPriceEntity tourPackageOptionCapacityPriceEntity = tourPackageOptionEntity.getTourPackageOptionCapacityPriceEntity();

        if (tourPackageOptionCapacityPriceEntity == null)
            tourPackageOptionCapacityPriceEntity = new TourPackageOptionCapacityPriceEntity();
        else {
            tourPackageOptionCapacityPriceEntity = tourPackageOptionEntity.getTourPackageOptionCapacityPriceEntity();
        }

        BigDecimal rackRate = request.getRackPrice();
        BigDecimal corporateRate = request.getCorporatePrice();

        BigDecimal corporateDiscountPercent =
                new BigDecimal(100).multiply(rackRate.subtract(corporateRate)).divide(rackRate, 2, RoundingMode.HALF_UP);

        BigDecimal ghuddyCommissionPercent = request.getGhuddyCommissionPercent();
        BigDecimal shurjoCommissionPercent = request.getShurjoCommissionPercent();

        BigDecimal ghuddyWebSiteBlackPrice = corporateRate
                .multiply(BigDecimal.ONE.add(shurjoCommissionPercent.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP)));

        BigDecimal shurjoCommissionAmount =
                ghuddyWebSiteBlackPrice.multiply(shurjoCommissionPercent.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP));
        BigDecimal ghuddyCommissionAmount =
                ghuddyWebSiteBlackPrice.subtract(corporateRate).subtract(shurjoCommissionAmount);

        BigDecimal ghuddyWebSiteRedPrice = request.getGhuddyWebsiteRedPrice();

        BigDecimal ghuddySubsidyAmount = ghuddyWebSiteBlackPrice.subtract(ghuddyWebSiteRedPrice);
        BigDecimal ghuddySubsidyPercent =
                new BigDecimal(100).multiply(ghuddySubsidyAmount).divide(ghuddyWebSiteBlackPrice, 2, RoundingMode.HALF_UP);

        BigDecimal totalDiscountPercent =
                new BigDecimal(100).multiply(rackRate.subtract(ghuddyWebSiteRedPrice)).divide(rackRate, 2, RoundingMode.HALF_UP);

        BigDecimal totalShurjoCommissionAmount =
                ghuddyWebSiteRedPrice.multiply(shurjoCommissionPercent.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP));
        BigDecimal totalGhuddyCommissionAmount =
                ghuddyWebSiteRedPrice.subtract(corporateRate).subtract(totalShurjoCommissionAmount);
        BigDecimal totalGhuddyCommissionPercent =
                new BigDecimal(100).multiply(totalGhuddyCommissionAmount).divide(corporateRate, 2, RoundingMode.HALF_UP);

        Long totalSeats = request.getTotalSeats();
        Long bookableSeats = request.getBookableSeats();
        tourPackageOptionCapacityPriceEntity.setRackRate(rackRate);
        tourPackageOptionCapacityPriceEntity.setCorporateRate(corporateRate);
        tourPackageOptionCapacityPriceEntity.setCorporateDiscountPercent(corporateDiscountPercent);
        tourPackageOptionCapacityPriceEntity.setGhuddyCommissionPercent(ghuddyCommissionPercent);
        tourPackageOptionCapacityPriceEntity.setShurjoCommissionPercent(shurjoCommissionPercent);
        tourPackageOptionCapacityPriceEntity.setGhuddyWebSiteBlackPrice(ghuddyWebSiteBlackPrice);
        tourPackageOptionCapacityPriceEntity.setGhuddySubsidyAmount(ghuddySubsidyAmount);
        tourPackageOptionCapacityPriceEntity.setGhuddySubsidyPercent(ghuddySubsidyPercent);
        tourPackageOptionCapacityPriceEntity.setGhuddyWebsiteRedPrice(ghuddyWebSiteRedPrice);
        tourPackageOptionCapacityPriceEntity.setShurjoCommissionAmount(shurjoCommissionAmount);
        tourPackageOptionCapacityPriceEntity.setGhuddyCommissionAmount(ghuddyCommissionAmount);
        tourPackageOptionCapacityPriceEntity.setTotalShurjoCommissionAmount(totalShurjoCommissionAmount);
        tourPackageOptionCapacityPriceEntity.setTotalGhuddyCommissionAmount(totalGhuddyCommissionAmount);
        tourPackageOptionCapacityPriceEntity.setTotalGhuddyCommissionPercent(totalGhuddyCommissionPercent);
        tourPackageOptionCapacityPriceEntity.setTotalDiscountPercent(totalDiscountPercent);
        tourPackageOptionCapacityPriceEntity.setTotalSeats(totalSeats);
        tourPackageOptionCapacityPriceEntity.setBookableSeats(bookableSeats);

        tourPackageOptionCapacityPriceEntity.setTourPackageOptionEntity(tourPackageOptionEntity);

        if (tourPackageOptionCapacityPriceEntity.getCapacityPriceFilters() == null)
            tourPackageOptionCapacityPriceEntity.setCapacityPriceFilters(new ArrayList<>());

        tourPackageOptionEntity.setTourPackageOptionCapacityPriceEntity(tourPackageOptionCapacityPriceEntity);
        tourPackageOptionEntity.setCapacityPriceGenerated(true);
        tourPackageOptionEntity = tourPackageOptionRepository.save(tourPackageOptionEntity);

        return new TourPackageOptionCapacityPriceResponse(new TourPackageOptionCapacityPriceData(tourPackageOptionEntity.getTourPackageOptionCapacityPriceEntity()), request.getRequestId());
    }


    @Override
    @Transactional
    // 2. add filter
    public TourPackageOptionCapacityPriceFilterResponse addCapacityPriceFilter(TourPackageOptionCapacityPriceFilterAddRequest request) throws TourPackageOptionCapacityPriceNotFoundException {

        TourPackageOptionCapacityPriceEntity tourPackageOptionCapacityPriceEntity = tourPackageOptionCapacityPriceRepository.findByIdAndDeleted(request.getCapacityPriceId(), false);

        if (tourPackageOptionCapacityPriceEntity == null)
            throw new TourPackageOptionCapacityPriceNotFoundException(ErrorCode.TOUR_PACKAGE_OPTION_CAPACITY_DOES_NOT_EXIST);
        TourPackageOptionCapacityPriceFilterEntity filter = tourPackageOptionCapacityPriceFilterRepository.findByTourDateAndPolicyTypeAndCapacityPriceAndDeleted(
                request.getTourDate(),
                request.getPolicyType(),
                tourPackageOptionCapacityPriceEntity, false);

        if (filter == null)
            filter = new TourPackageOptionCapacityPriceFilterEntity();
        filter.setPolicyName(request.getPolicyName());
        filter.setTourDate(request.getTourDate());
        filter.setCapacityPrice(tourPackageOptionCapacityPriceEntity);
        filter.setPolicyType(AvailabilityPricePolicyTypeEnum.valueOf(request.getPolicyType().name()));
        filter.setDeltaRackRate(request.getDeltaRackPrice());
        filter.setDeltaCorporateRate(request.getDeltaCorporatePrice());
        filter.setDeltaCorporateDiscountPercentPoint(request.getDeltaCorporateDiscountPercentPoint());
        filter.setDeltaGhuddyCommissionPercentPoint(request.getDeltaGhuddyCommissionPercentPoint());
        filter.setDeltaGhuddyCommissionAmount(request.getDeltaGhuddyCommissionAmount());
        filter.setDeltaShurjoCommissionPercentPoint(request.getDeltaShurjoCommissionPercentPoint());
        filter.setDeltaShurjoCommissionAmount(request.getDeltaShurjoCommissionAmount());
        filter.setDeltaTotalGhuddyCommissionAmount(request.getDeltaTotalGhuddyCommissionAmount());
        filter.setDeltaTotalGhuddyCommissionPercentPoint(request.getDeltaTotalGhuddyCommissionPercentPoint());
        filter.setDeltaTotalShurjoCommissionAmount(request.getDeltaTotalShurjoCommissionAmount());
        filter.setDeltaGhuddyWebSiteBlackPrice(request.getDeltaGhuddyWebSiteBlackPrice());
        filter.setDeltaGhuddySubsidyAmount(request.getDeltaGhuddySubsidyAmount());
        filter.setDeltaGhuddySubsidyPercentPoint(request.getDeltaGhuddySubsidyPercentPoint());
        filter.setDeltaGhuddyWebSiteRedPrice(request.getDeltaGhuddyWebSiteRedPrice());
        filter.setDeltaTotalSeats(request.getDeltaTotalSeats());
        filter.setDeltaBookableSeats(request.getDeltaBookableSeats());
        filter.setDeltaTotalDiscountPercentPoint(request.getDeltaTotalDiscountPercentPoint());

        filter = tourPackageOptionCapacityPriceFilterRepository.save(filter);
        tourPackageOptionCapacityPriceEntity.getCapacityPriceFilters().add(filter);
        tourPackageOptionCapacityPriceEntity = tourPackageOptionCapacityPriceRepository.save(tourPackageOptionCapacityPriceEntity);

        return new TourPackageOptionCapacityPriceFilterResponse(filter, request.getRequestId());
    }

    @Override
    @Transactional
    // 3. populate
    public AcknowledgeResponse generateAvailabilityCapacityPrice(Long packageOptionId, String requestId) throws TourPackageOptionCapacityPriceNotFoundException {
        log.info(tourPackageOptionCapacityPriceDAO.updateTourPackageOptionCapacityPriceDaily(packageOptionId)+"");
        boolean processed = tourPackageOptionCapacityPriceDAO.updateTourPackageOptionCapacityPriceDaily(packageOptionId) > 0;
        if (processed)
            return new AcknowledgeResponse();
        else return null;

    }

    public TourPackageOptionCapacityPriceDailyDataListResponse getTourPackageOptionCapacityPriceDailyList(
            long packageOptionId,
            LocalDate startDate,
            LocalDate endDate,
            String requestId) throws TourPackageOptionCapacityPriceNotFoundException {
        List<TourPackageOptionCapacityPriceDailyData> tourPackageOptionCapacityPriceDailyDataList = tourPackageOptionCapacityPriceDAO.getRoomCategoryAvailabilityPriceDailyList(packageOptionId, startDate, endDate);

        TourPackageOptionCapacityPriceDailyDataListResponse tourPackageOptionCapacityPriceDailyDataListResponse = new TourPackageOptionCapacityPriceDailyDataListResponse(
                tourPackageOptionCapacityPriceDailyDataList,
                requestId
        );
        return tourPackageOptionCapacityPriceDailyDataListResponse;

    }


    public TourPackageOptionCapacityPriceFilterListResponse getOptionCapacityPriceFilterList(
            long packageOptionId,
            LocalDate startDate,
            LocalDate endDate,
            String requestId) throws TourPackageOptionNotFoundException {
        TourPackageOptionEntity tourPackageOptionEntity = tourPackageOptionRepository.findByIdAndDeleted(packageOptionId, false);

        if (tourPackageOptionEntity == null)
            throw new TourPackageOptionNotFoundException(ErrorCode.TOUR_PACKAGE_OPTION_DOES_NOT_EXIST);
        List<TourPackageOptionCapacityPriceFilterEntity> filterEntityList = tourPackageOptionCapacityPriceFilterRepository.findAllByCapacityPriceAndTourDateAfterAndTourDateBeforeAndDeleted(
                tourPackageOptionEntity.getTourPackageOptionCapacityPriceEntity(),
                startDate,
                endDate,
                false
        );
        List<TourPackageOptionCapacityPriceFilterData> capacityPriceList = new ArrayList<>();
        for (TourPackageOptionCapacityPriceFilterEntity entity : filterEntityList) {
            capacityPriceList.add(new TourPackageOptionCapacityPriceFilterData(entity));
        }

        return new TourPackageOptionCapacityPriceFilterListResponse(capacityPriceList, requestId);

    }
}
