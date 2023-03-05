package com.reactive.demo.reactive.mysql.router;

import com.reactive.demo.reactive.mysql.handler.HistoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class RouterConfig {
    private final HistoryHandler userHistoryHandler;
    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .POST("/history/save", userHistoryHandler::saveHistory)
                .GET("/history/{userId}", userHistoryHandler::getAllByUserId)
                .POST("/history/{history}", userHistoryHandler::readHistory)
                .DELETE("/history/{history}", userHistoryHandler::deleteHistory)
                .build();
    }
}