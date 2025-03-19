/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolavl;

/**
 *
 * @author HP15
 */
public class ArbolAVL1 <T extends Comparable <T>>{
   NodoAVL<T> raiz;
   int cont;
   
   //Constructor
   public ArbolAVL1(NodoAVL<T> raiz){
       this.raiz = raiz;
   }
   
   //Método agrega
   public void agregaAVL(T elem) {
    NodoAVL<T> actual = raiz;
    NodoAVL<T> papa = raiz;
    boolean encontre = false;  //Bandera para terminar while
    NodoAVL<T> nuevo = new NodoAVL<T>(elem);

    //Encuentra cual es el papa del nodo a agregar
    while (actual != null) {
        papa = actual;
        if (elem.compareTo(actual.elem) < 0) {
            actual = actual.izq;
        } else if (elem.compareTo(actual.elem) > 0) {
            actual = actual.der;
        } else {
            return; 
        }
    }

    //Agrega el nodo donde va
    if (papa == null) {
        raiz = nuevo;
    } else if (elem.compareTo(papa.elem) < 0) {
        papa.izq = nuevo;
    } else {
        papa.der = nuevo;
    }

    //Conecta al nuevo nodo con papa, aumenta contador
    nuevo.papa = papa;
    cont++;

    boolean termine = false;
    actual = nuevo;

    //Actualiza Fe
    while (!termine && actual.papa != null) {
        papa = actual.papa;

        if (actual.elem.compareTo(papa.elem) < 0) {
            papa.fe--;
        } else {
            papa.fe++;
        }

        if (papa.fe == 0) {
            termine = true;
        } else if (papa.fe == 2 || papa.fe == -2) {
            NodoAVL<T> nuevaRaiz = roto(papa); 
            if (nuevaRaiz.papa == null) {
                raiz = nuevaRaiz; 
            }
            termine = true;
        }

        actual = papa;
    }
    }

   //Metodo que Rota el arbol si no está balanceado
   private NodoAVL<T> roto(NodoAVL<T> actual) {
    //Asigno valores a los nodos
    NodoAVL<T> papa = actual.papa;
    NodoAVL<T> alfa, beta, gamma, A, B, C, D;

    if (actual.fe == -2 && actual.izq.fe <= 0) { // Caso izquierda-izquierda 
        alfa = actual;
        beta = alfa.izq;
        gamma = beta.izq;
        A = gamma.izq;
        B = gamma.der;
        C = beta.der;
        D = alfa.der;

        alfa.izq = C;
        if (C != null) C.papa = alfa;
        alfa.der = D;
        if (D != null) D.papa = alfa;

        beta.izq = gamma;
        gamma.papa = beta;
        beta.der = alfa;
        alfa.papa = beta;

        if (papa == null) {
            raiz = beta;
        } else if (beta.elem.compareTo(papa.elem) < 0) {
            papa.izq = beta;
        } else {
            papa.der = beta;
        }
        beta.papa = papa;

        actualizafe();
        return beta;
    }

    if (actual.fe == 2 && actual.der.fe >= 0) { // Caso derecha-derecha 
        alfa = actual;
        beta = alfa.der;
        gamma = beta.der;
        A = alfa.izq;
        B = beta.izq;
        C = gamma.izq;
        D = gamma.der;

        alfa.izq = A;
        if (A != null) A.papa = alfa;
        alfa.der = B;
        if (B != null) B.papa = alfa;

        beta.izq = alfa;
        alfa.papa = beta;
        beta.der = gamma;
        gamma.papa = beta;

        if (papa == null) {
            raiz = beta;
        } else if (beta.elem.compareTo(papa.elem) < 0) {
            papa.izq = beta;
        } else {
            papa.der = beta;
        }
        beta.papa = papa;

        actualizafe();
        return beta;
    }

    if (actual.fe == 2 && actual.der.fe == -1) { // Caso derecha-izquierda 
        alfa = actual;
        beta = alfa.der;
        gamma = beta.izq;
        A = alfa.izq;
        B = gamma.izq;
        C = gamma.der;
        D = beta.der;

        alfa.der = B;
        if (B != null) B.papa = alfa;
        beta.izq = C;
        if (C != null) C.papa = beta;

        gamma.izq = alfa;
        alfa.papa = gamma;
        gamma.der = beta;
        beta.papa = gamma;

        if (papa == null) {
            raiz = gamma;
        } else if (gamma.elem.compareTo(papa.elem) < 0) {
            papa.izq = gamma;
        } else {
            papa.der = gamma;
        }
        gamma.papa = papa;

        actualizafe();
        return gamma;
    }

    if (actual.fe == -2 && actual.izq.fe == 1) { // Caso izquierda-derecha 
        alfa = actual;
        beta = alfa.izq;
        gamma = beta.der;
        A = beta.izq;
        B = gamma.izq;
        C = gamma.der;
        D = alfa.der;

        beta.der = B;
        if (B != null) B.papa = beta;
        alfa.izq = C;
        if (C != null) C.papa = alfa;

        gamma.izq = beta;
        beta.papa = gamma;
        gamma.der = alfa;
        alfa.papa = gamma;

        if (papa == null) {
            raiz = gamma;
        } else if (gamma.elem.compareTo(papa.elem) < 0) {
            papa.izq = gamma;
        } else {
            papa.der = gamma;
        }
        gamma.papa = papa;

        actualizafe();
        return gamma;
    }

    return null;
    }

   //Metodo que actualiza el factor de equilibrio, empieza en raiz
   private void actualizafe() {
    actualizarFe(raiz); 
    }

   //Continuación de la actualización del Fe
    private void actualizarFe(NodoAVL<T> actual) {
        if (actual == null) {
            return; // Caso base: si el nodo es nulo, terminamos
        }
        //Llamada recursiva a los hijos de cada nodo
        actualizarFe(actual.izq);
        actualizarFe(actual.der);

        //Obtengo la altura del nodo izquiero al hijo izquierdo
        int alturaIzq;
        if(actual.izq == null){
            alturaIzq = 0;
        }else
            alturaIzq = altura(actual.izq);

        //Altura del hijo derecho
        int alturaDer;
        if(actual.der == null){
            alturaDer = 0;
        }else
            alturaDer = altura(actual.der);
        
        //Altura = altura derecho - altura izquierdo
        actual.fe = alturaDer - alturaIzq;
    }

   //Metodo que calcula altura 
    private int altura(NodoAVL<T> nodo) {
        if (nodo == null) {
            return 0; 
        }
        return Math.max(altura(nodo.izq), altura(nodo.der));
    }

   
   //Metodo que busca un elemento en el arbol
    public NodoAVL<T> busca(NodoAVL<T> actual, T elem){
       if(actual == null)
           return null;
       if(actual.elem.equals(elem))
           return actual;
       NodoAVL<T> temp = busca(actual.getIzq(), elem);
       if(temp==null){
           temp = busca(actual.getDer(), elem);
       }
       return temp;
   }
   
   //Metodo que borra un elemento del arbol
    private NodoAVL<T> borraAVL(T elem) {
    //Selecciono el elemento a borrar usando busca
    NodoAVL<T> actual = busca(raiz, elem);
    //Caso 0: El elemento no está
    if (actual == null) {
        return null; 
    }

    NodoAVL<T> papa = actual.papa;
    NodoAVL<T> hijo;

    if (actual.izq == null && actual.der == null) { // Caso 1: Es un nodo hoja
        if (actual == raiz) {
            raiz = null; 
        } else {
            if (papa.izq == actual) {
                papa.izq = null;
            } else {
                papa.der = null;
            }
        }
    } else if (actual.izq == null) { // Caso 2: Tiene solo hijo derecho
        hijo = actual.der;

        if (actual == raiz) {
            raiz = hijo; 
        } else {
            if (papa.izq == actual) {
                papa.izq = hijo;
            } else {
                papa.der = hijo;
            }
        }
        hijo.papa = papa; 
    } else if (actual.der == null) { // Caso 2: Tiene solo hijo izquierdo
        hijo = actual.izq;

        if (actual == raiz) {
            raiz = hijo;
        } else {
            if (papa.izq == actual) {
                papa.izq = hijo;
            } else {
                papa.der = hijo;
            }
        }
        hijo.papa = papa;
    } else { // Caso 3: Tiene dos hijos, debo buscar el sucesor inorden
        NodoAVL<T> sucesor = actual.der;
        while (sucesor.izq != null) {
            sucesor = sucesor.izq;
        }

        actual.elem = sucesor.elem; 

        if (sucesor.papa != actual) {
            sucesor.papa.izq = sucesor.der;
        } else {
            actual.der = sucesor.der;
        }

        if (sucesor.der != null) {
            sucesor.der.papa = sucesor.papa;
        }

        papa = sucesor.papa; 
    }

    //Reduzcto el contador, actualizo los fe desde la raiz y regreso el nodo eliminado
    cont--;
    actualizafe(); 
    return actual; 
    }

   //Método que imprime por nivel
    public void ImprimePorNivel() {
        //Caso 0: arbol vacío
        if (raiz == null) {
            System.out.println("Árbol vacío");
            return;
        }

        //Si hay elementos, creo una cola y una bandera (null)
        ColaA<NodoAVL<T>> col = new ColaA<>(cont);
        col.agrega(raiz);
        col.agrega(null); 

        NodoAVL<T> aux;

        //While que agarra el primer elemento de la cola, si este elemento es bandera (null)
        //Salta una linea, si todavía hay elementos en el arbol agrega una nueva bandera para el nuevo nivel
        //Si el elemento no es bandera, imprime el elemento con su fe y agrega a sus hijos a la cola
        //Termina cuando ya no hay más elementos
        while (!col.estaVacia()) {
            aux = col.quita();

            if (aux == null) { 
                System.out.println(); 
                if (!col.estaVacia()) {
                    col.agrega(null); 
                }
            } else {
                System.out.print(aux.elem + "(FE: " + aux.fe + ")  "); 

                if (aux.izq != null) {
                    col.agrega(aux.izq);
                }
                if (aux.der != null) {
                    col.agrega(aux.der);
                }
            }
        }
    }

   
   

   //Prueba para ver cómo funcionan el imprime por nivel y el borra
   public static void main(String[] args) {
       ArbolAVL1<Integer> arbol = new ArbolAVL1<>(new NodoAVL<>(10)); 
        arbol.agregaAVL(2);
        arbol.agregaAVL(8);
        arbol.agregaAVL(23);
        arbol.agregaAVL(53);
        arbol.agregaAVL(67);
        arbol.agregaAVL(41);
        arbol.agregaAVL(19);
        arbol.agregaAVL(-5);
        arbol.agregaAVL(102);

        // Imprimir árbol por niveles
        System.out.println("Arbol AVL impreso por niveles:");
        arbol.ImprimePorNivel();
        
        arbol.borraAVL(53);
        
        System.out.println("Arbol AVL impreso por niveles:");
        arbol.ImprimePorNivel();
        
    }

    

    
    
    
    
}
