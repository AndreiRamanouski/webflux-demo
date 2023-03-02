package com.reactive.demo;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import com.reactive.demo.controller.ProductController;
import com.reactive.demo.dto.ProductDto;
import com.reactive.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@WebFluxTest(ProductController.class)
class SpringReactiveMongoTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductService productService;


    @Test
    public void addProductTest() {
        Mono<ProductDto> productDtoMono = Mono.just(new ProductDto("10", "TV", 1, 100));
        when(productService.saveProduct(productDtoMono)).thenReturn(productDtoMono);
        webTestClient.post().uri("/products").body(Mono.just(productDtoMono), ProductDto.class)
                .exchange().expectStatus().isOk();
    }

    @Test
    public void getProductsTest() {
        Flux<ProductDto> productDtoFlux = Flux.just(new ProductDto("10", "TV", 1, 100),
                new ProductDto("11", "mobile", 10, 90),
                new ProductDto("12", "microwave", 14, 40));
        when(productService.getProducts()).thenReturn(productDtoFlux);
        Flux<ProductDto> responseBody = webTestClient.get().uri("/products").exchange().expectStatus().isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();
        StepVerifier.create(responseBody).expectSubscription()
                .expectNext(new ProductDto("10", "TV", 1, 100))
                .expectNext(new ProductDto("11", "mobile", 10, 90))
                .expectNext(new ProductDto("12", "microwave", 14, 40))
                .verifyComplete();
    }

    @Test
    public void getProductTest() {
        Mono<ProductDto> productDtoMono = Mono.just(new ProductDto("10", "TV", 1, 100));
        when(productService.findById(any())).thenReturn(productDtoMono);
        Flux<ProductDto> responseBody = webTestClient.get().uri("/products/10")
                .exchange().expectStatus().isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();
        StepVerifier.create(responseBody).expectSubscription()
                .expectNextMatches(e -> e.getName().equals("TV"))
                .verifyComplete();
    }


    @Test
    public void updateProductTest() {
        String id = "10";
        Mono<ProductDto> productDtoMono = Mono.just(new ProductDto(id, "TV", 1, 100));
        when(productService.updateProduct(productDtoMono, id)).thenReturn(productDtoMono);
        webTestClient.put().uri("/products/" + id).body(Mono.just(productDtoMono), ProductDto.class)
                .exchange().expectStatus().isOk();
    }

    @Test
    public void deleteProductTest(){
        String id = "10";
        given(productService.deleteProduct(any())).willReturn(Mono.empty());
        webTestClient.delete().uri("/products/" + id)
                .exchange().expectStatus().isOk();
    }


}