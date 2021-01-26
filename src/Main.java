import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean exit=false,comprobar;
        Maquina m = new Maquina();

        do {
            //Obtenemos la respuesta de si salir
            comprobar=m.menu();

            //Si la repsuesta es si, se preapara para salir, sino se repite el bucle
            if(comprobar) {
                exit=true;
            }
        }while(!exit);
    }
}
