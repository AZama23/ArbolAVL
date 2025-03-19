/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package arbolavl;

/**
 *
 * @author edi
 */
//Interfaz ADT de la cola
public interface ColaADT <T>{
    public void agrega (T dato);
    public T quita();
    public T consultaPimero();
    public boolean estaVacia();
    public boolean estaLlena();
    
    
}
