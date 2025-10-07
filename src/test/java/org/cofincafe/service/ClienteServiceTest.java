package org.cofincafe.service;

import org.cofincafe.model.Cliente;
import org.cofincafe.repository.ClienteRepository;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {

    private ClienteService service;
    private ClienteRepository repo;
    private File testFile;

    @BeforeEach
    void setUp() throws Exception {
        testFile = new File("clientes_test.json");
        try (FileWriter fw = new FileWriter(testFile)) {
            fw.write("[{\"nombre\":\"Juan Pérez\",\"balance\":1000}," +
                    "{\"nombre\":\"Ana Gómez\",\"balance\":-500}," +
                    "{\"nombre\":\"Luis Díaz\",\"balance\":-200}," +
                    "{\"nombre\":\"Marta López\",\"balance\":1500}]");
        }

        repo = new ClienteRepository("clientes_test.json");
        service = new ClienteService(repo);
    }

    @AfterEach
    void tearDown() {
        if (testFile.exists()) testFile.delete();
    }

    @Test
    void testObtenerClientesConBalanceNegativo() {
        List<Cliente> negativos = service.obtenerClientesConBalanceNegativo();
        assertEquals(2, negativos.size());
        assertTrue(negativos.stream().allMatch(c -> c.getBalance() < 0));
    }

    @Test
    void testObtenerTop3PorBalance() {
        List<Cliente> top3 = service.obtenerTop3PorBalance();
        assertEquals(3, top3.size());
        assertTrue(top3.get(0).getBalance() >= top3.get(1).getBalance());
        assertTrue(top3.get(1).getBalance() >= top3.get(2).getBalance());
    }

    @Test
    void testConvertirAJson() {
        String json = service.convertirAJson(service.obtenerTodos());
        assertNotNull(json);
        assertTrue(json.startsWith("["));
        assertTrue(json.endsWith("]"));
        assertTrue(json.contains("Juan Pérez"));
    }
}
