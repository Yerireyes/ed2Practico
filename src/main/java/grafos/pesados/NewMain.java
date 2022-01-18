/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.ExcepcionNumVerticesInvalido;
import java.util.List;

/**
 *
 * @author Yeri
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste {
        GrafoPesado grafo = new GrafoPesado(10);
        grafo.insertarArista(0, 1, 5);
        grafo.insertarArista(0, 2, 10);
        grafo.insertarArista(0, 3, 8);
        grafo.insertarArista(1, 3, 6);
        grafo.insertarArista(1, 5, 5);
        grafo.insertarArista(2, 3, 7);
        grafo.insertarArista(2, 4, 8);
        grafo.insertarArista(2, 7, 15);
        grafo.insertarArista(3, 4, 5);
        grafo.insertarArista(3, 5, 11);
        grafo.insertarArista(4, 6, 4);
        grafo.insertarArista(4, 7, 3);
        grafo.insertarArista(5, 6, 9);
        grafo.insertarArista(5, 8, 7);
        grafo.insertarArista(6, 7, 12);
        grafo.insertarArista(6, 8, 4);
        grafo.insertarArista(6, 9, 6);
        grafo.insertarArista(7, 9, 12);
        grafo.insertarArista(8, 9, 7);

        Kruskal prueba = new Kruskal(grafo);
        GrafoPesado grafonuevo = prueba.algoritmoKuskal();
        for (int i = 0; i < grafonuevo.listaDeAdyacencias.size(); i++) {
            System.out.print("{ ");
            List<AdyacenteConPeso> adyacente = grafonuevo.listaDeAdyacencias.get(i);
            for (AdyacenteConPeso ady : adyacente) {
                System.out.print("{" + ady.getIndiceVertice() + ", " + ady.getPeso() +  "} ,");
            }
            System.out.println(" }");
        }
    }
    
}
