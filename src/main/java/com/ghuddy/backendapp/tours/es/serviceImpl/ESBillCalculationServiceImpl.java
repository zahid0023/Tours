package com.ghuddy.backendapp.tours.es.serviceImpl;

import com.ghuddy.backendapp.tours.es.dto.data.ESTourPackagePriceCalculationData;
import com.ghuddy.backendapp.tours.es.dto.response.ESBillCalculationResponse;
import com.ghuddy.backendapp.tours.es.service.ESBillCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class ESBillCalculationServiceImpl implements ESBillCalculationService {
    private final RestHighLevelClient restHighLevelClient;

    public ESBillCalculationServiceImpl(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    /**
     * @param esTourPackagePriceCalculationDataList
     */
    @Override
    public ESBillCalculationResponse calculateBill(List<ESTourPackagePriceCalculationData> esTourPackagePriceCalculationDataList) throws IOException {
        SearchResponse searchResponse = findMatchingDocuments(esTourPackagePriceCalculationDataList);
        log.info(searchResponse.toString());
        return new ESBillCalculationResponse();
    }

    private SearchResponse findMatchingDocuments(List<ESTourPackagePriceCalculationData> esTourPackagePriceCalculationDataList) throws IOException {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        for (ESTourPackagePriceCalculationData esTourPackagePriceCalculationData : esTourPackagePriceCalculationDataList) {
            BoolQueryBuilder setQuery = QueryBuilders.boolQuery();
            setQuery.must(QueryBuilders.termQuery("available_tour_package_id", esTourPackagePriceCalculationData.getAvailableTourPackageId()));
            setQuery.must(QueryBuilders.termQuery("accommodation_option_id", esTourPackagePriceCalculationData.getAccommodationOptionId()));
            setQuery.must(QueryBuilders.termQuery("food_option_id", esTourPackagePriceCalculationData.getFoodOptionId()));
            setQuery.must(QueryBuilders.termQuery("transfer_option_id", esTourPackagePriceCalculationData.getTransferOptionId()));
            setQuery.must(QueryBuilders.termQuery("transportation_option_id", esTourPackagePriceCalculationData.getTransportationPackageId()));
            setQuery.must(QueryBuilders.termQuery("guide_option_id", esTourPackagePriceCalculationData.getGuideOptionId()));
            setQuery.must(QueryBuilders.termQuery("spot_entry_option_id", esTourPackagePriceCalculationData.getSpotEntryId()));

            boolQuery.should(setQuery);
        }

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(boolQuery);
        SearchRequest searchRequest = new SearchRequest("available_tour_package_component_options_combination").source(searchSourceBuilder);
        log.info(searchRequest.toString());

        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }
}
