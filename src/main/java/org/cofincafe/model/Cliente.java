package org.cofincafe.model;

public class Cliente {

    private int id;
    private String nombre;
    private double balance;

    public Cliente() {}

    public Cliente(int id, String nombre, double balance) {
        this.id = id;
        this.nombre = nombre;
        this.balance = balance;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getBalance() { return balance; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return String.format("Cliente {id=%d, nombre='%s', balance=%.2f}", id, nombre, balance);
    }
}
