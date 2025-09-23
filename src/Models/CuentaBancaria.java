package Models;



public class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;
    private String tipoCuenta;
    private double limiteCredito;


    private CuentaBancaria(Builder builder) {
        this.numeroCuenta = builder.numeroCuenta;
        this.titular = builder.titular;
        this.saldo = builder.saldo;
        this.tipoCuenta = builder.tipoCuenta;
        this.limiteCredito = builder.limiteCredito;
    }

    // 🔹 Getters (para acceder a la info de la cuenta)
    public String getNumeroCuenta() { return numeroCuenta; }
    public String getTitular() { return titular; }
    public double getSaldo() { return saldo; }
    public String getTipoCuenta() { return tipoCuenta; }
    public double getLimiteCredito() { return limiteCredito; }


    public void depositar(double cantidad) {
        if (cantidad > 0) {
            this.saldo += cantidad;
        }
    }


    public boolean retirar(double cantidad) {
        if (cantidad > 0 && this.saldo >= cantidad) {
            this.saldo -= cantidad;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "CuentaBancaria {" +
                "Número de Cuenta='" + numeroCuenta + '\'' +
                ", Titular='" + titular + '\'' +
                ", Saldo=" + saldo +
                ", Tipo de Cuenta='" + tipoCuenta + '\'' +
                ", Límite de Crédito=" + limiteCredito +
                '}';
    }

   //clase internta builder
    public static class Builder {
        private String numeroCuenta;
        private String titular;
        private double saldo;
        private String tipoCuenta;
        private double limiteCredito;

        public Builder setNumeroCuenta(String numeroCuenta) {
            this.numeroCuenta = numeroCuenta;
            return this;
        }

        public Builder setTitular(String titular) {
            this.titular = titular;
            return this;
        }

        public Builder setSaldo(double saldo) {
            this.saldo = saldo;
            return this;
        }

        public Builder setTipoCuenta(String tipoCuenta) {
            this.tipoCuenta = tipoCuenta;
            return this;
        }

        public Builder setLimiteCredito(double limiteCredito) {
            this.limiteCredito = limiteCredito;
            return this;
        }


        public CuentaBancaria build() {
            return new CuentaBancaria(this);
        }
    }
}
