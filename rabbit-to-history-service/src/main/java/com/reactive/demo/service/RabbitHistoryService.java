package com.reactive.demo.service;

import com.reactive.demo.history.common.dto.HistoryDto;
import reactor.core.publisher.Mono;

public interface RabbitHistoryService {

    Mono<HistoryDto> saveHistory(Mono<HistoryDto> historyDto);
}
