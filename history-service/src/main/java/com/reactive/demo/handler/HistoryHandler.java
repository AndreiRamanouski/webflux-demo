package com.reactive.demo.handler;

import com.reactive.demo.history.common.dto.HistoryDto;
import com.reactive.demo.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Component
public class HistoryHandler {

    private final HistoryService historyService;


    public Mono<ServerResponse> getAllByUserId(ServerRequest serverRequest) {
        log.info("getAllByUserId");
        Flux<HistoryDto> historyDto = historyService.findAllByUserId(serverRequest.pathVariable("userId"));
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(historyDto, HistoryDto.class);
    }

    public Mono<ServerResponse> readHistory(ServerRequest serverRequest) {
        log.info("readHistory");
        Mono<Void> historyDto = historyService.readHistory(serverRequest.pathVariable("historyId"));
        return ServerResponse.ok().body(historyDto, HistoryDto.class);
    }

    public Mono<ServerResponse> deleteHistory(ServerRequest serverRequest) {
        log.info("");
        Mono<Void> history = historyService.deleteHistory(serverRequest.pathVariable("historyId"));
        return ServerResponse.ok().body(history, Void.class);
    }

}
