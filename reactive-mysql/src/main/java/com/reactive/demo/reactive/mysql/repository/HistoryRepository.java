package com.reactive.demo.reactive.mysql.repository;

import com.reactive.demo.reactive.mysql.entity.History;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface HistoryRepository extends ReactiveCrudRepository<History, Long> {

    Flux<History> findAllByUserId(String userId);

    @Query("UPDATE history SET `read` = :read WHERE id = :id")
    Mono<Void> setRead(Long id, Boolean read);
}
