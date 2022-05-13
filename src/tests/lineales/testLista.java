package tests.lineales;
import lineales.dinamicas.Lista;

public class testLista {
    public static void main(String[] args) {
        Lista lista = new Lista();
        for (int i = 1; i < 10; i++) {
            lista.insertar(i,i);
        }
        for (int i = 1; i <= lista.longitud(); i++) {
            System.out.println(lista.recuperar(i));
        }
    }
}
