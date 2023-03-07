package com.reactive.demo.reactive.mysql.service.impl;

import com.reactive.demo.reactive.mysql.dto.HistoryDto;
import com.reactive.demo.reactive.mysql.mapper.HistoryMapper;
import com.reactive.demo.reactive.mysql.repository.HistoryRepository;
import com.reactive.demo.reactive.mysql.service.HistoryService;
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
    public Mono<HistoryDto> saveHistory(Mono<HistoryDto> historyDto) {
        log.info("saveHistory");
        return historyDto.map(HistoryMapper::mapDtoToEntity).flatMap(historyRepository::save)
                .map(HistoryMapper::mapEntityToDto);
    }

    @Override
    public Mono<HistoryDto> readHistory(Long id) {
        log.info("readHistory");
        return historyRepository.findById(id).doOnNext(history -> history.setRead(!history.getRead()))
                .map(HistoryMapper::mapEntityToDto);
    }

    @Override
    public Mono<Void> deleteHistory(Long id) {
        log.info("deleteHistory");
        return historyRepository.deleteById(id);
    }
}
