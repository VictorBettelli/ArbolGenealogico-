/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol.genealogico;

import EDD.NodoTree;
import EDD.*;
import Principal.Persona;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
/**
 *
 * @author VictorB
 */

    


public class Visualizador {
    private mxGraph graph;
    private ListaEnlazada listaPersonas;
    private Tree arbol;
  

   public Visualizador(Tree arbol) {
        this.graph = new mxGraph();
        this.arbol = arbol; // Asignar el árbol al visualizador
    }

    public void construirGrafo() {
        if (arbol == null || arbol.getRaiz() == null) {
            JOptionPane.showMessageDialog(null, "El árbol está vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();
        try {
            // Crear nodos y relaciones desde la raíz del árbol
            agregarNodosYRelaciones(arbol.getRaiz(), parent, null);
        } finally {
            graph.getModel().endUpdate();
        }

        // Configurar el layout jerárquico
        mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
        layout.execute(graph.getDefaultParent());

        // Crear la ventana para mostrar el grafo
        JFrame frame = new JFrame("Árbol Genealógico");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setBackground(Color.WHITE);
        frame.add(graphComponent);
        frame.setVisible(true);

        // Agregar un listener para mostrar información al hacer clic en un nodo
        graphComponent.getGraphControl().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Object cell = graphComponent.getCellAt(e.getX(), e.getY());
                if (cell != null) {
                    String id = graph.getLabel(cell);
                    mostrarInformacionNodo(id);
                }
            }
        });
    }

    private void agregarNodosYRelaciones(NodoTree nodo, Object parent, Object nodoPadre) {
        if (nodo == null) {
            return;
        }

        // Crear el nodo actual
        String nodoId = nodo.getPersona().getNombre() + "-" + nodo.getPersona().getNumeral();
        Object nodoActual = graph.insertVertex(parent, null, nodoId, 0, 0, 80, 30);

        // Si hay un nodo padre, crear una arista entre el padre y este nodo
        if (nodoPadre != null) {
            graph.insertEdge(parent, null, "", nodoPadre, nodoActual);
        }

        // Recorrer los hijos del nodo actual
        Nodo aux = nodo.getHijos().getCabeza();
        while (aux != null) {
            NodoTree hijo = (NodoTree) aux.getDato();
            agregarNodosYRelaciones(hijo, parent, nodoActual);
            aux = aux.getSiguiente();
        }
    }

    private void mostrarInformacionNodo(String id) {
        String[] parts = id.split("-");
        if (parts.length == 2) {
            String nombre = parts[0];
            String numeral = parts[1];

            // Buscar el nodo de la persona correspondiente en el árbol
            NodoTree nodo = arbol.buscarPersona(nombre + "-" + numeral);

            if (nodo != null) {
                Persona persona = nodo.getPersona();  // Obtener la persona asociada al nodo
                StringBuilder info = new StringBuilder();
                info.append("Nombre: ").append(persona.getNombre()).append("\n");
                info.append("Apellido: ").append(persona.obtenerApellido()).append("\n");
                info.append("Numeral: ").append(persona.getNumeral()).append("\n");
                info.append("Mote: ").append(persona.getMote()).append("\n");
                info.append("Título: ").append(persona.getTitulo()).append("\n");
                info.append("Cónyuge: ").append(persona.getEsposa()).append("\n");
                info.append("Color de Ojos: ").append(persona.getOjos()).append("\n");
                info.append("Color de Cabello: ").append(persona.getCabello()).append("\n");
                info.append("Destino: ").append(persona.getDestino()).append("\n");
                info.append("Notas: ").append(persona.getNotas()).append("\n");

                // Mostrar información sobre los padres si están disponibles
                ListaEnlazada antepasados = arbol.antepasados(nodo);
                if (antepasados != null && !antepasados.estaVacia()) {
                    info.append("Antepasados:\n");
                    Nodo aux = antepasados.getCabeza();
                    while (aux != null) {
                        Persona ancestro = (Persona) aux.getDato();
                        info.append("\t").append(ancestro.getNombre()).append(" ").append(ancestro.obtenerApellido()).append("\n");
                        aux = aux.getSiguiente();
                    }
                }

                // Mostrar información sobre los hijos
                ListaEnlazada hijos = nodo.getHijos();  // Accedemos a los hijos a través del NodoTree
                if (hijos != null && !hijos.estaVacia()) {
                    info.append("Hijos:\n");
                    Nodo hijoNodo = hijos.getCabeza();
                    while (hijoNodo != null) {
                        NodoTree hijo = (NodoTree) hijoNodo.getDato();
                        Persona hijoPersona = hijo.getPersona();
                        info.append("\t").append(hijoPersona.getNombre()).append(" ").append(hijoPersona.obtenerApellido()).append("\n");
                        hijoNodo = hijoNodo.getSiguiente();
                    }
                }

                // Mostrar la información en un cuadro de diálogo
                JOptionPane.showMessageDialog(null, info.toString(), "Información de " + persona.getNombre(), JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró información asociada al nodo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
//    public static void visualizarArbol(Tree arbol) {
//        if (arbol.treeVacio()) {
//            System.out.println("El árbol está vacío.");
//            return;
//        }
//
//        Graph grafo = new SingleGraph("Árbol");
//        grafo.setAttribute("ui.stylesheet", "node { text-alignment: above; text-size: 15; }");
//
//        // Recorrer el árbol y agregar nodos y aristas al grafo
//        agregarNodoAGrafo(arbol.getRaiz(), grafo, null);
//
//        grafo.display();
//    }
//
//    private static void agregarNodoAGrafo(NodoTree nodoActual, Graph grafo, String nodoPadreId) {
//        if (nodoActual == null) {
//            return;
//        }
//
//        String idNodoActual = nodoActual.getPersona().getNombre(); // Usa un identificador único, como el nombre
//        grafo.addNode(idNodoActual).setAttribute("ui.label", idNodoActual);
//
//        // Si hay un nodo padre, agrega una arista
//        if (nodoPadreId != null) {
//            String idArista = nodoPadreId + "-" + idNodoActual;
//            grafo.addEdge(idArista, nodoPadreId, idNodoActual, true);
//        }
//
//        // Recorrer los hijos del nodo actual
//        Nodo aux = nodoActual.getHijos().getCabeza();
//        while (aux != null) {
//            NodoTree hijo = (NodoTree) aux.getDato();
//            agregarNodoAGrafo(hijo, grafo, idNodoActual);
//            aux = aux.getSiguiente();
//        }
//    }

//
//   
