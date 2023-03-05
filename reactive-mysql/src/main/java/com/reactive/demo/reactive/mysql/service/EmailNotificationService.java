package com.reactive.demo.reactive.mysql.service;

import com.reactive.demo.reactive.mysql.entity.User;
import com.reactive.demo.reactive.mysql.model.EmailNotificationRequest;
import java.util.List;
import reactor.core.publisher.Mono;

public interface EmailNotificationService {

    void sendSingleEmail(EmailNotificationRequest email, Mono<User> user);

    void sendEmails(List<String> emails, String title, String body);
}
