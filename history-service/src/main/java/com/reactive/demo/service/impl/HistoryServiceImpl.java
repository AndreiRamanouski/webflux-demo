package com.reactive.demo.service.impl;

import com.reactive.demo.history.common.dto.HistoryDto;
import com.reactive.demo.history.common.mapper.HistoryMapper;
import com.reactive.demo.history.common.repository.HistoryRepository;
import com.reactive.demo.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Override
    public Flux<HistoryDto> findAllByUserId(String userId) {
        log.info("findAllByUserId");
        return historyRepository.findAllByUserId(userId).map(HistoryMapper::mapEntityToDto);
    }

    @Override
    public Mono<Void> readHistory(String id) {
        log.info("readHistory");
        return historyRepository.findById(id).doOnNext(history -> history.setRead(!history.getRead()))
                .flatMap(historyRepository::save).then();

    }

    @Override
    public Mono<Void> deleteHistory(String id) {
        log.info("deleteHistory");
        return historyRepository.deleteById(id);
    }
}
