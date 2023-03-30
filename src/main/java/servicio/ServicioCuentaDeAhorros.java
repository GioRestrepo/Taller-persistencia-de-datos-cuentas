package servicio;

public class ServicioCuentaDeAhorros extends CuentaBancaria {


    public ServicioCuentaDeAhorros(float saldo, int numeroDeCuenta, String propietariio) {
        super(saldo, numeroDeCuenta, propietariio);
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
    public void retirarDinero(float dinero) throws BancoException {

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
            throw new BancoException("Saldo insuficiente");
        }

    }

    @Override
    public void transferirDinero(String tipoDeCuenta, String numeroDeCuenta, float dinero){
        if (tipoDeCuenta.equals("cuenta de ahorros")){

            if (dinero <= this.saldo) {
                if (numeroDeRetiros > 3) {
                    this.saldo -= (dinero + (dinero * 0.01));
                    this.numeroDeRetiros++;
                } else {
                    this.saldo -= dinero;
                    this.numeroDeRetiros++;
                }
                //Update
            }else if(tipoDeCuenta.equals("cuenta corriente")){
                if (dinero <= this.saldo) {
                    float dineroTransferido =  this.saldo -= (dinero + (dinero * 0.015));
                    //Update -> repositorioCuenta.transferir(numeroDeCuenta, dineroTransferido)
                }

            }else{
                System.out.println("");
            }
        }
    }
}
