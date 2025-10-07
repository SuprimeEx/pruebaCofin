package org.cofincafe.ui;

import org.cofincafe.model.Cliente;
import org.cofincafe.service.ClienteService;

import java.util.List;
import java.util.Scanner;

public class ConsolaApp {

    private final ClienteService servicio;
    private final Scanner scanner;

    public ConsolaApp() {
        this.servicio = new ClienteService();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> agregarCliente();
                case 2 -> listarTodos();
                case 3 -> mostrarClientesNegativos();
                case 4 -> mostrarTop3();
                case 5 -> convertirAJson();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n=== GESTIÓN DE CLIENTES ===");
        System.out.println("1. Agregar cliente");
        System.out.println("2. Listar todos los clientes");
        System.out.println("3. Mostrar clientes con balance negativo");
        System.out.println("4. Mostrar top 3 de clientes con mayor balance");
        System.out.println("5. Mostrar todos en formato JSON");
        System.out.println("0. Salir");
    }

    private void agregarCliente() {
        System.out.println("\n--- AGREGAR CLIENTE ---");

        System.out.print("ID del cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();

        System.out.print("Balance del cliente: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        boolean agregado = servicio.agregarCliente(new Cliente(id, nombre, balance));
        if (agregado) {
            System.out.println("Cliente agregado correctamente.");
        } else {
            System.out.println("Ya existe un cliente con el ID " + id + ". No se agregó.");
        }
    }


    private void listarTodos() {
        System.out.println("\nLista completa de clientes:");
        List<Cliente> todos = servicio.obtenerTodos();

        if (todos.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            todos.forEach(System.out::println);
        }
    }

    private void mostrarClientesNegativos() {
        System.out.println("\nClientes con balance negativo:");
        List<Cliente> lista = servicio.obtenerClientesConBalanceNegativo();

        if (lista.isEmpty()) {
            System.out.println("No hay clientes con balance negativo.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private void mostrarTop3() {
        System.out.println("\nTop 3 clientes con mayor balance:");
        List<Cliente> top = servicio.obtenerTop3PorBalance();

        if (top.isEmpty()) {
            System.out.println("No hay suficientes clientes para mostrar un top 3.");
        } else {
            top.forEach(System.out::println);
        }
    }

    private void convertirAJson() {
        System.out.println("\nLista de clientes en formato JSON:");
        String json = servicio.convertirAJson(servicio.obtenerTodos());
        System.out.println(json);
    }
}
