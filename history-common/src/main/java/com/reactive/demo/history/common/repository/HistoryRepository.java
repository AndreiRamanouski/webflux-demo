package com.reactive.demo.history.common.repository;

import com.reactive.demo.history.common.entity.History;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface HistoryRepository extends ReactiveMongoRepository<History, String> {
    Flux<History> findAllByUserId(String userId);
}
