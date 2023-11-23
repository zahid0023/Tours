package com.ghuddy.backendapp.tours.es.repository;

import com.ghuddy.backendapp.tours.es.model.entities.ESTourComponentOptionCombinationDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESTourComponentOptionCombinationRepository extends ElasticsearchRepository<ESTourComponentOptionCombinationDocument, Long> {
}
