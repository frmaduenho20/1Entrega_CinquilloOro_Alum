/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega). 
 * Se recomienda una implementación modular.
 */

//Esto son cambio

package es.uvigo.esei.aed1.core;
import es.uvigo.esei.aed1.iu.IU;
import es.uvigo.esei.aed1.iu.Main;
import cola.*;
import java.util.Collection;

public class Juego{
  private final IU iu;
    
    
public Juego(IU iu){
    this.iu = iu;

}

public void jugar(){
    int numJugadores = Jugador.class.
}

public Jugador leeJugador() throws Exception {
  System.out.println("\nIntroduce los datos del nuevo jugador:");
  
  String nombre = leeCadena("\tNombre: ");

  return new Jugador(nombre, mano);
    
}
