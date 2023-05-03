/*
* Representa la baraja española pero con 8 y 9, en total 48 cartas, 4 palos, valores de las cartas de 1 a 12. 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: barajar las cartas, devolver la carta situada encima del montón de cartas
 */

package es.uvigo.esei.aed1.core;

import java.util.LinkedList;
import java.util.List;


public class Baraja{

    public final int CARTAS_PALO=12;
    private List<Carta> baraja;
    

    
    public Baraja() {

        baraja = new LinkedList<>();
        Carta c;
        
        for (int i = 0; i < Carta.PALO.values().length; i++) {
            for (int j = 1; j <= CARTAS_PALO; j++) {  
                c = new Carta(Carta.PALO.values()[i], j);
                baraja.add(c);
            }    
        }
    } 
    
    public void barajar(){
        Carta[] cartas = new Carta[baraja.size()];
        List<Carta> nuevaBaraja = new LinkedList<>();
        
        for(int i = 0; i < 48; i++){
            cartas[i] = baraja.remove(0);
        }
        
        int numCartas = cartas.length;
        int posAleatoria;
        
        for(int i = 0; i < 48; i++){
            posAleatoria = (int) (Math.random() * ((48 - 1) - i));
            
            nuevaBaraja.add(cartas[posAleatoria]);
            
            for(int j = posAleatoria; j < numCartas - 1; j++ ){
                cartas[j] = cartas[j+1];
            }
            numCartas--;
        }
        baraja = nuevaBaraja;
    }
    
    public Carta popCarta(){
        return baraja.remove(0);
    }
    
}
