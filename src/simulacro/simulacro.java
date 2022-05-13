package simulacro;

import lineales.dinamicas.*;

public class simulacro {
    public static void main(String[] args) {
        Cola cola = new Cola();
        String cadena = "AB#C#DEF";
        for (int i = 0; i < cadena.length(); i++) {
            cola.poner(cadena.charAt(i));
        } // CARGAMOS LA COLA PARA TESTEAR
        //System.out.println(verificarBalanceo(cola)); 
        System.out.println(generarCola(cola));
    }

    public static Cola generarCola(Cola q) {
        Cola clon = q.clone();
        Cola retorna = new Cola();
        if (!clon.esVacia()) {
            int longitud = 0;
            Cola cola1 = new Cola();
            Pila pila = new Pila();
            Cola cola2= new Cola();
            char c = (char)clon.obtenerFrente();
            while (!clon.esVacia()) { //Iteramos siempre que el clon no sea vacio
                while (c != '#' && !clon.esVacia()) { //Si el clon no es vacio y si el ultimo caracter no es #
                    longitud++;
                    cola1.poner(c);
                    pila.apilar(c);
                    cola2.poner(c);
                    if (clon.sacar()) { //en el ultimo caso el caracter es igual a la ultima posicion de la cola 
                        //pero ya fue asignado asi que sacamos y si la cola queda vacia no hacemos obtener frente
                        if (!clon.esVacia()) {
                            c = (char)clon.obtenerFrente();
                        }
                    }
                }
                for (int i = 0; i < longitud; i++) { //cargamos las 3 estructuras en la cola retorna
                    retorna.poner(cola1.obtenerFrente());
                    cola1.sacar();
                }
                for (int i = 0; i < longitud; i++) {
                    retorna.poner(pila.obtenerTope());
                    pila.desapilar();
                }
                for (int i = 0; i < longitud; i++) {
                    retorna.poner(cola2.obtenerFrente());
                    cola2.sacar();
                }
                if (clon.sacar()) { //sacamos ya que la cola quedo con # o la recibimos vacia y no entramos
                    retorna.poner('#');
                    c = (char)clon.obtenerFrente();
                }
                longitud = 0;
            }
        }
        return retorna;
    }

    public static boolean verificarBalanceo(Cola q) {
        boolean balaceo = true;
        if (!q.esVacia()) {
            Cola clon = q.clone();
            Pila pila = new Pila();
            char cCola = (char) clon.obtenerFrente();
            if (cCola == '}' || cCola == ']' || cCola == ')') {
                balaceo = false;
            }
            while (!clon.esVacia() && balaceo) {
                cCola = (char) clon.obtenerFrente();
                clon.sacar();
                char cPila = ' ';
                if (!pila.esVacia()) {
                    cPila = (char) pila.obtenerTope();
                }
                if (cCola == '{' || cCola == '[' || cCola == '(') {
                    pila.apilar(cCola); //Si abrimos siempre vamos bien asi que apilamos solamente
                } else {
                    if (cCola == '}' || cCola == ']' || cCola == ')') //entramos solo cuando cCola es uno de estos caracteres
                        switch (cPila) { //vemos que fue lo ultimo que se abrio
                            case '{':
                                if (cCola == '}') { //Si es el mismo caracter pero cerrando sacamos ese caracter que estaba abriendo
                                    pila.desapilar();
                                } else {
                                    balaceo = false; //Solo quedan dos posibles casos y los dos son incorrectos
                                }
                                break;
                            case '[':
                                if (cCola == ']') {//Si es el mismo caracter pero cerrando sacamos ese caracter que estaba abriendo
                                    pila.desapilar();
                                } else {
                                    balaceo = false;
                                }
                                break;
                            case '(':
                                if (cCola == ')') {//Si es el mismo caracter pero cerrando sacamos ese caracter que estaba abriendo
                                    pila.desapilar();
                                } else {
                                    balaceo = false;
                                }
                                break;
                        }
                }
            }
            balaceo = pila.esVacia();
        }
        return balaceo;
    }

}

