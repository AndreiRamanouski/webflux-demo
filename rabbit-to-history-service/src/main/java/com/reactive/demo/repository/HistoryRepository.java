package com.reactive.demo.repository;

import com.reactive.demo.entity.History;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface HistoryRepository extends ReactiveMongoRepository<History, String> {

    Flux<History> findAllByUserId(String userId);

    @Query("UPDATE history SET `read` = :read WHERE id = :id")
    Mono<Void> setRead(String id, Boolean read);
}
