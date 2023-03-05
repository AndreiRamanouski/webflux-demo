package com.reactive.demo.reactive.mysql.repository;

import com.reactive.demo.reactive.mysql.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    Flux<User> findAllByUserId(String userId);
}
