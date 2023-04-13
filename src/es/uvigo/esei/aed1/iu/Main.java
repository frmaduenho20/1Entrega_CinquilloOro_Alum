
package es.uvigo.esei.aed1.iu;

import es.uvigo.esei.aed1.core.Juego;



//Inicio de Entrega 2 


public class Main {
    public static void main(String[] args) {
        IU iu = new IU();
        Juego cinquillo = new Juego(iu);
        cinquillo.jugar();
        
    }   
}