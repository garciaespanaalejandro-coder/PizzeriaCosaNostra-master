package com.biblioteca.repository;

import org.springframework.stereotype.Repository;

@Repository
public class RepositorioDummyImpl implements RepositorioDummy{
    @Override
    public void hacerAlgo() {
        System.out.println("Haciendo algo... :)");
    }
}
