package com.uj.billswift.clients;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

// Anotação que define esta classe como um controlador REST do Spring
@RestController
// Mapeamento da URL base para as requisições deste controlador
@RequestMapping("/api/clients")
public class ClientsController {

    // Injeta a dependência do serviço de clientes
    @Autowired
    private ClientsService clientsService;

    // Método para obter todos os clientes
    @GetMapping
    public ResponseEntity<List<Clients>> getAllClients(HttpServletRequest request) {
        List<Clients> clients = clientsService.getAllClients(request);
        // Retorna a lista de clientes com o status HTTP 200 (OK)
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    // Método para obter um cliente pelo seu ID
    @GetMapping("/{id}")
    public ResponseEntity<Clients> getClientsById(@PathVariable Long id, HttpServletRequest request) {
        Clients clients = clientsService.getClientsById(id, request);
        // Retorna o cliente encontrado com o status HTTP 200 (OK)
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    // Método para criar um novo cliente
    @PostMapping
    public ResponseEntity<Clients> createClient(@RequestBody Clients clients, HttpServletRequest request) {
        Clients createdClient = clientsService.createClients(clients, request);
        // Retorna o cliente criado com o status HTTP 201 (CREATED)
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    // Método para atualizar um cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<Clients> updateClient(@PathVariable Long id, @RequestBody Clients clientDetails, HttpServletRequest request) {
        try {
            Clients updatedClient = clientsService.updateClient(id, clientDetails, request);
            // Retorna o cliente atualizado com o status HTTP 200 (OK)
            return ResponseEntity.ok(updatedClient);
        } catch (RuntimeException e) {
            // Retorna o status HTTP 404 (NOT FOUND) se o cliente não for encontrado
            return ResponseEntity.notFound().build();
        }
    }

    // Método para deletar um cliente pelo seu ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id, HttpServletRequest request) {
        clientsService.deleteClient(id, request);
        // Retorna o status HTTP 204 (NO CONTENT) indicando que a deleção foi bem-sucedida
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}