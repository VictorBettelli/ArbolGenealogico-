/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import EDD.ListaEnlazada;

/**
 *
 * @author Victor Bettelli
 */
public class Persona {

    private String nombre;         // Nombre completo del integrante
    private String mote;           // Mote único del integrante
    private String titulo;         // Título nobiliario (opcional)
    private String esposa;
    private String ojos;           // Color de ojos
    private String cabello;        // Color de cabello
    private String destino;
    private String notas;
    private ListaEnlazada hijos;
    private String padre;
    private String madre;
    private String numeral;// Notas adicionales sobre el integrante

    public Persona(String nombre, String mote, String titulo, String esposa, String ojos, String cabello, String destino, String notas, String padre, String madre, String numeral) {
        this.nombre = nombre;
        this.mote = mote;
        this.titulo = titulo;
        this.esposa = esposa;
        this.ojos = ojos;
        this.cabello = cabello;
        this.destino = destino;
        this.notas = notas;
        this.hijos = hijos;
        this.padre = padre;
        this.madre = madre;
        this.numeral = numeral;
    }

    public ListaEnlazada getHijos() {
        return hijos;
    }

    public void setHijos(ListaEnlazada hijos) {
        this.hijos = hijos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEsposa() {
        return esposa;
    }

    public void setEsposa(String esposa) {
        this.esposa = esposa;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getMote() {
        return mote;
    }

    public void setMote(String mote) {
        this.mote = mote;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getOjos() {
        return ojos;
    }

    public void setOjos(String ojos) {
        this.ojos = ojos;
    }

    public String getCabello() {
        return cabello;
    }

    public void setCabello(String cabello) {
        this.cabello = cabello;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public String getMadre() {
        return madre;
    }

    public void setMadre(String madre) {
        this.madre = madre;
    }

    public String getNumeral() {
        return numeral;
    }

    public void setNumeral(String numeral) {
        this.numeral = numeral;
    }

    public String nombreuni() {
        if (mote != null) {
            return mote;
        }
        return nombre + " " + numeral;
    }

    public String obtenerApellido() {
        // Dividimos el nombre completo por espacios
        String[] partes = getNombre().split(" ");

        // Devolvemos el último elemento del arreglo, que es el apellido
        return partes[partes.length - 1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Información de la Persona:\n");
        sb.append("---------------------------\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Mote: ").append(mote).append("\n");
        sb.append("Título: ").append(titulo).append("\n");
        sb.append("Esposa: ").append(esposa).append("\n");
        sb.append("Color de Ojos: ").append(ojos).append("\n");
        sb.append("Color de Cabello: ").append(cabello).append("\n");
        sb.append("Destino: ").append(destino).append("\n");
        sb.append("Notas: ").append(notas).append("\n");
        sb.append("hijos: ").append(hijos).append("\n");
        sb.append("Padre: ").append(padre).append("\n");
        sb.append("Madre: ").append(madre).append("\n");
        sb.append("Numeral: ").append(numeral).append("\n");
        sb.append("---------------------------");
        return sb.toString();
    }

    public void agregarHijo(String hijo) {
        if (hijos == null) {
            hijos = new ListaEnlazada(); // Inicializa la lista si es nula
        }
        hijos.agregarfinal(hijo); // Agrega el hijo al final de la lista
    }
}


