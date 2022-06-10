package conjuntista;

import java.util.Optional;

import lineales.dinamicas.Lista;

public class ABB {
    NodoArbol raiz;

    public ABB() {
        raiz = null;
    }

    public boolean insertar(Comparable comp) {
        boolean exito = false;
        if (this.esVacio()) {
            raiz = new NodoArbol(comp);
        }
        {
            exito = insertarAux(comp, raiz);
        }

        return exito;
    }

    public boolean insertarAux(Comparable comp, NodoArbol nodo) {
        boolean exito = false;
        int compareTo = nodo.getElem().compareTo(comp);
        if (compareTo != 0) {
            if (compareTo > 0) {
                // Si es menor voy a la izquierda
                if (nodo.getIzq() == null) {
                    nodo.setIzq(new NodoArbol(comp));
                    exito = true;
                } else {
                    exito = insertarAux(comp, nodo.getIzq());
                }
            } else {
                // Si es mayor voy a la derecha
                if (nodo.getDer() == null) {
                    nodo.setDer(new NodoArbol(comp));
                    exito = true;
                } else {
                    exito = insertarAux(comp, nodo.getDer());
                }
            }
        }
        return exito;
    }

    private int obtenerCaso(NodoArbol nodo) {
        int caso;
        if (nodo.getIzq() == null && nodo.getDer() == null) {
            caso = 1; // Caso 1 de la teoria , es hoja
        } else {
            if (nodo.getIzq() == null && nodo.getDer() != null || (nodo.getIzq() != null && nodo.getDer() == null)) {
                caso = 2; // Caso 2
            } else {
                caso = 3; // Caso 3 tiene dos hijos
            }
        }
        return caso;
    }

    public boolean pertenece(Comparable comp) {
        boolean pertenece = false;
        if (!this.esVacio()) {
            if (this.raiz.getElem().compareTo(comp) == 0) {
                pertenece = true;
            } else {
                pertenece = perteneceAux(comp, this.raiz);
            }
        }
        return pertenece;
    }

    private boolean perteneceAux(Comparable comp, NodoArbol nodo) {
        boolean pertenece = false;
        ;
        int compareTo = nodo.getElem().compareTo(comp);
        if (compareTo == 0) {
            pertenece = true;
        } else {
            if (compareTo > 0) {
                // Si es menor voy a la izquierda
                if (nodo.getIzq() != null) {
                    pertenece = perteneceAux(comp, nodo.getIzq());
                }
            } else {
                // Si es mayor voy a la derecha
                if (nodo.getDer() != null) {
                    pertenece = perteneceAux(comp, nodo.getDer());
                }
            }
        }
        return pertenece;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public String toString() {
        String cadena = "";
        if (!this.esVacio()) {
            cadena = stringAux(this.raiz, "");
        }
        return cadena;
    }

    public boolean eliminar(Comparable comp) {
        boolean exito = false;
        if (!this.esVacio()) {
            exito = eliminarAux(comp, this.raiz);
        }
        return exito;
    }

    private boolean eliminarAux(Comparable comp, NodoArbol nodo) {
        boolean exito = false;
        int compareTo = nodo.getElem().compareTo(comp);
        if (compareTo == 0) {
            int caso = this.obtenerCaso(nodo);
            switch (caso) {
                case 1:
                    intercambiar(nodo, null, ' ');
                    break;
                case 2:
                    if (nodo.getIzq() != null) {
                        intercambiar(nodo, nodo.getIzq(), 'I');
                    } else {
                        intercambiar(nodo, nodo.getDer(), 'D');
                    }

                    break;
                case 3:
                    nodo = remplazarPorCandidato(nodo.getIzq());
                    break;
            }
            exito = true;
        } else {
            if (compareTo > 0) {
                exito = eliminarAux(comp, nodo.getIzq());
            } else {
                exito = eliminarAux(comp, nodo.getDer());
            }
        }
        return exito;
    }

    private void intercambiar(NodoArbol nodo, NodoArbol nodo2, char c) {

    }

    private NodoArbol remplazarPorCandidato(NodoArbol nodo) {
        NodoArbol retorna = null;
        if (nodo.getDer() == null) {
            retorna = nodo;
            nodo = null;
        } else {
            remplazarPorCandidato(nodo.getDer());
        }
        return retorna;
    }

    private String stringAux(NodoArbol nodo, String cadena) {
        String izq = " ";
        String der = " ";
        Boolean izqExis = nodo.getIzq() != null;
        Boolean derExis = nodo.getDer() != null;
        if (izqExis)
            izq = nodo.getIzq().getElem() + "";
        if (derExis)
            der = nodo.getDer().getElem() + "";

        cadena = cadena + "[" + nodo.getElem() + "]" + ":" + "[" + izq + "]" + "[" + der + "]" + "\n";

        if (izqExis)
            cadena = stringAux(nodo.getIzq(), cadena);
        if (derExis)
            cadena = stringAux(nodo.getDer(), cadena);
        return cadena;
    }

    public Lista listarMayorIgual(Comparable comp) {
        Lista lista = new Lista();
        if (!this.esVacio()) {
            listarMayorIgualAux(lista, this.raiz, comp);
        }
        return lista;
    }

    private void listarMayorIgualAux(Lista lista, NodoArbol nodo, Comparable comp) {
        if (nodo != null) {
            int compareTo = nodo.getElem().compareTo(comp);
            if (compareTo >= 0) {
                listarMayorIgualAux2(lista, nodo, comp);
            } else {
                listarMayorIgualAux(lista, nodo.getDer(), comp);
            }
        }
    }

    private void listarMayorIgualAux2(Lista lista, NodoArbol nodo,Comparable comp) {
        if (nodo != null) {
            Boolean esMayorIgual = nodo.getElem().compareTo(comp) >= 0;
            if (esMayorIgual) {
                listarMayorIgualAux2(lista, nodo.getIzq(), comp);
                lista.insertar(nodo.getElem(), 1);
                listarMayorIgualAux2(lista, nodo.getDer(), comp);
            }else{
                listarMayorIgualAux2(lista, nodo.getDer(), comp);
            }
        }
    }

}
