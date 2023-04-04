/*
* Representa la baraja española pero con 8 y 9, en total 48 cartas, 4 palos, valores de las cartas de 1 a 12. 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: barajar las cartas, devolver la carta situada encima del montón de cartas
 */

package es.uvigo.esei.aed1.core;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Baraja {
    public final int CARTAS_BARAJA=48;
    public final int CARTAS_PALO=12;
    private List<Carta> baraja;
    
    
//    System.out.println(cartas);
    
    public Baraja() {

        baraja = new LinkedList<>();
        Carta c;
        
        for (int i = 0; i <= Carta.Palo.values().length; i++) {
            for (int j = 0; j < CARTAS_PALO; j++) {  
                c = new Carta(Carta.Palo.values()[i], j);
                baraja.add(c);
            }    
        }
        
    } 
    
    // Método genérico para aleatorizar una lista en Java utilizando la reproducción aleatoria de Fisher-Yates
    public static<E> void barajar(List<E> list)
    {
        Random random = new Random();
        int n = list.size();

        // empieza desde el principio de la lista
        for (int i = 0; i < n - 1; i++)
        {
            // obtener un índice aleatorio `j` tal que `i <= j <= n`
            int j = i + random.nextInt(n - i);
 
            // intercambiar el elemento en la i-ésima posición en la lista con el elemento en
            // índice generado aleatoriamente `j`
            E carta = list.get(i);
            list.set(i, list.get(j));
            list.set(j, carta);
        }
    }
    
}
