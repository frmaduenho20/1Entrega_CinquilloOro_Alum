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
    public boolean addCartaMesa(Carta c){
        int i = 0;
        boolean added = false;
            
        while (!c.getPalo().toString().equalsIgnoreCase(Carta.PALO.values()[i].name())) {
            i++;
        }
        Carta peque = (Carta) palos.get(i).getFirst();
        Carta grande = (Carta) palos.get(i).getLast();
        if (palos.get(i).isEmpty()) {

            if (c.getValor() == 5) {
                palos.get(i).add(c);
                added = true;
            } else {
                System.err.println("Si no esta el 5 de "
                        + c.getPalo().toString().toLowerCase()
                        + " no se puede añadir la carta");
            }
        }else if(c.getValor() == peque.getValor() - 1 ){
            
            palos.get(i).addFirst(c);
            added = true;
            
        }else if(c.getValor() == grande.getValor() + 1){
            
            palos.get(i).addLast(c);
            added = true;
            
        }else{
            System.err.println("La carta que intentas "
                    + "meter no es consecutiva...");
        }

        return added;
    }
    
    public String toStringPalo(int pos) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Carta c;
        if (palos.get(pos).isEmpty()) {
            sb.append("*Palo vacío*");
        } else {
            while (i < palos.get(pos).size()) {
                c = (Carta) palos.get(i).pop();
                sb.append(c.getValor()).append(" de ").append(c.getPalo().name()).append("\n");
                palos.get(i).push(c);
            }
        }

        return sb.toString();
    }
    
    @Override 
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append("Mesa:\n");
        for (int i = 0; i < palos.size(); i++) {
            sb.append("Palo: ").append(Carta.PALO.values()[i].name());
            sb.append(toStringPalo(i));
        }
        return sb.toString(); 
    } 

}
