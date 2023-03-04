package com.reactive.demo.reactive.mysql.mapper;

import com.reactive.demo.reactive.mysql.dto.HistoryDto;
import com.reactive.demo.reactive.mysql.entity.History;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HistoryMapper {

    public HistoryDto mapEntityToDto(History history) {
        return new HistoryDto(history.getId(), history.getUserId(),
                history.getPayload(), history.getCreatedAt(), history.getRead());
    }

    public History mapDtoToEntity(HistoryDto historyDto) {
        return new History(historyDto.id(), historyDto.userId(),
                historyDto.payload(), historyDto.createdAt(), historyDto.read());
    }
}
