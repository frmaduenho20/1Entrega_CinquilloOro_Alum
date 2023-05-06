/*
* Representa la Mesa de juego. 
* Estructura: se utilizará un TAD adecuado. Piensa que hay 4 palos y de cada palo se pueden colocar cartas 
* por cualquiera de los dos extremos (un array con 4 doblescolas parece lo más adecuado). La DobleCola se comentó en clase de teoría
* Funcionalidad: saber si es posible colocar una carta en la mesa, colocar una carta en la mesa, mostrar la mesa
 */
package es.uvigo.esei.aed1.core;
import java.util.ArrayDeque;
import java.util.Deque;


public class Mesa {
    public static final int NUM_PALOS = 4;
    private Deque[] palos;

    //constructor
    public Mesa(){
        this.palos = new Deque[NUM_PALOS];
        
        for (int i = 0; i < NUM_PALOS; i++) {
            palos[i] = new ArrayDeque<Carta>();
        }
    }

    public Deque[] getPalos() {
        return palos;
    }
    
    public Carta getFirstCartaPalo(int posPalo){
        return (Carta) palos[posPalo].getFirst();
    }
    
    public Carta getLastCartaPalo(int posPalo){
        return (Carta) palos[posPalo].getLast();
    }
    
    

    /**
     * Metodo que introduce cartas en la mesa, si la carta se puede introducir,
     * la carta se introduce a la mesa y devuelve true. Si la carta no se puede
     * introducir salta un mensaje de error y devuelve false
     * 
     * @param c es la carta que quieres introducir
     * @return devuelve true si se añadió la carta, falso si no se añadió
     */
    public boolean addCartaMesa(Carta c){ //TODO Pasar todo el método a Jugador y enviar aquí la carta despues de comprobar que se puede introducir //TODO hacer comprobación que si la carta es As de Oros y se puede añadir sumamos (contador de partidas * 2)

        int i = 0;
        boolean added = true;
        boolean puede = puedeMeterse(c);
        
        if(puede){
            
            while (i < Carta.PALO.values().length-1 && !c.getPalo().name().equalsIgnoreCase(Carta.PALO.values()[i].name())) {
                i++;
            }
            
            if(c.getValor() == 5){
                palos[i].add(c);
                added = true;
            }
            else if(c.getValor() < 5){
                palos[i].addFirst(c);
                added = true;
            }
            else{
                palos[i].addLast(c);
                added = true;
            }
            
            if(added){
                System.out.println("\nAñadida la carta: " + c.toString());
            }
            else{
                System.out.println("\n La carta no se a podido añadir...");
            }
            
            return added;
        }
        else{
            System.out.println("\nLa carta ("+c.toString()+") no es consecutiva!!!");
            return false;
        }
        
    }
    
    /**
     * Comprubea si la Carta c pasada por parametro se puede 
     * introducir en la mesa en su palo correspondiente
     * @param c Carta que se quiere introducir en la mesa
     * @return devueleve true si la carta se puede añadir false si no se puede
     */
    public boolean puedeMeterse(Carta c){
        int i = 0;
        boolean puede = false;
        
        while (i < Carta.PALO.values().length-1 && !c.getPalo().name().equalsIgnoreCase(Carta.PALO.values()[i].name())) {
            i++;
        }
        
        if (palos[i].isEmpty() && c.getValor() == 5) {
            return true;
        }
        else if(palos[i].isEmpty() && c.getValor() != 5){
            System.out.println("\nSi no esta el 5 de "
                        + c.getPalo().toString().toLowerCase()
                        + " no se puede añadir la carta!!!");
            return false;
        }
        else {
            if ( !(palos[i].isEmpty()) && c.getValor() != 5){
                
                Carta peque = (Carta) palos[i].getFirst();
                Carta grande = (Carta) palos[i].getLast();
                
                if(c.getValor() == (peque.getValor() - 1)){
                    puede = true;
                }
                else if(c.getValor() == (grande.getValor() + 1)){
                    puede = true;
                }
                else{
                    puede = false;
                }
            }
            return puede;
        }

    }
    
    /**
     * Método que devuleve una carta que no se a podido introducir en la mesa
     * 
     * @param c carta que se quiere devolver al jugador
     * @return carta que no se ha podido introducir en la mesa y se devulve al jugador
     */
    public Carta devolverCarta(Carta c){
        return c;
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
        
        if (palos[pos].isEmpty()) {
            
            sb.append("*Palo vacío*");
        } else {
            
            while (i < palos[pos].size()) {
                c = (Carta) palos[pos].remove();
                if (c.getValor() == 1) {
                    sb.append(" || ").append(c.getValor()).append(" -");
                } 
                else if (c.getValor() == 12) {

                    sb.append("- ").append(c.getValor()).append(" || ");
                } 
                else {
                    sb.append("- ").append(c.getValor()).append(" -");
                }

                palos[pos].add(c);
                i++;
            }
            
        }

        return sb.toString();
    }

    @Override 
    public String toString() { 
        
        StringBuilder sb = new StringBuilder(); 
        boolean vacia = true;
        
        sb.append("Mesa:\n");
        
        for (int i = 0; i < palos.length; i++) {
            if (!palos[i].isEmpty()) {
                vacia = false;
            }
        }
        
        if (vacia) {
            sb.append("*Vacia*");
        } 
        else {
            for (int i = 0; i < palos.length; i++) {
                sb.append("\n").append(Carta.PALO.values()[i].name()).append(": ");
                sb.append(toStringPalo(i));
            }
        }

        return sb.toString(); 
    } 

}