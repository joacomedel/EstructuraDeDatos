package jerarquicas.dinamicas;

import javax.xml.catalog.CatalogManager;

import jerarquicas.dinamicas.NodoArbol;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Nodo;

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
    public Lista ancestro(Object obj){
        Lista lista = new Lista();
        if (!this.esVacio()) {
            ancestroAux(obj, this.raiz,lista);
            lista.eliminar(1); 
        }
        return lista;
    }
    private void ancestroAux(Object obj , NodoArbol nodo,Lista lista){
        boolean extHijoIzq;
        extHijoIzq = nodo.getHijo(0) != null;
        if (extHijoIzq) {
            ancestroAux(obj, nodo.getHijo(0), lista);
            if (lista.esVacia()) {
                NodoArbol temp = nodo.getHijo(0).getHijo(1); //Selecciona el hermano derecho del nodo izquierdo
                while (temp != null) {
                    ancestroAux(obj, temp, lista);
                    if (lista.esVacia()) {
                        temp = temp.getHijo(1);
                    } else {
                        temp = null;
                    }
                }
            }
        }
        if (!lista.esVacia()|| nodo.getElem().equals(obj)) {
            lista.insertar(nodo.getElem(), lista.longitud()+1);
        }
    }

    public boolean pertenece(Object obj){
        boolean pertenece = false;
        if(!this.esVacio()){
            pertenece  = null == buscarNodo(this.raiz, obj);
        }
        return pertenece;
    }
    public int altura() {
        int[] altura = new int[2];
        altura[0] = -1;
        altura[1] = -1;
        // AlturaYnivel[]0 altura ||| AlturaYnivel[1] nivelDelNodo
        if (!esVacio()) {
            alturaAux(this.raiz, altura);
        }
        return altura[0];
    }

    private void alturaAux(NodoArbol nodo, int[] alturaYnivel) {
            Boolean extHijoIzq;
            extHijoIzq = nodo.getHijo(0) != null;
            alturaYnivel[1]++; // Como ingresamos en un nuevo nodo su nivel se suma
            alturaYnivel[0] = Math.max(alturaYnivel[0], alturaYnivel[1]);
            // AlturaYnivel[]0 altura ||| AlturaYnivel[1] nivelDelNodo
            if (extHijoIzq) {
                alturaAux(nodo.getHijo(0), alturaYnivel);
                NodoArbol temp = nodo.getHijo(0).getHijo(1);
                while (temp != null) {
                    alturaAux(temp, alturaYnivel);
                    temp = temp.getHijo(1);
                }
                
            }
            alturaYnivel[1]--;
            }
             // Como termina el metodo y vamos a subir restamos a la altura
        
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
    public boolean sonFrontera(Lista lista){
        boolean esFrontera = false;
        int []cantidadHojas =  new int[1];
        cantidadHojas[0] = 0;
        if (this.esVacio() && lista.esVacia()) {
            esFrontera = true;
        }else{
            //Podriamos no mandar cuando la lista es vacia ya que siempre hay alguna hoja
            esFrontera = sonFronteraAux(this.raiz, lista,cantidadHojas);
            if (lista.longitud() > cantidadHojas[0]) {
                esFrontera = false;
            }
        }
        return esFrontera;
    }
    private boolean sonFronteraAux(NodoArbol nodo , Lista lista, int[]cantidadHojas){
        boolean esFrontera = true;
        if (nodo != null) {
            if (null == nodo.getHijo(0)) {
                cantidadHojas[0]++;
                boolean encontrado = false;
                Lista clon = lista.clone();
                while (!encontrado && !clon.esVacia()) {
                    encontrado = nodo.getElem().equals(lista.recuperar(1));
                    lista.eliminar(1);
                }
                esFrontera = encontrado;
            }
            if (esFrontera) {
                esFrontera = sonFronteraAux(nodo.getHijo(0), lista,cantidadHojas);
                if (esFrontera) {
                    esFrontera = sonFronteraAux(nodo.getHijo(1), lista,cantidadHojas);
                }
            }
        }
        return esFrontera;
    }


    public boolean esVacio() {
        return this.raiz == null;
    }
}
