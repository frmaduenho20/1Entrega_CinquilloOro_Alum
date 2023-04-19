/*
* Representa la baraja española pero con 8 y 9, en total 48 cartas, 4 palos, valores de las cartas de 1 a 12. 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: barajar las cartas, devolver la carta situada encima del montón de cartas
 */

package es.uvigo.esei.aed1.core;
import java.util.LinkedList;
import java.util.List;
//import java.util.List;
//import java.util.Random;
//import es.uvigo.esei.aed1.iu.IU;

public class Baraja {

    public final int CARTAS_BARAJA=48;
    public final int CARTAS_PALO=12;
    private List<Carta> baraja;
    

    
    public Baraja() {

        baraja = new LinkedList<>();
        Carta c;
        
        for (int i = 0; i < Carta.Palo.values().length; i++) {
            for (int j = 1; j <= CARTAS_PALO; j++) {  
                c = new Carta(Carta.Palo.values()[i], j);
                baraja.add(c);
            }    
        }
    } 
    
    public void barajar(){
        Carta[] cartas = new Carta[CARTAS_BARAJA];
        List<Carta> nuevaBaraja = new LinkedList<>();
        
        for(int i = 0; i < CARTAS_BARAJA; i++){
            cartas[i] = baraja.remove(0);
        }
        
        int numCartas = CARTAS_BARAJA;
        int posAleatoria;
        
        for(int i = 0; i < CARTAS_BARAJA; i++){
            posAleatoria = (int) (Math.random() * ((CARTAS_BARAJA - 1) - i));
            
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
