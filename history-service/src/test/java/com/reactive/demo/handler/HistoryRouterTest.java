package com.reactive.demo.handler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import com.reactive.demo.history.common.dto.HistoryDto;
import com.reactive.demo.router.RouterConfig;
import com.reactive.demo.service.HistoryService;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@WebFluxTest
@ContextConfiguration(classes = {HistoryHandler.class, RouterConfig.class})
public class HistoryRouterTest {

    @Autowired
    ApplicationContext applicationContext;

    @MockBean
    HistoryService historyService;

    private WebTestClient webTestClient;


    @PostConstruct
    public void setupWebClient() {
        webTestClient = WebTestClient.bindToApplicationContext(applicationContext).build();
    }

    @Test
    public void getAllByUserId() {
        LocalDateTime now = LocalDateTime.now();
        Flux<HistoryDto> historyDtoFlux = Flux.just(
                new HistoryDto("1", "1", "REGISTRATION", now, false),
                new HistoryDto("2", "1", "EMAIL_UPDATE", now, false),
                new HistoryDto("3", "1", "UPDATE_INFO", now, false),
                new HistoryDto("4", "1", "DELETE_USER", now, false)
        );
        when(historyService.findAllByUserId(any())).thenReturn(historyDtoFlux);

        Flux<HistoryDto> responseBody = webTestClient.get().uri("/history/" + 2).accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk().returnResult(HistoryDto.class).getResponseBody();

        StepVerifier.create(responseBody).expectSubscription()
                .expectNext(new HistoryDto("1", "1", "REGISTRATION", now, false))
                .expectNext(new HistoryDto("2", "1", "EMAIL_UPDATE", now, false))
                .expectNext(new HistoryDto("3", "1", "UPDATE_INFO", now, false))
                .expectNext(new HistoryDto("4", "1", "DELETE_USER", now, false))
                .verifyComplete();
    }

    @Test
    public void readHistory() {
        given(historyService.readHistory(any())).willReturn(Mono.empty());
        webTestClient.patch().uri("/history/" + 2)
                .exchange().expectStatus().isOk();
    }

    @Test
    public void deleteHistory() {
        given(historyService.deleteHistory(any())).willReturn(Mono.empty());
        webTestClient.delete().uri("/history/" + 2)
                .exchange().expectStatus().isOk();
    }
}
