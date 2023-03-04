package com.reactive.demo.reactive.mysql.repository;

import com.reactive.demo.reactive.mysql.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

}
