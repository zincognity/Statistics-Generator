package src.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public static void log(String type, String message){
        // Se crea la variable file_Path para indicar la ubicación y el nombre del archivo que creará o editará.
        String file_Path = "logs/auditoría.log";
        // Se hace un try para identificar algún error.
        try (FileWriter writer = new FileWriter(file_Path, true)){
            // Se le da el formato del tiempo.
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // Se le da el formato que tomará el tiempo, el tipo de error, y el mensaje.
            String logMessage = String.format("[%s] [%s] %s%n", LocalDateTime.now().format(formatter), type, message);
            // Escribe el formato del mensaje.
            writer.write(logMessage);
        } catch (IOException e) {
            // Se usa el printStackTrace para identificar mejor el error.
            e.printStackTrace();
        }
    }
}
