
/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega). 
 * Se recomienda una implementación modular.
 */

package es.uvigo.esei.aed1.core;
import cola.*;
import java.util.Collection;

import cola.Cola;
import es.uvigo.esei.aed1.iu.IU;


public class Juego{
  private final IU iu;
  private Cola<Jugador> jugadores;
  private Baraja baraja;
  private Mesa mesa;
  //private static final PUNTOS_PARTIDA = 2;
  //private static PUNTOS_AS_DE_ORO = 2;
  //private contadorPartidas = ; Poner a 0 e incrementarlo al iniciar la partida o a 1 e incrementarlo al final de la partida si no se colocó el As de Oros
    
    
public Juego(IU iu){
    this.iu = iu;
    this.jugadores = new EnlazadaCola();
    this.baraja = new Baraja();
    this.mesa = new Mesa();
}

    public void jugar() {
        crearJugadores();
        System.out.println("Creando Jugadores... ");
        
        System.out.println("\nBarajando...");
        baraja.barajar();
        
        System.out.println("\nRepartiendo cartas...\n");
        repartirCartas();
        
        iu.mostrarJugadores(jugadores);

        empiezaJugador(); // Decidimos quien empieza
        
        boolean added;
        boolean fin_partida = false;
        boolean fin_juego = false;
        int numCarta = 0;
        Carta c; // (!mesa.comprobarCartaMano(jugadores.primero().getMano()))
        
        do {
            do {
                // TODO Arreglar bug cuando se añaden los 4 5s, si no puede jugar se avisa y se pasa
                if (!turnoJugador().comprobarMano()) { 
                    
                    System.out.println(turnoJugador().getNombreJugador() 
                            + " no tiene carta para jugar!!!\n");
                    pasarJugador();
                    
                } else {
                    
                    System.out.println( mesa.toString());
                    
                    System.out.println("\nTurno de: " + turnoJugador().getNombreJugador());
                    
                    numCarta = pedirPosCarta();
                    
                    c = mesa.addCartaMesa(turnoJugador().sacarCartaMano(numCarta));
                    
//                    if (c != null) {
//                        turnoJugador().addCartaMano(c);
//                        added = false;
//                    } else {
//                        added = true;
//                    }
                    added = anhadida(c); // este metodo resume los el if de arriba

                    if (turnoJugador().getNumCartasMano() == 0) { //TODO metodo comprobación de suma puntos partida
                        fin_partida = true;
                        
                        System.out.println(mesa.toString());
                    } 
                    else {
                        if (added) {
                            pasarJugador();
                        }
                    }
                }

            } while (fin_partida == false);
            //TODO metodo comprobación de suma puntos as oros que devuelva boolean fin_juego = true
        } while (fin_juego == false);
        //TODO en vez de ganador, sumamos 2 puntos por ganar la partida, incrementamos el contador de partidas. Si no se sacó el As de Oros volvemos al principio.
            System.out.println("\nGanador: " + turnoJugador().getNombreJugador()); 
        //TODO comprobación de si está el As de Oros, o por el metodo añadir o por un metodo estaCartaMesa()

        //TODO Mostrar Una tabla ordenada de los jugadores con sus puntos acumulados (Mostrar puesto, puede haber varios en la misma posición)
        
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
     */
    public void repartirCartas(){ 
        Jugador j;
        
        int numCartasMano = 48;
        
        numCartasMano = numCartasMano / jugadores.tamaño();
        
        for (int i = 0; i < jugadores.tamaño(); i++) {
            j = jugadores.suprimir();
            for (int k = 0; k < numCartasMano; k++){
                j.addCartaMano(baraja.popCarta()); 
            }
            jugadores.insertar(j);
        }
    }
    
    /**
     * Selecciona aleatoriamente el jugador que empieza el jugador
     * 
     * @return jugador que empieza la partida
     */
    public Jugador empiezaJugador(){
        int posAleatoria;

        posAleatoria = (int) (Math.random() * jugadores.tamaño()) + 1;

        for (int k = 1; k < posAleatoria; k++) {
            pasarJugador();
        }
        
        return turnoJugador();
    }
    
    /**
     * Metodo pasa el turno al siguiente jugador
     */
    public void pasarJugador(){ //TODO usar
        jugadores.insertar(jugadores.suprimir());
    }
    
    /**
     * Metodo que devuelve el jugador que tiene el turno
     * 
     * @return jugadores.primero() primer jugador de la cola que le toca sacar carta
     */
    public Jugador turnoJugador(){
        return jugadores.primero();
    }
    
    /**
     * Enseña el ganador del juego
     */
    public void mostrarGanador(){ //TODO cambiar el ganador simple por una tabla de los jugadores ordenada por sus puntos 
        System.out.println("\nGanador: " + jugadores.primero().getNombreJugador());
    }
    
    /**
     * Método que pide la posicion de la carta que el 
     * jugador quiere poner en la mesa
     * 
     * @return pos la posicion de carta que se quiere poner en la mesa ya decrementada
     */
    public int pedirPosCarta(){
        int pos = 0;
        
        do { 
            System.out.println("\n" + turnoJugador().toStringMano());

            pos = iu.leeNum("Introduce la posicion de la carta que quieras sacar: ");

            if (pos < 1 || pos > turnoJugador().getNumCartasMano()) {
                System.out.println("Elige una posición valida");
            }
        } while (pos < 1 || pos > turnoJugador().getNumCartasMano());
        
        pos--;
        
        return pos;
    }
    
    /**
     * Metodo que comprueba si la carta se a introducido en la mesa correctamente
     * 
     * @param c carta que el jugador quiere añadir
     * @return true si la carta fue añadida correctamente false si no lo ha hecho
     */
    public boolean anhadida(Carta c){
        boolean anhadida;
        
        if (c != null) {
            turnoJugador().addCartaMano(c);
            anhadida = false;
        } else {
            anhadida = true;
        }
        return anhadida;
    }
}