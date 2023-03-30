package servicio;

import entidades.CuentaBancaria;
import repositorio.CuentaBancariaBaseDeDatos;
import repositorio.Repositorio;

public class ServicioCuentaBancaria {
    private Repositorio repositorioCuenta;

    public ServicioCuentaBancaria(){
        repositorioCuenta = new CuentaBancariaBaseDeDatos();
    }

    public void crearCuenta(Object objeto){
        repositorioCuenta.crearCuenta(objeto);
    }

    public CuentaBancaria buscarCuenta(String numeroDeCuenta){
        return (CuentaBancaria) repositorioCuenta.buscarCuenta(numeroDeCuenta);
    }

    public void actualizarSaldo(String numeroDeCuenta, float saldoConsignación){
        repositorioCuenta.actualizar(numeroDeCuenta, saldoConsignación);
    }
}
