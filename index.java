import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import src.auth.Auth;
import src.utils.Menu;

/**
 * index
 */
public class index {
    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        Auth asd = new Auth();
        if(asd.authenticate()){
            String title = "Principal";
            String[][] menu = {{ title+"-1", "Número de eventos sísmicos por año dado un rango de años."},{title+"-2", "Número de eventos sísmicos por mes dado un año."},{title+"-3","Número de eventos sísmicos por mes dados un rango de magnitudes y un año."},{title+"-4", "Número de eventos sísmicos por cada hora dado un año."}};
            Menu.showMenu(title, menu, in);
        } else {
            System.out.println("Authentication failed. Exiting program.");
        }
		
    }
}