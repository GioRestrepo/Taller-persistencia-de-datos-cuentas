package entidades;

import excepciones.BancoException;

import java.util.ArrayList;

public class CuentaCorriente extends CuentaBancaria{
private int numeroDetransferencias = 0;

    public CuentaCorriente(float saldo, String numeroDeCuenta, String propietario) {
        super(saldo, numeroDeCuenta, propietario);
        this.tipoCuenta = "CC";
    }

    @Override
    public void consignarDinero(float dinero) {
        if (dinero > 0) {
            this.saldo += dinero;
            this.numeroDeConsignaciones++;
        }
    }

    @Override
    public void retirarDinero(float dinero){
        if (dinero <= this.saldo && numeroDeRetiros <6) {
            this.saldo -= dinero;
            this.numeroDeRetiros++;
        } else{
            System.out.println("EL máximo de retiros es Cinco");
        }
    }

    @Override
    public ArrayList<CuentaBancaria> transferirDinero(CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino,
                                                   float transferencia){
       float dinero = (float) (transferencia + (transferencia * 0.02));

        if (cuentaOrigen.getTipoCuenta() != cuentaDestino.getTipoCuenta()){
            if (transferencia <= this.saldo && this.numeroDetransferencias <2) {
                    cuentaOrigen.retirarDinero(dinero);
                    cuentaDestino.consignarDinero(transferencia);
                    this.numeroDetransferencias++;
            }
        }else {
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
