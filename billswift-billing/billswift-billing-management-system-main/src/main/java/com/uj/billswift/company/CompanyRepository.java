package com.uj.billswift.company;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

// Interface que estende JpaRepository para manipulação de entidades Company no banco de dados
    public interface CompanyRepository extends JpaRepository<Company, String> {

    // Método para buscar uma Company pelo email
    Optional<Company> findByEmail(String email);

    // Método para buscar uma Company pelo CNPJ
    Optional<Company> findByCnpj(String cnpj);

    // Método para buscar uma Company pelo registro estadual (State Registration)
    Optional<Company> findByStateRegistration(String stateRegistration);
}