
package es.uvigo.esei.aed1.iu;

import es.uvigo.esei.aed1.core.Juego;

/**
 * @author ferna
 * 
 * Borrar carta As de Oros

 * Método comprobarAsOros (juego 71)

 * mostrarGanadores (iu 143) mostrar el/los ganadores y no mostrar todos

 * ordenarGanadores no es parte de iu, cambiarlo a juego

 * Meter puedemeterse dentro de addcartatomesa o hacer comprobación de que es anterior o siguiente

 * turnoJugador 10 accesos a pila en vez de la primera vez que se asigna y cuando se actualiza una variable
 */

public class Main {
    public static void main(String[] args) {
        IU iu = new IU();
        Juego cinquillo = new Juego(iu);
        cinquillo.jugar();
        
    }   
}