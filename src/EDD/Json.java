/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import EDD.*;
import Principal.Persona;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class Json {

    public ListaEnlazada Read() {
        StringBuilder texto = new StringBuilder();
        String line;
        File doc_data;
        ListaEnlazada listaPersonas = new ListaEnlazada();

        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filtro_json = new FileNameExtensionFilter(".json", "json");
        fc.setFileFilter(filtro_json);
        fc.setCurrentDirectory(new File("Packages"));
        fc.setDialogTitle("Seleccione el archivo para cargar la Casa deseada");

        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            doc_data = fc.getSelectedFile();
            if (doc_data != null && doc_data.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(doc_data))) {
                    while ((line = br.readLine()) != null) {
                        if (!line.isEmpty()) {
                            texto.append(line).append("\n");
                        }
                    }

                    if (!JSONvalido(texto.toString())) {
                        JOptionPane.showMessageDialog(null, "El archivo JSON no cumple con el formato requerido.");
                        return null;
                    }

                    try {
                        Gson gson = new Gson();
                        Map<String, List<Map<String, List<Map<String, Object>>>>> houseMap = gson.fromJson(
                                texto.toString(),
                                new TypeToken<Map<String, List<Map<String, List<Map<String, Object>>>>>>() {
                                }.getType()
                        );

                        houseMap.forEach((house, members) -> {
                            System.out.println("House: " + house);
                            for (Map<String, List<Map<String, Object>>> member : members) {
                                member.forEach((name, details) -> {
                                    String nombre = name;
                                    String mote = "";
                                    String titulo = "";
                                    String esposa = "";
                                    String ojos = "";
                                    String cabello = "";
                                    String destino = "";
                                    String notas = "";
                                    String padre = "";
                                    String madre = "";
                                    String numeral = "";
                                    ListaEnlazada hijos = new ListaEnlazada(); // Lista para los hijos

                                    for (Map<String, Object> detail : details) {
                                        for (Map.Entry<String, Object> entry : detail.entrySet()) {
                                            switch (entry.getKey()) {
                                                case "Of his name":
                                                    numeral = entry.getValue().toString();
                                                    break;
                                                case "Born to":
                                                    if (padre.isEmpty()) {
                                                        padre = entry.getValue().toString();
                                                    } else {
                                                        madre = entry.getValue().toString();
                                                    }
                                                    break;
                                                case "Known throughout as":
                                                    mote = entry.getValue().toString();
                                                    break;
                                                case "Held title":
                                                    titulo = entry.getValue().toString();
                                                    break;
                                                case "Wed to":
                                                    esposa = entry.getValue().toString();
                                                    break;
                                                case "Of eyes":
                                                    ojos = entry.getValue().toString();
                                                    break;
                                                case "Of hair":
                                                    cabello = entry.getValue().toString();
                                                    break;
                                                case "Father to":
                                                    if (!entry.getValue().toString().isEmpty()) {
                                                        String[] hijosArray = entry.getValue().toString().split(", ");
                                                        for (String hijoNombre : hijosArray) {
                                                            hijos.agregarfinal(hijoNombre.trim()); // Añadir cada hijo a la lista temporal
                                                        }
                                                    }
                                                    break;
                                                case "Notes":
                                                    notas = entry.getValue().toString();
                                                    break;
                                                case "Fate":
                                                    destino = entry.getValue().toString();
                                                    break;
                                            }
                                        }
                                    }

                                    // Crear la persona y asignarle sus hijos
                                    Persona persona = new Persona(nombre, mote, titulo, esposa, ojos, cabello, destino, notas, padre, madre, numeral);
                                    persona.setHijos(hijos); // Asignar la lista de hijos
                                    listaPersonas.agregarfinal(persona);
                                });
                            }
                        });
                        return listaPersonas;
                    } catch (JsonSyntaxException e) {
                        JOptionPane.showMessageDialog(null, "El archivo JSON no es válido: " + e.getMessage());
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error procesando el archivo JSON: " + e.getMessage());
                    }

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error durante la lectura del archivo: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "El archivo seleccionado no existe.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se seleccionó ningún archivo.");
        }
        return null;
    }

    private boolean JSONvalido(String jsonString) {
        String[] requiredKeys = {"Of his name", "Born to", "Known throughout as", "Held title", "Of eyes", "Of hair", "Notes"};
        for (String key : requiredKeys) {
            if (!jsonString.contains(key)) {
                return false;
            }
        }
        return true;
    }
}

//    public ListaEnlazada cargarJSON() {
//        ListaEnlazada listaPersonas = new ListaEnlazada();
//        String linea;
//        File archivoSeleccionado;
//
//        // Configurar el JFileChooser para seleccionar archivos JSON
//        JFileChooser selectorArchivo = new JFileChooser();
//        FileNameExtensionFilter filtroJSON = new FileNameExtensionFilter(".json", "json");
//        selectorArchivo.setFileFilter(filtroJSON);
//        selectorArchivo.setCurrentDirectory(new File("Packages"));
//        selectorArchivo.setDialogTitle("Seleccione un archivo JSON para cargar datos");
//
//        if (selectorArchivo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//            archivoSeleccionado = selectorArchivo.getSelectedFile();
//            if (archivoSeleccionado != null && archivoSeleccionado.exists()) {
//                try (BufferedReader lector = new BufferedReader(new FileReader(archivoSeleccionado))) {
//                    StringBuilder contenidoArchivo = new StringBuilder();
//                    while ((linea = lector.readLine()) != null) {
//                        contenidoArchivo.append(linea).append("\n");
//                    }
//
//                    // Procesar el JSON usando JSON.simple
//                    try {
//                        JSONParser parser = new JSONParser();
//                        JSONObject jsonObject = (JSONObject) parser.parse(contenidoArchivo.toString());
//
//                        for (Object key : jsonObject.keySet()) {
//                            String casa = (String) key;
//                            JSONArray miembros = (JSONArray) jsonObject.get(casa);
//                            String apellido = extraerApellido(casa);
//
//                            for (Object miembroObj : miembros) {
//                                JSONObject miembro = (JSONObject) miembroObj;
//                                for (Object nombreKey : miembro.keySet()) {
//                                    String nombre = (String) nombreKey;
//                                    JSONArray detalles = (JSONArray) miembro.get(nombre);
//                                    Persona persona = construirPersona(nombre, apellido, detalles);
//                                    listaPersonas.agregarfinal(persona);
//                                }
//                            }
//                        }
//                        return listaPersonas;
//
//                    } catch (ParseException e) {
//                        JOptionPane.showMessageDialog(null, "Error al analizar el archivo JSON: " + e.getMessage());
//                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(null, "Error procesando el archivo JSON: " + e.getMessage());
//                    }
//
//                } catch (IOException e) {
//                    JOptionPane.showMessageDialog(null, "Error durante la lectura del archivo: " + e.getMessage());
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "El archivo seleccionado no existe.");
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "No se seleccionó ningún archivo.");
//        }
//        return null;
//    }
//
//    private String extraerApellido(String casa) {
//        int indice = casa.lastIndexOf(" ");
//        return indice != -1 ? casa.substring(indice).trim() : casa;
//    }
//
//    private Persona construirPersona(String nombre, String apellido, JSONArray detalles) {
//        String numeral = "";
//        String padre = "";
//        String madre = "";
//        String mote = "";
//        String titulo = "";
//        String ojos = "";
//        String cabello = "";
//        String notas = "";
//        String destino = "";  // Agregar una variable para el destino
//        String esposa = "";
//        for (Object obj : detalles) {
//            JSONObject detalle = (JSONObject) obj;
//
//            for (Object key : detalle.keySet()) {
//                String clave = (String) key;
//                Object valor = detalle.get(key);
//
//                switch (clave) {
//                    case "Of his name":
//                        numeral = valor.toString();
//                        break;
//                    case "Born to":
//                        // Aquí manejamos múltiples entradas "Born to"
//                        if (padre.isEmpty()) {
//                            padre = valor.toString();
//                        } else if (madre.isEmpty()) {
//                            madre = valor.toString();
//                        }
//                        break;
//                    case "Known throughout as":
//                        mote = valor.toString();
//                        break;
//                    case "Held title":
//                        titulo = valor.toString();
//                        break;
//                    case "Of eyes":
//                        ojos = valor.toString();
//                        break;
//                    case "Of hair":
//                        cabello = valor.toString();
//                        break;
//                    case "Notes":
//                        notas = valor.toString();
//                        break;
//                    case "Destination":  // Añadir el caso para "Destination"
//                        destino = valor.toString();
//                        break;
//                    case "Wed to":  // Caso para asignar la esposa
//                        esposa = valor.toString();
//                        break;
//                }
//            }
//        }
//
//        // Crear la Persona con el nuevo campo "destino"
//        return new Persona(nombre, mote, titulo, esposa, ojos, cabello, notas, padre, madre, numeral, destino);
//    }
//
//    private String obtenerNombreDelLinaje(Tree arbolGenealogico) {
//        if (arbolGenealogico.getRaiz() != null) {
//            Persona raiz = arbolGenealogico.getRaiz().getPersona();
//            return raiz.getNombre(); // Ajusta según el atributo que almacene el nombre del linaje
//        }
//        return "Linaje desconocido";
//    }

