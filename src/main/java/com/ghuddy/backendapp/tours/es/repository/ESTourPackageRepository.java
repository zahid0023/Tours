package com.ghuddy.backendapp.tours.es.repository;

import com.ghuddy.backendapp.tours.es.model.entities.ESTourPackageDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESTourPackageRepository extends ElasticsearchRepository<ESTourPackageDocument, Long> {
}
