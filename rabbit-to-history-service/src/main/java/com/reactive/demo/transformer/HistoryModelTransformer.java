package com.reactive.demo.transformer;

import com.reactive.demo.HistoryModel;
import com.reactive.demo.dto.HistoryDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HistoryModelTransformer {


    public HistoryDto modelToEntity(HistoryModel historyModel) {
        return new HistoryDto(null, historyModel.getUserId(), historyModel.getUserAction().name(), null, null);
    }
}
