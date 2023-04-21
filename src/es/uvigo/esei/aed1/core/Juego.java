
/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega). 
 * Se recomienda una implementación modular.
 */

package es.uvigo.esei.aed1.core;
import cola.*;
import java.util.Collection;

import cola.Cola;
import es.uvigo.esei.aed1.iu.IU;
import es.uvigo.esei.aed1.core.Mesa;

public class Juego{
  private final IU iu;
  private Cola<Jugador> jugadores;
  private Baraja baraja;
  private Mesa mesa;
    
    
public Juego(IU iu){
    this.iu = iu;
    this.jugadores = new EnlazadaCola();
    this.baraja = new Baraja();
    this.mesa = new Mesa();
}

    public void jugar() {
        System.out.println("Creando Jugadores... ");
        crearJugadores();
        
        System.out.println("Barajando...");
        baraja.barajar();
        
        System.out.println("Repartiendo cartas...");
        repartirCartas(jugadores, baraja);
        
        System.out.println("Mostrando jugadores con sus manos...");
        for (int i = 0; i < jugadores.tamaño(); i++) {
            System.out.println(jugadores.primero().toString());
            jugadores.insertar(jugadores.suprimir());
        }

        empiezaJugador(jugadores); // Decidimos quien empieza
        
        boolean added;
        boolean fin = false;
        int numCarta = 0;
        Carta c;
        
        do {
            if (!mesa.comprobarMano(jugadores.primero().getMano())) { // si no puede jugar se avisa y se pasa
                System.out.println("\n!!!El jugador: " + jugadores.primero().getNombreJugador() + " no tiene ninguna carta para jugar¡¡¡");
                jugadores.insertar(jugadores.suprimir());
            }

            do {
                System.out.println("\n" + mesa.toString());
                System.out.println(jugadores.primero());
                numCarta = iu.leeNum("\n" + "Qué carta quieres sacar?(1-" + jugadores.primero().getNumCartasMano() + "): ");

                if (numCarta < 1 || numCarta > jugadores.primero().getNumCartasMano()) {
                    System.out.println("Elige una posición válida");
                }
            } while (numCarta < 1 || numCarta > jugadores.primero().getNumCartasMano());
            numCarta--;
            c = mesa.addCartaMesa(jugadores.primero().sacarCartaMano(numCarta)); // Hay que validar que la carta se puede sacar o no cumple las condiciones del juego, si no la elimina aunque no la use
            
            if (c != null) {
                jugadores.primero().addCartaMano(c); // Parcheado por aquí abajo
                added = false;
            } else {
                added = true;
            }

            if (jugadores.primero().getMano().isEmpty()) {
                fin = true;
                System.out.println(mesa.toString());
            } else {
                if (added) {
                    jugadores.insertar(jugadores.suprimir());
                }
            }

        } while (fin == false);

            System.out.println("\nGanador: " + jugadores.primero().getNombreJugador());
        

    }
    public Cola<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Cola<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int getNumJugadores() {
        return jugadores.tamaño();
    }

    public Baraja getBaraja() {
        return baraja;
    }
    
    public void crearJugadores(){
        
        Collection<String> nombresJugadores = iu.pedirDatosJugadores();
        
        Jugador j;
        
        for (String s: nombresJugadores){
            j = new Jugador(s);
            jugadores.insertar(j);
        }
    }
    
    /**
     * Reparte las cartas dependiendo de numero de jugadores
     * si son 3 reparte 16 a cada jugador
     * si son 4 reparte 12 a cada jugador
     * 
     * @param jugadores una cola de los jugadores
     * @param baraja una lista de cartas barajada
     */
    public void repartirCartas(Cola<Jugador> jugadores, Baraja baraja){
        Jugador j;
        
        int numCartasMano = baraja.CARTAS_BARAJA;
        
        numCartasMano = numCartasMano / jugadores.tamaño();
        
        for (int i = 0; i < jugadores.tamaño(); i++) {
            j = jugadores.suprimir();
            for (int k = 0; k < numCartasMano; k++){
                j.addCartaMano(baraja.popCarta()); // usar el j. añadir carta nuevo
            }
            jugadores.insertar(j);
        }
    }
    
    /**
     * Selecciona aleatoriamente el jugador que empieza el jugador
     * @param jugadores
     * @return jugador que empieza la partida
     */
    public Jugador empiezaJugador(Cola<Jugador> jugadores){
        
        Jugador j;
        
        int posAleatoria;
        
        posAleatoria = (int) (Math.random() * jugadores.tamaño()) + 1;;
        System.out.println("Posición del jugador que inicia :" + posAleatoria);//Borrar luego

        for (int k = 1; k < posAleatoria; k++){
            j = jugadores.suprimir();
            jugadores.insertar(j);
        }
        return jugadores.primero();
    }

}