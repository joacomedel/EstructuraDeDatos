package tests.lineales;

//import lineales.estaticas.Cola;
import lineales.dinamicas.Cola;

public class testCola {
    public static void main(String[] args) {
        Cola cola = new Cola();
        for (int i = 0; i < 7; i++) {
            cola.poner(i);
        }
        System.out.println(cola.toString());
        for (int i = 0; i < 3; i++) {
            cola.sacar();
        }
        System.out.println(cola.toString());
        Cola cola2 = cola.clone();
        System.out.println(cola2.toString());
        cola2.vaciar();
        System.out.println(cola2.toString());
        System.out.println(cola.toString());
    }
}

