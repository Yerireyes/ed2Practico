/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.ExcepcionNumVerticesInvalido;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Yeri
 */
public class Kruskal extends GrafoPesado{
    private GrafoPesado miGrafo;
    public boolean[] marcados;
    
    public Kruskal(GrafoPesado miGrafo){
        this.miGrafo = miGrafo;
        this.marcados = new boolean[miGrafo.cantidadDeVertices()];
        for (int i = 0; i < miGrafo.cantidadDeVertices(); i++) {
            marcados[i] = false;
        }
    }
    
    public GrafoPesado algoritmoKuskal() throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste{
        //tiene que ser conexo
        GrafoPesado newGrafo = new GrafoPesado(miGrafo.cantidadDeVertices());
        List<Arista> listaDePesosOrdenados = this.generarListaDeArista(miGrafo);
        for (int i = 0; i < listaDePesosOrdenados.size(); i++) {
            Arista aristaActual = listaDePesosOrdenados.get(i);
            System.out.println(aristaActual.peso);
            if (!marcados[aristaActual.posicionVerticeOrigen] || !marcados[aristaActual.posicionVerticeDestino]) {
                newGrafo.insertarArista(aristaActual.posicionVerticeOrigen, aristaActual.posicionVerticeDestino, aristaActual.peso);
            }
            marcados[aristaActual.posicionVerticeOrigen] = true;
            marcados[aristaActual.posicionVerticeDestino] = true;
        }
        return newGrafo;
    }

    private List<Arista> generarListaDeArista(GrafoPesado miGrafo) {
        List<Arista> listaDePesosOrdenados = new LinkedList<>();
        for (int i = 0; i < miGrafo.cantidadDeVertices(); i++) {
            List<AdyacenteConPeso > listaDeAdyacencias = this.miGrafo.listaDeAdyacencias.get(i);
            for(AdyacenteConPeso adyacente : listaDeAdyacencias){
                Arista nuevaArista = new Arista(i, adyacente.getIndiceVertice(), adyacente.getPeso());
                listaDePesosOrdenados.add(nuevaArista);
            }
        }
        this.ordernarLista(listaDePesosOrdenados);
        return listaDePesosOrdenados;
    }

    private void ordernarLista(List<Arista> listaDePesosOrdenados) {
//         for (int i = 0; i < listaDePesosOrdenados.size(); i++) {
//            double pesoI = listaDePesosOrdenados.get(i).peso;
//            for (int j = i+1; j < listaDePesosOrdenados.size(); j++) {
//                double pesoJ = listaDePesosOrdenados.get(j).peso;
//                if (pesoI < pesoJ) {
//                    Arista aristaAReemplazar = listaDePesosOrdenados.get(i);
//                    listaDePesosOrdenados.set(i, listaDePesosOrdenados.get(j));
//                    listaDePesosOrdenados.set(j, aristaAReemplazar);
//                }
//            }
//        }
        
        for (int i = 0; i < listaDePesosOrdenados.size(); i++) {
            for (int j = i + 1; j < listaDePesosOrdenados.size(); j++) {
                if (listaDePesosOrdenados.get(i).posicionVerticeOrigen == listaDePesosOrdenados.get(j).posicionVerticeDestino) {
                    listaDePesosOrdenados.remove(j);
                    j--;
                }
            }
        }
        listaDePesosOrdenados.sort(Comparator.comparing(arista -> arista.peso));
       
    }
}
