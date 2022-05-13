package jerarquicas.dinamicas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Nodo;
import lineales.dinamicas.Cola;

public class ArbolBin {
    private NodoArbol raiz;

    public ArbolBin() {
        raiz = null;
    }

    public boolean insertar(Object elemIn, Object elemPadre, int posc) {
        // Insertamos , el usuario nos pasa el elemento del padre y la posicion del hijo
        // 0 der 1 izq
        boolean exito = false;
        if (raiz == null) {
            this.raiz = new NodoArbol(elemIn);
            exito = true;
        } else {
            NodoArbol nodoPadre = buscarNodo(elemPadre, this.raiz);
            if (nodoPadre.getHijo(posc) == null) {
                nodoPadre.setHijo(posc, new NodoArbol(elemIn));
                exito = true;
            }
        }
        return exito;
    }

    private NodoArbol buscarNodo(Object elemIn, NodoArbol nodoActual) {
        // elemIn elemento que tiene el nodo que estamos buscando
        NodoArbol nodoRetornado = null;
        if (nodoActual != null) {
            if (nodoActual.getElem().equals(elemIn))
                nodoRetornado = nodoActual;
            // Si el nodo actual tiene el elemento es el nodo que buscamos
            else {
                nodoRetornado = buscarNodo(elemIn, nodoActual.getHijo(0));// buscamos en el hijo izquierdo
                if (nodoRetornado == null)
                    nodoRetornado = buscarNodo(elemIn, nodoActual.getHijo(1));
                // Si luego de buscar en el ultimo hijo izquierdo no encontramos pasamos al
                // derecho
            }
        }
        return nodoRetornado;
    }

    public Lista listaPreorden() {
        Lista lista = new Lista();
        preOrdenAux(this.raiz, lista);
        return lista;
    }

    private void preOrdenAux(NodoArbol nodo, Lista lista) {
        if (nodo != null) {
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            preOrdenAux(nodo.getHijo(0), lista);
            preOrdenAux(nodo.getHijo(1), lista);
        }
    }

    public Lista listaInorden() {
        Lista lista = new Lista();
        inOrdenAux(this.raiz, lista);
        return lista;
    }

    private void inOrdenAux(NodoArbol nodo, Lista lista) {
        if (nodo != null) {
            inOrdenAux(nodo.getHijo(0), lista);
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            inOrdenAux(nodo.getHijo(1), lista);
        }
    }

    public Lista listaPosorden() {
        Lista lista = new Lista();
        posOrdenAux(this.raiz, lista);
        return lista;
    }

    private void posOrdenAux(NodoArbol nodo, Lista lista) {
        if (nodo != null) {
            posOrdenAux(nodo.getHijo(0), lista);
            posOrdenAux(nodo.getHijo(1), lista);
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
        }
    }

    public Lista listaNiveles() {
        Lista lista = new Lista();
        Cola cola = new Cola();
        cola.poner(this.raiz);
        while (cola.obtenerFrente() != null) {
            NodoArbol nodo;
            nodo = (NodoArbol) cola.obtenerFrente();
            cola.sacar();
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            if (nodo.getHijo(0) != null) {
                cola.poner(nodo.getHijo(0));
            }
            if (nodo.getHijo(1) != null) {
                cola.poner(nodo.getHijo(1));
            }
        }
        return lista;
    }

    public int altura() {
        int[] altura = new int[2];
        altura[0] = -1;
        altura[1] = -1;
        if (!esVacio()) {
            alturaAux(this.raiz, altura);
        }
        return altura[0];
    }

    private void alturaAux(NodoArbol nodo, int[] alturaYnivel) {
        if (nodo != null) {
            // AlturaYnivel[]0 altura ||| AlturaYnivel[1] nivelDelNodo
            alturaYnivel[1]++; // Como ingresamos en un nuevo nodo su nivel se suma
            alturaYnivel[0] = Math.max(alturaYnivel[0], alturaYnivel[1]);
            alturaAux(nodo.getHijo(0), alturaYnivel);
            alturaAux(nodo.getHijo(1), alturaYnivel);
            alturaYnivel[1]--; // Como termina el metodo y vamos a subir restamos a la altura
        }

    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public String toString() {
        return stringAux(this.raiz, "");
    }

    private String stringAux(NodoArbol nodo, String cadena) {
        String izq = " ";
        String der = " ";
        Boolean izqExis = nodo.getHijo(0) != null;
        Boolean derExis = nodo.getHijo(1) != null;
        if (izqExis)
            izq = nodo.getHijo(0).getElem() + "";
        if (derExis)
            der = nodo.getHijo(1).getElem() + "";

        cadena = cadena + "[" + nodo.getElem() + "]" + ":" + "[" + izq + "]" + "[" + der + "]" + "\n";

        if (izqExis)
            cadena = stringAux(nodo.getHijo(0), cadena);
        if (derExis)
            cadena = stringAux(nodo.getHijo(1), cadena);
        return cadena;
    }

    public int nivel(Object elem) {
        int nivel = -1;
        if (!this.esVacio()) {
            nivel = nivelAux(elem, this.raiz);
        }
        return nivel;
    }

    private int nivelAux(Object elem, NodoArbol nodo) {
        int niv = -1;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                niv++;
            } else {
                niv = nivelAux(elem, nodo.getHijo(0)); // Lo buscamos en el arbol izquierdo
                if (niv == -1)
                    niv = nivelAux(elem, nodo.getHijo(1)); // si no estaba en el izquierdo buscamos en el derecho
                if (niv != -1)
                    niv++;
            }
        }
        return niv;
    }

    public boolean verificarPatron(Lista listaPatron) {
        boolean patronExiste = false;
        if (!this.esVacio()) {
            patronExiste = verificarPatronAux(listaPatron, this.raiz, 1);
        }
        return patronExiste;
    }
    private  boolean verificarPatronAux(Lista listaPatron, NodoArbol nodo, int posc) {
        boolean patronExiste = true; //Valor base el patron es aceptado , ya que si llegamos a un nodo nulo tenemos que retornar true
        if (nodo != null) {
            patronExiste = listaPatron.recuperar(posc).equals(nodo.getElem());//verificamos si el obj de nodo es igual al de la posicion de la lista
            if (patronExiste && posc < listaPatron.longitud()) { //Si es verdadero queda verificar con los hijos y la siguiente posicion
                patronExiste = verificarPatronAux(listaPatron, nodo.getHijo(0), posc + 1); //Verificamos con el hijo izquierdo
                if (!patronExiste) { //Si en el izquierdo no esta el patron vamos a los derechos , recorrido posorden
                    patronExiste = verificarPatronAux(listaPatron, nodo.getHijo(1), posc + 1);
                }
            }
        }
        return patronExiste;
    }

    public ArbolBin clonarInvertido(){
        ArbolBin clon = new ArbolBin();
        if (!this.esVacio()) {
            clon.raiz = new NodoArbol(null);
            clonAux(this.raiz,clon.raiz);
        }
        return clon;
    }
    public void clonAux(NodoArbol thisNodo , NodoArbol clonNodo) {
        clonNodo.setElem(thisNodo.getElem());
            if (thisNodo.getHijo(0) != null) { //Verifico que le hijo izquierdo existe
                clonNodo.setHijo(1, new NodoArbol(null)); //Creo que hijo derecho del nodo clon
                clonAux(thisNodo.getHijo(0), clonNodo.getHijo(1));
                //Paso el hijo izquierdo del ArbolBin y el hijo derecho del Clon
            }
            if (thisNodo.getHijo(1) != null) { //Verifico que le hijo derecho existe
                clonNodo.setHijo(0, new NodoArbol(null)); //Creo que hijo izquierdo del nodo clon
                clonAux(thisNodo.getHijo(1), clonNodo.getHijo(0));
                //Paso el hijo derecho del ArbolBin y el hijo derecho del Clon
            } 
    }
}
