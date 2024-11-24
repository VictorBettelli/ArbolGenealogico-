/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import Principal.Persona;

/**
 *
 * @author VictorB
 */
public class ListaEnlazada<T> {

    private Nodo<T> cabeza;
    private int tamanio;

    public ListaEnlazada() {
        this.cabeza = null;
        this.tamanio = 0;
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo<T> cabeza) {
        this.cabeza = cabeza;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }

    public void agregarfinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamanio++;
    }

    public void eliminafinal() {
        if (!estaVacia()) {

            Nodo<T> actual = cabeza;
            while (actual.getSiguiente().getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(null);

            tamanio--;
        }

    }

    public T obtener(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    public void destruir() {
        cabeza = null;
        tamanio = 0;
    }

    public void mostrar() {
        Nodo temp = cabeza;
        while (temp != null) {
            System.out.println(temp.getDato() + " ");
            temp = temp.getSiguiente();
        }

    }
    
    public boolean seEncuentra(Object dato) {
        Nodo actual = cabeza;

        // Lista vacia
        if (estaVacia()) {
            return false;
        } else {
            
            while (actual != null) {
                if(actual.getDato()==dato){
                    return true;
                }
                actual = actual.getSiguiente();
            }

           
            return false;
        }
    }
    public Persona buscarPorNombre(String nombre) {
    Nodo actual = cabeza;
    while (actual != null) {
        Persona persona = (Persona) actual.getDato();
        if (persona.getNombre().equalsIgnoreCase(nombre)) {
            return persona; // Devuelve la persona si coincide el nombre
        }
        actual = actual.getSiguiente();
    }
    return null; // Devuelve null si no se encuentra
}
}
