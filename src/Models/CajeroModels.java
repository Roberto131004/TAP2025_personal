package Models;

import java.util.HashMap;
import java.util.Map;

public class CajeroModels {
    private Map<String, CuentaBancaria> cuentas;
    private CuentaBancaria cuentaActual;

    public CajeroModels() {
        cuentas = new HashMap<>();
        inicializarCuentas();
    }

    // 🔹 Inicializar algunas cuentas de prueba usando el Builder
    private void inicializarCuentas() {
        CuentaBancaria cuenta1 = new CuentaBancaria.Builder()
                .setNumeroCuenta("12345")
                .setTitular("Juan Perez")
                .setSaldo(500000)
                .setTipoCuenta("Ahorros")
                .build();

        CuentaBancaria cuenta2 = new CuentaBancaria.Builder()
                .setNumeroCuenta("13579")
                .setTitular("Roberto Moreno")
                .setSaldo(100000)
                .setTipoCuenta("Corriente")
                .build();

        CuentaBancaria cuenta3 = new CuentaBancaria.Builder()
                .setNumeroCuenta("10000")
                .setTitular("Alexander Alejos")
                .setSaldo(200000)
                .setTipoCuenta("Crédito")
                .setLimiteCredito(30000)
                .build();

        cuentas.put(cuenta1.getNumeroCuenta(), cuenta1);
        cuentas.put(cuenta2.getNumeroCuenta(), cuenta2);
        cuentas.put(cuenta3.getNumeroCuenta(), cuenta3);
    }

    /
    public boolean autenticar(String numeroCuenta) {
        CuentaBancaria cuenta = cuentas.get(numeroCuenta);
        if (cuenta != null) {
            this.cuentaActual = cuenta;
            return true;
        }
        return false;
    }

    public CuentaBancaria getCuentaActual() {
        return this.cuentaActual;
    }


    public double consultarSaldo() {
        return this.cuentaActual != null ? cuentaActual.getSaldo() : 0;
    }


    public boolean realizarRetiro(double cantidad) {
        return cuentaActual != null && cuentaActual.retirar(cantidad);
    }


    public boolean realizarDeposito(double cantidad) {
        if (cuentaActual != null && cantidad > 0) {
            cuentaActual.depositar(cantidad);
            return true;
        }
        return false;
    }


    public boolean transferir(String numeroCuentaDestino, double cantidad) {
        if (cuentaActual == null || cantidad <= 0) return false;

        CuentaBancaria destino = cuentas.get(numeroCuentaDestino);
        if (destino == null) return false; // no existe
        if (destino.getNumeroCuenta().equals(cuentaActual.getNumeroCuenta())) return false;


        if (cuentaActual.retirar(cantidad)) {
            destino.depositar(cantidad);
            return true;
        }
        return false;
    }


    public boolean cuentaExistente(String numeroCuenta) {
        return cuentas.containsKey(numeroCuenta);
    }
}
