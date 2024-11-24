/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import Principal.Persona;


/**
 *
 * @author VictorB
 * * Clase Nodo representa a un integrante del árbol genealógico.
 * Cada nodo contiene información sobre un integrante y referencias a sus hijos y su padre.
 */
public class NodoTree {
    private Persona persona;
    private ListaEnlazada hijos;
    private NodoTree padre;
    

    public NodoTree(Persona persona) {
        this.persona = persona;
        this.hijos = new ListaEnlazada();
        this.padre = null;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public ListaEnlazada getHijos() {
        return hijos;
    }

    public void setHijos(ListaEnlazada hijos) {
        this.hijos = hijos;
    }

    public NodoTree getPadre() {
        return padre;
    }

    public void setPadre(NodoTree padre) {
        this.padre = padre;
    }
    
    
    public boolean buscarhijo(Persona hijo){
        if(!hijos.estaVacia()){
            Nodo aux = hijos.getCabeza();
            for (int i = 0; i < hijos.getTamanio(); i++) {
                NodoTree actual = (NodoTree)aux.getDato();
                if ( actual.persona.nombreuni().equalsIgnoreCase(hijo.nombreuni())){
                 return true;
                }
                aux= aux.getSiguiente();
            }
        }
        return false;
    } 
    
    public void insertarhijo(Persona hijo){
        if(!buscarhijo(hijo)){
            NodoTree hijoNew  = new NodoTree(hijo);
            hijoNew.setPadre(this);
            hijos.agregarfinal(hijoNew);
            
        }
        
    }
    
    public boolean Eshoja(){
        return hijos.estaVacia();
    }
}  

