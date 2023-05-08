/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano, convertir a String el objeto Jugador (toString)
 */

package es.uvigo.esei.aed1.core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Jugador{
    
    private final String nombreJugador;
    private List<Carta> mano;
    private int puntos; 

    public Jugador(String nombreJugador) {
        mano = new LinkedList<>();
        this.nombreJugador = nombreJugador;
        this.puntos = 0;
    }

    public String getNombreJugador() {
        return this.nombreJugador;
    }

    public int getPuntos() {
        return this.puntos;
    }

    private void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    /**
     * Metodo que devulve el numero de carta que tiene la mano del juagdor
     * @return int numero de cartas que tiene el jugador
     */
    public int getNumCartasMano(){
        return this.mano.size();
    }
    
    
    public Carta sacarCartaMano(int pos){
        return this.mano.remove(pos);
    }
    
    public void addCartaMano(Carta c){
        this.mano.add(c);
    }
    
    /**
     * Metodo que compruba carta por carta de la mano si 
     * se puede introducir en la mesa
     * 
     * @return boolean true si tiene alguna carta para jugar 
     * y false si no tiene ninguna carta para jugar.
     */
    public boolean comprobarMano(Mesa mesa){
        boolean puede = false;
        int i = 0;
        
        while(!puede && i < mano.size()){
            
            Carta c = mano.get(i);
            puede = mesa.puedeMeterse(c);    
            i++;
        }
        return puede;
    }
    
    public void sumarPuntos(int puntosGanados){
        this.setPuntos( getPuntos() + puntosGanados);
    }

    public String toStringMano(){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < getNumCartasMano(); i++) {
            sb.append(i+1).append(": ");
            sb.append(mano.get(i).toString());
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Jugador: ").append(nombreJugador);
        sb.append("\nMano del jugador: \n");
        sb.append(toStringMano());
        sb.append("\n");
        
        return sb.toString();
    }
 
    
}
