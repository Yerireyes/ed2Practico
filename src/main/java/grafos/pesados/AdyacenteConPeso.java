/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.pesados;

import java.util.Objects;

/**
 *
 * @author Yeri
 */
public class AdyacenteConPeso implements Comparable<AdyacenteConPeso>{
    private int indiceDeVertice;
    private double peso;

    public AdyacenteConPeso(int indiceDeVertice){
        this.indiceDeVertice = indiceDeVertice;
    }
    
    public AdyacenteConPeso(int indiceDeVertice, double peso){
        this.indiceDeVertice = indiceDeVertice;
        this.peso = peso;
    }
    
    public int getIndiceVertice() {
        return indiceDeVertice;
    }

    public void setIndiceVertice(int indiceVertice) {
        this.indiceDeVertice = indiceVertice;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    
    
    @Override
    public int compareTo(AdyacenteConPeso elOtroAdyacenteConPeso) {
        Integer esteVertice = this.indiceDeVertice;
        Integer elOtroVertice = elOtroAdyacenteConPeso.indiceDeVertice;
        return esteVertice.compareTo(elOtroVertice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indiceDeVertice, peso);
    }

    @Override
    public boolean equals(Object elOtroAdyacente) {
        if (this == elOtroAdyacente) {
            return true;
        }
        if (elOtroAdyacente == null || getClass() != elOtroAdyacente.getClass()) {
            return false;
        }
        AdyacenteConPeso other = (AdyacenteConPeso) elOtroAdyacente;
        return indiceDeVertice == other.indiceDeVertice; //&& Double.compare(other.peso, peso) == 0;
    }
    
    
    
}
