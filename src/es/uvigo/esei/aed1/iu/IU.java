/**
 * Representa la interfaz del juego del Cinquillo-Oro, en este proyecto va a ser una entrada/salida en modo texto 
 * Se recomienda una implementación modular.
 */
// 
package es.uvigo.esei.aed1.iu;

import es.uvigo.esei.aed1.core.Jugador;
import es.uvigo.esei.aed1.core.Juego;
import java.util.Collection;
import cola.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
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
                        System.out.println("Entrada no válida. Debe ser un entero.");
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
            numJugadores = leeNum("Selecciona 3 o 4");
        } while (numJugadores != 3 && numJugadores != 4);
        
        return numJugadores;
    }

    public Collection<String> pedirDatosJugadores(int numJugadores){

        List<String> nombresJugadores = new LinkedList<>();
        String nombreJugador;

        for (int i = 0; i < numJugadores; i++) {
            do {
                nombreJugador = leeString("Introduce el nombre del jugador " + (i+1) + ": ");
            } while (nombreJugador.trim().equals(""));
            //Agregar jugador a la cola
            nombresJugadores.add(nombreJugador);
        }
        return nombresJugadores;
    }
    
//    public String [] pedirDatosJugadores2(int numJugadores){
//        String [] nombreJugadores = new String[numJugadores];
//        
//        System.out.println("Introduciendo los nombre de los jugadores en orden.");
//        
//        for (int i = 0; i < nombreJugadores.length; i++) {
//            nombreJugadores[i] = leeString("Introduce el nombre de jugador: ");
//        }
//        
//        return nombreJugadores;
//    }
    
}
