/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano, convertir a String el objeto Jugador (toString)
 */

package es.uvigo.esei.aed1.core;

//import java.util.LinkedList;

import java.util.List;

//import java.util.List;


public class Jugador extends Mano{
    private final String nombreJugador;
    private Mano mano; //Sobra el new mano y hay que meterlo en el constructor this.mano = new Mano()

    public Jugador(String nombreJugador, List<Carta> mano) {
        super(mano);
        this.nombreJugador = nombreJugador;
    }

    @Override
    public int getNumCartas() {
        return mano.getNumCartas();
    }
    
    @Override
    public Carta getCarta(int pos){
       return mano.getCarta(pos);
   }
    
    @Override
    public void addCarta(Carta nueva){
        mano.addCarta(nueva);
    }
    
    @Override
    public Carta sacarCarta(int pos){
        return mano.sacarCarta(pos);
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    private Mano getMano() {
        return mano;
    }

    private void setMano(Mano mano) {
        this.mano = mano;
    }
    
    public int getNumCartasMano(){
        return mano.getNumCartas();
    }
    
    private Carta sacarCartaMano(int pos){ // crear añadir carta y repartir según este nuevo método
        return mano.sacarCarta(pos);
    }
    
    public void addCartaMano(Carta c){
        mano.addCarta(c);
    }
    
    public String toStringMano(){
        return mano.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jugador: ").append(nombreJugador);
        sb.append("\nMano: \n").append(getMano()).append("\n");
        
        return sb.toString();
    }
 
    
}
