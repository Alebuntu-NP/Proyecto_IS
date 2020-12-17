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
        // El email a validar
        String email = "JUANMOGAL20@GMAIL";

        Matcher mather = pat.matcher(email);

        if (mather.find() == true) {
            System.out.println("El email ingresado es válido.");
        } else {
            System.out.println("El email ingresado es inválido.");
        }
    }
}
