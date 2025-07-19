package com.brokerage.spring.util;

import com.brokerage.spring.SpringApplicationContext;
import com.brokerage.spring.service.CustomerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class WebUtils {

    public Integer getLoginnedCustomerId() {
        var securityContext = SecurityContextHolder.getContext();
        var authentication = securityContext.getAuthentication();

        if (!authentication.getPrincipal().equals("anonymousUser")) {
            var username = authentication.getName();

            var customerService = (CustomerService) SpringApplicationContext.getBean("customerServiceImpl");

            var customerEntity = customerService.getCustomerByEmail(username);

            return customerEntity.getCustomerId();
        }

        return null;
    }

}
