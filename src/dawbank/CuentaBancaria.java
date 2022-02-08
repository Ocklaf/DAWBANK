package dawbank;

/**
 *
 * @author nostromo
 */
public class CuentaBancaria {
    
    private final String IBAN, NOMBRE;
    private double saldo = 0;
    private double movimientos [] = new double[100];
    
    public CuentaBancaria(String iban, String nombre){
        this.IBAN = iban;
        this.NOMBRE = nombre;
    }
    
}
