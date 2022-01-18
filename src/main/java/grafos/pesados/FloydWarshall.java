/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.pesados;

import java.util.List;
import java.util.Stack;

/**
 *
 * @author Yeri
 */
public class FloydWarshall extends DiGrafoPesado {

    private DiGrafoPesado miGrafo;
    protected double[][] matrizDePeso;
    protected int[][] matrizPred;

    public FloydWarshall(DiGrafoPesado miGrafo) {
        this.miGrafo = miGrafo;
        matrizDePeso = new double[miGrafo.cantidadDeVertices()][miGrafo.cantidadDeVertices()];
        matrizPred = new int[miGrafo.cantidadDeVertices()][miGrafo.cantidadDeVertices()];
        this.cargarMatriz();
    }

    private void cargarMatriz() {
        for (int i = 0; i < miGrafo.cantidadDeVertices(); i++) {
            List<AdyacenteConPeso> adyacentesDeVertice = miGrafo.listaDeAdyacencias.get(i);
            for (int j = 0; j < adyacentesDeVertice.size(); j++) {
                matrizDePeso[i][adyacentesDeVertice.get(j).getIndiceVertice()] = adyacentesDeVertice.get(j).getPeso();
            }
        }
        for (int i = 0; i < miGrafo.cantidadDeVertices(); i++) {
            for (int j = 0; j < miGrafo.cantidadDeVertices(); j++) {
                if (matrizDePeso[i][j] == 0) {
                    matrizDePeso[i][j] = Double.MAX_VALUE;
                }
                matrizPred[i][j] = -1;
            }
        }
        for (int i = 0; i < miGrafo.cantidadDeVertices(); i++) {
            matrizDePeso[i][i] = 0;
        }
    }
    
    public void algoritmoFloyd(){
        int cantidadVertices = miGrafo.cantidadDeVertices();
        for (int i = 0; i < cantidadVertices; i++) {
            for (int j = 0; j < cantidadVertices; j++) {
                for (int k = 0; k < cantidadVertices; k++) {
                    if (matrizDePeso[j][k] > matrizDePeso[j][i] + matrizDePeso[i][k]) {
                        matrizDePeso[j][k] = matrizDePeso[j][i] + matrizDePeso[i][k];
                        matrizPred[j][k] = i;
                    }
                }
            }
        }
    }
    
    public void recorridoMinimo(int posicionInicial, int posicionFinal){
        System.out.println(posicionInicial);
        CCM(posicionInicial, posicionFinal);
        System.out.println(posicionFinal);
    }

    private void CCM(int posicionInicial, int posicionFinal) {
        if (matrizPred[posicionInicial][posicionFinal] != -1) {
            int nuevaPosicion = matrizPred[posicionInicial][posicionFinal];
            CCM(posicionInicial, nuevaPosicion);
            System.out.println(nuevaPosicion);
            CCM(nuevaPosicion,posicionFinal);
        }
    }
}
