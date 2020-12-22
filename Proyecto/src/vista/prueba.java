/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Juan
 */
public class prueba {

    public static void main(String[] args) {

        // Patrón para validar el email
        Pattern pat = Pattern.compile("([A-Za-z0-9]+(\\.?[A-Za-z0-9])*)+@(([A-Za-z]+)\\.([A-Za-z]+))+");
        
        //patro para validar dni 
        Pattern patron = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
        

        
        // El email a validar
        String email = "FF@GMAIL.COM";
        
        // El email a validar
        String dni = "47426785A";

        Matcher matherEMAIL = pat.matcher(email);

        //Matcher matherDNI = patron.matcher(dni);
        
        if (matherEMAIL.find() == true) {
            System.out.println("El email ingresado es válido.");
        } else {
            System.out.println("El email ingresado es inválido.");
        }

//        if (matherDNI.find() == true && dni.length() == 9) {
//            System.out.println("El dni ingresado es válido.");
//        } else {
//            System.out.println("El dni ingresado es inválido.");
//        }


    }
}
