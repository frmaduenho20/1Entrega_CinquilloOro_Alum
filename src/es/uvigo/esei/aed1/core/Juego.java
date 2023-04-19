/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega). 
 * Se recomienda una implementación modular.
 */

package es.uvigo.esei.aed1.core;
import cola.*;
import java.util.Collection;

import cola.Cola;
//import es.uvigo.esei.aed1.core.Baraja;
//import es.uvigo.esei.aed1.core.Jugador;
import es.uvigo.esei.aed1.iu.IU;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.ListIterator;
//import java.util.Random;


public class Juego{
  private final IU iu;
  private Cola<Jugador> jugadores;
  private int numJugadores;
  private Baraja baraja;
    
    
public Juego(IU iu){
    this.iu = iu;
    this.jugadores = new EnlazadaCola();
    this.numJugadores = pedirNumJugadores();
    this.baraja = new Baraja();
}

    public void jugar() {
        System.out.println("Creando Jugadores... ");
        crearJugadores( iu.pedirDatosJugadores(numJugadores));
        
        System.out.println("Barajando...");
        baraja.barajar();
        
        System.out.println("Repartiendo cartas...");
        repartirCartas(jugadores, baraja);
        
        System.out.println("Mostrando jugadores con sus manos...");
        for (int i = 0; i < numJugadores; i++) {
            System.out.println(jugadores.primero().toString());
            jugadores.insertar(jugadores.suprimir());
        }
        
        System.out.println("Empieza el jugador: " 
                + empiezaJugador(jugadores).getNombreJugador());
        
    }
    public Cola<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Cola<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public Baraja getBaraja() {
        return baraja;
    }
 
    public int pedirNumJugadores(){ //Añadir si Escribe Cuatro, cuatro CUATRO...
        int numJugadores = 0;
       
        do {
            System.out.println("Cuantos jugadores tendremos en la partida?");
            numJugadores = iu.leeNum("Selecciona 3 o 4: ");
        }while (numJugadores != 3 && numJugadores != 4);
        
        return numJugadores;
    }
    
    public void crearJugadores(Collection<String> pedirDatosJugadores){
        
        Collection<String> nombresJugadores = pedirDatosJugadores;
        
        String [] nombres = new String[numJugadores];
        nombres = nombresJugadores.toArray(nombres);
        
        Jugador j;
        for (int i = 0; i < nombres.length; i++) {
            j = new Jugador(nombres[i]);
            jugadores.insertar(j);
        }
        
    }
    
    /**
     * Reparte las cartas dependiendo de numero de jugadores
     * si son 3 reparte 16 a cada jugador
     * si son 4 reparte 12 a cada jugador
     * 
     * @param Cola de jugadores
     * @param Lista baraja ya barajada
     */
    public void repartirCartas(Cola<Jugador> jugadores, Baraja baraja){
        Jugador j;
        int numCartasMano = 48;
        
        numCartasMano = numCartasMano / jugadores.tamaño();
        
        for (int i = 0; i < jugadores.tamaño(); i++) {
            j = jugadores.suprimir();
            for (int k = 0; k < numCartasMano; k++) {
                j.getMano().addCarta(baraja.popCarta()); // usar el j. añadir carta nuevo
            }
            jugadores.insertar(j);
        }
    }
    /**
     * Selecciona aleatoriamente el jugador que empieza el jugador
     * @param Cola de los jugadores
     * @return jugador que empieza la partida
     */
    public Jugador empiezaJugador(Cola<Jugador> jugadores){
        
        Jugador j;
        
        int posAleatoria;
        
        posAleatoria = (int) (Math.random() * numJugadores) + 1;;
        System.out.println("Posición del jugador que inicia :" + posAleatoria);//Borrar luego

        for (int k = 1; k < posAleatoria; k++){
            j = jugadores.suprimir();
            jugadores.insertar(j);
        }
        return jugadores.primero();
    }
}