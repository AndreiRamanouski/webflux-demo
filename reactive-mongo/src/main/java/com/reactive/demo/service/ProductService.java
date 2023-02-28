package com.reactive.demo.service;

import com.reactive.demo.dto.ProductDto;
import com.reactive.demo.repository.ProductRepository;
import com.reactive.demo.util.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public Flux<ProductDto> getProducts() {
        log.info("getProducts");
        return productRepository.findAll().map(AppUtils::entityToDto).log();
    }

    public Mono<ProductDto> findById(String id) {
        log.info("findById");
        return productRepository.findById(id).map(AppUtils::entityToDto);
    }

    public Flux<ProductDto> getProductInRange(double min, double max) {
        log.info("getProductInRange");
        return productRepository.findByPriceBetween(Range.closed(min, max)).map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
        log.info("saveProduct");
       return productDtoMono.map(AppUtils::dtoToEntity).flatMap(productRepository::insert).map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id){
        log.info("updateProduct");
        return productRepository.findById(id)
                .flatMap(product -> productDtoMono.map(AppUtils::dtoToEntity))
                .doOnNext(product -> product.setId(id))
                .flatMap(productRepository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteProduct(String id) {
        log.info("deleteProduct");
        return productRepository.deleteById(id);
    }
}
