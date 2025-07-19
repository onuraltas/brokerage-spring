package com.brokerage.spring.service.impl;

import com.brokerage.spring.entity.CustomerEntity;
import com.brokerage.spring.enums.ExceptionMessage;
import com.brokerage.spring.exceptions.BrokerageException;
import com.brokerage.spring.repository.CustomerRepository;
import com.brokerage.spring.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerEntity getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).orElseThrow(() -> new BrokerageException(ExceptionMessage.RECORD_NOT_FOUND));
    }
}
