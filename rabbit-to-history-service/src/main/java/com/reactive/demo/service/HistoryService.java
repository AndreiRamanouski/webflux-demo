package com.reactive.demo.service;

import com.reactive.demo.dto.HistoryDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HistoryService {

    Flux<HistoryDto> findAllByUserId(String userId);

    Mono<HistoryDto> saveHistory(Mono<HistoryDto> historyDto);

    Mono<Void> readHistory(String id);
    Mono<Void> deleteHistory(String id);
}
