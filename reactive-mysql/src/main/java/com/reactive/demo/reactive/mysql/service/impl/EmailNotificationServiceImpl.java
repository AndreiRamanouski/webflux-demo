package com.reactive.demo.reactive.mysql.service.impl;

import com.reactive.demo.reactive.mysql.entity.User;
import com.reactive.demo.reactive.mysql.model.EmailNotificationRequest;
import com.reactive.demo.reactive.mysql.service.EmailNotificationService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {

    @Override
    public void sendEmail(EmailNotificationRequest email, Mono<User> user) {

    }
}
