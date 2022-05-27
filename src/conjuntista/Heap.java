package conjuntista;



public class Heap {
    int tamanio = 10;
    Comparable[] heap;
    int ultimo = 0;

    // minimo
    public Heap() {
        heap = new Comparable[this.tamanio];
    }

    // Arreglo hijo izquierdo , posicion 2n
    // Arreglo hijo derecho , posicion 2n + 1
    // Padre , n/2
    public boolean insertar(int num) {
        boolean puesto;
        boolean esVacio = this.esVacio();
        puesto = poner(num);
        if (!esVacio) {
            ordenarUltimo();
        }
        return puesto;
    }

    private boolean poner(int num) {
        if (esLleno()) {
            aumentarArreglo();
        }
        heap[ultimo + 1] = num;
        ultimo = ultimo + 1;
        return true;

    }

    private void ordenarUltimo() {
        int posicion = ultimo;
        while (heap[posicion].compareTo(heap[(posicion / 2)]) < 0) {
            Comparable padre = heap[posicion / 2];
            heap[posicion / 2] = heap[posicion];
            heap[posicion] = padre;
            posicion = posicion / 2;
        }
    }

    private void aumentarArreglo() {
        tamanio = tamanio + 10;
        Comparable[] temp = new Comparable[tamanio];
        int longi = heap.length;
        for (int i = 0; i < longi; i++) {
            temp[i] = heap[i];
        }
        heap = temp;
    }

    public boolean elimiarCima() {
        boolean elimino = false;
        if (!this.esVacio()) {
            if (ultimo == 1) {
                heap[1] = null;
                ultimo = 0;
            }else{
                heap[1] = heap[ultimo];
                heap[ultimo] = null;
                ultimo--;
                elimino = true;
                boolean hijoDerMen = false;
                boolean hijoIzqMen = false;
                int posicion = 1;
                if (this.getHijoIzq(posicion) != null) {//Existe el hijoIzq?heap[posicion]
                    hijoIzqMen = this.getHijoIzq(posicion).compareTo(heap[posicion]) < 0;
                }
                if (this.getHijoDer(posicion) != null) { //Existe el hijo der?
                    hijoDerMen = this.getHijoDer(posicion).compareTo(heap[posicion]) < 0;
                }
                while (hijoIzqMen || hijoDerMen) {
                    if (hijoIzqMen && hijoDerMen) { //Caso donde son los dos menores
                        if (getHijoIzq(posicion).compareTo(getHijoDer(posicion))<0) { //Obtenemos el minimo de los dos
                            //Izquierdo es el menor 
                            intercambiar(posicion, posicion*2);
                            posicion = posicion*2;
                        }else{
                            //derecho es el menor
                            intercambiar(posicion, posicion*2+1);
                            posicion = posicion*2+1;
                        }
                    }else{
                        if (hijoIzqMen && !hijoDerMen) {
                            //caso donde izquierdo es el menor
                            intercambiar(posicion, posicion*2);
                            posicion = posicion*2;  
                        }else{
                            //caso donde derecho es el menor
                            if (hijoDerMen && !hijoIzqMen) {
                                intercambiar(posicion, posicion*2+1);
                                posicion = posicion*2+1;
                            }
                        }
                    }
                    if (posicion*2 <= ultimo) { //Si la posicion es el 
                        hijoIzqMen = this.getHijoIzq(posicion).compareTo(heap[posicion]) < 0;
                    }else{
                        hijoIzqMen = false;
                    }
                    if (posicion*2+1 <= ultimo) {
                        hijoDerMen = this.getHijoDer(posicion).compareTo(heap[posicion]) < 0;
                    }else{
                        hijoDerMen = false;
                    }
                }
            }
        }
        return elimino;
    }

    public Comparable recuperarCima() {
        Comparable comp = null;
        if (!this.esVacio()) {
            comp = heap[1];
        }
        return comp;

    }

    public boolean esVacio() {
        return ultimo == 0;
    }
    private Comparable getHijoIzq(int num){
        Comparable retorna=null;
        if (num*2 <= ultimo) {
            retorna = heap[num*2];
        }
        return retorna;
    }
    private Comparable getHijoDer(int num){
        Comparable retorna=null;
        if (num*2+1 <= ultimo) {
            retorna = heap[num*2+1];
        }
        return retorna;
    }
    private boolean intercambiar(int posc , int posc2){
        boolean exito = false;
        if (posc <= ultimo && posc2 <= ultimo) {
            exito = true;
            Comparable temp = heap[posc2];
            heap[posc2] = heap[posc];
            heap[posc]= temp;
        }
        return exito;
    }
    public boolean esLleno() {
        return ultimo == heap.length - 1;
    }
    public String toString() {
        String cadena = "";
        for (int i = 1; i <= ultimo; i++) {
            cadena = cadena+ "["+ heap[i]+"]";
        }
        return cadena;
    }
}
