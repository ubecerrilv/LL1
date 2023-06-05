package principal;

import java.util.*;

public class LeftRecursionRemover {
    
    // Método para eliminar la recursividad indirecta por la izquierda
    public static List<String> eliminarRecursion(List<String> producciones) {
        List<String> nuevasProducciones = new ArrayList<>();
        
        for (String produccion : producciones) {
            String[] partes = produccion.split("->");
            String noTerminal = partes[0].trim();
            String[] opciones = partes[1].trim().split("\\|");
            
            List<String> alfa = new ArrayList<>();
            List<String> beta = new ArrayList<>();
            
            for (String opcion : opciones) {
                if (opcion.startsWith(noTerminal)) {
                    beta.add(opcion.substring(noTerminal.length()).trim());
                } else {
                    alfa.add(opcion.trim());
                }
            }
            
            if (!beta.isEmpty()) {
                String nuevoNoTerminal = noTerminal + "'";
                
                for (String opcion : alfa) {
                    nuevasProducciones.add(noTerminal + " -> " + opcion + " " + nuevoNoTerminal);
                }
                
                List<String> nuevasOpciones = new ArrayList<>();
                for (String opcion : beta) {
                    nuevasOpciones.add(opcion + " " + nuevoNoTerminal);
                }
                nuevasOpciones.add("ε"); // Añadir producción epsilon
                
                nuevasProducciones.add(nuevoNoTerminal + " -> " + String.join(" | ", nuevasOpciones));
            } else {
                nuevasProducciones.add(produccion);
            }
        }
        
        return nuevasProducciones;
    }
    
    // Método principal para probar la eliminación de recursión indirecta
    public static void main(String[] args) {
        // Definir las producciones de la gramática
        List<String> producciones = new ArrayList<>();
        producciones.add("S -> A a | b");
        producciones.add("A -> A c | S d | ε");
        
        // Mostrar las producciones originales
        System.out.println("Producciones originales:");
        for (String produccion : producciones) {
            System.out.println(produccion);
        }
        
        // Eliminar la recursividad indirecta
        List<String> nuevasProducciones = eliminarRecursion(producciones);
        
        // Mostrar las nuevas producciones
        System.out.println("\nProducciones sin recursión indirecta:");
        for (String produccion : nuevasProducciones) {
            System.out.println(produccion);
        }
    }
}

