package com.brokerage.spring.service;

import com.brokerage.spring.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var customerEntity = customerRepository.findByEmail(email).orElseThrow();

        return new User(customerEntity.getEmail(), customerEntity.getEncryptedPassword(), new ArrayList<>());
    }


}
