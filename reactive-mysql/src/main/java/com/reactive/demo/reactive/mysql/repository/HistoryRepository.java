package com.reactive.demo.reactive.mysql.repository;

import com.reactive.demo.reactive.mysql.entity.History;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends ReactiveCrudRepository<History, Long> {

}
