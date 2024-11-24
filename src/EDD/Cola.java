/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Victor Bettelli
 */
public class Cola {

    private Nodo inicio;
    private Nodo ultimo;
    private int iN;

    /**
     * Constructor por defecto que crea una cola vacía.
     */
    public Cola() {
        inicio = null;
        ultimo = null;
        iN = 0;
    }

    //----------getters y setters---------
    /**
     * Devuelve el nodo al inicio de la cola.
     *
     * @return El primer nodo de la cola.
     */
    public Nodo getInicio() {
        return inicio;
    }

    /**
     * Establece el nodo al inicio de la cola.
     *
     * @param inicio El nuevo nodo inicial.
     */
    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    /**
     * Devuelve el &uacute;ltimo nodo de la cola.
     *
     * @return El &uacute;ltimo nodo de la cola.
     */
    public Nodo getUltimo() {
        return ultimo;
    }

    /**
     * Establece el &uacute;ltimo nodo de la cola.
     *
     * @param ultimo El nuevo &uacute;ltimo nodo.
     */
    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }

    /**
     * Devuelve el n&uacute;mero de elementos en la cola.
     *
     * @return El tamano de la cola.
     */
    public int getiN() {
        return iN;
    }

    /**
     * Establece el n&uacute;mero de elementos en la cola.
     *
     * @param iN El nuevo tamano de la cola.
     */
    public void setiN(int iN) {
        this.iN = iN;
    }

    //----------primitivas----------
    /**
     * Elimina todos los elementos de la cola.
     */
    public void destruirCola() {
        Nodo temporal; // No se usa a proposito para que el recolector de basura de Java lo borre
        while (inicio != null) {
            temporal = inicio;
            inicio.setSiguiente(inicio.getSiguiente());
        }
    }

    /**
     * Agrega un nuevo elemento al final de la cola.
     *
     * @param x El elemento a agregar.
     */
    public void encolar(Object x) {
        Nodo nuevoNodo = new Nodo(x);

        if (inicio == null) {
            inicio = nuevoNodo;
        } else {
            ultimo.setSiguiente(nuevoNodo);
        }

        ultimo = nuevoNodo;
        iN++;
    }

    /**
     * Verifica si la cola est&aacute; vac&iacute;a.
     *
     * @return `true` si la cola está vacía, `false` en caso contrario.
     */
    public boolean estaVacia() {
        return iN == 0;
    }

    /**
     * Elimina y devuelve el elemento al frente de la cola.
     *
     * @return El elemento eliminado.
     * @throws RuntimeException si la cola est&aacute; vac&iacute;a.
     */
    public Object desencolar() {
        if (estaVacia()) {
            throw new RuntimeException("La cola está vacía");
        }

        Object dato = inicio.getDato();
        inicio = inicio.getSiguiente();
        iN--;

        return dato; //retorna la info del nodo eliminado
    }
}


