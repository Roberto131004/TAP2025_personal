package Models;

public class OperacionRetiro implements Operacion {
    @Override
    public void ejecutar(CuentaBancaria cuenta, double monto) {
        if (monto > 0 && cuenta.retirar(monto)) {
            System.out.println("Retiro exitoso de $" + monto);
        } else {
            System.out.println("No se pudo realizar el retiro (fondos insuficientes o monto inválido).");
        }
    }
}

