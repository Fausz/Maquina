public class Main {
    public static void main(String[] args) {
        boolean exit=false,comprobar;
        Maquina m = new Maquina();

        /**
         * Creamos fuera el objeto Maquina par poder trabajar constantemente con él sin resetear sus valores.
         */
        do {
            //Obtenemos la respuesta de si salir.
            comprobar=m.menu();

            //Si la repsuesta es si, se preapara para salir, sino se repite el bucle.
            if(comprobar) {
                exit=true;
            }
        }while(!exit);
    }
}
