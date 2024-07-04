package com.uj.billswift.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uj.billswift.company.Company;
import com.uj.billswift.company.CompanyService;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

// Anotação que define esta classe como um serviço do Spring
@Service
public class ClientsService {

    // Injeta automaticamente uma instância de ClientsRepository
    @Autowired
    private ClientsRepository clientsRepository;

    // Injeta automaticamente uma instância de CompanyService
    @Autowired
    private CompanyService companyService;

    // Método para obter todos os clientes de uma empresa específica
    public List<Clients> getAllClients(HttpServletRequest request) {
        // Obtém a empresa associada ao request
        Company company = companyService.getCompany(request);
        String companyId = company.getId();
        
        // Retorna a lista de clientes da empresa ou lança uma exceção se não encontrar
        return clientsRepository.findByCompanyId(companyId)
               .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
    }

    // Método para obter um cliente específico pelo ID e empresa
    public Clients getClientsById(Long id, HttpServletRequest request) {
        // Obtém a empresa associada ao request
        Company company = companyService.getCompany(request);
        String companyId = company.getId();
        
        // Retorna o cliente ou lança uma exceção se não encontrar
        return clientsRepository.findByIdAndCompanyId(id, companyId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado para esta empresa."));
    }

    // Método para criar um novo cliente
    public Clients createClients(Clients clients, HttpServletRequest request) {
        // Obtém a empresa associada ao request
        Company company = companyService.getCompany(request);

        // Define o ID da empresa no cliente a ser criado
        String companyId = company.getId();
        clients.setCompanyId(companyId);
        
        // Salva e retorna o cliente criado
        return clientsRepository.save(clients);
    }

    // Método para atualizar um cliente existente
    public Clients updateClient(Long id, Clients clientDetails, HttpServletRequest request) {
        // Obtém a empresa associada ao request
        Company company = companyService.getCompany(request);
        String companyId = company.getId();

        // Busca o cliente a ser atualizado
        Clients client = clientsRepository.findByIdAndCompanyId(id, companyId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado para esta empresa."));

        // Atualiza os detalhes do cliente
        client.setName(clientDetails.getName());
        client.setPhone(clientDetails.getPhone());
        client.setState(clientDetails.getState());
        client.setNeighborhood(clientDetails.getNeighborhood());
        client.setCpf(clientDetails.getCpf());
        client.setZipCode(clientDetails.getZipCode());
        client.setCity(clientDetails.getCity());
        client.setAddress(clientDetails.getAddress());
        
        // Salva e retorna o cliente atualizado
        return clientsRepository.save(client);
    }

    // Método para deletar um cliente
    public void deleteClient(Long id, HttpServletRequest request) {
        // Obtém a empresa associada ao request
        Company company = companyService.getCompany(request);
        String companyId = company.getId();

        // Busca o cliente a ser deletado
        Clients client = clientsRepository.findByIdAndCompanyId(id, companyId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado para esta empresa."));
        
        // Deleta o cliente
        clientsRepository.delete(client);
    }
}