package com.reactive.demo.reactive.mysql.service;

import com.reactive.demo.reactive.mysql.dto.HistoryDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HistoryService {

    Flux<HistoryDto> findAllByUserId(String userId);

    Mono<HistoryDto> saveHistory(Mono<HistoryDto> historyDto);

    Mono<HistoryDto> readHistory(String id);
    Mono<Void> deleteHistory(String id);
}
