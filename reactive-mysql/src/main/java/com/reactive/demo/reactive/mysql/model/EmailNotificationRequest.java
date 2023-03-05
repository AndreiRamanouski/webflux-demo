package com.reactive.demo.reactive.mysql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmailNotificationRequest {

    private String title;
    private String body;
}
