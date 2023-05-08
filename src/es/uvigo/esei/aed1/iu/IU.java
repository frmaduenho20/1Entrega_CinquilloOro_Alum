/**
 * Representa la interfaz del juego del Cinquillo-Oro, en este proyecto va a ser una entrada/salida en modo texto 
 * Se recomienda una implementación modular.
 */

package es.uvigo.esei.aed1.iu;


import es.uvigo.esei.aed1.core.Jugador;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class IU {
    private final Scanner teclado;

    public IU() {
            teclado = new Scanner(System.in).useDelimiter("\r?\n");
    }

    /**
     * Lee un num. de teclado
     * 
     * @param msg El mensaje a visualizar.
     * @return El num., como entero
     */
    public int leeNum(String msg) {
        do {
                System.out.print(msg);

                try {
                        return teclado.nextInt();
                } catch (InputMismatchException exc) {
                        teclado.next();
                        System.out.println("\nEntrada no válida. Debe ser un entero.");
                }
        } while (true);
    }

    public String leeString(String msg) {
        System.out.print(msg);
        return teclado.next();
    }

    public String leeString(String msg, Object... args) {
        System.out.printf(msg, args);
        return teclado.next();
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public void mostrarMensaje(String msg, Object... args) {
        System.out.printf(msg, args);
    }
    
    
    public int getNumJugadores(){
       int numJugadores = 0;
       
        do {
            System.out.println("Cuantos jugadores tendremos en la partida?");
            numJugadores = leeNum("Selecciona 3 o 4: ");
        } while (numJugadores != 3 && numJugadores != 4);
        
        return numJugadores;
    }

    public Collection<String> pedirDatosJugadores(){

        List<String> nombresJugadores = new LinkedList<>();
        String nombreJugador;
        
        int numJ = getNumJugadores();

        for (int i = 0; i < numJ; i++) {
            do {
                nombreJugador = leeString("Introduce el nombre del jugador " + (i+1) + ": ");
            } while (nombreJugador.trim().equals(""));
            
            //Agregar jugador a la cola
            nombresJugadores.add(nombreJugador);
        }
        return nombresJugadores;
    }
    
    public void mostrarJugador(Jugador jugador){
        System.out.println(jugador.toString());
    }
    
    public void mostrarJugadores(Queue<Jugador> jugadores){
        StringBuilder sb = new StringBuilder();
        
        sb.append("Mostrando los jugadores: \n\n");
        
        for (int i = 0; i < jugadores.size(); i++) {
            sb.append(jugadores.element().toString());
            jugadores.add(jugadores.remove());
        }
        sb.toString();
        
        System.out.println(sb);
    }
    
    /**
     * Método que pide la posicion de la carta que el 
     * jugador quiere poner en la mesa
     * 
     * @param j jugador que quiere introducir una carta en la mesa
     * @return pos la posicion de carta que se quiere poner en la mesa ya decrementada
     */
    public int pedirPosCarta(Jugador j){
        int pos = 0;
        
        do { 
            System.out.println(j.toStringMano());

            pos = leeNum("Introduce la posicion de la carta que quieras sacar: ");

            if (pos < 1 || pos > j.getNumCartasMano()) {
                System.out.println("Elige una posición valida");
            }
        } while (pos < 1 || pos > j.getNumCartasMano());
        
        pos--;
        
        return pos;
    }
    
    public void mostrarGanadores(Queue<Jugador> j){
        Jugador[] winners = new Jugador[j.size()];
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < winners.length; i++) {
            winners[i] = j.remove();
        }
        
        ordenarGanadores(winners);
        
        sb.append("\nGandores:");
        
        if(winners[0].getPuntos() == winners[1].getPuntos() && winners[0].getPuntos() == winners[1].getPuntos()){
            for (int i = 0; i < winners.length; i++) {
                sb.append("\n1º ").append(winners[i].getNombreJugador())
                        .append(" con: ")
                        .append(winners[i].getPuntos());     
            }
        }
        else if(winners[0].getPuntos() == winners[1].getPuntos()){
            sb.append("\n1º ").append(winners[1].getNombreJugador()).append(" con: ")
                    .append(winners[1].getPuntos());
        }
        
        else if(winners[1].getPuntos() == winners[2].getPuntos()){
            sb.append("\n2º ").append(winners[2].getNombreJugador()).append(" con: ")
                .append(winners[2].getPuntos());
        }
        else{
            for (int i = 0; i < winners.length; i++) {
                sb.append("\n").append(i + 1).append("º ")
                        .append(winners[i].getNombreJugador())
                        .append(" con: ")
                        .append(winners[i].getPuntos());     
            }
        }
        mostrarMensaje(sb.toString());
        
    }
    
    public void ordenarGanadores(Jugador[] aux){
        
        for (int pasada = 0; pasada < (aux.length - 1) / 2; pasada++) {
            for (int j = 0; j < (aux.length - pasada - 1); j++) {
                if (aux[j].getPuntos() < aux[j + 1].getPuntos()) {
                    Jugador temp = aux[j];
                    aux[j] = aux[j + 1];
                    aux[j + 1] = temp;
                }
            }

            for (int j = (aux.length - pasada - 2); j > pasada; j--) {
                if (aux[j].getPuntos() > aux[j - 1].getPuntos()) {
                    Jugador temp = aux[j];
                    aux[j] = aux[j - 1];
                    aux[j - 1] = temp;
                }
            }
        }
    }
    
}
