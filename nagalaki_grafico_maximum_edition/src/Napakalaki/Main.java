/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Napakalaki;

/**
 *
 * @author jorge
 */
public class Main {

    public static void main(String[] args) {
        Napakalaki juego = Napakalaki.getInstance();
        Vista v = new VentanaPrincipal(juego);
        //juego.setVista(v); // aquí irían otras declaraciones
                // e instrucciones que puedan ser necesarias

        v.mostrar(args); // esta instrucción debe ser la última del método
    }
}
