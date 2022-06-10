package tests.conjuntista;
import conjuntista.ABB;
public class testABB {
    public static void main(String[] args) {
        ABB arbol = new ABB();
        arbol.insertar(10);
        arbol.insertar(5);
        arbol.insertar(1);
        arbol.insertar(14);
        arbol.insertar(12);
        arbol.insertar(17);
        arbol.insertar(13);
        arbol.insertar(15);
        arbol.insertar(20);
        arbol.insertar(11);
        System.out.println(arbol);
        System.out.println(arbol.listarMayorIgual(13));

        
    }
}
