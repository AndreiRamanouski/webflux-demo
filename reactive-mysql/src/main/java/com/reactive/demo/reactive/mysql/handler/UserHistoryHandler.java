package com.reactive.demo.reactive.mysql.handler;

import com.reactive.demo.reactive.mysql.dto.HistoryDto;
import com.reactive.demo.reactive.mysql.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Component
public class UserHistoryHandler {
    private final HistoryService historyService;

    public Mono<ServerResponse> saveHistory(ServerRequest serverRequest){
        log.info("saveHistory");
        Mono<HistoryDto> savedHistory = historyService.saveHistory(serverRequest.bodyToMono(HistoryDto.class)).log();
        return ServerResponse.ok().body(savedHistory, HistoryDto.class);
    }

}
