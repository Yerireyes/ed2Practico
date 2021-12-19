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
        System.out.println("insertando en el AVL");
        AVL.insertar(50, "a");
        AVL.insertar(100, "b");
        AVL.insertar(20, "c");
        AVL.insertar(30, "d");
        AVL.insertar(40, "e");
        System.out.println("recorrido por niveles :" + AVL.recorridoPorNiveles());
        System.out.println("eliminando el 100");
        AVL.eliminar(100);
        System.out.println("recorrido por niveles :" + AVL.recorridoPorNiveles());
        System.out.println("insertando en el MVias");
        arbolMVias.insertar(50, "a");
        arbolMVias.insertar(100, "b");
        arbolMVias.insertar(30, "d");
        arbolMVias.insertar(60, "e");
        arbolMVias.insertar(70, "f");
        arbolMVias.insertar(110, "g");
        arbolMVias.insertar(120, "h");
        arbolMVias.insertar(300, "i");
        System.out.println("recorrido por niveles :" +arbolMVias.recorridoPorNiveles());
        System.out.println("eliminando 60, 70, 100");
        arbolMVias.eliminar(60);
        arbolMVias.eliminar(70);
        arbolMVias.eliminar(100);
        System.out.println("recorrido por niveles :" +arbolMVias.recorridoPorNiveles());
        
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
        
        
        
        arbolMVias2.insertar(100, "a");
        arbolMVias2.insertar(110, "g");
        arbolMVias2.insertar(50, "b");
        arbolMVias2.insertar(200, "h");
        arbolMVias2.insertar(300, "i");
        
        System.out.println("arbol binario de puerba, recorrido por niveles :" + arbolBinarioDeBusqueda.recorridoPorNiveles());
        System.out.println("ejercicio 7 :" + arbolBinarioDeBusqueda.ejercicio7());
        System.out.println("ejercicio 8 :" + arbolBinarioDeBusqueda.ejercicio8());
        System.out.println("ejercicio 9 :" + arbolBinarioDeBusqueda.ejercicio9());
        System.out.println("ejercicio 11 sucesor del 43 :" + arbolBinarioDeBusqueda.ejercicio11(43));
        System.out.println("ejercicio 12 nivel 0 :" + arbolMVias.ejercicio12(0));
        System.out.println("ejercicio 12 nivel 1 :" + arbolMVias.ejercicio12(1));
        System.out.println("arbolMVias a comparar :" + arbolMVias2.recorridoPorNiveles() + arbolMVias.recorridoPorNiveles());
        System.out.println("ejercicio 14 (arboles de distintos valores y mismas magnitudes) :" + arbolMVias.ejercicio14(arbolMVias2));
        System.out.println("eliminando el 300 del segundo arbolMVias");
        arbolMVias2.eliminar(300);
        System.out.println("ejercicio 14 :" + arbolMVias.ejercicio14(arbolMVias2));
        System.out.println("arbolesBinario a comparar" + arbolBinarioDeBusqueda.recorridoPorNiveles() + arbolBinarioDeBusqueda2.recorridoPorNiveles());
        System.out.println("ejercicio 15 :" + arbolBinarioDeBusqueda.ejercicio15(arbolBinarioDeBusqueda2));
        System.out.println("eliminando el 75");
        arbolBinarioDeBusqueda.eliminar(75);
        System.out.println("ejercicio 15 :" + arbolBinarioDeBusqueda.ejercicio15(arbolBinarioDeBusqueda2));
    }

}
