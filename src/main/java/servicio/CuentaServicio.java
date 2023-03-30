package servicio;

import entidades.CuentaBancaria;
import repositorio.Repositorio;

public class CuentaServicio {
    private Repositorio repositorioCuenta;

    public void crearCuenta(Object objeto){
         repositorioCuenta.crearCuenta(objeto);
    }
    public CuentaBancaria buscarCuenta(String numeroDeCuenta){
        Object cuentaBancaria = repositorioCuenta.buscarCuenta(numeroDeCuenta);
        if(cuentaBancaria == null) {
            System.out.println("Cuenta no encontrada");
        }
        return (CuentaBancaria) cuentaBancaria;
    }
}
