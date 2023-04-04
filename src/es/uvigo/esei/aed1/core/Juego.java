/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega). 
 * Se recomienda una implementación modular.
 */

package es.uvigo.esei.aed1.core;
import cola.*;
import java.util.Collection;

import cola.Cola;
import es.uvigo.esei.aed1.core.Baraja;
import es.uvigo.esei.aed1.core.Jugador;
import es.uvigo.esei.aed1.iu.IU;


public class Juego{
  private final IU iu;
    
    
public Juego(IU iu){
    this.iu = iu;

}

public void jugar(){
    int numJugadores = iu.getNumJugadores();
    Cola<Jugador> jugadores = null;

    
}

   
 public static Cola<Jugador> crearJugadores(Cola<Jugador> jugadores, int numJugadores, String[] nombresJugadores){
        
        Baraja baraja = new Baraja(); 
        baraja.barajar();                                       
        
        int numCartas = numCartasPorJugador(numJugadores);
        
        List<Jugador> nuevosJugadores = new LinkedList<>();
        for(int i = 0; i < numJugadores; i++){
            Jugador jugador = new Jugador(nombresJugadores[i]);
            
            for(int j = 0; j < numCartas; j++){      
                jugador.añadirCartaMano(baraja.popCarta());
            }
            
            if(jugadores != null){
                for(int k = 0; k < jugadores.get(i).getPuntuacionesRondasTamaño(); k++){
                    jugador.añadirPuntuacion(jugadores.get(i).getPuntuacionRonda(k));
                }
                
            }
            
            nuevosJugadores.add(jugador);                   
        }
        
        return nuevosJugadores;
    }
    
}