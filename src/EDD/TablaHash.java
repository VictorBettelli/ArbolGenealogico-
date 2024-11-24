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
public class TablaHash {

    private ListaEnlazada[] tabla;
    private int tmax;

    public TablaHash(int tmax) {
        this.tmax = tmax;
        this.tabla = new ListaEnlazada[tmax];
        for (int i = 0; i < tmax; i++) {
            tabla[i] = new ListaEnlazada();
        }
    }

    public ListaEnlazada[] getTabla() {
        return tabla;
    }

    public void setTabla(ListaEnlazada[] tabla) {
        this.tabla = tabla;
    }

    public int getTmax() {
        return tmax;
    }

    public void setTmax(int tmax) {
        this.tmax = tmax;
    }

    public boolean estaVacia() {
        for (int i = 0; i < tmax; i++) {
            if (!tabla[i].estaVacia()) {
                return false;

            }
        }

        return true;

    }

    public int hash(Object clave) {
        return Math.abs(clave.hashCode());
    }

    public int getIndex(Object clave) {
        return this.hash(clave) % tmax;
    }

    public void insertar(Object clave, Object data) {
        int indice = this.getIndex(clave);
        ListaEnlazada lista = tabla[indice];
        if (!lista.seEncuentra(data)) {
            lista.agregarfinal(data);
        }
    }

    public boolean buscar(String clave) {
        if (!this.estaVacia()) {
            int indice = this.getIndex(clave);
            ListaEnlazada lista = tabla[indice];
            if (!lista.estaVacia()) {
                Nodo temp = lista.getCabeza();
                for (int i = 0; i < lista.getTamanio(); i++) {
                    Persona persona = (Persona) temp.getDato();
                    if (persona.nombreuni().equalsIgnoreCase(clave)) {
                        return true;
                    }
                }
            }

            return false;
        }

        return false;

    }

    public void mostrar() {
        if (!estaVacia()) {
            for (int i = 0; i < tmax; i++) {
                tabla[i].mostrar();

            }
        } else {
            System.out.println("La Tabla esta VAcia");
        }

    }

    public void destruir() {
        for (int i = 0; i < tmax; i++) {
            tabla[i] = new ListaEnlazada();

        }

    }

   

}
