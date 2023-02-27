package com.reactive.demo.dao;

import com.reactive.demo.dto.Customer;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class CustomerDao {


    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 20)
                .peek(CustomerDao::sleep)
                .peek(i -> log.info("processing count: {}", i))
                .mapToObj(i -> new Customer(i, "customer:" + i))
                .collect(Collectors.toList());
    }
    public Flux<Customer> getCustomersStream() {
        return Flux.range(1,20)
                .delayElements(Duration.ofMillis(100))
                .doOnNext(i -> log.info("processing count: {}", i))
                .map(i->new Customer(i, "customer:" + i))
                .log();
    }



    private static void sleep(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
