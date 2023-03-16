package com.reactive.demo.reactive.mysql.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmailNotificationRequest {

    @NotNull
    @NotEmpty
    private String title;
    private String body;
}
