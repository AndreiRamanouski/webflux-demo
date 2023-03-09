package com.reactive.demo.reactive.mysql.service.impl;

import com.reactive.demo.reactive.mysql.entity.User;
import com.reactive.demo.reactive.mysql.model.EmailNotificationRequest;
import com.reactive.demo.reactive.mysql.service.EmailNotificationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailNotificationServiceImpl implements EmailNotificationService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendSingleEmail(EmailNotificationRequest emailRequest, Mono<User> userRequest) {
        log.info("sendSingleEmail");
        SimpleMailMessage simpleMailMessage = getSimpleMailMessage(emailRequest);
        simpleMailMessage.setTo(userRequest.map(User::getEmail).toString());
        javaMailSender.send(simpleMailMessage);
    }


    @Override
    public void sendEmails(List<String> emails, EmailNotificationRequest emailNotificationRequest) {
        log.info("sendEmails");
        SimpleMailMessage simpleMailMessage = getSimpleMailMessage(emailNotificationRequest);
        emails.forEach((e) -> {
            simpleMailMessage.setTo(e);
            javaMailSender.send(simpleMailMessage);
        });
    }

    private static SimpleMailMessage getSimpleMailMessage(EmailNotificationRequest emailRequest) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(emailRequest.getTitle());
        simpleMailMessage.setText(emailRequest.getBody());
        return simpleMailMessage;
    }
}
