
/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega). 
 * Se recomienda una implementación modular.
 */

package es.uvigo.esei.aed1.core;
import java.util.Collection;


import static es.uvigo.esei.aed1.core.Carta.PALO.OROS;
import es.uvigo.esei.aed1.iu.IU;
import java.util.LinkedList;
import java.util.Queue;


public class Juego{
  private final IU iu;
  private Queue<Jugador> jugadores;
  private Baraja baraja;
  private Mesa mesa;
  private static final int PUNTOS_PARTIDA = 4;
  private int pAsOros;
  
    
public Juego(IU iu){
    this.iu = iu;
    this.jugadores = new LinkedList<>();
    this.mesa = new Mesa();
    this.pAsOros = 2;
}

    public void jugar() {
        
        boolean added;
        boolean fin_partida = false;
        boolean fin_juego = false;
        int numCarta;
        Carta c;
        
        crearJugadores();
        System.out.println("Creando Jugadores... ");
        
        do {
            
            empiezaPartida();
            
            do {//TODO añadir variable Jugador jugadorConTurno y cambiarlo cada vez que se compruebe o modifique (jugadores.peek() vamos que muestra la información del primer jugador que es el que está jugando)
                
                added = false;
                
                if (!turnoJugador().comprobarMano(mesa)) {
                    
                   iu.mostrarMensaje("\n" + turnoJugador().getNombreJugador() 
                           + " no tiene carta para jugar!!");
                   
                } else{
                    
                    do{
                        iu.mostrarMensaje(mesa.toString());
                        
                        iu.mostrarMensaje("\nTurno de: " + turnoJugador().getNombreJugador());
                        
                        numCarta = iu.pedirPosCarta(turnoJugador());
                    
                        c = turnoJugador().sacarCartaMano(numCarta);
                        
                        if(mesa.puedeMeterse(c)){
                            
                            added = mesa.addCartatoMesa(c);
                            
                            if (c.getPalo().toString().equalsIgnoreCase("OROS") //TODO convertir (c.getPalo().toString().equalsIgnoreCase("OROS") && c.getValor() == 1) en método esLaCarta(int valor, Carta.Palo palo) ( palo == Carta.PALO.OROS)
                                    && c.getValor() == 1) {
                                
                                if (added) {
                                    turnoJugador().sumarPuntos(pAsOros);
                                    fin_juego = true;
                                }
                                
                            }
                            
                            iu.mostrarMensaje("\nPuesta la carta: " + c.toString() + "\n");
                        }
                        else{
                            turnoJugador().addCartaMano(c);
                        }

                    }while(!added);
                    
                    if(turnoJugador().getNumCartasMano() == 0){
                        fin_partida = true;
                        turnoJugador().sumarPuntos(PUNTOS_PARTIDA);
                        iu.mostrarMensaje(mesa.toString());
                    }
                    
                }
                
                if(!fin_partida) pasarJugador();
                
            } while (!fin_partida);
            
            iu.mostrarMensaje("\nGanador de la partida: " + turnoJugador().getNombreJugador());
            
            terminarPartida();
            
            if(!fin_juego){
                this.pAsOros += 2;
            }
            
        } while (!fin_juego);
            
        iu.mostrarGanadores(jugadores);
    }
    
    /**
     * Método que devulve la cantidad de jugadores que juegan
     * @return numero de jugadores que juegan
     */
    public int getNumJugadores() {
        return jugadores.size();
    }
    
    /**
     * Crea los jugadores que van a jugar
     */
    public void crearJugadores(){
        
        Collection<String> nombresJugadores = iu.pedirDatosJugadores();
        
        Jugador j;
        
        for (String s: nombresJugadores){
            j = new Jugador(s);
            jugadores.add(j);
        }
    }
    
    /**
     * Reparte las cartas dependiendo de numero de jugadores
     * si son 3 reparte 16 a cada jugador
     * si son 4 reparte 12 a cada jugador
     */
    public void repartirCartas(){ 
        Jugador j;
        
        int numCartasMano = 48;
        
        numCartasMano = numCartasMano / getNumJugadores();
        
        for (int i = 0; i < getNumJugadores(); i++) {
            j = jugadores.remove();
            for (int k = 0; k < numCartasMano; k++){
                j.addCartaMano(baraja.popCarta()); 
            }
            jugadores.add(j);
        }
    }
    
    /**
     * Selecciona aleatoriamente el jugador que empieza el jugador
     * 
     * @return jugador que empieza la partida
     */
    public Jugador empiezaJugador(){
        int posAleatoria;

        posAleatoria = (int) (Math.random() * getNumJugadores()) + 1;

        for (int k = 1; k < posAleatoria; k++) {
            pasarJugador();
        }
        
        return turnoJugador();
    }
    
    /**
     * Metodo pasa el turno al siguiente jugador
     */
    public void pasarJugador(){
        jugadores.add(jugadores.remove());
    }
    
    /**
     * Metodo que devuelve el jugador que tiene el turno
     * 
     * @return jugadores.primero() primer jugador de la cola que le toca sacar carta
     */
    public Jugador turnoJugador(){
        return jugadores.peek();
    }
    
    /**
     * Cuando se termina una partida este vacia la mesa 
     * y las manos de los jugadores e incrementa los puntos del As de Oros
     */
    public void terminarPartida(){
        vaciarManos();
        mesa.vaciarMesa();
    }
    
    /**
     * Método que empieza la partida, crea la baraja,
     * baraja esta misma y reparte las cartas
     */
    public void empiezaPartida(){
        
        //Creamos la baraja con la que se va a jugar
        this.baraja = new Baraja();
        
        //La barajamos
        iu.mostrarMensaje("\nBarajando...");
        baraja.barajar();

        //Repartimos las cartas
        iu.mostrarMensaje("\nRepartiendo cartas...\n");
        repartirCartas();

        //Se muestran los jugadores que van a jugar
        iu.mostrarJugadores(jugadores);

        // Se escoge el jugador que empieza aleatoriamente
        empiezaJugador();
    }
    
    /**
     * Metodo que vacia las manos de los jugadores una vez que acaba la partida
     */
    public void vaciarManos(){
        
        Jugador pj = turnoJugador();
        
        for(int j = 0; j < getNumJugadores(); j++){
            if(pj.getNumCartasMano() != 0){
                for (int i = 0; i < pj.getNumCartasMano(); i++) {
                    pj.sacarCartaMano(0);
                }
            }
            pasarJugador();
        }
    }
    
    public boolean esLaCarta(int valor, Carta.PALO palo){
        boolean es = false;
        
        return es;
    }
    
}