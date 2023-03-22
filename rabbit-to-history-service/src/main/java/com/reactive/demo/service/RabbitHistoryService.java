package com.reactive.demo.service;

import com.reactive.demo.dto.HistoryDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RabbitHistoryService {

    Mono<HistoryDto> saveHistory(Mono<HistoryDto> historyDto);
}
