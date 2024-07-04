package com.uj.billswift.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

// Interface que define o repositório para a entidade Clients, estendendo JpaRepository
    public interface ClientsRepository extends JpaRepository<Clients, Long> {
    
    // Método para encontrar uma lista de clientes por ID da empresa
    Optional<List<Clients>> findByCompanyId(String companyId);
    
    // Método para encontrar um cliente específico por ID do cliente e ID da empresa
    Optional<Clients> findByIdAndCompanyId(Long id, String companyId);
    
    // Método para encontrar um cliente por nome
    Clients findByName(String name);
}