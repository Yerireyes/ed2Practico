/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.pesados;

/**
 *
 * @author Yeri
 */
public class Arista {
    int posicionVerticeOrigen;
    int posicionVerticeDestino;
    double peso;
    
    public Arista(int posicionVerticeOrigen, int posicionVerticeDestino, double peso){
        this.posicionVerticeOrigen = posicionVerticeOrigen;
        this.posicionVerticeDestino = posicionVerticeDestino;
        this.peso = peso;
    }
}
