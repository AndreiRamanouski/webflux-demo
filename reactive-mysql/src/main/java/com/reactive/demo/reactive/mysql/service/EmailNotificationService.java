package com.reactive.demo.reactive.mysql.service;

import com.reactive.demo.reactive.mysql.entity.User;
import com.reactive.demo.reactive.mysql.model.EmailNotificationRequest;
import reactor.core.publisher.Mono;

public interface EmailNotificationService {


    void sendEmail(EmailNotificationRequest email, Mono<User> user);
}
