package com.reactive.demo.reactive.mysql.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "history")
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class History {

    @Id
    private Long id;
    private String userId;
    private String payload;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    private Boolean read;

}
