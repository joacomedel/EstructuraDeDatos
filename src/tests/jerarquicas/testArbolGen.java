package tests.jerarquicas;
import jerarquicas.dinamicas.ArbolGen;

public class testArbolGen {
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        arbol.insertar("a","a");
        arbol.insertar("b","a");
        arbol.insertar("d","b");
        arbol.insertar("c","a");
        arbol.insertar("e","c");
        arbol.insertar("f","c");
        arbol.insertar("g","e");
        arbol.insertar("h","e");
        arbol.insertar("x","a");
        System.out.println(arbol);
        System.out.println(arbol.listarPreorden());
        System.out.println(arbol.listarInorden());
        System.out.println(arbol.listarPosorden());
    }
    
}
