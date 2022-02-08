/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dawbank;

/**
 *
 * @author nostromo
 */
import java.util.Arrays;
import java.util.Scanner;

public class DawBank {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String iban, nombreCompleto;
        int opcion;

        do {
            System.out.println("Dame el IBAN de tu cuenta bancaria (2 letras más 22 números):");
            iban = input.nextLine();
        } while (!verificarIBAN(iban));//While True... hemos de repetir hasta que la función nos devuelva True y hacer un NOT para salir del bucle
        iban = iban.toUpperCase();
        //System.out.println(iban);

        nombreCompleto = solicitarVerificarNombre();
        
        CuentaBancaria OpenBank = new CuentaBancaria(iban, nombreCompleto);

        do {
            opcion = menu();
            menuSwitch(opcion, OpenBank);
        } while (opcion != 8);
    }

    public static boolean verificarIBAN(String iban) {
        return iban.matches("^[A-Za-z]{2}[0-9]{22}$+");//Que empieza y acaba por lo indicado... A-Za-z incluidos 2 carácteres al comienzo seguido de 22 dígitos y sólo una vez +
    }

    public static String solicitarVerificarNombre() {
        Scanner input = new Scanner(System.in);
        String nombre, apellido1, apellido2, segundo;

        do {
            System.out.println("Dígame su nombre:");
            nombre = input.nextLine();
        } while (nombre.length() <= 2);

//        nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
//        System.out.println(nombre);
        do {
            System.out.println("Dígame su primer apellido:");
            apellido1 = input.nextLine();
        } while (apellido1.length() <= 2);

        do{
            System.out.println("¿Tiene segundo apellido? S/N");
            segundo = input.nextLine().toUpperCase();
        }while(segundo.charAt(0) != 'S' && segundo.charAt(0) != 'N');
        

        if (segundo.charAt(0) == 'S') {
            do {
                System.out.println("Dígame su segundo apellido:");
                apellido2 = input.nextLine();
            } while (apellido2.length() <= 2);
            return nombre + " " + apellido1 + " " + apellido2;
        } else {
            return nombre + " " + apellido1;
        }
    }
    
    public static int menu(){
        Scanner input = new Scanner(System.in);        
        int opcion = 0;
                do{
            System.out.println("Elija una de las opciones disponibles:\n"
                    + "1. Datos de la cuenta\n"
                    + "2. IBAN\n"
                    + "3. Titular\n"
                    + "4. Saldo\n"
                    + "5. Ingreso\n"
                    + "6. Retirada\n"
                    + "7. Movimientos\n"
                    + "8. Salir");
            opcion = input.nextInt();
        }while(opcion<=1 && opcion>=8);
        
        return opcion;
    }
    
    public static void ingresoSaldo(CuentaBancaria OpenBank){
        Scanner input = new Scanner(System.in);
        double ingreso;
        int contador = 0;
        
        do{
            System.out.println("Dígame el importe a ingresar mayor a 0€ (3 intentos o menú principal)");
            ingreso = input.nextDouble();
            contador++;
            if(ingreso<=0)
                System.out.println("ERROR: no puede hacer ingresos de 0€ o negativos");
        }while(ingreso<=0 && contador<=3);
        
        if(ingreso>0)
            OpenBank.setIngreso(ingreso);
    }
    
        public static void retiradaSaldo(CuentaBancaria OpenBank){
        Scanner input = new Scanner(System.in);
        double retirada;
        int contador = 0;
        
        do{
            System.out.println("Dígame el importe a retirar debe ser mayor a 0€ (3 intentos o menú principal)");
            retirada = input.nextDouble();
            contador++;
            if(retirada<=0)
                System.out.println("ERROR: no puede hacer ingresos de 0€ o negativos");
        }while(retirada<=0 && contador<=3);
        
        if(retirada>0)
            OpenBank.setRetirada(retirada);
    }
        
        public static void mostrarMovimientos(CuentaBancaria OpenBank){
            double movimientos[];
            int longitud;
            
            longitud = OpenBank.getMovimientos().length;
            if(longitud==0){
                System.out.println("No existen movimientos actualmente");
            }
            else{
                movimientos = Arrays.copyOf(OpenBank.getMovimientos(),OpenBank.getMovimientos().length-1);
                for(int i=0; i<movimientos.length; i++){
                    System.out.println(movimientos[i]);
                }
                
            }
        }
    
    public static void menuSwitch(int opcion, CuentaBancaria OpenBank){
        
        
        switch(opcion){
            case 1:
                System.out.println(OpenBank.getDatosCuenta());
                break;
            case 2:
                System.out.println(OpenBank.getIBAN());
                break;
            case 3:
                System.out.println(OpenBank.getNOMBRE());
                break;
            case 4: 
                System.out.println(OpenBank.getSaldo());
                break;
            case 5:
                ingresoSaldo(OpenBank);
                break;
            case 6:
                retiradaSaldo(OpenBank);
                break;
            case 7:
                mostrarMovimientos(OpenBank);
                break;
        }
    }
}
