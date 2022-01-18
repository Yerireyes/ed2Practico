/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados;

import java.util.Arrays;

/**
 *
 * @author Yeri
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws ExcepcionNumVerticesInvalido , ExcepcionAristaYaExiste {
        Grafo grafo = new Grafo(9);
        grafo.insertarArista(0, 8);
        grafo.insertarArista(8, 6);
        grafo.insertarArista(1, 4);
        grafo.insertarArista(4, 5);
        grafo.insertarArista(5, 3);
        grafo.insertarArista(5, 2);
        grafo.insertarArista(2, 7);
        grafo.insertarArista(7, 3);
        grafo.insertarArista(0, 1);
        System.out.println(grafo.cantidadDeIslas());
    }
    
}
