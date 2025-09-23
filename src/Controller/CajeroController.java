package Controller;

import Models.CajeroModels;
import Models.CuentaBancaria;
import Models.Operacion;
import Models.OperacionRetiro;
import Models.OperacionDeposito;
import Models.OperacionConsulta;
import Views.CajeroView;

public class CajeroController {
    private CajeroModels model;
    private CajeroView view;
    private boolean sistemaActivo;
    private Operacion operacion; // Estrategia actual

    public CajeroController(CajeroModels model, CajeroView view) {
        this.model = model;
        this.view = view;
        this.sistemaActivo = true;
    }

    // Setter de estrategia
    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    // Ejecutar estrategia
    public void ejecutarOperacion(double monto) {
        if (operacion != null && model.getCuentaActual() != null) {
            CuentaBancaria cuenta = model.getCuentaActual();
            operacion.ejecutar(cuenta, monto);
        } else {
            view.mostrarMensaje("⚠️ No hay operación definida o cuenta activa.");
        }
    }

    // Iniciar sistema principal
    public void iniciarSistema() {
        view.mostrarBienvenida();
        while (sistemaActivo) {
            if (autenticarUsuario()) {
                ejecutarMenuPrincipal();
            } else {
                view.mostrarMensaje("Cuenta inválida. Intenta de nuevo.");
            }
        }
        view.mostrarMensaje("Gracias por usar nuestro cajero.");
    }

    // Autenticación (solo con número de cuenta por ahora)
    private boolean autenticarUsuario() {
        String numeroCuenta = view.solicitarNumeroCuenta();
        return model.autenticar(numeroCuenta);
    }

    // Menú principal
    private void ejecutarMenuPrincipal() {
        boolean sessionActiva = true;
        while (sessionActiva) {
            view.mostrarMenuPrincipal(model.getCuentaActual().getTitular());
            int opcion = view.leerOpcion();
            switch (opcion) {
                case 1: // Consulta saldo
                    setOperacion(new OperacionConsulta());
                    ejecutarOperacion(0);
                    break;

                case 2: // Retiro
                    double retiro = view.solicitarCantidad("Retirar");
                    setOperacion(new OperacionRetiro());
                    ejecutarOperacion(retiro);
                    break;

                case 3: // Depósito
                    double deposito = view.solicitarCantidad("Depositar");
                    setOperacion(new OperacionDeposito());
                    ejecutarOperacion(deposito);
                    break;

                case 4: // Transferencia
                    this.realizarTransferencia();
                    break;

                case 5: // Salir
                    this.cerrarSistema();
                    sessionActiva = false;
                    sistemaActivo = false;
                    break;

                default:
                    view.mostrarMensaje("Opción inválida, intenta de nuevo.");
            }
        }
    }

    // Transferencia entre cuentas
    public void realizarTransferencia() {
        String cuentaDestino = view.solicitarCuentaDestino();

        if (!model.cuentaExistente(cuentaDestino)) {
            view.mostrarMensaje("La cuenta destino no existe.");
            return;
        }

        String cuentaOrigen = model.getCuentaActual().getNumeroCuenta();
        if (cuentaOrigen.equals(cuentaDestino)) {
            view.mostrarMensaje("No puedes transferir a la MISMA cuenta.");
            return;
        }

        double cantidad = view.solicitarCantidad("transferir");
        if (cantidad <= 0) {
            view.mostrarMensaje("Error en la cantidad.");
            return;
        }

        boolean ok = model.transferir(cuentaDestino, cantidad);
        if (ok) {
            view.mostrarMensaje("✅ Transferencia exitosa de $" + cantidad + " a la cuenta " + cuentaDestino);
        } else {
            view.mostrarMensaje("❌ No se pudo completar la transferencia (fondos insuficientes o datos inválidos).");
        }
    }

    // Cerrar sistema
    public void cerrarSistema() {
        view.cerrar();
    }
}


