package com.ghuddy.backendapp.tours.es.serviceImpl;

import com.ghuddy.backendapp.tours.es.dto.data.ESTourPackagePriceCalculationData;
import com.ghuddy.backendapp.tours.es.dto.response.ESComponentCombinationCheckResponse;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourComponentOptionCombinationDocument;
import com.ghuddy.backendapp.tours.es.repository.ESTourComponentOptionCombinationRepository;
import com.ghuddy.backendapp.tours.es.service.ESOptionsCombinationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ESOptionsCombinationsServiceImpl implements ESOptionsCombinationService {
    private ESTourComponentOptionCombinationRepository esTourComponentOptionCombinationRepository;

    public ESOptionsCombinationsServiceImpl(ESTourComponentOptionCombinationRepository esTourComponentOptionCombinationRepository) {
        this.esTourComponentOptionCombinationRepository = esTourComponentOptionCombinationRepository;
    }

    /**
     * @param availableTourPackageId
     * @param requestId
     * @return
     */
    @Override
    public List<ESTourComponentOptionCombinationDocument> getAllComponentCombinationsByAvailableTourPackageId(Long availableTourPackageId, String requestId) {
        return esTourComponentOptionCombinationRepository.findByAvailableTourPackageId(availableTourPackageId);
    }

    /**
     * @param esTourComponentOptionCombinationDocumentList
     * @param requestId
     * @return
     */
    @Override
    public List<Long> getAllComponentCombinationIds(List<ESTourComponentOptionCombinationDocument> esTourComponentOptionCombinationDocumentList, String requestId) {
        return esTourComponentOptionCombinationDocumentList.stream()
                .map(esTourComponentOptionCombinationDocument -> esTourComponentOptionCombinationDocument.getOptionCombinationId())
                .toList();
    }

    /**
     * @param esTourPackagePriceCalculationData
     * @param requestId
     * @return
     */
    @Override
    public ESComponentCombinationCheckResponse getComponentCombinationPrice(ESTourPackagePriceCalculationData esTourPackagePriceCalculationData, String requestId) {
        log.info(esTourPackagePriceCalculationData.toString());
        Optional<ESTourComponentOptionCombinationDocument> existingEsTourComponentOptionCombinationDocument = esTourComponentOptionCombinationRepository.findByAccommodationOptionIdAndFoodOptionIdAndTransferOptionIdAndTransportationOptionIdAndGuideOptionIdAndSpotEntryOptionId(
                esTourPackagePriceCalculationData.getAccommodationOptionId(),
                esTourPackagePriceCalculationData.getFoodOptionId(),
                esTourPackagePriceCalculationData.getTransferOptionId(),
                esTourPackagePriceCalculationData.getTransportationPackageId(),
                esTourPackagePriceCalculationData.getGuideOptionId(),
                esTourPackagePriceCalculationData.getSpotEntryId()
        );
        if (existingEsTourComponentOptionCombinationDocument.isPresent()){
            log.info(existingEsTourComponentOptionCombinationDocument.get().toString());
            return new ESComponentCombinationCheckResponse(existingEsTourComponentOptionCombinationDocument.get().getGhuddyWebsiteBlackPrice(), existingEsTourComponentOptionCombinationDocument.get().getGhuddyWebsiteRedPrice());
        }
        else throw new EntityNotFoundException("Option Not Found");
    }
}
