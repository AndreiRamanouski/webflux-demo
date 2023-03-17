package com.reactive.demo.reactive.mysql.service.impl;

import com.reactive.demo.reactive.mysql.entity.User;
import com.reactive.demo.reactive.mysql.model.EmailNotificationRequest;
import com.reactive.demo.reactive.mysql.repository.UserRepository;
import com.reactive.demo.reactive.mysql.service.EmailNotificationService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
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
    private final UserRepository userRepository;

    @Override
    public void sendSingleEmail(EmailNotificationRequest emailRequest, Mono<User> userRequest) {
        log.info("sendSingleEmail");
        SimpleMailMessage simpleMailMessage = getSimpleMailMessage(emailRequest);
        simpleMailMessage.setTo(userRequest.map(User::getEmail).toString());
        javaMailSender.send(simpleMailMessage);
    }


    @Override
    public void sendEmailToAllUsers(EmailNotificationRequest emailNotificationRequest) {
        log.info("sendEmailToAllUsers");
        SimpleMailMessage simpleMailMessage = getSimpleMailMessage(emailNotificationRequest);
        Set<String> emails = new HashSet<>();
        List<User> all;
        try {
            all = userRepository.findAll().collectList().toFuture().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        all.forEach(user -> emails.add(user.getEmail()));
        log.debug("Found {} emails, unique {}", all.size(), emails.size());
        emails.forEach((e) -> {
            simpleMailMessage.setTo(e);
            log.info("email {}", e);
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
