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

        float dinero = (float) (transferencia + (transferencia * 0.015));

        if (cuentaOrigen.getTipoCuenta() != cuentaDestino.getTipoCuenta()){

            if (transferencia <= this.saldo) {
                cuentaOrigen.retirarDinero(dinero);
                cuentaDestino.consignarDinero(transferencia);
            }
            }else{
                if (transferencia <= this.saldo) {
                    cuentaOrigen.retirarDinero(transferencia);
                    cuentaDestino.consignarDinero(transferencia);
                }

            }
        ArrayList<CuentaBancaria> cuentas = new ArrayList<>();
        cuentas.add(cuentaOrigen);
        cuentas.add(cuentaDestino);
        return cuentas;

    }
}