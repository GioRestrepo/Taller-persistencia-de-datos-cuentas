package servicio;

public abstract class CuentaBancaria {
    protected float saldo;
    protected int numeroDeCuenta;
    protected String propietariio;
    protected int numeroDeRetiros = 0;
    protected int numeroDeConsignaciones = 0;


    public CuentaBancaria(float saldo, int numeroDeCuenta, String propietariio) {
        this.saldo = saldo;
        this.numeroDeCuenta = numeroDeCuenta;
        this.propietariio = propietariio;
    }

    public abstract void consignarDinero(float dinero);

    public abstract void transferirDinero(String tipoDeCuenta, String numeroDeCuenta, float dinero);

    public void retirarDinero (float dinero) throws BancoException{
        if (dinero <= this.saldo) {
            this.saldo -= dinero;
            this.numeroDeRetiros++;
        }else {
            throw new BancoException("Saldo insuficiente");
        }
    }
}