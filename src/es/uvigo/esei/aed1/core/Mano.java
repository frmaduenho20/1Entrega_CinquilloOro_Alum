/*
 * Representa la mano de cada jugador, formada por 12 o 16 cartas
 */
package es.uvigo.esei.aed1.core;
import java.util.LinkedList;
//import java.util.List;


public class Mano { //hacer privada
    
    LinkedList<Carta> mano; //Con list llega como interfaz y luego creamos en el constructor con la clase LinkedList
    int numCartas; // Sobra porque se usa el size, eliminar también getters y modificar donde aparezca

    public Mano() {
       mano = new LinkedList<>();
       numCartas = 0;
    }

    public int getNumCartas() {
        return numCartas;
    }
    
    public Carta getCarta(int pos){
       return mano.get(pos);
   }
    
    public void addCarta(Carta nueva){
        mano.add(nueva);
        numCartas++;
    }
    
    public Carta sacarCarta(int pos){
        numCartas--;
        return mano.remove(pos);
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < mano.size(); i++) {
            sb.append(i+1).append(": ").append(mano.get(i)).append("\n");
            
        }
        return sb.toString();
    }

    
    
    
}