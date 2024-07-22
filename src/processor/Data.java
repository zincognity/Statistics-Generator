package src.processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import src.models.Event;
import src.utils.Logger;

public class Data {
    // Se crea la función loadDara que permitirá guardar los datos del archivo data.csv dentro de una variable local.
    public List<Event> loadData(String filePath){
        // Se crea la lista de eventos.
        List<Event> events = new ArrayList<>();
        // Se crea la variable line que será el lector de cada línea del archivo.
        String line;
        // Se realiza un try para identificar cualquier error al momento de tratar de leer el archivo.
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            // Se inicia con un readLine para saltar la cabecera.
            br.readLine();
            // Se crea un while con la condición de que la línea un contenido.
            while ((line = br.readLine()) != null) {
                // Se crea la variable values que permitirá serarar los datos, y se usa el replaceAll para eliminar los " de los datos y el split que separa los datos después de una ,.
                String[] values = line.replaceAll("\"", "").trim().split(",");
                // Se declarar y definen los valores obtenidos.
                String date = values[1];
                String time = values[2];
                String latitude = values[3];
                String longitude = values[4];
                String depth = values[5];
                String magnitude = values[6];
                String cutoff_date = values[7];

                // Se verifica que los datos no estés vacíos antes de convertir
                if(!date.isEmpty() && !time.isEmpty() && !latitude.isEmpty() && !longitude.isEmpty() && !depth.isEmpty() && !magnitude.isEmpty() && !cutoff_date.isEmpty()){
                    // Se inicializa la clase Event y le pasa los pámetros convertidos.
                    Event event = new Event(
                        Integer.parseInt(date),
                        Integer.parseInt(time),
                        Double.parseDouble(latitude),
                        Double.parseDouble(longitude),
                        Double.parseDouble(depth),
                        Double.parseDouble(magnitude),
                        Integer.parseInt(cutoff_date)
                    );
                    // Se agregan los eventos en la lista de eventos.
                    events.add(event);
                }
            }
        } catch (IOException e) {
            // Se usa printStackTrace para identificar el error detalladamente.
            e.printStackTrace();
        } catch (NumberFormatException e) { // Por si detecta un error al convertir un dato.
            // Mensaje de error al convertir algún valor.
            System.out.println("Error converting a numeric value: " + e.getMessage());
            // Lo escribe en el Logger.
            Logger.log("ERROR CONVERTING VALUES", e.getMessage());
        }
        // Retorna los eventos.
        return events;
    }
}
