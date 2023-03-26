package com.reactive.demo.history.common.dto;

import java.time.LocalDateTime;

public record HistoryDto(String id,
                         String userId,
                         String userAction,
                         LocalDateTime createdAt,
                         Boolean read) {

}
