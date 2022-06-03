package tests.jerarquicas.test;
import jerarquicas.dinamicas.ArbolGen;
import lineales.dinamicas.Lista;

public class testArbolGen {
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        arbol.insertar('A', 'S');
        arbol.insertar('B', 'A');
        arbol.insertar('D', 'B');
        arbol.insertar('C', 'A');
        arbol.insertar('E', 'C');
        arbol.insertar('G', 'E');
        arbol.insertar('H', 'E');
        arbol.insertar('E', 'C');
        Lista lista = new Lista();
        lista.insertar('A', 1);
        lista.insertar('C', 2);
        lista.insertar('E', 3);
        System.out.println(arbol.verificarCamino(lista)); 

    }
    
}
