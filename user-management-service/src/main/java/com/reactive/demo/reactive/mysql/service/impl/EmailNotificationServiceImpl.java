package com.reactive.demo.reactive.mysql.service.impl;

import com.reactive.demo.reactive.mysql.entity.User;
import com.reactive.demo.reactive.mysql.model.EmailNotificationRequest;
import com.reactive.demo.reactive.mysql.repository.UserRepository;
import com.reactive.demo.reactive.mysql.service.EmailNotificationService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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


    /**
     * Sends the email to a particular user.
     *
     * @param emailRequest contains title and body for email
     * @param userRequest the receiver
     */

    @Override
    public void sendSingleEmail(EmailNotificationRequest emailRequest, Mono<User> userRequest) {
        log.info("sendSingleEmail");
        SimpleMailMessage simpleMailMessage = getSimpleMailMessage(emailRequest);
        simpleMailMessage.setTo(userRequest.map(User::getEmail).toString());
        javaMailSender.send(simpleMailMessage);
    }

    /**
     * Sends the email to all users saved in the database.
     *
     * @param emailNotificationRequest contains title and body for email
     */
    @Override
    @SneakyThrows
    public void sendEmailToAllUsers(EmailNotificationRequest emailNotificationRequest) {
        log.info("sendEmailToAllUsers");
        SimpleMailMessage simpleMailMessage = getSimpleMailMessage(emailNotificationRequest);

        List<User> allUsers = userRepository.findAll().collectList().toFuture().get();

        Set<String> emails = allUsers.stream().map(User::getEmail).collect(Collectors.toSet());

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
