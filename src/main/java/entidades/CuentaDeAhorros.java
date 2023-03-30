package entidades;

import excepciones.BancoException;

import java.util.ArrayList;

public class CuentaDeAhorros extends CuentaBancaria {


    public CuentaDeAhorros(float saldo, String numeroDeCuenta, String propietario) {
        super(saldo, numeroDeCuenta, propietario);
        this.tipoCuenta = "CA";
    }

    @Override
    public void consignarDinero(float dinero) {
        if (dinero > 0) {
            if (dinero > 0 && numeroDeConsignaciones < 2) {
                //float dineroprop = this.saldo;
                this.saldo += (dinero + (dinero * 0.05));
                this.numeroDeConsignaciones++;
            } else {
                this.saldo += dinero;
                this.numeroDeConsignaciones++;
            }
        }


    }

    @Override
    public void retirarDinero(float dinero){

        if (dinero <= this.saldo) {
            if (numeroDeRetiros > 3) {
                float dineroRetirado = this.saldo -= (dinero + (dinero * 0.01));
                // repositorioBanco.retirarDinero(dineroRetirado);
                this.numeroDeRetiros++;
            } else {
                this.saldo -= dinero;
                this.numeroDeRetiros++;
            }
        } else {
            System.out.println("Saldo insuficiente");
        }

    }

    @Override
    public ArrayList<CuentaBancaria> transferirDinero(CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino, float transferencia){

        float dinero;

        if (cuentaOrigen.getTipoCuenta() != cuentaDestino.getTipoCuenta()){

            if (transferencia <= this.saldo) {
                dinero = this.saldo -= (transferencia + (transferencia * 0.015));
                cuentaOrigen.retirarDinero(dinero);
                cuentaDestino.consignarDinero(dinero);
            }
            }else{
                if (transferencia <= this.saldo) {
                    dinero = this.saldo -= transferencia;
                    cuentaOrigen.retirarDinero(dinero);
                    cuentaDestino.consignarDinero(dinero);
                }

            }
        ArrayList<CuentaBancaria> cuentas = new ArrayList<>();
        cuentas.add(cuentaOrigen);
        cuentas.add(cuentaDestino);
        return cuentas;

    }
}