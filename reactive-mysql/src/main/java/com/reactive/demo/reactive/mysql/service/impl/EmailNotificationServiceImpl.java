package com.reactive.demo.reactive.mysql.service.impl;

import com.reactive.demo.reactive.mysql.entity.User;
import com.reactive.demo.reactive.mysql.model.EmailNotificationRequest;
import com.reactive.demo.reactive.mysql.service.EmailNotificationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailNotificationServiceImpl implements EmailNotificationService {

    @Override
    public void sendSingleEmail(EmailNotificationRequest email, Mono<User> user) {
        log.info("sendSingleEmail");
    }


    @Override
    public void sendEmails(List<String> emails, String title, String body){
        log.info("sendEmails");
    }
}
