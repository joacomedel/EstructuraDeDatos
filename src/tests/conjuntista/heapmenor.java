package tests.conjuntista;
import conjuntista.Heap;
public class heapmenor {
    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.insertar(1);
        heap.insertar(10);
        heap.insertar(3);
        heap.insertar(7);
        heap.insertar(5);
        heap.insertar(6);
        heap.insertar(7);
        heap.insertar(8);
            heap.elimiarCima();
        System.out.println(heap);

    }
}
