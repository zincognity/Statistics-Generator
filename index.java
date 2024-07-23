import java.util.Scanner;

import src.auth.Auth;
import src.utils.Menu;

/**
 * index
 */
public class index {
    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        Auth auth = new Auth();
        if(auth.authenticate()){
            String title = "Principal";
            String[][] menu = {{ "YearRange", "Número de eventos sísmicos por año dado un rango de años."},{"Monthly", "Número de eventos sísmicos por mes dado un año."},{"MagnitudeRangeByYear","Número de eventos sísmicos por mes dados un rango de magnitudes y un año."},{"Hour", "Número de eventos sísmicos por cada hora dado un año."}};
            Menu.showMenu(title, menu, in);
        } else {
            System.out.println("Authentication failed. Exiting program.");
        }
		
    }
}