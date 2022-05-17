package lineales.dinamicas;

import lineales.dinamicas.Lista;

public class Lista {
    private Nodo cabecera;
    private int tamanio;

    public Lista() {
        this.cabecera = null;
        this.tamanio = 0;
    }

    public boolean insertar(Object objetoIn, int posc) {
        boolean exito = false;
        if (posc > 0 && posc <= this.tamanio + 1) {
            if (posc == 1) {
                cabecera = new Nodo(objetoIn, this.cabecera);
                exito = true;
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < posc - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(new Nodo(objetoIn, aux.getEnlace()));
                exito = true;
            }
            tamanio++;
        }
        return exito;
    }

    public boolean eliminar(int posc) {
        boolean exito = false;
        if (posc > 0 && posc <= this.tamanio) {
            if (posc == 1) {
                this.cabecera = this.cabecera.getEnlace();
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < posc - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            exito = true;
            tamanio--;
        }
        return exito;
    }

    public boolean esVacia() {
        return this.cabecera == null;
    }

    public Object recuperar(int posc) {
        Object objOut = null;
        if (posc > 0 && posc <= this.tamanio) {
            if (posc == 1) {
                objOut = this.cabecera.getElem();
            } else {
                Nodo aux = this.cabecera;
                int i = 0;
                while (i < posc - 2) {
                    aux = aux.getEnlace();
                    i++;
                }
                objOut = aux.getEnlace().getElem();
            }
        }
        return objOut;
    }

    public int localizar(Object objIn) {
        int posc = -1;
        if (!this.esVacia()) {
            int i = 1;
            Nodo aux = this.cabecera;
            while (aux != null && !aux.getElem().equals(objIn) ) {
                aux = aux.getEnlace();
                i++;
            }
            if (aux != null) {
                posc = i;
            }
        }
        return posc;
    }

    public int longitud() {
        return tamanio;
    }

    public void vaciar() {
        cabecera = null;
        tamanio = 0;
    }

    public Lista clone() {
        Lista listaClon = new Lista();
        if (!this.esVacia()) {
            listaClon.cabecera = new Nodo(this.cabecera.getElem(), null);
            cloneAux(listaClon.cabecera, this.cabecera.getEnlace());
            listaClon.tamanio = this.tamanio;
        }
        return listaClon;
    }

    private void cloneAux(Nodo nodoTemp, Nodo nodoEnlace) {
        if (nodoEnlace != null) {
            nodoTemp.setEnlace(new Nodo(nodoEnlace.getElem(), null));
            cloneAux(nodoTemp.getEnlace(), nodoEnlace.getEnlace());
        }

    }

    public String toString() {
        String cadena = "[|";
        Nodo aux = this.cabecera;
        while (aux != null) {
            cadena = cadena + aux.getElem() + "|";
            aux = aux.getEnlace();
        }
        return cadena + "]";
    }

    public Lista obtenerMultiplos(int num) {
        Lista listaOut = new Lista();
        if (num > 0) {
            int posc = 1;
            Nodo temp = this.cabecera;
            boolean primeraEntrada = true;
            Nodo nodoListaOut = null;
            while (posc <= this.tamanio) {
                if (posc % num == 0) {
                    if (primeraEntrada) {
                        listaOut.cabecera = new Nodo(temp.getElem(), null);
                        nodoListaOut = listaOut.cabecera;
                        primeraEntrada = false;
                    } else {
                        nodoListaOut.setElem(temp.getElem());
                    }
                    nodoListaOut.setEnlace(new Nodo(null, null));
                    nodoListaOut = nodoListaOut.getEnlace();
                    listaOut.tamanio = listaOut.tamanio +1;
                }
                temp = temp.getEnlace();
                posc++;
            }
            nodoListaOut = null; // Siempre el ultimo nodo tiene que quedar apuntando a null
        }
        return listaOut;
        
    }
}
