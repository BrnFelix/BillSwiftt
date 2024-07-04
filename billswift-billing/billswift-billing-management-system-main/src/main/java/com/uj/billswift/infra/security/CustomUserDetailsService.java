package com.uj.billswift.infra.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.uj.billswift.company.Company;
import com.uj.billswift.company.CompanyRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CompanyRepository repository; // Repositório para acesso aos dados da entidade Company

    // Método exigido pela interface UserDetailsService para carregar um UserDetails com base no username (neste caso, email)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca a Company pelo email (username) no banco de dados
        Company company = this.repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Retorna um UserDetails que representa o usuário encontrado
        return new org.springframework.security.core.userdetails.User(
                company.getEmail(), // Username é o email da Company
                company.getPassword(), // Password da Company
                new ArrayList<>() // Lista vazia de permissões (roles)
        );
    }
}