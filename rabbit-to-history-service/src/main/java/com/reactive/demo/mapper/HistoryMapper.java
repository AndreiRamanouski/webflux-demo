package com.reactive.demo.mapper;

import com.reactive.demo.dto.HistoryDto;
import com.reactive.demo.entity.History;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HistoryMapper {

    public HistoryDto mapEntityToDto(History history) {
        return new HistoryDto(history.getId(), history.getUserId(),
                history.getUserAction(), history.getCreatedAt(), history.getRead());
    }

    public History mapDtoToEntity(HistoryDto historyDto) {
        return History.builder()
                .userAction(historyDto.userAction())
                .userId(historyDto.userId())
                .build();
    }
}
