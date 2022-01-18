/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.ExcepcionNumVerticesInvalido;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Yeri
 */
public class DiGrafoPesado extends GrafoPesado{
    
    public DiGrafoPesado() {
        super();
    }

    public DiGrafoPesado(int nroDeVertices) throws ExcepcionNumVerticesInvalido {
        super(nroDeVertices);
    }

    @Override
    public int cantidadDeAristas () {
        int cantidad = 0;
        for(int i = 0; i < this.listaDeAdyacencias.size(); i++) {
            List<AdyacenteConPeso> adyacencias = listaDeAdyacencias.get(i);
            cantidad += adyacencias.size();
        }
        return cantidad;
    }

    @Override
    public void insertarArista (int posVerticeOrigen, int posVerticeDestino, double peso) {
        if(this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new RuntimeException("La lista ya existe");
        }
        List<AdyacenteConPeso> adyacenciaDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenciaAlOrigen = new AdyacenteConPeso(posVerticeDestino, peso);
        adyacenciaDelOrigen.add(adyacenciaAlOrigen);
        Collections.sort(adyacenciaDelOrigen);
    }

    @Override
    public void eliminarArista (int posVerticeOrigen, int posVerticeDestino) {
        if(!this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new IllegalArgumentException("La arista ya existe");
        }
        List<AdyacenteConPeso> adyacenteDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenciaAlOrigen = new AdyacenteConPeso(posVerticeDestino, 0);
        int posicionDelDestino = adyacenteDelOrigen.indexOf(adyacenciaAlOrigen);
        adyacenteDelOrigen.remove(posicionDelDestino);
    }

    @Override
    public int gradoDeVertice(int posDeVertice) {
        throw new UnsupportedOperationException("metodo no soportado en grafos dirigidos");
    }

    public int gradoDeEntrdaDeVertice (int posDeVertice) {
        super.validarVertice(posDeVertice);
        int entradaDeVertice = 0;
        for(List<AdyacenteConPeso> adyacentesDeVertice : super.listaDeAdyacencias) {
            for(AdyacenteConPeso posDeAdyacente : adyacentesDeVertice) {
                if(posDeAdyacente.getIndiceVertice() == posDeVertice) {
                    entradaDeVertice++;
                }
            }
        }
        return entradaDeVertice;
    }

    public int gradoDeSalida(int posDeVertice) {
        return super.gradoDeVertice(posDeVertice);
    }
}
