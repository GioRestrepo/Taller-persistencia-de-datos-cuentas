package entidades;

import excepciones.BancoException;

import java.util.ArrayList;

public abstract class CuentaBancaria {
    protected float saldo;
    protected String numeroDeCuenta;
    protected String propietario;
    protected String tipoCuenta;
    protected int numeroDeRetiros = 0;
    protected int numeroDeConsignaciones = 0;


    public CuentaBancaria(float saldo, String numeroDeCuenta, String propietario) {
        this.saldo = saldo;
        this.numeroDeCuenta = numeroDeCuenta;
        this.propietario = propietario;
    }

    public abstract void consignarDinero(float dinero);

    public  abstract ArrayList<CuentaBancaria> transferirDinero(CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino, float transferencia);

    public void retirarDinero (float dinero){
        if (dinero <= this.saldo) {
            this.saldo -= dinero;
            this.numeroDeRetiros++;
        }else {
            System.out.println("Saldo insuficiente");
        }
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

    public void setPropietario(String propietariio) {
        this.propietario = propietariio;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public int getNumeroDeRetiros() {
        return numeroDeRetiros;
    }

    public void setNumeroDeRetiros(int numeroDeRetiros) {
        this.numeroDeRetiros = numeroDeRetiros;
    }

    public int getNumeroDeConsignaciones() {
        return numeroDeConsignaciones;
    }

    public void setNumeroDeConsignaciones(int numeroDeConsignaciones) {
        this.numeroDeConsignaciones = numeroDeConsignaciones;
    }
}
