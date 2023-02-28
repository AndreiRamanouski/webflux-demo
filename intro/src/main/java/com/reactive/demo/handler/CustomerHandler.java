package com.reactive.demo.handler;

import com.reactive.demo.dao.CustomerDao;
import com.reactive.demo.dto.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerHandler {

    private final CustomerDao customerDao;


    public Mono<ServerResponse> loadCustomers(ServerRequest serverRequest) {
        Flux<Customer> customersWithoutDelay = customerDao.getCustomersWithoutDelay();
        return ServerResponse.ok().body(customersWithoutDelay, Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest serverRequest) {
        log.info("findCustomer");
        Integer input = Integer.valueOf(serverRequest.pathVariable("input"));
        //        Mono<Customer> single = customerDao
        //        .getCustomersWithoutDelay().filter(customer -> customer.getId() == input)
        //                .take(1).single();
        Mono<Customer> next = customerDao.getCustomersWithoutDelay().filter(customer -> customer.getId() == input)
                .next().log();
        return ServerResponse.ok().body(next, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest serverRequest) {
        log.info("saveCustomer");
        Mono<Customer> customerMono = serverRequest.bodyToMono(Customer.class).log();
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ":" + dto.getName());
        return ServerResponse.ok().body(saveResponse, String.class);
    }


}
