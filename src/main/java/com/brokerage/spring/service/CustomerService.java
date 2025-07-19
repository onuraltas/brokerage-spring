package com.brokerage.spring.service;

import com.brokerage.spring.entity.CustomerEntity;

public interface CustomerService {

    CustomerEntity getCustomerByEmail(String email);

}
