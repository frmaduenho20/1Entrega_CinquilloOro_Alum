/*
* Representa la Mesa de juego. 
* Estructura: se utilizará un TAD adecuado. Piensa que hay 4 palos y de cada palo se pueden colocar cartas 
* por cualquiera de los dos extremos (un array con 4 doblescolas parece lo más adecuado). La DobleCola se comentó en clase de teoría
* Funcionalidad: saber si es posible colocar una carta en la mesa, colocar una carta en la mesa, mostrar la mesa
 */
package es.uvigo.esei.aed1.core;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import es.uvigo.esei.aed1.core.Carta;

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
    public Carta addCartaMesa(Carta c){ // TODO Arreglar error
        int i = 0;
        boolean added = false;
            
        
        while (i < Carta.PALO.values().length-1 || !(c.getPalo().name().equalsIgnoreCase(Carta.PALO.values()[i].name()))) {
            i++;
            System.out.println("Valor de i en el while= "+i); //Borrar
        }
        System.out.println("Valor de i al salir del while= "+i); //Borrar

        if (palos.get(i).isEmpty()) {

            if (c.getValor() == 5) {
                palos.get(i).add(c);
                added = true;
                System.out.println("Añadida la carta: "+palos.get(i).getFirst().toString());
            } else {
                System.err.println("Si no esta el 5 de "
                        + c.getPalo().toString().toLowerCase()
                        + " no se puede añadir la carta");
            }
        }else {
         
            Carta peque = (Carta) palos.get(i).getFirst();
            Carta grande = (Carta) palos.get(i).getLast();
            
            if (c.getValor() == peque.getValor() - 1) {

                palos.get(i).addFirst(c);
                added = true;
                System.out.println("Añadida la carta: "+palos.get(i).getFirst().toString());

            } else if (c.getValor() == grande.getValor() + 1) {

                palos.get(i).addLast(c);
                added = true;
                System.out.println("Añadida la carta: "+palos.get(i).getLast().toString());

            } else {
                System.err.println("La carta que intentas "
                        + "meter no es consecutiva...");
            }
        }
        if (added) {
            c = null;
        }
        return c;
    }
    
    public String toStringPalo(int pos) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Carta c;
        if (palos.get(pos).isEmpty()) {
            sb.append("*Palo vacío*");
        } else {
            while (i < palos.get(pos).size()) {
                c = (Carta) palos.get(pos).remove();
                sb.append(c.getValor()).append(" de ").append(c.getPalo().name()).append("\n");
                palos.get(pos).add(c);
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
            sb.append("*Vacía*");
        } else {
            for (int i = 0; i < palos.size(); i++) {
            sb.append("Palo: ").append(Carta.PALO.values()[i].name());
            sb.append(toStringPalo(i));
        }
        }
        
        return sb.toString(); 
    } 

}
