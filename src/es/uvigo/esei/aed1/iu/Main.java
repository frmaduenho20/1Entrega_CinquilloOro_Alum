
package es.uvigo.esei.aed1.iu;

import es.uvigo.esei.aed1.core.Juego;



//Inicio de Entrega 2 

//Ordenar las manos por orden para la comodidad del juego
/**
 * 
 * 4. Interfaz Comparable
● Se utiliza para definir un orden natural en una clase: se
le indica a Java cómo comparar objetos de esa clase y
en qué orden se deben ordenar.
● Tiene un solo método “compareTo()” que se utiliza
para comparar dos objetos y que devuelve un:
    ○ valor negativo si el objeto que llama es menor que
el objeto que se pasa como parámetro,
    ○ cero si son iguales
    ○ un valor positivo si el objeto que llama es mayor
que el objeto que se pasa como parámetro
● Para ordenar un ArrayList, podemos usar el método
sort de Collections
* 
* https://moovi.uvigo.gal/pluginfile.php/1109705/mod_resource/content/1/TEMA7_ArrayListYComparable.pdf
* 
* https://jarroba.com/ordenar-un-arraylist-en-java/
* 
 * @author ferna
 */

public class Main {
    public static void main(String[] args) {
        IU iu = new IU();
        Juego cinquillo = new Juego(iu);
        cinquillo.jugar();
        
    }   
}