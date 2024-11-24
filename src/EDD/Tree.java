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

public class Tree {

    private NodoTree raiz;

    public Tree() {
        this.raiz = null;
    }

    public NodoTree getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoTree raiz) {
        this.raiz = raiz;
    }

    public boolean treeVacio() {
        if (raiz == null) {
            return true;
        }
        return false;
    }

    public void insertar(NodoTree padre, Persona hijo) {
        padre.insertarhijo(hijo);

    }

    public void crearRaiz(Persona raizPer) {
        NodoTree raizP = new NodoTree(raizPer);
        setRaiz(raizP);
    }
    

    public NodoTree buscarPersona(String nombreUni) {
        if (!treeVacio()) {

            // Cola para manejar el recorrido en anchura (BFS)
            Cola cola = new Cola();

            // Inicializa la cola con la parada de partida y marca como visitada
            cola.encolar(raiz);

            while (!cola.estaVacia()) {
                // Desencola la siguiente parada y la agrega a la cobertura
                NodoTree actual = (NodoTree) cola.desencolar();
                if (actual.getPersona().nombreuni().equalsIgnoreCase(nombreUni)) {
                    return actual;
                }

                Nodo nodo = actual.getHijos().getCabeza();

                // Itera sobre cada parada adyacente
                while (nodo != null) {
                    NodoTree nodoHijoActual = (NodoTree) nodo.getDato();
                    cola.encolar(nodoHijoActual);
                    nodo = nodo.getSiguiente();
                }

            }

            return null;

        }

        return null;
    }

     public void mostrarPorNiveles() {
        if (!this.treeVacio()) {
            String arbolStr = "Arbol General:\n";
            Cola cola = new Cola();

            cola.encolar(this.raiz);

            while (!cola.estaVacia()) {
                NodoTree nodoActual = (NodoTree) cola.desencolar();
                Persona personaActual = (Persona) nodoActual.getPersona();

                arbolStr += personaActual.toString() + "\n";

                Nodo temp = nodoActual.getHijos().getCabeza();
                while (temp != null) {
                    NodoTree nodoHijoActual = (NodoTree) temp.getDato();
                    cola.encolar(nodoHijoActual);

                    temp = temp.getSiguiente();
                }
            }

            System.out.println(arbolStr);
        }
    }

    public int nivelMaximo() {
        if (this.treeVacio()) {
            return 0;
        }
        int max = 0;
        Cola cola = new Cola();
        cola.encolar(this.raiz);

        Cola colaNiveles = new Cola();
        colaNiveles.encolar(1);

        while (!cola.estaVacia()) {
            NodoTree nodoActual = (NodoTree) cola.desencolar();
            int nivelActual = (int) colaNiveles.desencolar();

            max = Math.max(max, nivelActual);

            Nodo temp = nodoActual.getHijos().getCabeza();
            while (temp != null) {
                NodoTree nodoHijoActual = (NodoTree) temp.getDato();
                cola.encolar(nodoHijoActual);
                colaNiveles.encolar(nivelActual + 1);
                temp = temp.getSiguiente();
            }
        }

        return max;
    }
    public ListaEnlazada listarNivel(int nivel) {
        ListaEnlazada personasNivel = new ListaEnlazada();
        if (this.treeVacio()) {
            return null;
        }
        
        Cola cola = new Cola();
        cola.encolar(this.raiz);

        Cola colaNiveles = new Cola();
        colaNiveles.encolar(1);

        while (!cola.estaVacia()) {
            NodoTree nodoActual = (NodoTree) cola.desencolar();
            int nivelActual = (int) colaNiveles.desencolar();

            if(nivelActual == nivel){
                Persona personaActual = (Persona) nodoActual.getPersona();
                personasNivel.agregarfinal(personaActual);
            }

            Nodo temp = nodoActual.getHijos().getCabeza();
            while (temp != null) {
                NodoTree nodoHijoActual = (NodoTree) temp.getDato();
                cola.encolar(nodoHijoActual);
                colaNiveles.encolar(nivelActual + 1);
                temp = temp.getSiguiente();
            }
        }

        return personasNivel;

    }
        
    public ListaEnlazada antepasados(NodoTree nodo) {
        if (nodo == null) {
            return null;
        }

        ListaEnlazada ancestros = new ListaEnlazada();
        NodoTree actual = nodo.getPadre();

        while (actual != null) {
            Persona personaActual = (Persona) actual.getPersona();
            ancestros.agregarfinal(personaActual);
            actual = actual.getPadre();
        }

        return ancestros;
     }

    
public static Tree construirTree(ListaEnlazada listaPersonas) {
    if (listaPersonas == null || listaPersonas.estaVacia()) {
        System.out.println("La lista de personas está vacía o nula.");
        return null;
    }

    Tree arbolGenealogico = new Tree();

    // Paso 1: Crear la raíz
    Nodo aux = listaPersonas.getCabeza();
    while (aux != null) {
        Persona persona = (Persona) aux.getDato();
        if ("[Unknown]".equalsIgnoreCase(persona.getPadre())) {
            arbolGenealogico.crearRaiz(persona);
            System.out.println("Raíz creada: " + persona.getNombre());
            break;
        }
        aux = aux.getSiguiente();
    }

    if (arbolGenealogico.treeVacio()) {
        System.out.println("No se encontró una raíz. Verifica los datos.");
        return null;
    }

    // Paso 2: Agregar hijos recursivamente
    agregarHijosRecursivamente(arbolGenealogico.getRaiz(), listaPersonas);

    System.out.println("Árbol genealógico construido con éxito.");
    arbolGenealogico.mostrarPorNiveles(); // Mostrar el árbol construido por niveles
    return arbolGenealogico;
}

// Método auxiliar para agregar hijos recursivamente
private static void agregarHijosRecursivamente(NodoTree nodoActual, ListaEnlazada listaPersonas) {
    if (nodoActual == null || nodoActual.getPersona() == null) {
        return;
    }

    // Obtener la lista de hijos de la persona en el nodo actual
    Persona persona = nodoActual.getPersona();
    Nodo aux = persona.getHijos().getCabeza();
    while (aux != null) {
        String nombreHijo = (String) aux.getDato();
        Persona hijoPersona = listaPersonas.buscarPorNombre(nombreHijo);

        if (hijoPersona != null) {
            // Crear el nodo del hijo y agregarlo al nodo actual
            NodoTree nodoHijo = new NodoTree(hijoPersona);
            nodoHijo.setPadre(nodoActual);
            nodoActual.getHijos().agregarfinal(nodoHijo);

            System.out.println("Añadido: " + hijoPersona.getNombre() + " como hijo de " + persona.getNombre());

            // Llamada recursiva para agregar los hijos del hijo
            agregarHijosRecursivamente(nodoHijo, listaPersonas);
        }
        aux = aux.getSiguiente();
    }
}

private static void crearEnlacesDesdeRaiz(NodoTree nodo, ListaEnlazada listaPersonas) {
    if (nodo == null) {
        return;
    }

    Persona persona = nodo.getPersona();
    ListaEnlazada hijos = persona.getHijos(); // Obtener la lista de hijos de la persona

    if (hijos != null && !hijos.estaVacia()) {
        Nodo aux = hijos.getCabeza();
        while (aux != null) {
            String nombreHijo = (String) aux.getDato();

            // Buscar al hijo en la lista de personas
            Persona hijoPersona = buscarPersonaPorNombre(nombreHijo, listaPersonas);
            if (hijoPersona != null) {
                NodoTree nodoHijo = new NodoTree(hijoPersona);
                nodoHijo.setPadre(nodo); // Establecer referencia al padre
                nodo.getHijos().agregarfinal(nodoHijo); // Añadir al nodo actual como hijo
                System.out.println("Añadido: " + hijoPersona.getNombre() + " como hijo de " + persona.getNombre());

                // Recursivamente crear enlaces para este hijo
                crearEnlacesDesdeRaiz(nodoHijo, listaPersonas);
            }

            aux = aux.getSiguiente();
        }
    }
}

private static Persona buscarPersonaPorNombre(String nombre, ListaEnlazada listaPersonas) {
    Nodo actual = listaPersonas.getCabeza();
    while (actual != null) {
        Persona persona = (Persona) actual.getDato();
        if (persona.getNombre().equalsIgnoreCase(nombre)) {
            return persona;
        }
        actual = actual.getSiguiente();
    }
    return null; // Si no se encuentra, retorna null
}

private static void recorrerArbol(NodoTree nodo, int nivel) {
    if (nodo == null) {
        return;
    }

    String indentacion = " ".repeat(nivel * 2);
    System.out.println(indentacion + "- " + nodo.getPersona().getNombre());

    Nodo aux = nodo.getHijos().getCabeza();
    while (aux != null) {
        NodoTree hijo = (NodoTree) aux.getDato();
        recorrerArbol(hijo, nivel + 1);
        aux = aux.getSiguiente();
    }
}
 
private static boolean agregarNodo(NodoTree nodoActual, Persona persona) {
    if (nodoActual == null) {
        return false;
    }

    // Normalizar nombres a minúsculas para evitar discrepancias
    String nombrePadreNodo = nodoActual.getPersona().getNombre().toLowerCase();
    String nombrePadrePersona = persona.getPadre().toLowerCase();

    // Si el nodo actual es el padre, agregar como hijo
    if (nombrePadreNodo.equals(nombrePadrePersona)) {
        NodoTree nodoHijo = new NodoTree(persona);
        nodoHijo.setPadre(nodoActual); // Establecer referencia al padre
        nodoActual.getHijos().agregarfinal(nodoHijo);
        System.out.println("Añadido: " + persona.getNombre() + " como hijo de " + persona.getPadre());
        return true;
    }

    // Buscar en los hijos
    Nodo aux = nodoActual.getHijos().getCabeza();
    while (aux != null) {
        NodoTree hijo = (NodoTree) aux.getDato();
        if (agregarNodo(hijo, persona)) {
            return true;
        }
        aux = aux.getSiguiente();
    }

    return false;
}



   
    private static void agregarNodo1(NodoTree nodoActual, Persona persona) {
        if (nodoActual == null) {
            return;
        }

        // Si el nodo actual es el padre de la persona, agregar como hijo
        if (nodoActual.getPersona().getNombre().equals(persona.getPadre())) {
            nodoActual.getHijos().agregarfinal(new NodoTree(persona));
            return;
        }

        // Si no es el padre, buscar en los hijos del nodo actual
        Nodo aux = nodoActual.getHijos().getCabeza();
        while (aux != null) {
            NodoTree hijo = (NodoTree) aux.getDato();
            agregarNodo(hijo, persona);
            aux = aux.getSiguiente();
        }
    }
}






 