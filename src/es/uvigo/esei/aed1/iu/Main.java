
package es.uvigo.esei.aed1.iu;

import es.uvigo.esei.aed1.core.Juego;

//TODO Ordenar las manos por orden para la comodidad del juego (Interfaz Comparable - compareTo() - negativo menor que el objeto que se pasa como parámetro, cero = , positivo si el objeto que llama es mayor)
//Para ordenar un ArrayList, podemos usar el método sort de Collections

public class Main {
    public static void main(String[] args) {
        IU iu = new IU();
        Juego cinquillo = new Juego(iu);
        cinquillo.jugar();
        
    }   
}