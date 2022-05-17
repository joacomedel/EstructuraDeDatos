package tests.jerarquicas.test;
import jerarquicas.dinamicas.ArbolBin;
import lineales.dinamicas.Lista;

public class testArbolBin {
    public static void main(String[] args) {
        ArbolBin arbol = new ArbolBin();
        /*arbol.insertar("a",null,0);
        arbol.insertar("b","a",0);
        arbol.insertar("d","b",0);
        arbol.insertar("c","a",1);
        arbol.insertar("e","c",0);
        arbol.insertar("g","e",0);
        arbol.insertar("f","c",1);
        arbol.insertar("h","e",1);*/
        arbol.insertar('a',null,'I');
        arbol.insertar('b','a','I');
        arbol.insertar('f','a','D');
        arbol.insertar('c','b','I');
        arbol.insertar('d','b','D');
        System.out.println(arbol);
        System.out.println(arbol.padre('a'));
        
        /*String listaString =  "aba";
        Lista lista = new Lista();
        for (int i = 0; i < listaString.length(); i++) {
            lista.insertar(listaString.charAt(i), i+1);
        } 
        System.out.println(arbol.verificarPatron(lista));*/
        /*System.out.println("preOrden");
        System.out.println(arbol.listaPreorden());
        System.out.println("inOrden");
        System.out.println(arbol.listaInorden());
        System.out.println("posOrden");
        System.out.println(arbol.listaPosorden());
        System.out.println("niveles");
        System.out.println(arbol.listaNiveles());
        System.out.println("Altura");
        System.out.println(arbol.altura());
        System.out.println("Nivel del elemento h:" + arbol.nivel("b"));*/
        
    }
    
}
