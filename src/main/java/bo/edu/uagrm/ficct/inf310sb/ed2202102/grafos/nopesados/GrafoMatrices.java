/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Yeri
 */
public class GrafoMatrices extends DiGrafo{
    protected byte[][] matrizDeCamino;
    
    public GrafoMatrices(){
        super();
    }
    
    public GrafoMatrices(int nroDeVertices) throws ExcepcionNumVerticesInvalido{
        super(nroDeVertices);
        matrizDeCamino = new byte[nroDeVertices][nroDeVertices];
    }
    
    @Override
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste {
        super.insertarArista(posVerticeOrigen, posVerticeDestino);
        this.matrizDeCamino[posVerticeOrigen][posVerticeDestino] = 1;
    }
    
    @Override
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        super.eliminarArista(posVerticeOrigen, posVerticeDestino);
        this.matrizDeCamino[posVerticeOrigen][posVerticeDestino] = 0;
    }
    
    public byte[][] matrizDeCaminos() {
        int cantidadDeVertices = this.cantidadDeVertices();
        byte[][] matrizDeCaminos = this.matrizDeCamino;
        byte[][] matrizA = new byte[cantidadDeVertices][cantidadDeVertices];
        cargarMatriz(matrizA, this.matrizDeCamino);
        byte[][] matrizN = new byte[cantidadDeVertices][cantidadDeVertices];
        byte suma = 0;
        for (int i = 0; i < cantidadDeVertices - 1; i++) {
            for (int j = 0; j < cantidadDeVertices; j++) {
                for (int k = 0; k < cantidadDeVertices; k++) {
                    for (int l = 0; l < cantidadDeVertices; l++) {
                        suma += this.matrizDeCamino[j][l] * matrizA[l][k];
                    }
                    if (suma > 0) {
                        matrizN[j][k] = 1;
                    }
                    suma = 0;
                    
                }
            }
            matrizDeCaminos = sumarMatrices(matrizDeCaminos, matrizN);
            cargarMatriz(matrizA, matrizN);
        }
        return matrizDeCaminos;
    }

    public byte[][] algoritmoWarshall() {
        int cantidadDeVertices = this.cantidadDeVertices();
        byte[][] matrizDeCaminos = new byte[cantidadDeVertices][cantidadDeVertices];
        cargarMatriz(matrizDeCaminos, this.matrizDeCamino);
        for (int i = 0; i < cantidadDeVertices; i++) {
            for (int j = 0; j < cantidadDeVertices; j++) {
                for (int k = 0; k < cantidadDeVertices; k++) {
                    if (matrizDeCaminos[i][j] == matrizDeCaminos[k][i] && matrizDeCaminos[i][j] == 1) {
                        matrizDeCaminos[k][j] = 1;
                    }
                }
            }
        }

        return matrizDeCaminos;
    }

    public byte[][] sumarMatrices(byte[][] matrizA, byte[][] matrizB) {
        int cantidadDeVertices = this.cantidadDeVertices();
        byte[][] matrizC = new byte[cantidadDeVertices][cantidadDeVertices];
        for (int i = 0; i < cantidadDeVertices; i++) {
            for (int j = 0; j < cantidadDeVertices; j++) {
                if (matrizA[i][j] + matrizB[i][j] > 0) {
                    matrizC[i][j] = 1;
                }
            }
        }
        return matrizC;
    }

    public void cargarMatriz(byte[][] matrizA, byte[][] matrizB) {
        int cantidadDeVertices = this.cantidadDeVertices();
        for (int i = 0; i < cantidadDeVertices; i++) {
            for (int j = 0; j < cantidadDeVertices; j++) {
                matrizA[i][j] = matrizB[i][j];
            }
        }
    }
    
    public boolean hayCiclos(){
        byte[][] matriz = this.algoritmoWarshall();
        for (int i = 0; i < this.cantidadDeVertices(); i++) {
            if (matriz[i][i] != 0) {
                return true;
            }
        }
        return false;
    }
    
    public boolean debilmenteConexo(){
        DFS dfs = new DFS(this, 0);
        return dfs.esDebilmenteConexo();
    }
    
    public List<Integer> ordenamientoTopologico(){
        if (this.hayCiclos()) {
            return null;
        }
        if (!this.debilmenteConexo()) {
            return null;
        }
        List<Integer> ordenamientoTopologico = new LinkedList<>();
        List<List<Integer>> gradosDeNodo = new LinkedList<>();
        cargarGradoNodos(gradosDeNodo);
        Queue<Integer> vertices = new LinkedList<>();
        añadirNodosGradoCero(vertices, gradosDeNodo);
        while(!vertices.isEmpty()){
            int vertice = vertices.poll();
            ordenamientoTopologico.add(vertice);
            List<Integer> listaDeAdyacendias = this.listaDeAdyacencias.get(vertice);
            for (int i = 0; i < listaDeAdyacendias.size(); i++) {
                int adyacencia = listaDeAdyacendias.get(i); 
                int valorActualDeGrado = gradosDeNodo.get(adyacencia).get(0);
                gradosDeNodo.get(adyacencia).set(0, valorActualDeGrado - 1);
            }
            añadirNodosGradoCero(vertices, gradosDeNodo);
        }
        return ordenamientoTopologico;
    }

    private void añadirNodosGradoCero(Queue<Integer> vertices, List<List<Integer>> gradosDeNodo) {
        for (int i = 0; i < gradosDeNodo.size(); i++) {
            int gradoDeEntrada = gradosDeNodo.get(i).get(0);
            if (gradoDeEntrada == 0) {
                vertices.add(i);
                gradosDeNodo.get(i).set(0, -1);
            }
        }
    }

    private void cargarGradoNodos(List<List<Integer>> gradosDeNodo) {
        for (int i = 0; i < this.cantidadDeVertices(); i++) {
            List<Integer> vertice = new LinkedList<>();
            vertice.add(super.gradoDeEntrada(i));
            gradosDeNodo.add(vertice);
        }
    }
}
