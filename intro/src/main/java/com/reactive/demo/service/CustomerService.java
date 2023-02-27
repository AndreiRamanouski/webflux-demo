package com.reactive.demo.service;


import com.reactive.demo.dao.CustomerDao;
import com.reactive.demo.dto.Customer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerDao customerDao;

    public List<Customer> loadAllCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customers = customerDao.getCustomers();

        long end = System.currentTimeMillis();
        log.info("Execution time is: {} milliseconds", end-start);
        return customers;
    }

    public Flux<Customer> loadAllCustomersStream() {
        long start = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getCustomersStream();
        long end = System.currentTimeMillis();
        log.info("Execution time is: {} milliseconds", end-start);
        return customers;
    }
}
