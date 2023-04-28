/*
 * Representa una carta, formada por un número y su palo correspondiente
 */

package es.uvigo.esei.aed1.core;

public class Carta {
    public static enum PALO{OROS, ESPADAS, COPAS, BASTOS};
    private PALO palo; 
    private int valor;

    public Carta(PALO palo, int valor) {
        this.palo = palo;
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

//    public void setValor(int valor) {
//        this.valor = valor;
//    }

    public PALO getPalo() {
        return palo;
    }

//    public void setPalo(PALO palo) {
//        this.palo = palo;
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(valor).append(" de ");
        sb.append(palo);
        return sb.toString();
    }

    
    
    
}
