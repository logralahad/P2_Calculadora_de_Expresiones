/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculadora;

/**
 *
 * @author logra
 */
public class Pila {
    
    Nodo inicio = null;
    
    public void push(int dato){
        Nodo nuevo = nuevoNodo(dato);
        if(!isEmpty()){
            nuevo.siguiente = inicio;
        }
        inicio = nuevo;
    }
    
    public int pop(){
        int resultado = inicio.dato;
        Nodo aux = inicio;
        inicio = inicio.siguiente;
        aux.siguiente = null;
        return resultado;
    }
    
    public int peek(){
        return inicio.dato;
    }
    
    public boolean isEmpty(){
        return inicio == null;
    }
    
    public Nodo nuevoNodo(int dato){
        Nodo nuevo = new Nodo();
        nuevo.dato = dato;
        nuevo.siguiente = null;
        return nuevo;
    }
    
}
