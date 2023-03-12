package com.reactive.demo.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class History {

    @Id
    private String id;
    private String userId;
    private String payload;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    @Builder.Default
    private Boolean read = false;

}
