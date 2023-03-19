package com.reactive.demo.reactive.mysql.rabbit.runner;


import com.reactive.demo.HistoryModel;
import com.reactive.demo.reactive.mysql.config.RabbitHistoryConfigData;
import com.reactive.demo.reactive.mysql.rabbit.RabbitProducer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HistoryRabbitProducer implements RabbitProducer {

    private final MappingJackson2MessageConverter mappingJackson2MessageConverter;
    private final RabbitMessagingTemplate rabbitTemplate;
    private final RabbitHistoryConfigData rabbitHistoryConfigData;


    @PostConstruct
    public void srtConverter() {
        log.info("rabbit history config: {}", rabbitHistoryConfigData);
        rabbitTemplate.setMessageConverter(mappingJackson2MessageConverter);
    }

    public void produceMessage(HistoryModel historyModel) {
        rabbitTemplate.convertAndSend(rabbitHistoryConfigData.getTopic(), rabbitHistoryConfigData.getKey(),
                historyModel);
    }


}
