package src.utils;

public class Months {
    public static String getMonth(int month){
        String value = "";
        switch (month) {
            case 1:
                value = "ENERO";
                break;
            case 2:
                value = "FEBRERO";
                break;
            case 3:
                value = "MARZO";
                break;
            case 4:
                value = "ABRIL";
                break;
            case 5:
                value = "MAYO";
                break;
            case 6:
                value = "JUNIO";
                break;
            case 7:
                value = "JULIO";
                break;
            case 8:
                value = "AGOSTO";
                break;
            case 9:
                value = "SETIEMBRE";
                break;
            case 10:
                value = "OCTUBRE";
                break;
            case 11:
                value = "NOVIEMBRE";
                break;
            case 12:
                value = "DICIEMBRE";
        }
        return value;
    }
}
