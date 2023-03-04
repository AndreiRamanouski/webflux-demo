package com.reactive.demo.reactive.mysql.service.impl;

import com.reactive.demo.reactive.mysql.dto.HistoryDto;
import com.reactive.demo.reactive.mysql.service.HistoryService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Override
    public Flux<HistoryDto> findAllByUserId(String userId) {
        return null;
    }

    @Override
    public Mono<HistoryDto> saveHistory(Mono<HistoryDto> historyDto) {
        return null;
    }

    @Override
    public Mono<HistoryDto> readHistory(String id) {
        return null;
    }

    @Override
    public Mono<Void> deleteHistory(String id) {
        return null;
    }
}
