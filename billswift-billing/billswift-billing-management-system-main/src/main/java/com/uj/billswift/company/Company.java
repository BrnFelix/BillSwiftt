package com.uj.billswift.company;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "companies")
@Getter
@Setter
@AllArgsConstructor

@NoArgsConstructor
public class Company {

    // Anotação que define o campo "id" como a chave primária da tabela
    @Id
    // Anotação que define a estratégia de geração do valor do campo "id" como UUID
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // Campos da entidade "Company" que representam as colunas da tabela
    private String name;
    private String email;
    private String cnpj;
    private String password;
    private String stateRegistration;
}