package src.utils;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import src.models.Event;
import src.processor.Data;
import src.processor.ReportExporter;
import src.processor.Stadistics;

public class Menu {
    private static final String file_Path = "data/data.csv";
    private static Data data = new Data();
    private static List<Event> events = data.loadData(file_Path);
    private static String titleHead;

    // Se crea la función showMenu que será un handlers para abrir menus.
    public static void showMenu(String title, String[][] options, Scanner in){
        // Se crea la variable option, que identificará la opción escogida, y las variables isEnd e isCorrect que permitirán si el ciclo continúo o si la opción escogida es correcta.
        int option = 0;
        boolean isEnd = false, isCorrect;
        // Se hace un bucle infinito para mostrar el menú con la condición de que la opción escogida, no sea la de salir.
        do {
            // Colocamos la variable isCorrect dentro del bucle para inicializarlo con true.
            isCorrect = true;
            // Realizamos un for para imprimir en la pantalla todas las opciones dependiendo el menu[], que es la lista de opciones.
            for (int i = 0; i <= options.length; i++) {
                // Si la variable i es diferente a options.length es porque estas opciones existen.
                if(i != options.length) System.out.printf("%d. %s\n", i + 1, options[i][1]);
                // Si la variable i es igual al tamaño de las opciones, es porque la opción no se ha definido, por lo tanto la toma como la opción de Salir del programa.
                if(i == options.length) System.out.printf("%d. %s\n", i + 1, "Regresar");
            }
            // Se hace un try para evitar errores de tipeo por parte del usuario.
            try {
                // Se ingresa la opción a escoger.
                System.out.println("Selecciona una opción:");
                option = in.nextInt();
                in.nextLine();
                Clear();
                // Buscamos en todas las opciones la opción escogida con la ayuda de un for.
                for (int i = 0; i <= options.length; i++) {
                    // Si la opción es la última de todas, o el tamaño del options[] es porque ha escogido Salir.
                    if(option == options.length + 1){
                        isEnd = true;
                    }
                    // Si no, la opción escogida se puede mostrar en pantalla.
                    if(option == i){
                        System.out.println("Apartado de: " + options[i-1][1]);
                        titleHead = options[i-1][1];
                        Option(options[i-1][1] ,options[i-1][0], in);
                    }
                    // De lo contrario es porque ha escogido una opción que no está dentro de las opciones.
                    if (option > options.length + 1 || option < 0){
                        isCorrect = false;
                    }
                }
            } catch (InputMismatchException e) { // Identificamos el valor de la entrada del scanner, y definimos el error.
                // Muestra el mensaje de error y lo logea en el Logger.
                System.out.println("Invalid entry. Please enter an integer.");
                Logger.log("ERROR INPUT", "Invalid entry for options.");
                // Permite continuar y volver a mostrar el menú.
                continue;
            }
            // Si isEnd es true, muestra en pantalla (Leaving...).
            if(isEnd) System.out.println("Leaving...");
            // Si isCorrect es false, muestra que tiene que ingresar una opción válida.
            if(!isCorrect) System.out.println("Invalid option, please enter a menu option.");
        } while (!isEnd);
    }

    // Se crea la función Clear para limpiar la pantalla.
    public static void Clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    // Se crea la función ModuleOptions que abrirá los apartados prederteminados para cada opción (Imprimir, Exportar).
    public static void ModuleOptions(String title, Scanner in){
        Menu.Clear();
        if(endYear != 0) System.out.println(title + ": " + year + " - " + endYear);
        if(endYear == 0) System.out.println(title + ": " + year);
        String[][] menu = {{"Print", "Imprimir por pantalla."}, {"Export", "Exportar a archivo plano."}};
        showMenu(title, menu, in);
    }

    // Se crea la función Option que ejecutará la función según la opción escogida.
    private static void Option(String title, String option, Scanner in){
        // Se realiza un switch para identificar la opción.
        switch (option) {
            case "YearRange":
                showYearRange(title, in); // Llama a la función showYearRange.
                break;
            case "Monthly":
                showMonthByYear(title, in); // Llama a la función showMonthByYear.
                break;
            case "MagnitudeRangeByYear":
                showMagnitudeRange(title, in); // Llama a la función showMagnitudeRange.
                break;
            case "Hour":
                showHourByYear(title, in); // Llama a la función showHourByYear.
                break;
            case "Print":
                printStadistics(stadistics); // Llama a la función printStadistics.
                break;
            case "Export":
                exportStadistics(stadistics);; // Llama a la función printStadistics.
                break;
        }
    }

    // Se crear las variables stadistics y type para almacenar datos momentáneos.
    private static Map<Integer, Long> stadistics;
    private static String type;
    private static int year;
    private static int endYear;
    private static double minMagnitude;
    private static double maxMagnitude;

    // Se crea la función showYearRange que abrirá un formulario y ejecutará la función generateYearlyStadistics.
    private static void showYearRange(String title, Scanner in){
        System.out.println("Ingrese el año de inicio:");
        year = in.nextInt();
        System.out.println("Ingrese el año final:");
        endYear = in.nextInt();
        in.nextLine();
        // Se ejecuta la función de la clase Stadistics.
        stadistics = Stadistics.generateYearlyStadistics(events, year, endYear);
        // Se setea el tipo de dato.
        type = "Year";
        // Abre el menú predeterminado de cada módulo.
        ModuleOptions(title, in);
    }

    // Se crea la función showMonthByYear que abrirá un formulario y ejecutará la función generateMonthlyStatistics.
    private static void showMonthByYear(String title, Scanner in){
        endYear = 0;
        System.out.println("Ingrese el año: ");
        year = in.nextInt();
        in.nextLine();
        // Se ejecuta la función de la clase Stadistics.
        stadistics = Stadistics.generateMonthlyStatistics(events, year);
        // Se setea el tipo de dato.
        type = "Monthly";
        // Abre el menú predeterminado de cada módulo.
        ModuleOptions(title, in);
    }

    // Se crea la función showMagnitudeRange que abrirá un formulario y ejecutará la función generateMagnitudeStatistics.
    private static void showMagnitudeRange(String title, Scanner in){
        endYear = 0;
        System.out.println("Ingrese el año: ");
        year = in.nextInt();
        System.out.println("Ingrese la magnitud mínima: ");
        minMagnitude = in.nextDouble();
        System.out.println("Ingrese la magnitud máxima: ");
        maxMagnitude = in.nextDouble();
        // Se ejecuta la función de la clase Stadistics.
        stadistics = Stadistics.generateMagnitudeStatistics(events, minMagnitude, maxMagnitude, year);
        // Se setea el tipo de dato.
        type = "Monthly-Magnitude";
        // Abre el menú predeterminado de cada módulo.
        ModuleOptions(title, in);
    }

    // Se crea la función showHourByYear que abrirá un formulario y ejecutará la función generateHourlyStatistics.
    private static void showHourByYear(String title, Scanner in){
        endYear = 0;
        System.out.println("Ingrese el año: ");
        year = in.nextInt();
        // Se ejecuta la función de la clase Stadistics.
        stadistics = Stadistics.generateHourlyStatistics(events, year);
        // Se setea el tipo de dato.
        type = "Hour";
        // Abre el menú predeterminado de cada módulo.
        ModuleOptions(title, in);
    }

    // Se crea la función printStadistics que permitirá imprimir en pantalla las estadísticas según la opción escogida.
    private static void printStadistics(Map<Integer, Long> statistics) {
        // Se usa un switch para identificar el tipo, en este casó sería el formato.
        switch (type) {
            case "Year":
                System.out.println("Año\tEventos\tPorcentaje");
                break;
            case "Monthly", "Monthly-Magnitude":
                System.out.println("Nº\tMes\tEventos\tPorcentaje");
                break;
            case "Hour":
                System.out.println("Hora\tEventos\tPorcentaje");
                break;
        }
        long sum = 0;
        double porcentT = 0;
        DecimalFormat format = new DecimalFormat("#.00");
        // Se realiza un for para setear la suma de todos los valores o eventos.
        for (Map.Entry<Integer, Long> entry : statistics.entrySet()) {
            sum = sum + entry.getValue();
        }
        // Se realizar un for para imprimir los datos da las estadísticas seteadas.
        for (Map.Entry<Integer, Long> entry : statistics.entrySet()) {
            double porcent = entry.getValue().doubleValue() / sum * 100;
            porcentT = porcentT + porcent;
            if(type.equals("Monthly") || type.equals("Monthly-Magnitude")){
                System.out.println(entry.getKey() + "\t" + Months.getMonth(entry.getKey()) + "\t" + entry.getValue() + "\t" + format.format(porcent) + "%");
            } else {
                System.out.println(entry.getKey() + "\t" + entry.getValue() + "\t" + format.format(porcent) + "%");
            }
            
        }
        System.out.println("TOTAL\t\t" + sum + "\t" + format.format(porcentT) + "%");
    }

    // Se crea la función exportStadistics que permitirá imprimir los datos obtenido en un archivo a crear o editar.
    private static void exportStadistics(Map<Integer, Long> statistics){
        // Se crea un StringBuilder para añadirle los valores requeridos según el tipo de función.
        StringBuilder reportContent = new StringBuilder();
        // Se crea una variable local fileName que definirá el nombre del archivo a crear o modificar.
        String fileName = "";
        switch (type) {
            case "Year":
                reportContent.append(titleHead+"\n");
                reportContent.append("Rango de años: ").append(year).append(" - ").append(endYear).append("\n");
                reportContent.append("Año\tEventos\tPorcentaje\n");
                fileName = "reporte_anual_";
                break;
            case "Monthly":
                reportContent.append(titleHead+"\n");
                reportContent.append("Año: ").append(year).append("\n");
                reportContent.append("Nº\tMes\tEventos\tPorcentaje\n");
                fileName = "reporte_mensual_";
                break;
            case "Monthly-Magnitude":
                reportContent.append(titleHead+"\n");
                reportContent.append("Año: ").append(year).append("\n");
                reportContent.append("Rango de magnitudes: ").append(minMagnitude).append(" - ").append(maxMagnitude).append("\n");
                reportContent.append("Nº\tMes\tEventos\tPorcentaje\n");
                fileName = "reporte_magnitudes_";
                break;
            case "Hour":
                reportContent.append(titleHead+"\n");
                reportContent.append("Año: ").append(year).append("\n");
                reportContent.append("Hora\tEventos\tPorcentaje\n");
                fileName = "reporte_horario_";
                break;
        }
        long sum = 0;
        double porcentT = 0;
        DecimalFormat format = new DecimalFormat("#.00");
        // Se realizar un for para imprimir los datos da las estadísticas seteadas.
        for (Map.Entry<Integer, Long> entry : statistics.entrySet()) {
            sum = sum + entry.getValue();
        }

        for (Map.Entry<Integer, Long> entry : statistics.entrySet()) {
            double porcent = entry.getValue().doubleValue() / sum * 100;
            porcentT = porcentT + porcent;
            if(!type.equals("Monthly") && !type.equals("Monthly-Magnitude")) reportContent.append(entry.getKey()).append("\t").append(entry.getValue()).append("\t").append(format.format(porcentT)).append("%").append("\n");
            if(type.equals("Monthly") || type.equals("Monthly-Magnitude")) reportContent.append(entry.getKey()).append("\t").append(Months.getMonth(entry.getKey())).append("\t").append(entry.getValue()).append("\t").append(format.format(porcent)).append("%").append("\n");
        }
        reportContent.append("TOTAL\t\t").append(sum).append("\t").append(format.format(porcentT)).append("%");
        // Se condiciona si el endYear es igual a 0, es porque no entró en una opción que requiera el endYear.
        if(endYear != 0) ReportExporter.exportReport(reportContent.toString(), fileName + year + "_" + endYear + ".txt");
        // Se llama a la función exportReport que creará el archivo con su contenido.
        if(endYear == 0) ReportExporter.exportReport(reportContent.toString(), fileName + year + ".txt");
    }
}
