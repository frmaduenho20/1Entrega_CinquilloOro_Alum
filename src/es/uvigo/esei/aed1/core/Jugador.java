/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano, convertir a String el objeto Jugador (toString)
 */

package es.uvigo.esei.aed1.core;

import java.util.LinkedList;
import java.util.List;

public class Jugador{
    private final String nombreJugador;
    private List<Carta> mano;
    //private int puntosPartida; //TODO Impelmentarlo y modificar todo lo necesario

    public Jugador(String nombreJugador) {
        mano = new LinkedList<>();
        this.nombreJugador = nombreJugador;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }
    
    public int getNumCartasMano(){
        return mano.size();
    }
    
    public Carta sacarCartaMano(int pos){
        return mano.remove(pos);
    }
    
    public void addCartaMano(Carta c){
        mano.add(c);
    }

    public List<Carta> getMano() {
        return mano;
    }

    public String toStringMano(){
        StringBuilder sb = new StringBuilder();
        System.out.println("\nMostrando la mano del jugador: ");
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
        sb.append("\n");
        sb.append(toStringMano());
        sb.append("\n");
        
        return sb.toString();
    }
 
    
}
