package com.ghuddy.backendapp.tours.es.repository;

import com.ghuddy.backendapp.tours.es.dto.data.ESTourData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESTourDetailsRepository extends ElasticsearchRepository<ESTourData, Long> {
}
