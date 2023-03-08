package com.reactive.demo.reactive.mysql.repository;

import com.reactive.demo.reactive.mysql.entity.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    Flux<User> findAllByUserId(String userId);

    Mono<User> findByUserIdAndDeviceId(String userId, String deviceId);

    @Query("UPDATE users SET email = :email WHERE user_id = :userId AND device_id = :deviceId")
    Mono<Void> updateEmail(String email, String userId, String deviceId);
}
