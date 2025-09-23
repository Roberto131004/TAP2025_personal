package Models;

public class OperacionDeposito implements Operacion {
    @Override
    public void ejecutar(CuentaBancaria cuenta, double monto) {
        if (monto > 0) {
            cuenta.depositar(monto);
            System.out.println("Depósito exitoso de $" + monto);
        } else {
            System.out.println("Monto inválido para depósito.");
        }
    }
}


