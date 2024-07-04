package com.uj.billswift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Anotação que marca esta classe como a aplicação principal do Spring Boot
@SpringBootApplication
public class BillswiftApplication {

    // Método principal que inicia a aplicação Spring Boot
    public static void main(String[] args) {
        SpringApplication.run(BillswiftApplication.class, args);
    }

}