package com.uj.billswift.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uj.billswift.infra.security.SecurityFilter;
import com.uj.billswift.infra.security.TokenService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CompanyService {

    @Autowired
    TokenService tokenService; // Serviço para manipulação de tokens JWT

    @Autowired
    CompanyRepository companyRepository; // Repositório para acesso aos dados da entidade Company

    @Autowired
    SecurityFilter securityFilter; // Filtro de segurança para manipulação de autenticação

    // Método para obter a Company associada ao token JWT presente na requisição
    public Company getCompany(HttpServletRequest request) {
        var token = securityFilter.recoverToken(request); // Recupera o token JWT da requisição
        var login = tokenService.validateToken(token); // Valida o token e recupera o login do usuário

        // Busca a Company associada ao login (email) extraído do token
        Company company = companyRepository.findByEmail(login)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        return company; // Retorna a Company encontrada
    }

    // Método para obter uma Company pelo seu ID
    public Company getCompanyById(String id) {
        // Busca a Company pelo ID no banco de dados
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }
}