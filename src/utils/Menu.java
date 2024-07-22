package src.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

import src.auth.Auth;

public class Menu {
    // Se crea la función showMenu que será un handlers para abrir menus.
    public static void showMenu(String title, String[][] options, Scanner in){
        // Se crea la variable option, que identificará la opción escogida, y las variables isEnd e isCorrect que permitirán si el ciclo continúo o si la opción escogida es correcta.
        int option = 0;
        boolean isEnd = false, isCorrect;
        // Se hace un bucle infinito para mostrar el menú con la condición de que la opción escogida, no sea la de salir.
        do {
            // Colocamos la variable isCorrect dentro del bucle para inicializarlo con true.
            isCorrect = true;
            // Mensaje de bienvenida al usuario identificado.
            if(Auth.auth != null) System.out.println("Bienvenid@ " + Auth.auth);
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
                // Buscamos en todas las opciones la opción escogida con la ayuda de un for.
                for (int i = 0; i <= options.length; i++) {
                    // Si la opción es la última de todas, o el tamaño del options[] es porque ha escogido Salir.
                    if(option == options.length + 1){
                        isEnd = true;
                        // Si es el menú principal, es porque ha dejado la autenticación, por lo tanto, el auth se vuelve null.
                        if(title.equals("Principal")) Auth.auth = null;
                    }
                    // Si no, la opción escogida se puede mostrar en pantalla.
                    if(option == i){
                        System.out.println("Apartado de: " + options[i-1][1]);
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

    public static void ModuleOptions(String title, Scanner in){
        String[][] menu = {{"Print", "Imprimir por pantalla."}, {"Export", "Exportar a archivo plano."}};
        showMenu(title, menu, in);
    }

    private static void Option(String title, String option, Scanner in){
        switch (option) {
            case "Principal-1":
                ModuleOptions(title, in);
                break;
            case "Principal-2":
                ModuleOptions(title, in);
                break;
            case "Principal-3":
                ModuleOptions(title, in);
                break;
            case "Principal-4":
                ModuleOptions(title, in);
                break;

        
            default:
                break;
        }
    }
}
