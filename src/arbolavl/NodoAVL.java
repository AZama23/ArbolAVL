/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolavl;

/**
 *
 * @author HP15
 */
//Nodos usados en el arbol AVL
public class NodoAVL  <T extends Comparable<T>> {
    T elem;
    NodoAVL<T> izq;
    NodoAVL<T> der, papa;
    int fe;

    //Constructores
    public NodoAVL() {
    }

    public NodoAVL(T elem, NodoAVL<T> izq, NodoAVL<T> der, NodoAVL<T> papa, int fe) {
        this.elem = elem;
        this.izq = izq;
        this.der = der;
        this.papa = papa;
        this.fe = fe;
    }

    public NodoAVL(T elem) {
        this.elem = elem;
    }
    
    //Getters y Setters
    public T getElem() {
        return elem;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public NodoAVL<T> getIzq() {
        return izq;
    }

    public void setIzq(NodoAVL<T> izq) {
        this.izq = izq;
    }

    public NodoAVL<T> getDer() {
        return der;
    }

    public void setDer(NodoAVL<T> der) {
        this.der = der;
    }

    public NodoAVL<T> getPapa() {
        return papa;
    }

    public void setPapa(NodoAVL<T> papa) {
        this.papa = papa;
    }

    public int getFe() {
        return fe;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }
    
    
    
}
