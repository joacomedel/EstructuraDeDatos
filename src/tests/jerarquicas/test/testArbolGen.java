package tests.jerarquicas.test;
import jerarquicas.dinamicas.ArbolGen;
import lineales.dinamicas.Lista;

public class testArbolGen {
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        arbol.insertar(14, 'S');
        arbol.insertar(0, 14);
        arbol.insertar(17, 0);
        arbol.insertar(7, 0);
        arbol.insertar(8, 0);
        arbol.insertar(9, 7);
        arbol.insertar(11, 14);
        arbol.insertar(10, 11);
        arbol.insertar(47, 11);
        System.out.println(arbol);
        arbol.eliminarConDescendientes(7);
        System.out.println(arbol);

    }
    
}
