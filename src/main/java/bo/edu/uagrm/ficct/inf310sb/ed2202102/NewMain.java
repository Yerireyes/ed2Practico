/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102;
/**
 *
 * @author Yeri
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExcepcionClaveNoExiste {
        ArbolBinarioBusqueda<Integer,String> arbolBinarioDeBusqueda = new ArbolBinarioBusqueda<>();
        AVL<Integer,String> AVL = new AVL<>();
        ArbolBinarioBusqueda<Integer,String> arbolBinarioDeBusqueda2 = new ArbolBinarioBusqueda<>();
        ArbolMViasBusqueda<Integer,String> arbolMVias = new ArbolMViasBusqueda<>();
        ArbolMViasBusqueda<Integer,String> arbolMVias2 = new ArbolMViasBusqueda<>();
        
        AVL.insertar(50, "a");
        AVL.insertar(100, "b");
        AVL.insertar(20, "c");
        AVL.insertar(30, "d");
        AVL.insertar(40, "e");
       
        AVL.eliminar(100);
       
        arbolMVias.insertar(50, "a");
        arbolMVias.insertar(100, "b");
        arbolMVias.insertar(30, "d");
        arbolMVias.insertar(60, "e");
        arbolMVias.insertar(70, "f");
        arbolMVias.insertar(110, "g");
        arbolMVias.insertar(120, "h");
        arbolMVias.insertar(300, "i");
        
        
        arbolBinarioDeBusqueda.insertar(75, "a");
        arbolBinarioDeBusqueda.insertar(60, "b");
        arbolBinarioDeBusqueda.insertar(43, "d");
        arbolBinarioDeBusqueda.insertar(68, "e");
        arbolBinarioDeBusqueda.insertar(56, "f");
        arbolBinarioDeBusqueda.insertar(70, "f");

        arbolBinarioDeBusqueda2.insertar(75, "a");
        arbolBinarioDeBusqueda2.insertar(60, "b");
        arbolBinarioDeBusqueda2.insertar(43, "d");
        arbolBinarioDeBusqueda2.insertar(68, "e");
        arbolBinarioDeBusqueda2.insertar(56, "f");
        arbolBinarioDeBusqueda2.insertar(70, "f");
        
        
        
        arbolMVias2.insertar(50, "a");
        arbolMVias2.insertar(100, "b");
        arbolMVias2.insertar(30, "d");
        arbolMVias2.insertar(60, "e");
        arbolMVias2.insertar(70, "f");
        arbolMVias2.insertar(110, "g");
        arbolMVias2.insertar(120, "h");
        arbolMVias2.insertar(300, "i");
        
        
//        Digrafo diGrafo = new Digrafo(5);
        byte[][] matrizPrueba = new byte[3][3];
        matrizPrueba[0][0] = 1;
        System.out.println(matrizPrueba[1][0]);
    }

}
