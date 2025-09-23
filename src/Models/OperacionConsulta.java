package Models;





public class OperacionConsulta implements Operacion {
    @Override
    public void ejecutar(CuentaBancaria cuenta, double monto) {
        System.out.println("Saldo actual: $" + cuenta.getSaldo());
    }
}


