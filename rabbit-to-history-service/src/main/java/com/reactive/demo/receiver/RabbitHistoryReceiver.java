package com.reactive.demo.receiver;

import com.reactive.demo.HistoryModel;
import com.reactive.demo.dto.HistoryDto;
import com.reactive.demo.service.RabbitHistoryService;
import com.reactive.demo.transformer.HistoryModelTransformer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitHistoryReceiver {

    private final RabbitHistoryService rabbitHistoryService;

    @SneakyThrows
    @RabbitListener(queues = "spring-boot")
    public void receiveMessage(HistoryModel message) {
        log.info("Received message {}", message);
        HistoryDto historyDto = HistoryModelTransformer.modelToEntity(message);
        rabbitHistoryService.saveHistory(Mono.just(historyDto)).subscribe();
    }
}
