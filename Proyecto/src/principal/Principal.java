/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import ordenador.Departamento;
import ordenador.Ordenador;
import vista.Pantalla;


/**
 *
 * @author aleja
 */
public class Principal {
            public static void main(String[] args) {

        //Instrucciones del metodo
        Departamento o = new Departamento("Tienda Galvarro");
        Ordenador controlador = new Ordenador(o);
        Pantalla pa = new Pantalla(controlador);

        pa.mostrarOpciones();


    }
}
