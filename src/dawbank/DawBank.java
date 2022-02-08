/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dawbank;

/**
 *
 * @author nostromo
 */
import java.util.Scanner;

public class DawBank {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String iban;
               
        do{
            System.out.println("Dame el IBAN de tu cuenta bancaria (2 letras más 22 números)");
            iban = input.nextLine();
        }while(!verificarIBAN(iban));//While True... hemos de repetir hasta que la función nos devuelva True y hacer un NOT para salir del bucle
        iban = iban.toUpperCase();
        //System.out.println(iban);
    }
    
    public static boolean verificarIBAN(String iban){
        return iban.matches("^[A-Za-z]{2}[0-9]{22}$+");//Que empieza y acaba por lo indicado... A-Za-z incluidos 2 carácteres al comienzo seguido de 22 dígitos y sólo una vez +
    }
}
