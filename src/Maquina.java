


import java.util.Scanner;

public class Maquina<iniciarOpcionMenuAdministracion> {

    Scanner sc = new Scanner(System.in);

    String caracterErroneo="El valor introducido no es un caracter valido, vuelva a introducirlo.";
    String opcionIncorrecta="Has introducido una opción no correspondiente al menú, inserta otra.";
    String insertaDinero="No has introducido el dinero suficiente para comprar este articulo, inserta dinero.";
    String noHayCambio="La maquina no contiene el cambio suficiente, intentelo más tarde.\n";
    //Producto [] productos = new Producto[30];

    public boolean menu(){
        boolean exit=false;
        int opMenu=-1;

        //Mostramos opciones del menú
        mostrarMenu();

        //Mostramos el dinero del cajetin

        System.out.println("Tu credito es de: "+String.format("%.2f", Moneda.getCajetin())+"€");

        //Asignamos la opcion del menú
        opMenu=opcionMenu();

        //Si es la opcion 6 se apaga la maquina
        if(opMenu==0){
            exit=true;
        }else{
            //Sino se ejecuta el menu de la maquina
            iniciarOpcionMenu(opMenu);
        }
        //Devolvemos al main si queremos salir o no
        return exit;
    }

    private int opcionMenu(){
        int opMenu=-1;
        boolean salirOpcionMenu,opMenuValida=true;

        do {
            salirOpcionMenu = true;
            try {

                //Se elige una opcion del menú
                opMenu = sc.nextInt();
            }catch (Exception e){
                //Si se introduce un caracter que no sea numerico entero saltará un error y se pedirá reintroducir opcion de nuevo
                opMenuValida=false;
                System.out.println(caracterErroneo);
                sc.nextLine();
            }

            if(opMenuValida) {
                //Se comprueba que la opcion seleccionada es correcta
                if (opcionMenuCorrecta(opMenu)) {
                    //Si es correcta se podrá salir de este bucle de seleccion
                    salirOpcionMenu = true;
                    opMenuValida=false;
                } else {
                    //Si la opcion está fuera del rango del menu se mostrara mensaje de error y se pedira reintroducir de nuevo
                    System.out.println(opcionIncorrecta);
                    mostrarMenu();
                    salirOpcionMenu = false;
                    opMenuValida=false;
                }
            }
        }while(!salirOpcionMenu);
        return opMenu;
    }

    private void iniciarOpcionMenu(int opMenu){
        //Metodo que hará la seleccion del menú
        boolean exit=false,comprobar;
        switch (opMenu){
            case 1:
                introducirMonedas();
                break;
            case 2:
                crearCafeSolo();
                break;
            case 3:
                crearCafeConLeche();
                break;
            case 4:
                crearTe();
                break;
            case 5:

                do {
                    //Obtenemos la respuesta de si salir
                    comprobar=menuAdministracion();

                    //Si la repsuesta es si, se preapara para salir, sino se repite el bucle
                    if(comprobar) {
                        exit=true;
                    }
                }while(!exit);
                break;

            case 6:
                devolucionMonedas();
                break;
        }
    }

    private void introducirMonedas(){
        //Metodo para introducir las monedas
        boolean salir,monedaAceptada;
        int moneda=-1;

        do{
            salir=false;
            monedaAceptada=true;
            //Muestro las monedas aceptadas
            Moneda.mostrarMonedas();
            System.out.println("Tu credito es de: "+String.format("%.2f", Moneda.getCajetin())+"€");

            try {
                moneda = sc.nextInt();
            }catch (Exception e){
                System.err.println();
                monedaAceptada=false;
            }

            if(monedaAceptada) {
                if (moneda == 0) {
                    salir = true;
                    monedaAceptada=true;
                } else {
                    //Comprobamos que el numero insertado este en el rango valido
                    if(Moneda.esMonedaValida(moneda)) {
                        //Si no salimos insertamos moneda al cajetin
                        Moneda.totalCajetin(moneda);
                        System.out.println("Tu credito es de: " + String.format("%.2f", Moneda.getCajetin()) + "€");

                    }else{
                        System.out.println("Has introducido una moneda falsa o una moneda que no es centimo, inserta otra moneda.");

                    }
                }
            }
        }while(!salir);
    }

    private void crearCafeSolo(){
        //Establecemos el tipo del objeto
        Tipo tipo=Tipo.SOLO;

        //1RO COMPRUEBA QUE HAY DINERO
        //2DO COMPRUEBA QUE HAY CAMBIO
        //3RO COMPRUEBA QUE HAY CANTIDAD DISPONIBLE
        //4RO SI SE CUMPLEN AMBAS CONDICIONES CREA EL OBJETO
        //5TO SE ASIGNA AL ARRAY

        if(Moneda.hayCambio(Producto.getPrecioActualSolo())){


            //PASO1
            if(Moneda.comprobarPrecio(tipo)) {

                //PASO2
                if (Producto.comprobarCantidad(tipo)) {

                    //PASO 3
                    Producto p = new Producto(tipo);

                    //PASO 4
                    //insertarProducto(p);
                    //System.out.println(productos[Producto.getCantidadTotal()-1].toString());
                }
            }else{
                System.out.println(insertaDinero);
            }
        }else{
            System.out.println(noHayCambio);
            Moneda.devolucionMonedas();
        }
    }


    private void crearCafeConLeche(){
        //Establecemos el tipo del objeto
        Tipo tipo = Tipo.CONLECHE;

        //1RO COMPRUEBA QUE HAY DINERO
        //2DO COMPRUEBA QUE HAY CAMBIO
        //3RO COMPRUEBA QUE HAY CANTIDAD DISPONIBLE
        //4RO SI SE CUMPLEN AMBAS CONDICIONES CREA EL OBJETO
        //5TO SE ASIGNA AL ARRAY


        //PASO1
        if(Moneda.comprobarPrecio(tipo)) {
            //PASO2
            if(Producto.comprobarCantidad(tipo)) {

                //PASO 3
                Producto p = new Producto(tipo);

                //PASO 4
                //insertarProducto(p);
                //System.out.println(productos[Producto.getCantidadTotal() - 1].toString());
            }
        }else{
            System.out.println(insertaDinero);
        }
    }

    private void crearTe(){
        //Establecemos el tipo del objeto
        Tipo tipo = Tipo.TE;

        //1RO COMPRUEBA QUE HAY DINERO
        //2DO COMPRUEBA QUE HAY CAMBIO
        //3RO COMPRUEBA QUE HAY CANTIDAD DISPONIBLE
        //4TO SI SE CUMPLEN AMBAS CONDICIONES CREA EL OBJETO
        //5TO SE ASIGNA AL ARRAY

        //PASO1
        if(Producto.comprobarCantidad(tipo)) {
            //PASO2
            if(Producto.comprobarCantidad(tipo)) {
                //PASO 3
                Producto p = new Producto(tipo);

                //PASO 4
                //insertarProducto(p);
                //System.out.println(productos[Producto.getCantidadTotal() - 1].toString());
            }
        }else{
            System.out.println(insertaDinero);
        }
    }

    private boolean menuAdministracion(){
        boolean exit=false;
        int opMenuAdministracion=-1;

        //Metodo para calcular el credito actual
        //calcularCredito();

        mostrarInfoMaquina();
        //Asignamos la opcion del menú
        opMenuAdministracion=opcionMenuAdministracion();

        if(opMenuAdministracion==0){
            exit=true;
        }else{
            iniciarOpcionMenuAdministracion(opMenuAdministracion);
        }
        //Devolvemos al main si queremos salir o no
        return exit;
    }

    private void devolucionMonedas(){

        if(Moneda.getCajetin()==0){
            System.out.println("No has introducido ninguna moneda.");
        }else{
            Moneda.devolucionMonedas();

        }
    }

    private int opcionMenuAdministracion(){
        int opAdmin=-1;
        boolean salirOpcionMenu,opMenuValida=true;


        do {
            salirOpcionMenu = true;
            try {
                //Se elige una opcion del menú
                opAdmin = sc.nextInt();
            }catch (Exception e){
                //Si se introduce un caracter que no sea numerico entero saltará un error y se pedirá reintroducir opcion de nuevo
                opMenuValida=false;
                System.out.println(caracterErroneo);
                sc.nextLine();
            }
            while(opMenuValida) {
                //Se comprueba que la opcion seleccionada es correcta
                if (opcionMenuAdminCorrecta(opAdmin)) {
                    //Si es correcta se podrá salir de este bucle de seleccion
                    salirOpcionMenu = true;
                    opMenuValida=false;
                } else {
                    //Si la opcion está fuera del rango del menu se mostrara mensaje de error y se pedira reintroducir de nuevo
                    System.out.println(opcionIncorrecta);
                    mostrarInfoMaquina();
                    salirOpcionMenu = false;
                    opMenuValida=false;
                }
            }
        }while(!salirOpcionMenu);
        return opAdmin;
    }

    private void iniciarOpcionMenuAdministracion(int opMenuAdmin){
        switch (opMenuAdmin){
            case 1:
                Moneda.mostrarCambioInterno();
                break;
            case 2:
                Producto.modificarPrecioProductos();
                break;
            case 3:
                mostrarUnidadesVendidas();
                break;
            case 4:
                System.out.println("Añadir productos (No implementado)");
                break;
        }
    }

    /*public void insertarProducto(Producto p){
        int punteroArray;
        //Creo un puntero para poder asignar al array el producto
        punteroArray=Producto.getCantidadTotal()-1;
        //Inserto el producto en la posicion con el puntero creado
        productos[punteroArray]=p;
    }*/

    private boolean opcionMenuCorrecta(int opMenu){
        //Si es una de las siguientes opciones será valida
        if(opMenu>=0 && opMenu<=6){
            return true;
        }else{
            return false;
        }
    }

    private boolean opcionMenuAdminCorrecta(int opMenu){
        //Si es una de las siguientes opciones será valida
        if(opMenu>=0 && opMenu<=4){
            return true;
        }else{
            return false;
        }
    }

    public void mostrarMenu(){
        System.out.println("\n--------------------------MENÚ-----------------------------");
        System.out.println("1 ------- Introducir Monedas");
        System.out.println("2 ------- Café Solo ("+Producto.getPrecioActualSolo()+") €      (Unidades disponibles: "+Producto.getCantidadSolo()+")");
        System.out.println("3 ------- Cafe con Leche ("+Producto.getPrecioActualConLeche()+") € (Unidades disponibles: "+Producto.getCantidadConLeche()+")");
        System.out.println("4 ------- Té ("+Producto.getPrecioActualTe()+") €             (Unidades disponibles: "+Producto.getCantidadTe()+")");
        System.out.println("5 ------- Menu Administracion");
        System.out.println("6 ------- Devolucion de Monedas");
        System.out.println("0 ------- Apagar la maquina \n");
    }

    private void mostrarInfoMaquina(){
        System.out.println("\n----------MENÚ ADMINISTRACION-----------");
        System.out.println("1 ------- Mostrar contenido de cajetines.");
        System.out.println("2 ------- Modificar precio productos.");
        System.out.println("3 ------- Mostrar unidades vendidas.");
        System.out.println("4 ------- Añadir productos (No implementado).");
        System.out.println("0 ------- Volver\n");
    }

    private void mostrarUnidadesVendidas(){
        System.out.println("\n-Unidades Vendidas-");
        System.out.println("Cafe Solo: "+Producto.getCantidadSoloVendida());
        System.out.println("Cafe con Leche: "+Producto.getCantidadConLecheVendida());
        System.out.println("Te: "+Producto.getCantidadTeVendida());
    }

}
