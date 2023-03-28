package entidades;

public class CuentaBancaria {
    private float saldo;
    private String numeroDeCuenta;
    private String propietario;

    public CuentaBancaria(float saldo, String numeroDeCuenta, String propietario) {
        this.saldo = saldo;
        this.numeroDeCuenta = numeroDeCuenta;
        this.propietario = propietario;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(String numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
}
