package repositorio;

import entidades.CuentaBancaria;

import java.sql.*;

public class CuentaBancariaBaseDeDatos implements Repositorio {

    private String conexionBD;

    public CuentaBancariaBaseDeDatos() {
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            conexionBD = "jdbc:sqlite:cuentas.db";
            crearTabla();
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
    }

    private void crearTabla() {
        try (Connection conexion = DriverManager.getConnection(conexionBD)) {

            String sql = "CREATE TABLE IF NOT EXISTS cuentas (\n"
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + " saldo FLOAT NOT NULL,\n"
                    + " numeroDeCuenta TEXT NOT NULL UNIQUE,\n"
                    + " propietario TEXT NOT NULL\n"
                    + ");";

            Statement sentencia = conexion.createStatement();
            sentencia.execute(sql);

        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    @Override
    public void crearCuenta(Object objeto) {
        try (Connection conexion = DriverManager.getConnection(conexionBD)) {
            CuentaBancaria cuentaBancaria = (CuentaBancaria) objeto;
            String sentenciaSql = "INSERT INTO cuentas (saldo, numeroDeCuenta, propietario) " +
                    " VALUES('" + cuentaBancaria.getSaldo() + "', '" + cuentaBancaria.getNumeroDeCuenta()
                    + "', " + cuentaBancaria.getPropietario() + "');";
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

    }

    @Override
    public Object buscarCuenta(String numeroDeCuenta) {
        try (Connection conexion = DriverManager.getConnection(conexionBD)) {
            String sentenciaSQL = "SELECT * FROM cuentas WHERE numeroDeCuenta= ?";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, numeroDeCuenta);
            ResultSet resultadoConsulta = sentencia.executeQuery();
            if (resultadoConsulta != null && resultadoConsulta.next()) {
                CuentaBancaria cuentaBancaria = null;
                float saldo = resultadoConsulta.getFloat("saldo");
                String numeroDeCuentaResultado = resultadoConsulta.getString("numeroDeCuenta");
                String propietario = resultadoConsulta.getString("propietario");

                cuentaBancaria = new CuentaBancaria(saldo,numeroDeCuentaResultado,propietario);
                return cuentaBancaria;
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;
    }
    public void actualizar(String numeroDeCuenta, float saldo){
        try (Connection conexion = DriverManager.getConnection(conexionBD)) {
            String sentenciaSql = "UPDATE cuentas SET saldo = '" + saldo + "'  WHERE numeroDeCuenta = '" + numeroDeCuenta + "';";
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

    }
}
