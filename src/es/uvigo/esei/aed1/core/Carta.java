/*
 * Representa una carta, formada por un número y su palo correspondiente
 */

//Comentario github
package es.uvigo.esei.aed1.core;

public class Carta {
    public enum Palo{OROS, ESPADAS, COPAS, BASTOS};
    private Palo palo; 
    private int valor;

    public Carta(Palo palo, int valor) {
        this.palo = palo;
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Palo getPalo() {
        return palo;
    }

    public void setPalo(Palo palo) {
        this.palo = palo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(valor).append(" de ");
        sb.append(palo);
        return sb.toString();
    }

    
    
    
}
