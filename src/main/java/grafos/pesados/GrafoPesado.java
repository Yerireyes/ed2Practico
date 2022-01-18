/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.ExcepcionNumVerticesInvalido;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Yeri
 */
public class GrafoPesado {
    protected List<List<AdyacenteConPeso>> listaDeAdyacencias;
    
    public GrafoPesado() {
        this.listaDeAdyacencias = new LinkedList<>();
    }

    public GrafoPesado(int nroDeVertices) throws ExcepcionNumVerticesInvalido {
        if (nroDeVertices <= 0) {
            throw new ExcepcionNumVerticesInvalido();
        }

        this.listaDeAdyacencias = new LinkedList<>();
        for (int i = 0; i < nroDeVertices; i++) {
            this.insertarVertice();
        }
    }

    public void insertarVertice() {
        List<AdyacenteConPeso> adyacenteVerticeAInsertar = new LinkedList<>();
        this.listaDeAdyacencias.add(adyacenteVerticeAInsertar);
    }

    public int cantidadDeVertices() {
        return listaDeAdyacencias.size();
    }

    public int gradoDeVertice(int posDeVertice) {
        validarVertice(posDeVertice);
        List<AdyacenteConPeso> adyacentesDelVertice = this.listaDeAdyacencias.get(posDeVertice);
        return adyacentesDelVertice.size();
    }

    public void validarVertice(int posDeVertice) {
        if (posDeVertice < 0 || posDeVertice >= this.cantidadDeVertices()) {
            throw new IllegalArgumentException("no existe vertice en la posicion" + posDeVertice + " en su grafo");
        }
    }

    public void insertarArista(int posVerticeOrigen, int posVerticeDestino, double peso) throws ExcepcionAristaYaExiste {
        if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        List<AdyacenteConPeso> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenteDelOrigen = new AdyacenteConPeso(posVerticeDestino, peso);
        adyacentesDelVerticeOrigen.add(adyacenteDelOrigen);
        Collections.sort(adyacentesDelVerticeOrigen);
        if (posVerticeOrigen != posVerticeDestino) {
            List<AdyacenteConPeso> adyacentesDelVerticeDestino = this.listaDeAdyacencias.get(posVerticeDestino);
            AdyacenteConPeso adyacenteDelDestino = new AdyacenteConPeso(posVerticeOrigen, peso);
            adyacentesDelVerticeDestino.add(adyacenteDelDestino);
            Collections.sort(adyacentesDelVerticeDestino);
        }
    }

    public boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino) {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<AdyacenteConPeso> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenteDelOrigen = new AdyacenteConPeso(posVerticeDestino);
        return adyacentesDelVerticeOrigen.contains(adyacenteDelOrigen);
    }
    
    public Iterable<Integer> adyacentesDeVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<AdyacenteConPeso> adyacentesDelVertice = this.listaDeAdyacencias.get(posDeVertice);
        List<Integer> soloVertices = new ArrayList<>();
        for(AdyacenteConPeso adyacenteConPeso: adyacentesDelVertice){
            soloVertices.add(adyacenteConPeso.getIndiceVertice());
        }
        Iterable<Integer> iterableDeAdyacentesDeVertice = soloVertices;
        return iterableDeAdyacentesDeVertice;
    }
    
    public int cantidadDeAristas(){
        int aristas = 0;
        int lazos = 0;
        for (int i = 0; i < this.cantidadDeVertices(); i++) {
            Iterable<Integer> aristasDelVertice = this.adyacentesDeVertice(i);
            for (Integer posDeAdyacente : aristasDelVertice) {
                if (posDeAdyacente == i) {
                    lazos++;
                }else{
                    aristas++;
                }
            }
        }
        return (aristas/2) + lazos;
    }
    
    public void eliminarVertice(int posVerticeAEliminar){
        validarVertice(posVerticeAEliminar);
        this.listaDeAdyacencias.remove(posVerticeAEliminar);
        for (List<AdyacenteConPeso> adyacentesDeUnVertice : this.listaDeAdyacencias) {
            AdyacenteConPeso unAdyacenteConPeso = new AdyacenteConPeso(posVerticeAEliminar);
            int posicionDeVerticeAEliminarEnAdy = adyacentesDeUnVertice.indexOf(unAdyacenteConPeso);
            if (posicionDeVerticeAEliminarEnAdy >= 0) {
                adyacentesDeUnVertice.remove(posicionDeVerticeAEliminarEnAdy);
            }
            for (int i = 0; i < adyacentesDeUnVertice.size(); i++) {
                AdyacenteConPeso adyaCenteEnTurno = adyacentesDeUnVertice.get(i);
                if (adyaCenteEnTurno.getIndiceVertice() > posVerticeAEliminar) {
                    adyaCenteEnTurno.setIndiceVertice(adyaCenteEnTurno.getIndiceVertice() - 1);
                    //adyacentesDeUnVertice.set(i, adyaCenteEnTurno - 1);
                }
            }
        }
    }
    
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        if (!this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }
        List<AdyacenteConPeso> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso unAdyacenteConPeso = new AdyacenteConPeso(posVerticeDestino);
        int posicionAristaAEliminar = adyacentesDelVerticeOrigen.indexOf(unAdyacenteConPeso);
        adyacentesDelVerticeOrigen.remove(posicionAristaAEliminar);
        if (posVerticeOrigen != posVerticeDestino) {
            List<AdyacenteConPeso> adyacentesDelVerticeDestino = this.listaDeAdyacencias.get(posVerticeDestino);
            unAdyacenteConPeso = new AdyacenteConPeso(posVerticeOrigen);
            posicionAristaAEliminar = adyacentesDelVerticeDestino.indexOf(unAdyacenteConPeso);
            adyacentesDelVerticeDestino.remove(posicionAristaAEliminar);
        }
    }
    
    public double peso(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste{
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if (!this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }
        List<AdyacenteConPeso> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso unAdyacenteDelOrigen = new AdyacenteConPeso(posVerticeDestino);
        int posicionDeLaAdyacencia = adyacentesDelVerticeOrigen.indexOf(unAdyacenteDelOrigen);
        AdyacenteConPeso unAdyacenteReal = adyacentesDelVerticeOrigen.get(posicionDeLaAdyacencia);
        return unAdyacenteReal.getPeso();
    }
}
