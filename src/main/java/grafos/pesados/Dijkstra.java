/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.RecorridoUtils;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Yeri
 */
public class Dijkstra extends DiGrafoPesado {

    private DiGrafoPesado miGrafo;
    private RecorridoUtils marcados;
    List<Double> pesos;
    List<Integer> predecesor;

    public Dijkstra(DiGrafoPesado miGrafo) {
        this.miGrafo = miGrafo;
        this.pesos = new LinkedList<>();
        this.predecesor = new LinkedList<>();
        marcados = new RecorridoUtils(miGrafo.cantidadDeVertices());
        this.cargarListas();
    }

    public void cargarListas(){
        for (int i = 0; i < miGrafo.cantidadDeVertices(); i++) {
            pesos.add(Double.MAX_VALUE);
            predecesor.add(-1);
        }
    }
    
    public void algoritmoDijkstra(int posicionDeOrigen, int posicionDestino) {
        pesos.set(posicionDeOrigen, 0.0);
        while (!marcados.estaVerticeMarcado(posicionDestino)) {
            int posicionCostoMenor = buscarCostoMenor();
            marcados.marcarVertice(posicionCostoMenor);
            List<AdyacenteConPeso> listaDeAdyacentes = miGrafo.listaDeAdyacencias.get(posicionCostoMenor);
            for(AdyacenteConPeso adyacente: listaDeAdyacentes){
                if (pesos.get(adyacente.getIndiceVertice()) > pesos.get(posicionCostoMenor) + adyacente.getPeso()) {
                    pesos.set(adyacente.getIndiceVertice(), pesos.get(posicionCostoMenor) + adyacente.getPeso());
                    predecesor.set(adyacente.getIndiceVertice(), posicionCostoMenor);
                }
            }
        }
        this.recorrido(posicionDestino);
    }
    
    public void recorrido(int posicionDestino){
        Stack<Integer> recorrido = new Stack<>();
        int posicion = posicionDestino;
        while(predecesor.get(posicion) != -1){
            recorrido.add(posicion);
            posicion = predecesor.get(posicion);
        }
        recorrido.add(posicion);
        while(!recorrido.isEmpty()){
            System.out.println(recorrido.pop());
        }
    }

    private int buscarCostoMenor() {
        double menor = Double.MAX_VALUE;
        int posicion = 0;
        for (int i = 0; i < pesos.size(); i++) {
            if (pesos.get(i) < menor && !marcados.estaVerticeMarcado(i)) {
                menor = pesos.get(i);
                posicion = i;
            }
        }
        return posicion;
    }
    
    
}
