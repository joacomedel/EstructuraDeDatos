package jerarquicas.dinamicas;

import jerarquicas.dinamicas.NodoArbol;
import lineales.dinamicas.Lista;

public class ArbolGen {
    NodoArbol raiz;

    public ArbolGen() {
        raiz = null;
    }

    public boolean insertar(Object objNodo, Object objPadre) {
        boolean exito = false;
        if (esVacio()) {
            raiz = new NodoArbol(objNodo);
            exito = true;
        } else {
            NodoArbol nodoArbol = buscarNodo(this.raiz, objPadre);
            NodoArbol nodoTemp = nodoArbol.getHijo(0);
            if (nodoTemp == null) {
                nodoArbol.setHijo(0, new NodoArbol(objNodo));
            }else{
                while (nodoTemp.getHijo(1) != null) {
                    nodoTemp = nodoTemp.getHijo(1);
                }
                nodoTemp.setHijo(1, new NodoArbol(objNodo));
            }
            exito = true;
        }
        return exito;
    }

    public NodoArbol buscarNodo(NodoArbol nodoIn, Object obj) {
        NodoArbol nodoTemp = null;
        if (nodoIn != null) {
            if (nodoIn.getElem().equals(obj)) {
                nodoTemp = nodoIn;
            } else {
                nodoTemp = buscarNodo(nodoIn.getHijo(0), obj);
                if (nodoTemp == null) {
                    nodoTemp = buscarNodo(nodoIn.getHijo(1), obj); // GetHijo(1)Obtiene el hermano izquierdo
                }
            }
        }
        return nodoTemp;
    }

    public String toString() {
        String cadena = "";
        if (!this.esVacio()) {
            cadena = toStringAux(cadena, this.raiz);
        }
        return cadena;
    }

    public String toStringAux(String cadena, NodoArbol nodoTemp){
        cadena = "";
        if (nodoTemp != null) {
            cadena = nodoTemp.getElem() + "|" ;
        NodoArbol nodoHijo = nodoTemp.getHijo(0);
        while (nodoHijo != null) {
            cadena = cadena + nodoHijo.getElem() + ",";
            nodoHijo = nodoHijo.getHijo(1);
        }
        cadena = cadena + "\n";
        cadena = cadena + toStringAux(cadena, nodoTemp.getHijo(0));
        cadena = cadena +toStringAux(cadena, nodoTemp.getHijo(1));
        }
        
        return cadena;
    }
    public Lista listarPreorden() {
        Lista lista = new Lista();
        if (!this.esVacio()) {
            preordenAux(lista, this.raiz);
        }
        return lista;
    }
    private void preordenAux(Lista lista,NodoArbol nodo) {
        if (nodo!=null) {
            lista.insertar(nodo.getElem(), lista.longitud()+1);
            preordenAux(lista, nodo.getHijo(0));
            if (nodo.getHijo(0) != null) {
                NodoArbol temp = nodo.getHijo(0).getHijo(1); //Selecciona el hermano derecho del nodo izquierdo
                while (temp != null) {
                    preordenAux(lista, temp);
                    temp = temp.getHijo(1);
                }
            }
        }
    }

    public Lista listarInorden (){
        Lista lista = new Lista();
        if (!this.esVacio()) {
            inordenAux(lista, this.raiz);
        }
        return lista;

    }
    private void inordenAux(Lista lista ,NodoArbol nodo){
        if (nodo!=null) {
            inordenAux(lista, nodo.getHijo(0));
            lista.insertar(nodo.getElem(), lista.longitud()+1);
            if (nodo.getHijo(0) != null) {
                NodoArbol temp = nodo.getHijo(0).getHijo(1); //Selecciona el hermano derecho del nodo izquierdo
                while (temp != null) {
                    inordenAux(lista, temp);
                    temp = temp.getHijo(1);
                }
            }
            
        }
    }
    public Lista listarPosorden() {
        Lista lista = new Lista();
        if (!this.esVacio()) {
            posordenAux(lista, this.raiz);
        }
        return lista;
    }
    private void posordenAux(Lista lista , NodoArbol nodo){
        if (nodo!=null) {
            posordenAux(lista, nodo.getHijo(0));
            if (nodo.getHijo(0) != null) {
                NodoArbol temp = nodo.getHijo(0).getHijo(1); //Selecciona el hermano derecho del nodo izquierdo
                while (temp != null) {
                    posordenAux(lista, temp);
                    temp = temp.getHijo(1);
                }
            }
            lista.insertar(nodo.getElem(), lista.longitud()+1);
        }
    }
    public boolean esVacio() {
        return this.raiz == null;
    }
}