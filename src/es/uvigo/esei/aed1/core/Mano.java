/*
 * Representa la mano de cada jugador, formada por 12 o 16 cartas
 */
package es.uvigo.esei.aed1.core;
import java.util.LinkedList;
import java.util.List;


abstract class Mano { //hacer privada
    
    private List<Carta> mano; 

    public Mano(List<Carta> mano) {
        this.mano = mano;
    }


    public int getNumCartas() {
        return mano.size();
    }
    
    public Carta getCarta(int pos){
       return mano.get(pos);
   }
    
    public void addCarta(Carta nueva){
        mano.add(nueva);
    }
    
    public Carta sacarCarta(int pos){
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