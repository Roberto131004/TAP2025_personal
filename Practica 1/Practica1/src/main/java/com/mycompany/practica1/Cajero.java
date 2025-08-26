package com.mycompany.practica1;

import java.util.Scanner;

public class Cajero {
    private Banco banco;
    private Scanner scanner;

    public Cajero() {
        banco = new Banco();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("=== Bienvenido al Cajero ===");

        Usuario usuario = autenticarUsuario();
        if (usuario == null) {
            System.out.println("Demasiados intentos fallidos.");
            return;
        }

        System.out.println("Bienvenido, " + usuario.getNombre());
        mostrarMenu(usuario);
    }

    private Usuario autenticarUsuario() {
        int intentos = 0;
        Usuario usuario = null;

        while (intentos < 3 && usuario == null) {
            System.out.print("Ingrese su PIN: ");
            String pin = scanner.nextLine();
            usuario = banco.autenticar(pin);

            if (usuario == null) {
                System.out.println("PIN incorrecto.");
                intentos++;
            }
        }

        return usuario;
    }

    private void mostrarMenu(Usuario usuario) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n1. Ver saldo");
            System.out.println("2. Retirar dinero");
            System.out.println("3. Depositar dinero");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    System.out.println("Su saldo es: $" + usuario.getSaldo());
                    break;
                case 2:
                    realizarRetiro(usuario);
                    break;
                case 3:
                    realizarDeposito(usuario);
                    break;
                case 4:
                    salir = true;
                    System.out.println("Gracias por usar el cajero.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private int leerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer
        return opcion;
    }

    private void realizarRetiro(Usuario usuario) {
        System.out.print("Ingrese cantidad a retirar: ");
        double monto = leerMonto();
        if (usuario.retirar(monto)) {
            System.out.println("Retiro exitoso. Nuevo saldo: $" + usuario.getSaldo());
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }
    
    private void realizarDeposito(Usuario usuario) {
        System.out.print("Ingrese cantidad a depositar: ");
        double monto = leerMonto();
        usuario.depositar(monto);
        System.out.println("Depósito exitoso. Nuevo saldo: $" + usuario.getSaldo());
    }

    private double leerMonto() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, ingrese un monto válido.");
            scanner.next();
        }
        double monto = scanner.nextDouble();
        scanner.nextLine(); // limpiar buffer
        return monto;
    }
}

