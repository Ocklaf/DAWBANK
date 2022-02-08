package dawbank;

import java.util.Arrays;

/**
 *
 * @author nostromo
 */
public class CuentaBancaria {
    
    private final String IBAN, NOMBRE;
    private final double SALDOMINIMO = -50;
    private double saldo = 0;
    private double movimientos [] = new double[100];
    private int posicionMovimientos = 0;
    
    public CuentaBancaria(String iban, String nombre){
        IBAN = iban;
        NOMBRE = nombre;
    }
    
    public String getIBAN(){
        return IBAN;
    }
    
    public String getDatosCuenta(){
        return "IBAN: " + IBAN + "\nTitular de la cuenta: " + NOMBRE + "\nSaldo actual: " + saldo;
    }
    
    public String getNOMBRE(){
        return NOMBRE;
    }
    
    public double getSaldo(){
        return saldo;
    }
    
    public void setIngreso(double ingreso){
        if(ingreso>3000)
            System.out.println("AVISO: Notificación a Hacienda");
        saldo+=ingreso;
        movimientos[posicionMovimientos] = ingreso;
        posicionMovimientos++;
    }
    
    public void setRetirada(double retirada){
        if(saldo <= SALDOMINIMO){
            System.out.println("AVISO: Saldo en cuenta -50€, no puede realizar más retiradas de efectivo");
        }
        else if(saldo - retirada < 0){
            System.out.println("AVISO: Saldo negativo");
            if (saldo - retirada <= SALDOMINIMO) {                
                movimientos[posicionMovimientos] = saldo;
                saldo = SALDOMINIMO;
                posicionMovimientos++;
            } 
            else {
                saldo -= retirada;
                movimientos[posicionMovimientos] = -retirada;
                posicionMovimientos++;
            }
        }
        else if(retirada>3000){
            System.out.println("AVISO: Notificación a Hacienda");
            saldo -= retirada;
            movimientos[posicionMovimientos] = -retirada;
            posicionMovimientos++;
        }
        else{
            saldo -= retirada;
            movimientos[posicionMovimientos] = -retirada;
            posicionMovimientos++;
        }
    }
    
    public double[] getMovimientos(){
        //Hago un array con los movimientos existentes y no 100 directamente por si está vacío
        double listado[];
        listado = Arrays.copyOf(movimientos, posicionMovimientos+1);
        return listado;
    }
    
    //public void setMovimientos()
}
