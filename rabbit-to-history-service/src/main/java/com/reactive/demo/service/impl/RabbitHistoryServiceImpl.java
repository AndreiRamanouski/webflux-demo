package com.reactive.demo.service.impl;

import com.reactive.demo.history.common.dto.HistoryDto;
import com.reactive.demo.history.common.mapper.HistoryMapper;
import com.reactive.demo.history.common.repository.HistoryRepository;
import com.reactive.demo.service.RabbitHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitHistoryServiceImpl implements RabbitHistoryService {

    private final HistoryRepository historyRepository;


    @Override
    public Mono<HistoryDto> saveHistory(Mono<HistoryDto> historyDto) {
        log.info("saveHistory");
        return historyDto.map(HistoryMapper::mapDtoToEntity).flatMap(historyRepository::save)
                .map(HistoryMapper::mapEntityToDto);
    }
}
