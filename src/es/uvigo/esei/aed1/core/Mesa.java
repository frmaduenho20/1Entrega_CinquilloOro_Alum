/*
* Representa la Mesa de juego. 
* Estructura: se utilizará un TAD adecuado. Piensa que hay 4 palos y de cada palo se pueden colocar cartas 
* por cualquiera de los dos extremos (un array con 4 doblescolas parece lo más adecuado). La DobleCola se comentó en clase de teoría
* Funcionalidad: saber si es posible colocar una carta en la mesa, colocar una carta en la mesa, mostrar la mesa
 */
package es.uvigo.esei.aed1.core;
import java.util.ArrayDeque;
import java.util.Deque;
//import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

public class Mesa {
    private Deque<Carta> oros;
    private Deque<Carta> espadas;
    private Deque<Carta> copas;
    private Deque<Carta> bastos;

    private List<Deque> palos;

    //constructor
    public Mesa(){

        this.palos = new LinkedList<>();

        palos.add(oros = new ArrayDeque<>());
        palos.add(espadas = new ArrayDeque<>());
        palos.add(copas = new ArrayDeque<>());
        palos.add(bastos = new ArrayDeque<>());
    }

    /**
     * Metodo que introduce cartas en la mesa, si la carta se puede introducir,
     * la carta se introduce a la mesa y devuelve true. Si la carta no se puede
     * introducir salta un mensaje de error y devuelve false
     * 
     * @param c es la carta que quieres introducir
     * @return devules true si se añadió la carta, falso si no se añadió
     */
    public Carta addCartaMesa(Carta c){ //TODO hacer comprobación que si la carta es As de Oros y se puede añadir sumamos (contador de partidas * 2)

        int i = 0;
        boolean added = false;

        while (i < Carta.PALO.values().length-1 && !c.getPalo().name().equalsIgnoreCase(Carta.PALO.values()[i].name())) {
            i++;

        }

        if (palos.get(i).isEmpty()) {

            if (c.getValor() == 5) {
                palos.get(i).add(c);
                added = true;
                System.out.println("\nAñadida la carta: "+palos.get(i).getFirst().toString());
            } else {
                System.out.println("\n!!!Si no esta el 5 de "
                        + c.getPalo().toString().toLowerCase()
                        + " no se puede añadir la carta¡¡¡");
            }
        }else {

            Carta peque = (Carta) palos.get(i).getFirst();
            Carta grande = (Carta) palos.get(i).getLast();

            if (c.getValor() == peque.getValor() - 1) {

                palos.get(i).addFirst(c);
                added = true;
                System.out.println("\nAñadida la carta: "+palos.get(i).getFirst().toString());

            } else if (c.getValor() == grande.getValor() + 1) {

                palos.get(i).addLast(c);
                added = true;
                System.out.println("\nAñadida la carta: "+palos.get(i).getLast().toString());

            } else {
                System.out.println("\n!!!La carta ("+c.toString()+") no es consecutiva¡¡¡");
            }
        }
        if (added) {
//            if () { //Si es el As de Oros añadimos los puntos al jugador
//                
//            }
            c = null;
        }
        return c;
    }
    
    /**
     * Comprueba la mano del jugador que se le pasa por parametro 
     * y que tenga una carta que se pueda añadir a la mesa. Si tiene return true, si no return false.
     * @param mano
     * @return devuelve si el jugador puede jugar. 
     */
    public boolean comprobarMano(List<Carta> mano){ 

        int i;
        boolean puede = false;
        Carta c;

        int j = 0;
        while (j < mano.size() && puede == false) {
            
            c = mano.get(j);
            
            i=0;
            while (i < Carta.PALO.values().length - 1 && !c.getPalo().name().equalsIgnoreCase(Carta.PALO.values()[i].name())) {
                i++;

            }

            if (palos.get(i).isEmpty()) {
                if (c.getValor() == 5) {
                    puede = true;
                }
                
            } else {

                Carta peque = (Carta) palos.get(i).getFirst();
                Carta grande = (Carta) palos.get(i).getLast();

                if (c.getValor() == peque.getValor() - 1 || c.getValor() == grande.getValor() + 1) {
                    puede = true;

                }
            }
            
            j++;

        }
        
        
        return puede;
    }
/**
 * Muestra los palos de la mesa y devuelve un string del palo con la 
 * posicion que se le pasa por parametro.
 * @param pos
 * @return string del palo.
 */
    public String toStringPalo(int pos) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Carta c;
        if (palos.get(pos).isEmpty()) {
            sb.append(" *Palo vacío*");
        } else {
            while (i < palos.get(pos).size()) {
                c = (Carta) palos.get(pos).remove();
                if (c.getValor() == 1) {

                    sb.append(" || ").append(c.getValor()).append(" -");
                } else if (c.getValor() == 12) {

                    sb.append("- ").append(c.getValor()).append(" || ");
                } else {

                    sb.append("- ").append(c.getValor()).append(" -");
                }

                palos.get(pos).add(c);
                i++;
            }
        }

        return sb.toString();
    }

    @Override 
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append("Mesa:\n");
        boolean vacia = true;
        for (int i = 0; i < palos.size(); i++) {
            if (!palos.get(i).isEmpty()) {
                vacia = false;
            }
        }
        if (vacia) {
            sb.append("\n*Vacía*");
        } else {
            for (int i = 0; i < palos.size(); i++) {
            sb.append("\n").append(Carta.PALO.values()[i].name()).append(": ");
            sb.append(toStringPalo(i));
        }
        }

        return sb.toString(); 
    } 

}