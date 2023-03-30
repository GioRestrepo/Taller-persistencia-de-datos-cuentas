package repositorio;

public interface Repositorio {
    public void crearCuenta(Object objeto);
    public Object buscarCuenta(String numeroDeCuenta);

    public void actualizar(String numeroDeCuenta, float saldo);
}
