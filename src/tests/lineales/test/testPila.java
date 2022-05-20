package tests.lineales.test;
import java.util.Scanner;
//import src.lineales.estaticas.Pila;
import lineales.dinamicas.Pila;
public class testPila {
    public static void main(String[] args) throws Exception {
        Pila pila = new Pila();
        //cargarManual(pila);
        cargarAuto(pila);
        System.out.println(pila.toString());
        testVaciar(pila);
        //System.out.println(pila.esVacia());
        //testCapicua(pila);


    }
    private static void testVaciar(Pila pila) {
        System.out.println(pila.toString());
        pila.vaciar();
        System.out.println(pila.toString());
    }
    private static void testCapicua(Pila pila) {
        if (esCapicua(pila)) {
            System.out.println("Es Capicua");
        } else {
            System.out.println("No es capicua");
        }
    }
    private static boolean esCapicua(Pila pilaIn) {
        Pila pilaInvertida = new Pila();
        Pila pilaClon ;
        Pila pilaTemp ;
        pilaClon = pilaIn.clone();
        pilaTemp = pilaClon.clone();
        boolean esCapicua;
        int tamanio=0;
        int i =0;
        do {
            pilaInvertida.apilar(pilaTemp.obtenerTope());
            tamanio++;
        } while (pilaTemp.desapilar());
        pilaTemp = null; //Eliminamos la pila temp
        do {
            esCapicua = pilaClon.obtenerTope() == pilaInvertida.obtenerTope();
            i++;
        } while (esCapicua && i<(tamanio/2));
        return esCapicua;
    }
    private static void cargarManual(Pila pila) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de elementos que quiere ingresar");
        int c = sc.nextInt();
        for (int i = 0; i < c; i++) {
            Object l = sc.nextInt(); //Cambiar el next para alterar el tipo que carga
            pila.apilar(l);
        }
    }
    private static void cargarAuto(Pila pila) {
        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);
        pila.apilar(4);
        pila.apilar(5);
    }
}
