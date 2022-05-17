package tests.jerarquicas.test;
import jerarquicas.dinamicas.ArbolGen;

public class testArbolGen {
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        arbol.insertar("a","a");
        arbol.insertar("b","a");
        arbol.insertar("c","a");
        arbol.insertar("d","a");
        arbol.insertar("e","b");
        arbol.insertar("f","d");
        arbol.insertar("g","d");
        arbol.insertar("h","e");
        arbol.insertar("i","e");
        arbol.insertar("j","e");
        arbol.insertar("k","i");
        System.out.println(arbol);
        System.out.println(arbol.listarPorNiveles());
    }
    
}
