package com.reactive.demo.reactive.mysql.repository;

import com.reactive.demo.reactive.mysql.entity.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    @Query("UPDATE users SET email = :email WHERE id = :id")
    Mono<Void> updateEmail(String email, Long id);

    Mono<User> findByEmail(String email);
}
