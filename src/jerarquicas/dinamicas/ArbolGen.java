package jerarquicas.dinamicas;


import jerarquicas.dinamicas.NodoGen;
import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Nodo;

public class ArbolGen {
    private NodoGen raiz;

    public ArbolGen() {
        raiz = null;
    }

    public boolean insertar(Object objNodo, Object objPadre) {
        boolean exito = false;
        if (esVacio()) {
            raiz = new NodoGen(objNodo);
            exito = true;
        } else {
            NodoGen nodo = buscarNodo(this.raiz, objPadre);
            if (nodo != null) {
                NodoGen nodoTemp = nodo.getHijoIzq();
            if (nodoTemp == null) {
                nodo.setHijoIzq(new NodoGen(objNodo));
            }else{
                while (nodoTemp.getHermanoDer() != null) {
                    nodoTemp = nodoTemp.getHermanoDer();
                }
                nodoTemp.setHermanoDer(new NodoGen(objNodo));
            }
            exito = true;
            }
        }
        return exito;
    }

    public boolean pertenece(Object obj){
        boolean pertenece = false;
        if(!this.esVacio()){
            pertenece  = !(null == buscarNodo(this.raiz, obj));
        }
        return pertenece;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public Object padre(Object obj) {
        Object padre = null;
        if (!this.esVacio()) {
            if (obj != null && !obj.equals(this.raiz.getElem())) {
                padre = padreAux(obj, this.raiz);
            }
        }
        return padre;
    }
    private Object padreAux(Object obj, NodoGen nodo) {
        Object temp = null;
        Boolean esPadre = false;
        if (nodo != null) {
            Boolean extIzq = nodo.getHijoIzq() != null;
            if (extIzq) {//reviso todos los hijos
                esPadre = nodo.getHijoIzq().equals(obj);
                if (esPadre) {
                    temp = nodo.getElem();
                }else{
                    temp = padreAux(obj, nodo.getHijoIzq());
                    NodoGen hijosDer;
                    hijosDer = nodo.getHijoIzq().getHermanoDer();//Obtenemos el primer hijo izquierdo
                    while (hijosDer != null && !esPadre) {
                        esPadre = hijosDer.getElem().equals(obj);
                        hijosDer = hijosDer.getHermanoDer();//Obtenemos el siguiente hijo derecho
                    }
                    if (esPadre) {
                        temp = nodo.getElem();
                    }
    
                }
            }
            if (temp == null) {
                temp = padreAux(obj,nodo.hijoIzq);
                if (temp == null) {
                    temp = padreAux(obj,nodo.hermanoDer);
                }
            }
            
            
        }
        

        return temp;
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
    private void alturaAux(NodoGen nodo, int[] alturaYnivel) {
            Boolean extHijoIzq;
            extHijoIzq = nodo.getHijoIzq() != null;
            alturaYnivel[1]++; // Como ingresamos en un nuevo nodo su nivel se suma
            alturaYnivel[0] = Math.max(alturaYnivel[0], alturaYnivel[1]);
            // AlturaYnivel[]0 altura ||| AlturaYnivel[1] nivelDelNodo
            if (extHijoIzq) {
                alturaAux(nodo.getHijoIzq(), alturaYnivel);
                NodoGen temp = nodo.getHijoIzq().getHermanoDer();
                while (temp != null) {
                    alturaAux(temp, alturaYnivel);
                    temp = temp.getHermanoDer();
                }
                
            }
            alturaYnivel[1]--;
            }// Como termina el metodo y vamos a subir restamos a la altura

    public NodoGen buscarNodo(NodoGen nodoIn, Object obj) {
        NodoGen nodoTemp = null;
        if (nodoIn != null) {
            if (nodoIn.getElem().equals(obj)) {
                nodoTemp = nodoIn;
            } else {
                nodoTemp = buscarNodo(nodoIn.getHijoIzq(), obj);
                if (nodoTemp == null) {
                    nodoTemp = buscarNodo(nodoIn.getHermanoDer(), obj); 
                }
            }
        }
        return nodoTemp;
    }


    public int nivel(Object obj) {
        int nivel = -1 ;
        if (!this.esVacio()) {
            nivel = nivelAux(this.raiz,0, obj);
        }
        return nivel;
    }
    private int nivelAux(NodoGen nodo, int nivel,Object obj){
        int nivelRetorna = -1;
        if (nodo != null) {
            if (nodo.getElem().equals(obj)) {
                nivelRetorna = nivel;
            }else{
                nivelRetorna = nivelAux(nodo.getHijoIzq(), nivel+1, obj);
                if (nivelRetorna == -1 && nodo.getHijoIzq() != null) {
                    NodoGen hijosDer = nodo.getHijoIzq().getHermanoDer();
                    while (hijosDer != null && nivelRetorna == -1) {
                        nivelRetorna = nivelAux(hijosDer, nivel+1, obj);
                        hijosDer = hijosDer.getHermanoDer();
                    }
                }
            }
        }
        return nivelRetorna;
    }

    public Lista ancestros(Object obj){
        Lista lista = new Lista();
        if (!this.esVacio() && !obj.equals(this.raiz.getElem())) {
            ancestrosAux(obj, this.raiz,lista);
            lista.eliminar(1); 
        }
        return lista;
    }
    private void ancestrosAux(Object obj , NodoGen nodo,Lista lista){
        boolean extHijoIzq;
        extHijoIzq = nodo.getHijoIzq() != null;
        if (extHijoIzq) {
            ancestrosAux(obj, nodo.getHijoIzq(), lista);
            if (lista.esVacia()) {
                NodoGen temp = nodo.getHijoIzq().getHermanoDer(); //Selecciona el hermano derecho del nodo izquierdo
                while (temp != null) {
                    ancestrosAux(obj, temp, lista);
                    if (lista.esVacia()) {
                        temp = temp.getHermanoDer();
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

    public ArbolGen clone() {
        ArbolGen clon = new ArbolGen();
        if (!this.esVacio()) {
            clon.raiz = new NodoGen(null);
            cloneAux(this.raiz, clon.raiz);
        }
        return clon;
    }
    private void cloneAux(NodoGen thisNodo, NodoGen clonNodo) {
        clonNodo.setElem(thisNodo.getElem());
        if (thisNodo.getHijoIzq() != null) { 
            clonNodo.setHijoIzq(new NodoGen(null));; 
            cloneAux(thisNodo.getHijoIzq(), clonNodo.getHijoIzq());
        }
        if (thisNodo.getHermanoDer() != null) { 
            clonNodo.setHermanoDer(new NodoGen(null)); 
            cloneAux(thisNodo.getHermanoDer(), clonNodo.getHermanoDer());
        }
    }

    public void vaciar() {
        this.raiz = null;
    }

    public Lista listarPreorden() {
        Lista lista = new Lista();
        if (!this.esVacio()) {
            preordenAux(lista, this.raiz);
        }
        return lista;
    }
    private void preordenAux(Lista lista,NodoGen nodo) {
        if (nodo!=null) {
            lista.insertar(nodo.getElem(), lista.longitud()+1);
            preordenAux(lista, nodo.getHijoIzq());
            if (nodo.getHijoIzq() != null) {
                NodoGen temp = nodo.getHijoIzq().getHermanoDer(); //Selecciona el hermano derecho del nodo izquierdo
                while (temp != null) {
                    preordenAux(lista, temp);
                    temp = temp.getHermanoDer();
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
    private void inordenAux(Lista lista ,NodoGen nodo){
        if (nodo!=null) {
            inordenAux(lista, nodo.getHijoIzq());
            lista.insertar(nodo.getElem(), lista.longitud()+1);
            if (nodo.getHijoIzq() != null) {
                NodoGen temp = nodo.getHijoIzq().getHermanoDer(); //Selecciona el hermano derecho del nodo izquierdo
                while (temp != null) {
                    inordenAux(lista, temp);
                    temp = temp.getHermanoDer();
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
    private void posordenAux(Lista lista , NodoGen nodo){
        if (nodo!=null) {
            posordenAux(lista, nodo.getHijoIzq());
            if (nodo.getHijoIzq() != null) {
                NodoGen temp = nodo.getHijoIzq().getHermanoDer(); //Selecciona el hermano derecho del nodo izquierdo
                while (temp != null) {
                    posordenAux(lista, temp);
                    temp = temp.getHermanoDer();
                }
            }
            lista.insertar(nodo.getElem(), lista.longitud()+1);
        }
    }

    public Lista listarPorNiveles() {
        Lista lista = new Lista();
        if (!this.esVacio()) {
            Cola cola = new Cola();
            cola.poner(this.raiz);
            NodoGen nodo;
            while (!cola.esVacia()) {
                nodo = (NodoGen)cola.obtenerFrente();
                cola.sacar();
                while (nodo != null) {
                    lista.insertar(nodo.getElem(), lista.longitud()+1);
                    if (nodo.getHijoIzq() != null) {
                        cola.poner(nodo.getHijoIzq());
                    }
                    nodo = nodo.getHermanoDer();
                }
            }
        }
        return lista;
    }

    public boolean sonFrontera(Lista lista){
        boolean esFrontera = false;
        if (this.esVacio() && lista.esVacia()) {
            esFrontera = true;
        }else{
            Lista clon = lista.clone();
            //Podriamos no mandar cuando la lista es vacia ya que siempre hay alguna hoja
            sonFronteraAux(this.raiz, clon);
            esFrontera = clon.esVacia();
        }
        return esFrontera;
    }
    private void sonFronteraAux(NodoGen nodo , Lista lista){
        if (nodo != null) {
            if (null == nodo.getHijoIzq()) {
                //Solo entra si es hoja
                int i = 0;
                boolean encontrado = false;
                Lista clon = lista.clone();
                while (!encontrado && !clon.esVacia()) {
                    encontrado = nodo.getElem().equals(clon.recuperar(1));
                    clon.eliminar(1);
                    i++;
                }
                if (encontrado) {
                    lista.eliminar(i);
                }
            }
            if (!lista.esVacia()) {
                sonFronteraAux(nodo.getHijoIzq(), lista);
                if (!lista.esVacia()) {
                    sonFronteraAux(nodo.getHermanoDer(), lista);
                }
            }
        }
    }
    
    public String toString() {
        String cadena = "";
        if (!this.esVacio()) {
            cadena = toStringAux(cadena, this.raiz);
        }
        return cadena;
    }
    private String toStringAux(String cadena, NodoGen nodoTemp){
        cadena = "";
        if (nodoTemp != null) {
            cadena = nodoTemp.getElem() + "|" ;
        NodoGen nodoHijo = nodoTemp.getHijoIzq();
        while (nodoHijo != null) {
            cadena = cadena + nodoHijo.getElem() + ",";
            nodoHijo = nodoHijo.getHermanoDer();
        }
        cadena = cadena + "\n";
        cadena = cadena + toStringAux(cadena, nodoTemp.getHijoIzq());
        cadena = cadena +toStringAux(cadena, nodoTemp.getHermanoDer());
        }
        
        return cadena;
    }

}
