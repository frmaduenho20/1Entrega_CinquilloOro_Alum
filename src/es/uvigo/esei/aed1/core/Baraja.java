/*
* Representa la baraja española pero con 8 y 9, en total 48 cartas, 4 palos, valores de las cartas de 1 a 12. 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: barajar las cartas, devolver la carta situada encima del montón de cartas
 */

package es.uvigo.esei.aed1.core;
import java.util.LinkedList;

public class Baraja {
    public final int CARTAS_BARAJA=48;
    public final int CARTAS_PALO=12;
    Carta c;
    ArrayList<Carta> cartas = new ArrayList<String>();
    cartas.add("Volvo");
    
    
    
//    System.out.println(cartas);
    
    public Baraja(Carta[] cartas) {
        cartas = new Carta[CARTAS_BARAJA];
        
        for (int i = 0; i <= Carta.Palo.values().length; i++) {
            for (int j = 0; j < CARTAS_PALO; j++) {
                c = new Carta(Carta.Palo.values()[i], j);
                cartas[i] = c; 
                System.out.println(cartas[i]);
            }    
        }
    }

    public Carta[] getCartas() {
        return cartas;
    }

    public void setCartas(Carta[] cartas) {
        this.cartas = cartas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Baraja{");
        sb.append("\nCARTAS POR BARAJA=").append(CARTAS_BARAJA);
        sb.append("\nCARTAS POR PALO=").append(CARTAS_PALO);
        sb.append("\nPALOS: ");
        for (int i = 0; i < Carta.Palo.values().length; i++) {
            sb.append(Carta.Palo.values()[i].name());
        }  
        sb.append("\nCARTAS");
        for (int i = 0; i < CARTAS_BARAJA; i++) {
            sb.append(cartas[i].toString());
            
        }
        sb.append("\n}");
        return sb.toString();
    }

    
}
