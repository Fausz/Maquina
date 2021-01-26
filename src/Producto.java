import javax.naming.ldap.PagedResultsControl;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Scanner;

public class Producto {

    private Tipo nombre;
    private double precio;
    private static int cantidadTotal=0;

    private static double precioActualSolo=0.5;
    private static double precioActualConLeche=0.8;
    private static double precioActualTe=0.5;

    private static int cantidadSolo=10;
    private static int cantidadConLeche=10;
    private static int cantidadTe=10;

    private static int cantidadSoloVendida=0;
    private static int cantidadConLecheVendida=0;
    private static int cantidadTeVendida=0;

    private static String vacio = "Stock terminado, por favor, elija otro producto.";
    private static String stockVacio = "Stock completamente terminado, espere a que se reposte la máquina.";
    private static String productoErroneo="El numero del producto está fuera del rango, vuelve a introducirlo.";
    private static String caracterErroneo="El valor introducido no es un caracter valido, vuelva a introducirlo.";
    private static String opcionIncorrecta="Has introducido una opción no correspondiente al menú, inserta otra.";

    static Scanner sc=new Scanner(System.in);

    public Producto(Tipo t){
        nombre=t;
        cantidadTotal++;
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
        if(t == Tipo.valueOf(Tipo.class, "SOLO")){
            precio=precioActualSolo;
            Producto.cantidadSolo--;
            Producto.cantidadSoloVendida++;
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
        if(Producto.getCantidadSolo() <=0 && Producto.getCantidadConLeche() <=0 && Producto.getCantidadTe() <=0 ) {
            System.out.println(stockVacio);
            return false;
        }else {
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
        int numProducto=-1;
        double cantidad=0;
        boolean salir=false,numProductoValido,cantidadValida;
        boolean repetir=true;
        do {
            numProductoValido=true;
            cantidadValida=true;
            Producto.mostrarProductos();
            repetir=true;

            try {
                numProducto = sc.nextInt();
            } catch (Exception e) {
                System.out.println(caracterErroneo);
                numProductoValido = false;
                sc.nextLine();
            }
            if(numProducto==0){
                salir=true;
                numProductoValido=false;
            }
            if(!comprobarNumProducto(numProducto)){
                numProductoValido=false;
            }
            /*if(numProducto<0 || numProducto>3){
                System.out.println("Numero fuera del rango del menú, vuelve a introducirlo.");
                numProductoValido=false;
            }*/

            //Tras elegir el producto, introducimos su nuvo precio
            if(numProductoValido) {
                System.out.println("Introduce el nuevo precio: (en decimal - x,xx)");
                try {
                    cantidad = sc.nextDouble();
                } catch (Exception e) {

                    System.out.println(caracterErroneo);
                    cantidadValida = false;
                    sc.nextLine();
                }

                /*if(cantidad<0){
                    //System.out.println("No se puede introducir un precio inferior a 0, vuelve a introducir un precio.");
                    cantidadValida = false;
                }*/
            }
            if(numProductoValido && cantidadValida) {
                //if (repetir) {
                    if (/*comprobarNumProducto(numProducto) && */comprobarNuevoPrecio(cantidad)) {
                        //COMPROBAR DESDE AQUI-----------------------------------------------------------------------
                        if (Producto.cambiarPrecio(numProducto, cantidad)) {

                            salir = true;
                            numProductoValido = false;
                            cantidadValida = false;
                        } else {
                            System.out.println("Se ha cancelado la modificacion del precio del producto.");
                            salir = true;
                            numProductoValido = false;
                            cantidadValida = false;
                        }

                    } else {
                        //System.out.println(productoErroneo);
                        salir = false;
                        repetir = false;
                    }
                }
            //}
        }while (!salir);
    }

    public static boolean cambiarPrecio(int op, double cant){
        boolean modif=false;
        double precioAntiguo;
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(cant));
        switch (op){
            case 0:
                modif= false;
                break;
            case 1:
                precioAntiguo=precioActualSolo;
                precioActualSolo=cant;
                System.out.println("El precio ha sido modificado de: "+precioAntiguo+" a: "+df.format(cant)+"€.");
                modif= true;
                break;
            case 2:
                precioAntiguo=precioActualSolo;
                precioActualConLeche=cant;
                System.out.println("El precio ha sido modificado de: "+precioAntiguo+" a: "+df.format(cant)+"€.");
                modif= true;
                break;
            case 3:
                precioAntiguo=precioActualSolo;
                precioActualTe=cant;
                System.out.println("El precio ha sido modificado de: "+precioAntiguo+"€ a: "+df.format(cant)+"€.");
                modif= true;
                break;
            default:
                //System.out.println("No se puede cambiar el precio por un numero negativo.");
                modif=false;
        }

        return modif;
    }

    private static boolean comprobarNumProducto(int numProducto){
        if(numProducto>=0 && numProducto<=3){
            return true;
        }else{
            System.out.println("El numero del producto está fuera del rango, vuelve a introducirlo.");
            return false;
        }
    }

    private static boolean comprobarNuevoPrecio(double cantidad){
        if(cantidad<=0){
            System.out.println("No se puede asignar una cantidad igual o inferior a 0.");
            return false;
        }

        //Para comprobar los decimales para que nos lleguen siempre 2 del precio debemos:
        String precio=String.valueOf(cantidad);


        
        /*
        //Creamos String para almacenar los decimales del precio
        String str;
        //Crear el objeto DecimalFormatSymbols
        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
        separadoresPersonalizados.setDecimalSeparator('.');

        //Aplicamos el objeto DecimalFormat al objeto DecimalFormatSymbols para obtener el numero de decimales
        DecimalFormat formato1 = new DecimalFormat("#.00", separadoresPersonalizados);
        //Al String de cantidad le asignamos la cantidad de decimales
        str=formato1.format(cantidad); // Resultado => 3.33

        System.out.println(str);
*/
            //Obtenemos en un int los decimales obtenidos del String
            int decNumberInt = Integer.parseInt(precio.substring(precio.indexOf('.') + 1));
            String totalDec= String.valueOf(decNumberInt);

        if(totalDec.length()>2 ){
            System.out.println("Se tienen que introducir un maximo de 2 digitos decimales.");
            return false;
        }else {
            //Si la ultima cifra de los decimales termina en 1,2,3 o 4 no será valido
            if ((decNumberInt % 10) >= 1 && decNumberInt % 10 <= 4) {
                System.out.println("No esta maquina no admite las monedas de 1 y 2 cent, ingresa precios acabados en 0 o 5.");
                return false;
            } else {
                return true;
            }
        }
    }

    private static boolean comprobarCantidadCafeSolo(){
        if (Producto.getCantidadSolo() <= 0) {
            System.out.println(vacio);
            return false;
        }else{
            return true;
        }
    }

    private static boolean comprobarCantidadCafeConLeche(){
        if (Producto.getCantidadConLeche() <= 0) {
            System.out.println(vacio);
            return false;
        }else{
            return true;
        }
    }

    private static boolean comprobarCantidadTe(){
        if (Producto.getCantidadTe() <= 0) {
            System.out.println(vacio);
            return false;
        }else{
            return true;
        }
    }

    public static void mostrarProductos(){
        System.out.println("\n--------Productos--------");
        System.out.println("1 ---- Cafe Solo ("+Producto.getPrecioActualSolo()+") €");
        System.out.println("2 ---- Cafe con Leche ("+Producto.getPrecioActualConLeche()+") €");
        System.out.println("3 ---- Te ("+Producto.getPrecioActualTe()+") €");
        System.out.println("0 ---- Salir\n");
    }

}
