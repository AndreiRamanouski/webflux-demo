package com.reactive.demo.reactive.mysql.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "rabbit.history")
public class RabbitHistoryConfigData {

    private String topic;

    private String key;

}
