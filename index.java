import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * index
 */
public class index {
    public static void main(String [] args) {
        String file = "./input/Data.csv";
        String elemento = null;
        boolean foundFile = true;
        Scanner in = null;

        try {
            in = new Scanner(new File(file));
        }
        catch (FileNotFoundException fnE) {
            System.out.println(fnE.getMessage());
            foundFile = false;
        }
        if (foundFile) {
            in.nextLine();
            while (in.hasNext()) {
                elemento=in.next();
                System.out.println(elemento);
            }
        }
    }
}