package conjuntista;

public class Heap {
    int tamanio;
    int []heap;
    int ultimo = 0;
    //minimo
    public Heap(int tamanio){
        this.tamanio = tamanio;
        heap = new int[this.tamanio];
    }
    //Arreglo hijo izquierdo , posicion 2n
    //Arreglo hijo derecho , posicion 2n + 1
    //Padre , n/2
    public boolean insertar(int num){
        boolean puesto;
        puesto = poner(num);
        if (puesto) {
            ordenar();
        }
        return puesto;
    }
    private boolean poner(int num){
        boolean puesto = false;
        if (!this.esLleno()) {
            heap[ultimo+1] = num;
            ultimo = ultimo+1;
            puesto = true;
        }
        return puesto;
    
    }
    private void ordenar(){
        int posicion = ultimo;
        while (heap[posicion] < heap[(posicion/2)]) {
            int temp = heap[posicion+1];
            heap[posicion+1] = heap[posicion];
            heap[posicion] = temp;
            posicion= posicion/2;
        }
            
        
    }

    public void elimiarCima() {
        
    }
    public int recuperarCima() {
        return heap[0];
    }
    public boolean esVacio(){
        return ultimo == -1;
    }
    public boolean esLleno(){
        return ultimo == heap.length-1;
    }
}
