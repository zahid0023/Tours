package com.ghuddy.backendapp.tours.es.repository;

import com.ghuddy.backendapp.tours.es.dto.data.ESSubscribedTourData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESTourRepository extends ElasticsearchRepository<ESSubscribedTourData, Long> {
}
