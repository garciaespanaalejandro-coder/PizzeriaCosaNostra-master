package com.biblioteca.service;

import com.biblioteca.repository.RepositorioDummy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioDummyImpl implements ServicioDummy {
    @Autowired
    private RepositorioDummy repositorioDummy;


    @Override
    public void hacerAlgo() {
        this.repositorioDummy.hacerAlgo();
    }
}
