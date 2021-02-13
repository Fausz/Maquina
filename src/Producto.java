import javax.naming.ldap.PagedResultsControl;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Scanner;

public class Producto {

    //Variable que contendrá el tipo del producto.
    private Tipo nombre;

    //Variable que contendrá el precio del producto.
    private double precio;

    //Variable que contendrá la cantidad total de todos los productos.
    private static int cantidadTotal=0;

    //Variables que contendrán los precios actuales de los productos.
    private static double precioActualSolo=0.7;
    private static double precioActualConLeche=0.8;
    private static double precioActualTe=0.55;

    //Variables que contendrán las cantidades de stock de los productos.
    private static int cantidadSolo=10;
    private static int cantidadConLeche=10;
    private static int cantidadTe=10;

    //Variables que contendrán las cantidades vendidas de los productos.
    private static int cantidadSoloVendida=0;
    private static int cantidadConLecheVendida=0;
    private static int cantidadTeVendida=0;

    //Variables que contendrán los mensajes de error.
    private static String vacio = "Stock terminado, por favor, elija otro producto.";
    private static String stockVacio = "Stock completamente terminado, espere a que se reposte la máquina.";
    private static String productoErroneo="El numero del producto está fuera del rango, vuelve a introducirlo.";
    private static String caracterErroneo="El valor introducido no es un caracter valido, vuelva a introducirlo.";
    private static String opcionIncorrecta="Has introducido una opción no correspondiente al menú, inserta otra.";
    private static String monedaNoValida="Has introducido un precio no admitido por la máquina, inserta otro precio que termine en 0 o 5.";

    static Scanner sc=new Scanner(System.in);


    public Producto(Tipo t){
        /**
         * Constructor del objeto producto.
         */

        //Asignamos el nombre del producto.
        nombre=t;
        //Sumamos el contador de cantidad total de productos al crear uno.
        cantidadTotal++;
        //Dependiendo del producto que se cree se procederán los calculos.
        creacionProducto(nombre);
    }

    public Tipo getNombre() {
        return nombre;
    }

    public void setNombre(Tipo nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public static int getCantidadTotal() {
        return cantidadTotal;
    }

    public static void setCantidadTotal(int cantidadTotal) {
        Producto.cantidadTotal = cantidadTotal;
    }

    public static int getCantidadSolo() {
        return cantidadSolo;
    }

    public static void setCantidadSolo(int cantidadSolo) {
        Producto.cantidadSolo = cantidadSolo;
    }

    public static int getCantidadConLeche() {
        return cantidadConLeche;
    }

    public static void setCantidadConLeche(int cantidadConLeche) {
        Producto.cantidadConLeche = cantidadConLeche;
    }

    public static int getCantidadTe() {
        return cantidadTe;
    }

    public static void setCantidadTe(int cantidadTe) {
        Producto.cantidadTe = cantidadTe;
    }

    public static double getPrecioActualSolo() {
        return precioActualSolo;
    }


    public static void setPrecioActualSolo(double precioActualSolo) {
        Producto.precioActualSolo = precioActualSolo;
    }

    public static double getPrecioActualConLeche() {
        return precioActualConLeche;
    }

    public static void setPrecioActualConLeche(double precioActualConLeche) {
        Producto.precioActualConLeche = precioActualConLeche;
    }

    public static double getPrecioActualTe() {
        return precioActualTe;
    }

    public static void setPrecioActualTe(double precioActualTe) {
        Producto.precioActualTe = precioActualTe;
    }

    public static int getCantidadSoloVendida() {
        return cantidadSoloVendida;
    }

    public static void setCantidadSoloVendida(int cantidadSoloVendida) {
        Producto.cantidadSoloVendida = cantidadSoloVendida;
    }

    public static int getCantidadConLecheVendida() {
        return cantidadConLecheVendida;
    }

    public static void setCantidadConLecheVendida(int cantidadConLecheVendida) {
        Producto.cantidadConLecheVendida = cantidadConLecheVendida;
    }

    public static int getCantidadTeVendida() {
        return cantidadTeVendida;
    }

    public static void setCantidadTeVendida(int cantidadTeVendida) {
        Producto.cantidadTeVendida = cantidadTeVendida;
    }


    private void creacionProducto(Tipo t){
        /**
         * Método que crea el producto dependiendo de cual se haya seleccionado.
         */

        //Si el producto es del tipo seleccionado...
        if(t == Tipo.valueOf(Tipo.class, "SOLO")){
            //Se le asigna el precio actual.
            precio=precioActualSolo;

            //Se resta una cantidad del stock del producto.
            Producto.cantidadSolo--;

            //Se suma una cantidad al stock vendido del producto.
            Producto.cantidadSoloVendida++;

            //Se procede a la devolución del cambio del producto.
            Moneda.restarCajetin(precioActualSolo);

        }else if(t == Tipo.valueOf(Tipo.class, "CONLECHE")){
            precio=precioActualConLeche;
            Producto.cantidadConLeche--;
            Producto.cantidadConLecheVendida++;
            Moneda.restarCajetin(precioActualConLeche);
        }else if(t == Tipo.valueOf(Tipo.class, "TE")){
            precio=precioActualTe;
            Producto.cantidadTe--;
            Producto.cantidadTeVendida++;
            Moneda.restarCajetin(precioActualTe);
        }
    }


    public static boolean comprobarCantidad(Tipo t) {
        /**
         * Método que comprueba que haya cantidad en stock del producto seleccionado.
         */

        //Si no hay stock de ningún producto se muestra un mensaje de error y se devuelve como negativo.
        if(Producto.getCantidadSolo() <=0 && Producto.getCantidadConLeche() <=0 && Producto.getCantidadTe() <=0 ) {
            System.out.println(stockVacio);
            return false;
        }else {
            //Si hay stock de algun producto se comprueba si hay stock del producto del producto concreto.
            boolean opcion = true;
            switch (t) {
                case SOLO:
                    opcion = comprobarCantidadCafeSolo();
                    break;
                case CONLECHE:
                    opcion = comprobarCantidadCafeConLeche();
                    break;
                case TE:
                    opcion = comprobarCantidadTe();
                    break;
            }
            return opcion;
        }
    }

    public static void modificarPrecioProductos(){
        /**
         * Método que modifica el precio del producto.
         */
        int numProducto=-1;
        int cantidad=0;
        boolean salir=false,numProductoValido,cantidadValida;
        boolean repetir=true;
        do {
            numProductoValido=true;
            cantidadValida=true;

            //Se muestran los productos.
            Producto.mostrarProductos();
            repetir=true;

            try {
                //Se introduce el numero de producto.
                numProducto = sc.nextInt();
            } catch (Exception e) {
                //Si se introduce un caracter erroneo se prepara para repetir el bucle.
                System.out.println(caracterErroneo);
                numProductoValido = false;
                sc.nextLine();
            }
            if(numProducto==0){
                //Si se selecciona 0 sale del método.
                salir=true;
                numProductoValido=false;
            }
            if(!comprobarNumProducto(numProducto)){
                //Comprueba que sea un número dentro del rango de productos.
                numProductoValido=false;
            }


            //Tras elegir el producto, introducimos su nuevo precio.
            if(numProductoValido) {
                System.out.println("Introduce el nuevo precio (en centimos):");
                try {
                    //Introducimos el nuevo precio en centimos (numero entero)
                    cantidad = sc.nextInt();
                } catch (Exception e) {
                    //Si se introduce un caracter erroneo se prepara para repetir el bucle.
                    System.out.println(caracterErroneo);
                    cantidadValida = false;
                    sc.nextLine();
                }
            }

            //Si se cumplen las condiciones...
            if(numProductoValido && cantidadValida) {

                //Si el precio no es inferior o igual a 0 entrará
                if (comprobarNuevoPrecio(cantidad)) {

                    //Se castea el nuevo precio en double.
                    double cantDouble=(double)cantidad;

                    //Se pasa el precio a numero con decimales
                    double cant=cantDouble/100;

                    //Si el producto se ha modificado saldrá del bucle.
                    if (Producto.cambiarPrecio(numProducto, cant)) {
                        salir = true;
                        numProductoValido = false;
                        cantidadValida = false;
                    } else {
                        //Si no se ha modificado el precio saldrá.
                        System.out.println("Se ha cancelado la modificacion del precio del producto.");
                        salir = true;
                        numProductoValido = false;
                        cantidadValida = false;
                    }

                } else {
                    //Si es inferior o igual a 0 se repetirá el bucle.
                    salir = false;
                    repetir = false;
                }
            }

        }while (!salir);
    }

    public static boolean cambiarPrecio(int op, double cant){
        /**
         * Método que cambia el precio del producto seleccionado.
         */

        boolean modif=false;
        double precioAntiguo;

        //Se aplicará este formato para mostrar los precios.
        DecimalFormat df = new DecimalFormat("0.00");

        switch (op){
            case 0:
                //Si llega 0 será para salir.
                modif= false;
                break;
            case 1:
                //En caso de seleccion del producto con este número..

                //Se guarda el precio actual que será para mostrar el precio que tenía antes de la modificación.
                precioAntiguo=precioActualSolo;

                //Se asigna el nuevo precio del producto como precio actual.
                precioActualSolo=cant;

                //Se muestra el precio antiguo y nuevo.
                System.out.println("El precio ha sido modificado de: "+df.format(precioAntiguo)+"€ a: "+df.format(cant)+"€.");
                modif= true;
                break;
            case 2:
                precioAntiguo=precioActualConLeche;
                precioActualConLeche=cant;
                System.out.println("El precio ha sido modificado de: "+df.format(precioAntiguo)+"€ a: "+df.format(cant)+"€.");
                modif= true;
                break;
            case 3:
                precioAntiguo=precioActualTe;
                precioActualTe=cant;
                System.out.println("El precio ha sido modificado de: "+df.format(precioAntiguo)+"€ a: "+df.format(cant)+"€.");
                modif= true;
                break;
            default:
                //System.out.println("No se puede cambiar el precio por un numero negativo.");
                modif=false;
        }

        return modif;
    }

    private static boolean comprobarNumProducto(int numProducto){
        /**
         * Método que comprueba que el rango del producto elegido a modificar sea correcto.
         */

        //Si el número es entre el rango 0 y 3 será correcto.
        if(numProducto>=0 && numProducto<=3){
            return true;
        }else{
            //Si no está dentro del rango muestra mensaje de error.
            System.out.println("El numero del producto está fuera del rango, vuelve a introducirlo.");
            return false;
        }
    }

    private static boolean comprobarCantidadCafeSolo(){
        /**
         * Método que comprueba que haya cantidad de stock del producto: café solo.
         */

        //Si no hay stock mostrará mensaje de error.
        if (Producto.getCantidadSolo() <= 0) {
            System.out.println(vacio);
            return false;
        }else{
            return true;
        }
    }

    private static boolean comprobarCantidadCafeConLeche(){
        /**
         * Método que comprueba que haya cantidad de stock del producto: café con leche.
         */

        //Si no hay stock mostrará mensaje de error.
        if (Producto.getCantidadConLeche() <= 0) {
            System.out.println(vacio);
            return false;
        }else{
            return true;
        }
    }

    private static boolean comprobarCantidadTe(){
        /**
         * Método que comprueba que haya cantidad de stock del producto: té.
         */

        //Si no hay stock mostrará mensaje de error.
        if (Producto.getCantidadTe() <= 0) {
            System.out.println(vacio);
            return false;
        }else{
            return true;
        }
    }

    public static void mostrarProductos(){
        /**
         * Método que muestra los productos a la hora de modificar el precio.
         */
        System.out.println("\n--------Productos--------");
        System.out.println("1 ---- Cafe Solo ("+Producto.getPrecioActualSolo()+") €");
        System.out.println("2 ---- Cafe con Leche ("+Producto.getPrecioActualConLeche()+") €");
        System.out.println("3 ---- Te ("+Producto.getPrecioActualTe()+") €");
        System.out.println("0 ---- Salir\n");
    }
    private static boolean comprobarNuevoPrecio(int cantidad){
        /**
         * Método que comprueba que los nuevos precios antes de cambiarlos estén en rangos correctos.
         */

        //Si el precio es inferior o igual a 0 no será valido.
        if(cantidad<=0){
            System.out.println("No se puede asignar una cantidad igual o inferior a 0.");
            return false;
        }

        //Si se pone un precio desorbitado no será valido.
        if(cantidad>=300){
            System.out.println("Esto no es un Starbucks, baja el precio.");
            return false;
        }

        //Si el nuevo precio termina en 0 o en 5 será correcto.
        if(cantidad%10==5 || cantidad%10==0){
            return true;
        }else{
            //Si no termina en 5 o 0 sera inválido pues la máquina no admite monedas de 1 centimo o 2 y mostrará mensaje de error.
            System.out.println(monedaNoValida);
            return false;
        }
    }

}
