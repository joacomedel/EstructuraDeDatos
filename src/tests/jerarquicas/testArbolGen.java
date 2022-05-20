package tests.jerarquicas;
import jerarquicas.dinamicas.ArbolGen;
import lineales.dinamicas.Lista;

public class testArbolGen {
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        /*arbol.insertar("a","a");
        arbol.insertar("b","a");
        arbol.insertar("d","b");
        arbol.insertar("c","a");
        arbol.insertar("e","c");
        arbol.insertar("f","c");
        arbol.insertar("g","e");
        arbol.insertar("h","e");
        arbol.insertar("x","a");*/
        arbol.insertar("a", "a");
        arbol.insertar("b", "a");
        arbol.insertar("c", "b");
        arbol.insertar("d", "a");
        arbol.insertar("g", "a");
        arbol.insertar("e", "d");
        arbol.insertar("f", "d");
        System.out.println(arbol);
        Lista lista = new Lista();
        lista.insertar("c", lista.longitud()+1);
        lista.insertar("f", lista.longitud()+1);
        lista.insertar("e", lista.longitud()+1);
        lista.insertar("g", lista.longitud()+1);
        lista.insertar("x", lista.longitud()+1);
        System.out.println(arbol.sonFrontera(lista));

    }
    
}
