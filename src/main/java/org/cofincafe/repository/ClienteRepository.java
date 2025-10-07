package org.cofincafe.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cofincafe.model.Cliente;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    private final String archivo;
    private final ObjectMapper mapper = new ObjectMapper();

    public ClienteRepository() {
        this.archivo = "clientes.json";
    }
    public ClienteRepository(String archivo) {
        this.archivo = archivo;
    }
    public List<Cliente> cargar() {
        try {
            File file = new File(archivo);
            if (!file.exists()) return new ArrayList<>();
            return mapper.readValue(file, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error al leer " + archivo, e);
        }
    }
    public void guardar(List<Cliente> clientes) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(archivo), clientes);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar " + archivo, e);
        }
    }
}
