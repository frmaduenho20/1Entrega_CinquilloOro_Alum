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
    private Mesa mesa;
    //private int puntosPartida; //TODO Impelmentarlo y modificar todo lo necesario

    public Jugador(String nombreJugador) {
        mano = new LinkedList<>();
        this.nombreJugador = nombreJugador;
        this.mesa = new Mesa();
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

    public boolean comprobarMano(){
        Iterator itr = mano.iterator();
        boolean puede = false;
        Carta c;
        
        while (puede == false && itr.hasNext()) {
            c = (Carta) itr.next();
            if (comprobarCartaMano(c) == true) {
                puede = true;
            }
        }
        
        return puede;
    }
    
    /**
     * Comprueba la mano del jugador que se le pasa por parametro 
     * y que tenga una carta que se pueda añadir a la mesa.Si tiene return true, si no return false.
     * @param c
     * @return devuelve si el jugador puede jugar. 
     */
    public boolean comprobarCartaMano(Carta c){ 

        int i = 0;
        boolean valida = false;
        
        Carta peque;
        Carta grande;

        while (i < Carta.PALO.values().length - 1 && !c.getPalo().name().equalsIgnoreCase(Carta.PALO.values()[i].name())) {
            i++;

        }
        
        if (mesa.getPalos().get(i).isEmpty()) {
            if (c.getValor() == 5) {
                valida = true;
            }
        }
        else{ //TODO no funciona esta parte cuando se añaden todos los 5

            peque = (Carta) mesa.getPalos().get(i).getFirst();
            grande = (Carta) mesa.getPalos().get(i).getLast();

            if (c.getValor() == peque.getValor() - 1 || c.getValor() == grande.getValor() + 1) {
                valida = true;
            }
            
        }

        return valida;
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
