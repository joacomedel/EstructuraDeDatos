package tests.conjuntista;
import conjuntista.ABB;
public class testABB {
    public static void main(String[] args) {
        ABB arbol = new ABB();
        arbol.insertar(0);
        arbol.insertar(3);
        arbol.insertar(4);
        arbol.insertar(1);
        arbol.insertar(6);
        arbol.insertar(5);
        arbol.insertar(2);
        System.out.println(arbol);
        System.out.println(arbol.eliminar(0));
        
    }
}
