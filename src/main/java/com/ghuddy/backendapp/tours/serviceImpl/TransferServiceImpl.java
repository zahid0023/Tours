package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferPackageRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.data.transfer.TransferOptionData;
import com.ghuddy.backendapp.tours.model.data.transfer.TransferPackageData;
import com.ghuddy.backendapp.tours.model.entities.*;
import com.ghuddy.backendapp.tours.repository.TransferOptionRepository;
import com.ghuddy.backendapp.tours.repository.TransferPackageRepository;
import com.ghuddy.backendapp.tours.service.TourPackagePriceService;
import com.ghuddy.backendapp.tours.service.TransferService;
import com.ghuddy.backendapp.tours.service.TransportationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransferServiceImpl implements TransferService {
    private final TransportationService transportationService;
    private final TransferPackageRepository transferPackageRepository;
    private final TourPackagePriceService tourPackagePriceService;
    private final TransferOptionRepository transferOptionRepository;

    public TransferServiceImpl(TransportationService transportationService,
                               TransferPackageRepository transferPackageRepository,
                               TourPackagePriceService tourPackagePriceService,
                               TransferOptionRepository transferOptionRepository) {
        this.transportationService = transportationService;
        this.transferPackageRepository = transferPackageRepository;
        this.tourPackagePriceService = tourPackagePriceService;
        this.transferOptionRepository = transferOptionRepository;
    }

    /**
     * @param tourPackageEntity
     * @param transferOptionRequest
     * @return
     */
    @Override
    public InsertAcknowledgeResponse addTourPackageTransferOption(TourPackageEntity tourPackageEntity, TransferOptionRequest transferOptionRequest, String requestId) {
        TransferOptionEntity transferOptionEntity = setTourPackageTransferOptions(tourPackageEntity, List.of(transferOptionRequest)).get(0);
        transferOptionEntity = transferOptionRepository.save(transferOptionEntity);
        TransferOptionData transferOptionData = new TransferOptionData(transferOptionEntity);
        return new InsertAcknowledgeResponse<>(transferOptionData, requestId);
    }

    /**
     * @param tourPackageEntity
     * @param transferOptions
     * @return
     */
    @Override
    public InsertAcknowledgeListResponse addTourPackageTransferOptions(TourPackageEntity tourPackageEntity, List<TransferOptionRequest> transferOptions, String requestId) {
        List<TransferOptionEntity> transferOptionEntities = setTourPackageTransferOptions(tourPackageEntity, transferOptions);
        transferOptionEntities = transferOptionRepository.saveAll(transferOptionEntities);
        List<TransferOptionData> transferOptionDataList = transferOptionEntities.stream()
                .map(transferOptionEntity -> new TransferOptionData(transferOptionEntity))
                .collect(Collectors.toList());

        return new InsertAcknowledgeListResponse<>(transferOptionDataList, requestId);
    }

    /**
     * @param tourPackageEntity
     * @param transferOptions
     * @return
     */
    @Override
    public List<TransferOptionEntity> setTourPackageTransferOptions(TourPackageEntity tourPackageEntity, List<TransferOptionRequest> transferOptions) {
        Map<String, Set<Long>> idMaps = new HashMap<>();
        transferOptions.forEach(transferOptionRequest -> {
            transferOptionRequest.getTransferPackageRequestList().forEach(transferPackageRequest -> {
                idMaps.computeIfAbsent("mode", key -> new HashSet<>())
                        .add(transferPackageRequest.getTransferModeId());
                idMaps.computeIfAbsent("provider", key -> new HashSet<>())
                        .add(transferPackageRequest.getTransferProviderId());
            });
        });

        Map<Long, TransportationModeEntity> transportationModeEntityMap = transportationService.getTransportationModeEntitiesByIDs(idMaps.get("mode"));
        Map<Long, TransportationProviderEntity> transportationProviderEntityMap = transportationService.getTransportationProviderEntitiesByIDs(idMaps.get("provider"));

        List<TransferOptionEntity> transferOptionEntities = transferOptions.stream()
                .map(transferOptionRequest -> {
                    TransferOptionEntity transferOptionEntity = new TransferOptionEntity();
                    transferOptionEntity.setTourPackageEntity(tourPackageEntity);
                    List<TransferPackageEntity> transferPackageEntityList = transferOptionRequest.getTransferPackageRequestList().stream()
                            .map(transferPackageRequest -> {
                                TransferPackageEntity transferPackageEntity = new TransferPackageEntity();
                                transferPackageEntity.setTransferOptionEntity(transferOptionEntity);
                                transferPackageEntity.setTransportationModeEntity(transportationModeEntityMap.get(transferPackageRequest.getTransferModeId()));
                                transferPackageEntity.setTransportationProviderEntity(transportationProviderEntityMap.get(transferPackageRequest.getTransferProviderId()));
                                transferPackageEntity.setIsAc(transferPackageRequest.getIsAc());
                                transferPackageEntity.setSuitableForPersons(transferPackageRequest.getSuitableForPersons());
                                transferPackageEntity.setUnitPrice(transferPackageRequest.getTransferUnitPrice());
                                //transferPackageEntity.setDayNumbers(transferPackageRequest.getDayNumbers());
                                transferPackageEntity.setNumberOfVehicles(transferPackageRequest.getNumberOfVehicles());
                                transferPackageEntity.setPerPersonTransferPackagePrice(new BigDecimal(5));
                                transferPackageEntity.setTransferRoute(transferPackageRequest.getTransferRoute());
                                transferPackageEntity.setTripType(transferPackageRequest.getTripType());
                                return transferPackageEntity;
                            })
                            .collect(Collectors.toList());
                    transferOptionEntity.setTransferPackageEntities(transferPackageEntityList);
                    transferOptionEntity.setIsDefault(transferOptionRequest.getIsDefault());
                    return transferOptionEntity;

                })
                .collect(Collectors.toList());

        return transferOptionEntities;
    }
}
