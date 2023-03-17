package com.reactive.demo.reactive.mysql.util;

import com.reactive.demo.HistoryModel;
import com.reactive.demo.UserAction;
import com.reactive.demo.reactive.mysql.rabbit.RabbitProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitHistoryUtil {

    private final RabbitProducer rabbitProducer;


    public void produceEvent(Long id, UserAction userAction){
        log.info("produceEvent");
        log.info("User id: {}, user action: {}", id, userAction);
        rabbitProducer.produceMessage(
                HistoryModel.builder()
                        .userId(String.valueOf(id))
                        .userAction(userAction)
                        .build()
        );
    }

}
