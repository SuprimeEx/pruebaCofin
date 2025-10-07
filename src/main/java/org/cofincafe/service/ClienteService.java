package org.cofincafe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cofincafe.model.Cliente;
import org.cofincafe.repository.ClienteRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteService {

    private  List<Cliente> clientes;
    private  ClienteRepository repo;

    public ClienteService() {
        repo = new ClienteRepository();
        clientes = repo.cargar();
    }

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
        this.clientes = repo.cargar();
    }

    /////////////////////////////////////////////////////////////
    public List<Cliente> obtenerClientesConBalanceNegativo() {
        return clientes.stream()
                .filter(c -> c.getBalance() < 0)
                .collect(Collectors.toList());
    }

    public List<Cliente> obtenerTop3PorBalance() {
        return clientes.stream()
                .sorted(Comparator.comparingDouble(Cliente::getBalance).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public String convertirAJson(List<Cliente> lista) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al convertir a JSON", e);
        }
    }
////////////////////////////////////////////////////////////////////
    public boolean agregarCliente(Cliente nuevo) {
        boolean existe = clientes.stream().anyMatch(c -> c.getId() == nuevo.getId());
        if (existe) return false;
        clientes.add(nuevo);
        repo.guardar(clientes);
        return true;
    }

    public List<Cliente> obtenerTodos() {
        return clientes;
    }

}
