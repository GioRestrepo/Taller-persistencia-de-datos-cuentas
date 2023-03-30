package gui;

import entidades.CuentaBancaria;
import entidades.CuentaCorriente;
import entidades.CuentaDeAhorros;
import servicio.ServicioCuentaBancaria;

import java.util.Scanner;

public class gui_cuenta {
    private boolean running = true;
    private ServicioCuentaBancaria servicioCuentaBancaria;

    public gui_cuenta() {
        servicioCuentaBancaria = new ServicioCuentaBancaria();
    }

    public void iniciar() {
        System.out.println("Bienvenido al sistema de persistencia de Cuentas Bancarias");

        while (running) {
            System.out.println("1. Crear Cuenta");
            System.out.println("2. Buscar cuenta por numero de cuenta");
            System.out.println("3. Depositar");
            System.out.println("4. Transferir");
            System.out.println("5. Retirar");
            System.out.println("6. Salir");
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();
            seleccionarOpcionesPrincipales(opcion);
        }

    }

    private void seleccionarOpcionesPrincipales(int seleccion) {
        switch (seleccion) {
            case 1:
                crearCuenta();
                break;
            case 2:
                buscarCuenta();
                break;
            case 3:
                depositar();
                break;
            case 4:
                transferir();
                break;
            case 5:
                retirar();
                break;
            case 6:
                salir();
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    public CuentaBancaria buscarCuenta() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el numero  de la cuenta");
        String numeroCuenta = scanner.nextLine();
        CuentaBancaria cuenta = servicioCuentaBancaria.buscarCuenta(numeroCuenta);
        if(cuenta != null){
            String data = "Tipo de cuenta: " + cuenta.getTipoCuenta() + "\n"
                    + "Numero: " + cuenta.getNumeroDeCuenta() + "\n"
                    + "Propietario: " + cuenta.getPropietario() + "\n"
                    + "Saldo: " + cuenta.getSaldo();
            System.out.println(data);
        } else {
            System.out.println("La cuenta indicada no existe");
        }
        return cuenta;
    }

    private void crearCuenta() {
        CuentaBancaria cuenta;

        System.out.println("Creación de cuenta");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escoja el tipo de cuenta \n1.CC\n2.CA");
        String tipoCuenta = scanner.nextLine();
        if(tipoCuenta.equalsIgnoreCase("cc") || tipoCuenta.equalsIgnoreCase("ca")){
            System.out.println("Ingrese el saldo de la cuenta");
            float saldo = scanner.nextFloat();
            scanner.nextLine();
            System.out.println("Ingrese el número de la cuenta");
            String numeroCuenta = scanner.nextLine();

            System.out.println("Ingrese el nombre del propietario de la cuenta");
            String propietario = scanner.nextLine();

            if(tipoCuenta.equalsIgnoreCase("cc")) {
                cuenta = new CuentaCorriente(saldo, numeroCuenta, propietario);
            } else {
                cuenta = new CuentaDeAhorros(saldo, numeroCuenta, propietario);
            }

            servicioCuentaBancaria.crearCuenta(cuenta);
        } else {
            System.out.println("No ha seleccionado una opción valida");
        }
    }

    private void depositar() {
        CuentaBancaria cuenta = buscarCuenta();

        if(cuenta != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite el saldo a ingresar");
            float saldo = scanner.nextFloat();
            scanner.nextLine();
            if(cuenta.getTipoCuenta().equalsIgnoreCase("cc")) {
                CuentaCorriente cuentaCC = (CuentaCorriente) cuenta;
                cuentaCC.consignarDinero(saldo);

                servicioCuentaBancaria.actualizarSaldo(cuentaCC.getNumeroDeCuenta(), cuentaCC.getSaldo());
            } else {
                CuentaDeAhorros cuentaA = (CuentaDeAhorros) cuenta;
                cuentaA.consignarDinero(saldo);

                servicioCuentaBancaria.actualizarSaldo(cuentaA.getNumeroDeCuenta(), cuentaA.getSaldo());
            }
        }
    }

    private void transferir() {
        System.out.println("Cuenta desde la que se transfiere");
        CuentaBancaria cuentaOrigen = buscarCuenta();
        System.out.println("Cuenta a la que se va a transferir");
        CuentaBancaria cuentaDestino = buscarCuenta();

        if(cuentaOrigen != null && cuentaDestino != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el monto a transferir");
            float saldo = scanner.nextFloat();
            scanner.nextLine();
            if(cuentaOrigen.getTipoCuenta().equalsIgnoreCase("cc")){

                CuentaCorriente cuentaCC = (CuentaCorriente) cuentaOrigen;
                cuentaCC.transferirDinero(cuentaOrigen, cuentaDestino, saldo);
                servicioCuentaBancaria.actualizarSaldo(cuentaCC.getNumeroDeCuenta(), cuentaCC.getSaldo());

            }else{
                CuentaDeAhorros cuentaA = (CuentaDeAhorros) cuentaOrigen;
                cuentaA.transferirDinero(cuentaOrigen, cuentaDestino, saldo);

                servicioCuentaBancaria.actualizarSaldo(cuentaA.getNumeroDeCuenta(), cuentaA.getSaldo());
            }

        }

    }

    private void retirar() {
        CuentaBancaria cuenta = buscarCuenta();

        if(cuenta != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite el monto a retirar");
            float saldo = scanner.nextFloat();
            scanner.nextLine();
            if(cuenta.getTipoCuenta().equalsIgnoreCase("cc")) {
                CuentaCorriente cuentaCC = (CuentaCorriente) cuenta;
                cuentaCC.retirarDinero(saldo);

                servicioCuentaBancaria.actualizarSaldo(cuentaCC.getNumeroDeCuenta(), cuentaCC.getSaldo());
            } else {
                CuentaDeAhorros cuentaA = (CuentaDeAhorros) cuenta;
                cuentaA.retirarDinero(saldo);

                servicioCuentaBancaria.actualizarSaldo(cuentaA.getNumeroDeCuenta(), cuentaA.getSaldo());
            }
        }

    }

    private void salir() {
        System.out.println("Salir");
        running = false;
    }
}

