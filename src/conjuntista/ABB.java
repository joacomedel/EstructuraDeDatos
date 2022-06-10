package conjuntista;

import javax.lang.model.util.ElementScanner14;

import lineales.dinamicas.Lista;

public class ABB {
    private NodoArbol raiz;
    private boolean booleanEliminado;

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
<<<<<<< HEAD
            if (nodo.getIzq() == null && nodo.getDer() != null || (nodo.getIzq() != null && nodo.getDer() == null)) {
                caso = 2; // Caso 2
            } else {
                caso = 3; // Caso 3 tiene dos hijos
=======
            if (nodo.getIzq() == null && nodo.getDer() != null ) {
                caso = 4; // Caso 2 tiene solo derecho
            } else {
                if ((nodo.getIzq() != null && nodo.getDer() == null)) {
                    caso = 2; // Caso 4 tiene solo izquierdo
                } else {
                    caso = 3; // Caso 3 tiene dos hijos
                }
                
>>>>>>> ff53672660e2cd2fc80c5ad8616f7a0c307be1bb
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
<<<<<<< HEAD
        boolean exito = false;
=======
>>>>>>> ff53672660e2cd2fc80c5ad8616f7a0c307be1bb
        if (!this.esVacio()) {
            if (this.raiz.getElem().compareTo(comp) == 0) {
                eliminarRaiz();
            } else {
                eliminarAux(comp, this.raiz);
            }
        }
        return getBooleanEliminado();
    }

<<<<<<< HEAD
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
=======
    private boolean getBooleanEliminado() {
        //Metodos para guardar cuando el metodo logro eliminar
        boolean temp = this.booleanEliminado;
        this.booleanEliminado = false;
        return temp;
        //Vemos si Logro eliminar , siempre que veamos lo deja en false, asi si es true solo podemos obtenerlo una ves
    }
    private void eliminarRaiz() {
        int caso = obtenerCaso(this.raiz);
        switch (caso) {
            case 1:
                this.raiz = null;
                break;
            case 2:
                this.raiz = this.raiz.getIzq();
                break;
            case 4:
                this.raiz = this.raiz.getDer();
                break;
            case 3:
                //Revisar
                this.raiz.setElem(eliminarCaso3(this.raiz.getIzq(), this.raiz));
                break;
        }
        this.booleanEliminado = true;
    }

    private boolean eliminarAux(Comparable comp, NodoArbol nodo) {
        boolean posicionParaEliminar = false;
        boolean nodoBuscado = false;
        if (nodo != null) {
            int compareTo = nodo.getElem().compareTo(comp);
            char posc = 'X';
            if (compareTo == 0) {
                nodoBuscado = true;
            } else {
                if (compareTo > 0) {
                    posicionParaEliminar = eliminarAux(comp, nodo.getIzq());
                    posc = 'I';
                }else{
                    posicionParaEliminar = eliminarAux(comp, nodo.getDer());
                    posc = 'D';
                }
            }
            if (posicionParaEliminar) {
                eliminarYaEncontrado(nodo, posc);
                //si entra aca significa que encontramos el elemento para eliminar
                this.booleanEliminado = true;
                //le decimos que logramos eliminar
            }
            if (nodoBuscado) {
                posicionParaEliminar = true;
            }else{
                posicionParaEliminar = false;
>>>>>>> ff53672660e2cd2fc80c5ad8616f7a0c307be1bb
            }
        }
        return posicionParaEliminar;
    }
<<<<<<< HEAD

    private void intercambiar(NodoArbol nodo, NodoArbol nodo2, char c) {

    }

    private NodoArbol remplazarPorCandidato(NodoArbol nodo) {
        NodoArbol retorna = null;
        if (nodo.getDer() == null) {
            retorna = nodo;
            nodo = null;
        } else {
            remplazarPorCandidato(nodo.getDer());
=======
    
    private void eliminarYaEncontrado(NodoArbol nodo ,char posc) {
        int caso;
        if (posc == 'I') {
            caso = obtenerCaso(nodo.getIzq());
        } else {
            caso = obtenerCaso(nodo.getDer());
        }
        switch (caso) {
            case 1:
                if (posc == 'I') {
                    nodo.setIzq(null);
                } else {
                    nodo.setDer(null);
                }
                break;
            case 2:
                // Tiene solo hijo izquierdo
                if (posc == 'I') {
                    nodo.setIzq(nodo.getIzq().getIzq());
                } else {
                    nodo.setDer(nodo.getDer().getIzq());
                }
                break;
            case 3:
                // Hacer proximamente
                if (posc == 'I') {
                    nodo.getIzq().setElem(eliminarCaso3(this.raiz.getIzq(), this.raiz));
                } else {
                    nodo.getDer().setElem(eliminarCaso3(this.raiz.getIzq(), this.raiz));
                }
                break;
            case 4:
                if (posc == 'I') {
                    nodo.setIzq(nodo.getIzq().getDer());
                } else {
                    nodo.setDer(nodo.getDer().getDer());
                }
                break;
        }
    }
    private Comparable eliminarCaso3(NodoArbol hijo , NodoArbol padre) {
        Comparable retorna = null;
        if (hijo.getDer() == null) {
            retorna = hijo.getElem();
            //Eliminamos si es der o izq
            if (padre.getIzq().equals(hijo)) {
                padre.setIzq(null);
            }else{
                padre.setDer(null);
            }
        }else{
            retorna = eliminarCaso3(hijo.getDer(), hijo);
>>>>>>> ff53672660e2cd2fc80c5ad8616f7a0c307be1bb
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
<<<<<<< HEAD

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

=======
>>>>>>> ff53672660e2cd2fc80c5ad8616f7a0c307be1bb
}
