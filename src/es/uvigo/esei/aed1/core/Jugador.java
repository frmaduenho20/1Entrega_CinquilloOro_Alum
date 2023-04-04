/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano, convertir a String el objeto Jugador (toString)
 */
//borra
//Prueba que hice en clase
package es.uvigo.esei.aed1.core;
import es.uvigo.esei.aed1.iu.IU;
import es.uvigo.esei.aed1.iu.Main;


public class Jugador {
    private final String nombreJugador;
    private Mano mano;

    public Jugador(String nombreJugador, Mano mano) {
        this.nombreJugador = nombreJugador;
        mano = new Mano();
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
    
}
