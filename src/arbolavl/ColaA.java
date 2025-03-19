/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolavl;

/**
 *
 * @author edi
 */
//Metodo Cola, funciona similar a una queue, implementación de un ADT
public class ColaA <T> implements ColaADT<T> {
    private T[] cola;
    private int inicio;
    private int fin;
    private final int MAX = 5;
    
    //Constructores
    public ColaA(){
        cola = (T[]) new Object[MAX];
        inicio=-1; //Indica cola vacía
        fin = -1; // indica cola vacía
    }
    
     public ColaA(int max){
        cola = (T[]) new Object[max];
        inicio=-1; //Indica cola vacía
        fin = -1; // indica cola vacía
    }

    public int getInicio() {
        return inicio;
    }
    
    //Metodo agrega
    @Override
    public void agrega(T dato) {
        if (estaLlena())
            expande();
        fin = (fin + 1)% cola.length;
        cola[fin] = dato;
        if (inicio == -1)
            inicio=0;
    }

    //Metodo quita
    @Override
    public T quita() {
        if (estaVacia())
            throw new RuntimeException ("Cola vacia");
        T eliminado = cola[inicio];
        cola[inicio] = null;
        if (inicio == fin){
            inicio = -1;
            fin = -1;
        }
        else
            inicio = (inicio + 1) % cola.length;
        return eliminado;
    }

    //Revisa cual es el primer elemento de la cola (prox. en salir)
    @Override
    public T consultaPimero() {
        if (estaVacia())
            throw new RuntimeException ("Cola vacia");
        return cola[inicio];
    }

    //Checa si hay elementos en la cola
    @Override
    public boolean estaVacia() {
        return inicio == -1;
    }

    //Checa si hay espacio en la cola
    public boolean estaLlena() {
        return (fin + 1) % cola.length == inicio;
    }

    //Expande la cola si se llena
    private void expande() {
        T[] masGrande = (T[]) new Object [cola.length * 2];
        int n = cola.length;
        for (int i = 0; i < n; i++)
            masGrande[i] = cola[(inicio + i) % n];
        inicio=0;
        fin = n-1;
        cola = masGrande;
    }
    
    //toString normal y con stringBuilder
    public String toString(){
        String cadena = null;
        if (!estaVacia())
            cadena = toString (new StringBuilder(), inicio);
        return cadena; 
    }
    
    private String toString (StringBuilder sB, int indice){
        if (indice == fin){
            sB.append(cola[indice]);
            return sB.toString();
        }
        else{
            sB.append(cola[indice]).append(" ");
            indice = (indice + 1) % cola.length;
            return toString (sB, indice);
        }
        
    }
    
    //Equals
    public boolean equals (Object obj){
        ColaADT <T> aux1 = new ColaA();
        ColaADT <T> aux2 = new ColaA();
        ColaADT <T> save1 = new ColaA();
        ColaADT <T> save2 = new ColaA();
        if (obj instanceof ColaADT){
            ColaA <T> cola = (ColaA) obj;
            if (this.estaVacia() && cola.estaVacia()){
                return true;
            }
            else if (this.estaVacia() || cola.estaVacia()){
                return false;
            }
            else{
                while (!estaVacia()){
                    save1.agrega(this.consultaPimero());
                    aux1.agrega(this.quita());
                }
                while (!save1.estaVacia()){
                    agrega(save1.quita());
                }
                while (!cola.estaVacia()){
                    save2.agrega(cola.consultaPimero());
                    aux2.agrega(cola.quita());
                }
                while (!save2.estaVacia()){
                    cola.agrega(save2.quita());
                }
                return equals (aux1, aux2);   
            }        
        } else
            return false;
    }

    //Equals 2 colas
    private boolean equals(ColaADT<T> aux1, ColaADT<T> aux2) {
        if (aux1.estaVacia() && aux2.estaVacia()){
            return true;
        }
        else if (aux1.estaVacia() || aux2.estaVacia()){
            return false;
        }
        else if(aux1.consultaPimero().equals(aux2.consultaPimero())){
            aux1.quita();
            aux2.quita();
            return equals (aux1,aux2);
        }
        else
            return false;
    }


    
}
