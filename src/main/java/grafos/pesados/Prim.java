/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.ExcepcionNumVerticesInvalido;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Yeri
 */
public class Prim {
    private GrafoPesado miGrafo;
    private boolean[] marcados;
    
    public Prim(GrafoPesado miGrafo){
        this.miGrafo = miGrafo;
        this.marcados = new boolean[this.miGrafo.cantidadDeVertices()];
        for (int i = 0; i < this.marcados.length; i++) {
            this.marcados[i] = false;
        }
    }
    
    public List<List<AdyacenteConPeso>> algoritmoPrim() throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste{
        GrafoPesado otroGrafo = new GrafoPesado(miGrafo.cantidadDeVertices());
        this.marcados[0] = true;
        while(!estanTodosMarcados()){
            buscarAristaConMenorPeso(otroGrafo);
        }
        prueba(otroGrafo.listaDeAdyacencias);
        return otroGrafo.listaDeAdyacencias;
    }

    private boolean estanTodosMarcados() {
        for (int i = 0; i < this.marcados.length; i++) {
            if (this.marcados[i] != true) {
                return false;
            }
        }
        return true;
    }

    private void buscarAristaConMenorPeso(GrafoPesado otroGrafo) throws ExcepcionAristaYaExiste {
        int posOrigen = 0;
        int posDestino = 0;
        double peso = Double.MAX_VALUE;
        List<Integer> listaDeMarcados= listaDeMarcados();
        for(Integer marcado: listaDeMarcados){
            List<AdyacenteConPeso> adyacentesDeVerticeMarcado = miGrafo.listaDeAdyacencias.get(marcado);
            for(AdyacenteConPeso adyacente : adyacentesDeVerticeMarcado){
                if (!this.marcados[adyacente.getIndiceVertice()] && adyacente.getPeso() < peso) {
                    posOrigen = marcado;
                    posDestino = adyacente.getIndiceVertice();
                    peso = adyacente.getPeso();
                }
            }  
        }
        otroGrafo.insertarArista(posOrigen, posDestino, peso);
        this.marcados[posDestino] = true;
    }

    private List<Integer> listaDeMarcados() {
        List<Integer> listaDeMarcados = new LinkedList<>();
        for (int i = 0; i < this.marcados.length; i++) {
            if (this.marcados[i]) {
                listaDeMarcados.add(i);
            }
        }
        return listaDeMarcados;
    }

    public void prueba(List<List<AdyacenteConPeso>> listaDeAdyacencias) {
        for (int i = 0; i < listaDeAdyacencias.size(); i++) {
            List<AdyacenteConPeso> listaDeAdyacenciasDeUnVertice = listaDeAdyacencias.get(i);
            System.out.print("{ ");
            for(AdyacenteConPeso adyacencia:listaDeAdyacenciasDeUnVertice){
                System.out.print(" {" + adyacencia.getIndiceVertice() + ", " + adyacencia.getPeso() + " }");
            }
            System.out.print(" }");
            System.out.println("");
        }
    }
}
