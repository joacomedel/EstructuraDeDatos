package conjuntista;

public class Heap {
    int tamanio;
    int []heap;
    int ultimo = -1;
    //minimo
    public Heap(int tamanio){
        this.tamanio = tamanio;
        heap = new int[this.tamanio];
    }
    //Arreglo hijo izquierdo , posicion 2n
    //Arreglo hijo derecho , posicion 2n + 1
    //Padre , n/2
    public void insertar(int num){
        poner(num);
        ordenar(num);
    }
    private void poner(int num){
        if (this.esVacio()) {
            
        }
    
    }
    private void ordenar(int num){

    }

    public void elimiarCima() {
        
    }
    public int recuperarCima() {
        return heap[0];
    }
    public boolean esVacio(){
        return ultimo == -1;
    }
}
