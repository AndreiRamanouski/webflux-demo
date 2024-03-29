package com.reactive.demo.router;

import com.reactive.demo.handler.HistoryHandler;
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
                .GET("/history/{userId}", userHistoryHandler::getAllByUserId)
                .PATCH("/history/{historyId}", userHistoryHandler::readHistory)
                .DELETE("/history/{historyId}", userHistoryHandler::deleteHistory)
                .build();
    }
}