package src.processor;

import java.io.FileWriter;
import java.io.IOException;

import src.utils.Logger;

public class ReportExporter {
    // Se crea la función exportReport para crear archivos que contendrán los datos escogidos según el menú seleccionado.
    public void exportReport(String content, String fileName){
        // Se hace un try para identificar un error al exportar.
        try (FileWriter writer = new FileWriter("reports/" + fileName)){ // Se define un FileWriter con el parámetro del directorio y el nombre del archivo.
            // Se escribe el contenido.
            writer.write(content);
        } catch (IOException e) {
            // Se escribe en el Logger algún error al exportar.
            Logger.log("ERROR EXPORT", "Failed to export report: " + e.getMessage());
        }
    }
}
