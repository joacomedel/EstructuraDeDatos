package lineales.dinamicas;
public class Pila {
  private Nodo tope;

  public Pila() {
      this.tope = null;
  }
  public  boolean apilar(Object objeto) {
      Nodo nuevo = new Nodo(objeto,this.tope); //Creamos un nodo con el objeto que entra , como elemnto y lo enlazamos al anterior nodo
      this.tope = nuevo;
      return true; //retorna verdadero si las dos lineas de codigo anterior se ejecutaron sin error
  }
  public boolean desapilar() {
    boolean exito = false;//Si no entra en el if mandamos falso
    if (!this.esVacia()) { //Si no es vacia entramos en el if
      this.tope = this.tope.getEnlace(); //Pisamos el tope actual , con el enlace de este tope
      exito = true;
    }
    return exito;
    
  }
  public Object obtenerTope() {
    Object obj; 
    if (this.esVacia()) { //Si es vacia decimos que el objeto es null
       obj = null;
    } else {
       obj = this.tope.getElem(); //si no es vacia le damos el objeto del tope
    }
     return obj;
  }
  public boolean esVacia() {
      return  this.tope == null;
  }
  public void vaciar() {  
    if (!this.esVacia()) {//Si no es vacia "desapilamos"
        this.tope = this.tope.getEnlace(); 
        this.vaciar(); //llamamos recursivamente hasta que sea vacia
      }
  }
  public Pila clone() {
    Pila pilaClon = new Pila();
    cloneAux(this.tope,pilaClon); //Funcion recursiva auxiliar
    return pilaClon;
  }
  private void cloneAux(Nodo nodoParam , Pila pilaClon) {
    if (nodoParam != null) { //Cuando le hayamos llegado al enlace null cortamos las llamdas recursivas 
        this.cloneAux(nodoParam.getEnlace(),pilaClon); //"bajamos" hasta llegar al enlace null
        //Volvemos para "arriba"
        Nodo nodoTemp = new Nodo(nodoParam.getElem(),pilaClon.tope); //AL ir subiendo vamos armando la pila
        pilaClon.tope = nodoTemp;//Establecemos el nuevo tope
      }
  }
  public String toString () {
    String cadena;
    cadena = "]";
    Nodo nodoTemp = this.tope;
    while (nodoTemp !=null) {
        cadena = nodoTemp.getElem() + "|" + cadena  ; 
        nodoTemp = nodoTemp.getEnlace();
    }
    cadena = "[|"+cadena;
    return cadena;
  }

    
}
