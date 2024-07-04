package com.uj.billswift.infra.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uj.billswift.company.Company;
import com.uj.billswift.infra.security.SecurityFilter;
import com.uj.billswift.infra.security.TokenService;
import com.uj.billswift.company.CompanyRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    TokenService tokenService; // Serviço para manipulação de tokens JWT

    @Autowired
    CompanyRepository companyRepository; // Repositório para acesso aos dados da entidade Company

    @Autowired
    SecurityFilter securityFilter; // Filtro de segurança para manipulação de autenticação

    // Endpoint GET para obter informações da Company associada ao usuário autenticado pelo token JWT
    @GetMapping
    public ResponseEntity<Company> getCompany(HttpServletRequest request) {
        var token = securityFilter.recoverToken(request); // Recupera o token JWT da requisição
        var login = tokenService.validateToken(token); // Valida o token e recupera o login do usuário

        // Busca a Company associada ao login (email) extraído do token
        Company company = companyRepository.findByEmail(login)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        return ResponseEntity.ok(company); // Retorna a Company encontrada como resposta HTTP 200 OK
    }
}