package com.reactive.demo.reactive.mysql.repository;

import com.reactive.demo.reactive.mysql.entity.History;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface HistoryRepository extends ReactiveCrudRepository<History, Long> {

    Flux<History> findAllByUserId(String userId);
}
