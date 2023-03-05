package com.reactive.demo.reactive.mysql.dto;

import java.time.LocalDateTime;

public record HistoryDto(Long id,
                         String userId,
                         String payload,
                         LocalDateTime createdAt,
                         Boolean read) {

}
