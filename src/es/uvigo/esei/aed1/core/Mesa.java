/*
* Representa la Mesa de juego. 
* Estructura: se utilizará un TAD adecuado. Piensa que hay 4 palos y de cada palo se pueden colocar cartas 
* por cualquiera de los dos extremos (un array con 4 doblescolas parece lo más adecuado). La DobleCola se comentó en clase de teoría
* Funcionalidad: saber si es posible colocar una carta en la mesa, colocar una carta en la mesa, mostrar la mesa
 */
package es.uvigo.esei.aed1.core;
import static es.uvigo.esei.aed1.core.Carta.PALO.OROS;
import java.util.ArrayDeque;
import java.util.Deque;



public class Mesa {
    public static final int NUM_PALOS = 4;
    private Deque<Carta>[] palos;
    private Carta asOros;

    public Mesa(){
        this.palos = new Deque[NUM_PALOS];
        this.asOros = new Carta(OROS, 1);
        
        for (int i = 0; i < NUM_PALOS; i++) {
            palos[i] = new ArrayDeque<>();
        }
    }

    /**
     * Metodo que introduce cartas en la mesa, si la carta se puede introducir,
     * la carta se introduce a la mesa y devuelve true. Si la carta no se puede
     * introducir salta un mensaje de error y devuelve false
     * 
     * @param c es la carta que quieres introducir
     * @return devuelve true si se añadió la carta, falso si no se añadió
     */
    public boolean addCartatoMesa(Carta c){ 
        
        int i = c.getPalo().ordinal();
        boolean added;
            
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
            
            return added;
    }
    
    /**
     * Comprubea si la Carta c pasada por parametro se puede 
     * introducir en la mesa en su palo correspondiente
     * @param c Carta que se quiere introducir en la mesa
     * @return devueleve true si la carta se puede añadir false si no se puede
     */
    public boolean puedeMeterse(Carta c){
        int i = c.getPalo().ordinal();
        boolean puede = false;
        
        if (palos[i].isEmpty() && c.getValor() == 5) {
            return true;
        }
        else if(palos[i].isEmpty() && c.getValor() != 5){
            return false;
        }
        else {
            
            if (c.getValor() == (palos[i].getFirst().getValor() - 1)) {
                puede = true;
            } 
            else if (c.getValor() == (palos[i].getLast().getValor() + 1)) {
                puede = true;
            } 
            
            return puede;
        }

    }
    
    /**
     * Una vez acaba la partida este método vacia la mesa con las cartas
     */
    public void vaciarMesa(){
        
        for (int i = 0; i < palos.length; i++) {
           palos[i].clear();
        }  
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

                palos[pos].addLast(c);
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