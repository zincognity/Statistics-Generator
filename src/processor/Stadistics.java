package src.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.models.Event;

public class Stadistics {
    // Se crea la función generateYearlyStadistics que permitirá guardar los datos de estadísticas según el rango de años.
    public static Map<Integer, Long> generateYearlyStadistics(List<Event> events, int startYear, int endYear){
        // Se crea una variable Map local.
        Map<Integer, Long> stadistics = new HashMap<>();
        // Busca en todos los eventos de la clase eventos.
        for (Event event : events) {
            // Se divide entre 10000 para obtener el año exacto debido a que los datos están de esta forma: 19600130
            int year = event.getDate() / 10000;
            // Se realiza la condición si el año es mayor o igual al año inicial y si el año es menor o igual al año culminado, osea, un rango.
            if(year >= startYear && year <= endYear){
                // Entonces puede setear el año y busca el valor de ese año y si no lo encuentra, entonces lo define con un 0L, que 0 es la longitud del valor y L el identificador.
                stadistics.put(year, stadistics.getOrDefault(year, 0L) + 1); // Se realiza un +1 que indica que se ha encontrado un evento adicional.
            }
        }
        // Retorna las stadísticas obtenidas.
        return stadistics;
    }

    public static Map<Integer, Long> generateMonthlyStatistics(List<Event> events, int year) {
        // Se crea una variable Map local.
        Map<Integer, Long> statistics = new HashMap<>();
        // Busca en todos los eventos de la clase eventos.
        for (Event event : events) {
            // Se divide entre 10000 para obtener el año exacto debido a que los datos están de esta forma: 19600130
            int eventYear = event.getDate() / 10000;
            // Se crea la condición en donde si el año del evento es igual al año, entonces procede.
            if (eventYear == year) {
                // En la variable moth primero se obtiende el valor del dato del evento, luego se divide entre 10000 y se obtiene el resto, y para obtener el mes se divide entre 100, y se setea en el valor entero.
                int month = (event.getDate() % 10000) / 100;
                // Entonces puede setear el mes y busca el valor de ese mes y si no lo encuentra, entonces lo define con un 0L, que 0 es la longitud del valor y L el identificador.
                statistics.put(month, statistics.getOrDefault(month, 0L) + 1); // Se realiza un +1 que indica que se ha encontrado un evento adicional.
            }
        }
        // Retorna las stadísticas obtenidas.
        return statistics;
    }

    public static Map<Integer, Long> generateMagnitudeStatistics(List<Event> events, double minMagnitude, double maxMagnitude, int year) {
        // Se crea una variable Map local.
        Map<Integer, Long> statistics = new HashMap<>();
        // Busca en todos los eventos de la clase eventos.
        for (Event event : events) {
            // Se divide entre 10000 para obtener el año exacto debido a que los datos están de esta forma: 19600130
            int eventYear = event.getDate() / 10000;
            // Se crea la condición en donde si el año del evento es igual al año, y si la magnitud del evento es mayor o igual al valor mínimo, y si la magnitud del evento es menor o igual al valor máximo.
            if (eventYear == year && event.getMagnitude() >= minMagnitude && event.getMagnitude() <= maxMagnitude) {
                // En la variable moth primero se obtiende el valor del dato del evento, luego se divide entre 10000 y se obtiene el resto, y para obtener el mes se divide entre 100, y se setea en el valor entero.
                int month = (event.getDate() % 10000) / 100;
                // Entonces puede setear el mes y busca el valor de ese mes y si no lo encuentra, entonces lo define con un 0L, que 0 es la longitud del valor y L el identificador.
                statistics.put(month, statistics.getOrDefault(month, 0L) + 1); // Se realiza un +1 que indica que se ha encontrado un evento adicional.
            }
        }
        // Retorna las stadísticas obtenidas.
        return statistics;
    }

    public static Map<Integer, Long> generateHourlyStatistics(List<Event> events, int year) {
        // Se crea una variable Map local.
        Map<Integer, Long> statistics = new HashMap<>();
        // Busca en todos los eventos de la clase eventos.
        for (Event event : events) {
            // Se divide entre 10000 para obtener el año exacto debido a que los datos están de esta forma: 19600130
            int eventYear = event.getDate() / 10000;
            // Se crea la condición en donde si el año del evento es igual al año, entonces procede.
            if (eventYear == year) {
                // Se obtiene la hora dividiendo el valor del tiempo con 10000 debido a que el formate está en hhmmss
                int hour = event.getTime() / 10000;
                statistics.put(hour, statistics.getOrDefault(hour, 0L) + 1); // Se realiza un +1 que indica que se ha encontrado un evento adicional.
            }
        }
        // Retorna las stadísticas obtenidas.
        return statistics;
    }
}
