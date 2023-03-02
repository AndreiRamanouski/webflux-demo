package com.reactive.demo.controller;

import com.reactive.demo.dto.ProductDto;
import com.reactive.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public Flux<ProductDto> getProducts() {
        log.info("getProducts");
        return productService.getProducts();
    }

    @GetMapping("{id}")
    public Mono<ProductDto> getProduct(@PathVariable String id) {
        log.info("getProduct {}", id);
        return productService.findById(id);
    }


    @GetMapping("range")
    public Flux<ProductDto> getProductsBetweenRange(@RequestParam(name = "min", defaultValue = "0") Integer min,
            @RequestParam(name = "max", defaultValue = "1000") Integer max) {
        log.info("getProductsBetweenRange");
        return productService.getProductInRange(min, max);
    }


    @PostMapping
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDto) {
        log.info("saveProduct");
        return productService.saveProduct(productDto);
    }

    @PutMapping("{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDto, @PathVariable String id) {
        log.info("updateProduct");
        return productService.updateProduct(productDto, id);
    }


    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(@PathVariable String id) {
        log.info("deleteProduct");
        return productService.deleteProduct(id);
    }

}
