package src.auth;

import src.models.User;
import src.utils.Logger;
import src.utils.Menu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Auth {
    // Ubitación del archivo usuarios.txt.
    private static final String filePath = "data/usuarios.txt";

    public Auth(){
        // Al momento que se ejecuta la clase auth, se ejecuta la función loadUsers.
        loadUsers();
    }
    // Se crea la función loadUsers para registrar los datos que se encuentran en el usuarios.txt.
    private void loadUsers(){
        User.users = new HashMap<>();
        // Se hace un try para identificar cualquier error.
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){ // El BufferedReader es como un lector de inputs.
            // El input en este caso sería el contenido del archivo, y lo leerá línea por línea.
            String line;
            // Se hace un bucle hasta que la siguiente línea sea null.
            while ((line = br.readLine()) != null) {
                // Se registran los datos en la variable data y los separa en 2: username, password.
                String[] data = line.split(",");
                // Se llama a la clase users para registrar los datos en el hashMap.
                User.users.put(data[0], data[1]);
            }
        } catch (IOException e) {
            // Se manda un mensaje referente al archivo.
            Logger.log("ERROR", "Failed to lead users" + e.getMessage());
        }
    }

    @SuppressWarnings("resource")
    public boolean authenticate(){
        Menu.Clear();
        // Se crea la instancia del Scanner.
        Scanner in = new Scanner(System.in);
        // Formulario de inicio sesión.
        System.out.println("Username: ");
        String username = in.nextLine();
        System.out.println("Password: ");
        String password = in.nextLine();
        // Si alguno de los usuarios registrados coincide con el usuario ingresado.
        // Es decir, busca la key gracias al hashMap que tiene este modelo:
        /*
         * {
         *  [key]: [value]
         * }
        */
        if(User.users.containsKey(username) && User.users.get(username).equals(password)){
            Menu.Clear();
         // Se identifica la key (el usuario). Y si la key coincide con el password introducido, entonces retorna true.
            System.out.println("Login successful!");
            return true;
        } else {
            Menu.Clear();
            // Mensaje de no haber encontrado el usuario o la contraseña es incorrecta.
            System.out.println("Invalid credentials.");
            Logger.log("ERROR AUTH", "Failed login for invalid credentials.");
        }
        in.close();
        // Si no ingresa en la condición, retorna false.
        return false;
    }
}
