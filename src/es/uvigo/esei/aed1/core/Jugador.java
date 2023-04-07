/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano, convertir a String el objeto Jugador (toString)
 */
//borra
//Prueba que hice en clase
package es.uvigo.esei.aed1.core;

//import java.util.LinkedList;
//import java.util.List;


public class Jugador {
    private final String nombreJugador;
    private Mano mano = new Mano();

    public Jugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        this.setMano(mano);

    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public Mano getMano() {
        return mano;
    }

    public void setMano(Mano mano) {
        this.mano = mano;
    }
    
    public int getNumCartasMano(){
        return mano.getNumCartas();
    }
    
    public Carta sacarCartaMano(int pos){
        return mano.sacarCarta(pos);
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
