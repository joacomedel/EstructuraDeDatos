package tests.jerarquicas.test;
import jerarquicas.dinamicas.ArbolGen;
import lineales.dinamicas.Lista;

public class testEsFrontera {
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        Lista lista = new Lista();
    
        System.out.println("Tiene que devolver true , ambos vacios :" + arbol.sonFrontera(lista));
        
        arbol.insertar("a","1");

        System.out.println("Tiene que devolver true , hojas: {a} , lista : {} =" + arbol.sonFrontera(lista));

        lista.insertar("a", lista.longitud()+1);

        System.out.println("Tiene que devolver true , hojas: {a} , lista: {a}" + arbol.sonFrontera(lista));
        
        arbol.insertar("b", "a");
        arbol.insertar("c", "a");//Hoja
        arbol.insertar("d", "a");//Hoja
        arbol.insertar("f", "b");//Hoja

        System.out.println("Tiene que devolver false , hojas: {c,d,f} , lista: {a} "+ arbol.sonFrontera(lista));
        
        lista.vaciar();
        lista.insertar("c", lista.longitud()+1);
        lista.insertar("d", lista.longitud()+1);
        lista.insertar("f", lista.longitud()+1);

        System.out.println("Tiene que devolver true , hojas: {c,d,f} , lista: {c,d,f} "+ arbol.sonFrontera(lista));

        lista.eliminar(3);

        System.out.println("Tiene que devolver true , hojas: {f,c,d} , lista: {c,d} "+ arbol.sonFrontera(lista));
        
        
        lista.eliminar(1);
        lista.insertar("f", 1);

        System.out.println("Tiene que devolver true , hojas: {f,c,d} , lista: {f,d} "+ arbol.sonFrontera(lista));
        
        lista.eliminar(2);
        lista.insertar("c", 2);

        System.out.println("Tiene que devolver true , hojas: {f,c,d} , lista: {f,c} "+ arbol.sonFrontera(lista));
        //TESTEAR DEBUG , CORTOCIRCUITO

        lista.insertar("x", lista.longitud()+1);

        System.out.println("Tiene que devolver false , hojas: {f,c,d} , lista: {c,d,x} "+ arbol.sonFrontera(lista));
    }
}
