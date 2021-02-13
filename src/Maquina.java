import java.util.Scanner;

public class Maquina {

    Scanner sc = new Scanner(System.in);

    String caracterErroneo = "El valor introducido no es un caracter valido, vuelva a introducirlo.";
    String opcionIncorrecta = "Has introducido una opción no correspondiente al menú, inserta otra.";
    String insertaDinero = "No has introducido el dinero suficiente para comprar este articulo, inserta dinero.";
    String noHayCambio = "La maquina no contiene el cambio suficiente, intentelo más tarde.\n";


    public boolean menu() {
        /**
         * Metodo que contendrá el menú principal de la APP
         */
        boolean exit = false;
        int opMenu = -1;

        //Mostrar si falta alguna moneda en la máquina para el cambio
        Moneda.comprobarMonedas();

        //Mostramos opciones del menú
        mostrarMenu();

        //Mostramos el dinero del cajetin

        System.out.println("Tu credito es de: " + String.format("%.2f", Moneda.getCajetin()) + "€");

        //Asignamos la opcion del menú
        opMenu = opcionMenu();

        //Si es la opcion 6 se apaga la maquina
        if (opMenu == 0) {
            exit = true;
        } else {
            //Sino se ejecuta el menu de la maquina
            iniciarOpcionMenu(opMenu);
        }
        //Devolvemos al main si queremos salir o no
        return exit;
    }

    private int opcionMenu() {
        /**
         * Método que seleccionará la opción del menu a aplicar.
         */
        int opMenu = -1;
        boolean salirOpcionMenu, opMenuValida = true;

        do {
            salirOpcionMenu = true;
            try {
                //Se elige una opcion del menú.
                opMenu = sc.nextInt();
            } catch (Exception e) {
                //Si se introduce un caracter que no sea numerico entero saltará un error y se pedirá reintroducir opcion de nuevo.
                opMenuValida = false;
                System.out.println(caracterErroneo);
                sc.nextLine();
            }

            if (opMenuValida) {
                //Se comprueba que la opcion seleccionada es correcta.
                if (opcionMenuCorrecta(opMenu)) {
                    //Si es correcta se podrá salir de este bucle de seleccion.
                    salirOpcionMenu = true;
                    opMenuValida = false;
                } else {
                    //Si la opcion está fuera del rango del menu se mostrara mensaje de error y se pedira reintroducir de nuevo.
                    System.out.println(opcionIncorrecta);
                    mostrarMenu();
                    salirOpcionMenu = false;
                    opMenuValida = false;
                }
            }
        } while (!salirOpcionMenu);
        return opMenu;
    }

    private void iniciarOpcionMenu(int opMenu) {
        /**
         * Metodo que hará la seleccion del menú principal.
         */

        boolean exit = false, comprobar;
        switch (opMenu) {
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
                    //Obtenemos la respuesta de si salir del menú de administración.
                    comprobar = menuAdministracion();

                    //Si la repsuesta es si, se preapara para salir, sino se repite el bucle
                    if (comprobar) {
                        exit = true;
                    }
                } while (!exit);
                break;

            case 6:
                devolucionMonedas();
                break;
        }
    }

    private void introducirMonedas() {
        /**
         * Metodo para introducir las monedas.
         */

        boolean salir, monedaAceptada;
        int moneda = -1;

        do {
            salir = false;
            monedaAceptada = true;
            //Mostramos las monedas aceptadas.
            Moneda.mostrarMonedas();

            //Mostramos las monedas que hemos insertado en la cajetilla.
            System.out.println("Tu credito es de: " + String.format("%.2f", Moneda.getCajetin()) + "€");

            try {
                //Introducimos la moneda.
                moneda = sc.nextInt();
            } catch (Exception e) {
                //En caso de que sea un caracter no válido mostramos error.
                System.err.println();

                //Impedimos acceder al resto del código
                monedaAceptada = false;
                sc.nextLine();
            }
            //Si no ha habido error al introducir el caracter entramos al resto del codigo.
            if (monedaAceptada) {
                if (moneda == 0) {
                    salir = true;
                    monedaAceptada = true;
                } else {
                    //Comprobamos que el numero insertado este en el rango valido
                    if (Moneda.esMonedaValida(moneda)) {
                        //Si no salimos insertamos moneda al cajetin
                        Moneda.totalCajetin(moneda);

                        //Mostramos las monedas que hemos insertado en la cajetilla.
                        System.out.println("Tu credito es de: " + String.format("%.2f", Moneda.getCajetin()) + "€");

                    } else {
                        System.out.println("Has introducido una moneda falsa o una moneda que no es centimo, inserta otra moneda.");
                    }
                }
            }
        } while (!salir);
    }

    private void crearCafeSolo() {
        /**
         * Método que creará el producto de Café Solo.
         */
        //Establecemos el tipo del objeto
        Tipo tipo = Tipo.SOLO;

        //1RO COMPRUEBA QUE HAY DINERO
        //2DO COMPRUEBA QUE HAY CAMBIO
        //3RO COMPRUEBA QUE HAY CANTIDAD DISPONIBLE
        //4RO SI SE CUMPLEN AMBAS CONDICIONES CREA EL OBJETO


        //PASO 1
        if (Moneda.comprobarPrecio(tipo)) {
            //PASO 2
            if (Moneda.hayCambio(Producto.getPrecioActualSolo())) {
                //PASO2
                if (Producto.comprobarCantidad(tipo)) {
                    //PASO 3
                    Producto p = new Producto(tipo);
                }
            } else {
                System.out.println(noHayCambio);
                Moneda.devolucionMonedas();
            }
        } else {
            System.out.println(insertaDinero);
            if (Moneda.getCajetin() != 0) {
                Moneda.devolucionMonedas();
            }
        }
    }


    private void crearCafeConLeche() {
        /**
         * Método que creará el producto de Café con Leche.
         */
        //Establecemos el tipo del objeto
        Tipo tipo = Tipo.CONLECHE;

        //1RO COMPRUEBA QUE HAY DINERO
        //2DO COMPRUEBA QUE HAY CAMBIO
        //3RO COMPRUEBA QUE HAY CANTIDAD DISPONIBLE
        //4RO SI SE CUMPLEN AMBAS CONDICIONES CREA EL OBJETO



        //PASO1
        if (Moneda.comprobarPrecio(tipo)) {
            //PASO2
            if (Moneda.hayCambio(Producto.getPrecioActualConLeche())) {
                //PASO 3
                if (Producto.comprobarCantidad(tipo)) {
                    //PASO 4
                    Producto p = new Producto(tipo);
                }
            } else {
                System.out.println(noHayCambio);
                Moneda.devolucionMonedas();
            }
        } else {
            System.out.println(insertaDinero);
            if (Moneda.getCajetin() != 0) {
                Moneda.devolucionMonedas();
            }
        }
    }

    private void crearTe() {
        /**
         * Método que creará el producto de Té.
         */
        //Establecemos el tipo del objeto
        Tipo tipo = Tipo.TE;

        //1RO COMPRUEBA QUE HAY DINERO
        //2DO COMPRUEBA QUE HAY CAMBIO
        //3RO COMPRUEBA QUE HAY CANTIDAD DISPONIBLE
        //4TO SI SE CUMPLEN AMBAS CONDICIONES CREA EL OBJETO


        //PASO 1
        if (Moneda.comprobarPrecio(tipo)) {
            //PASO 2
            if (Moneda.hayCambio(Producto.getPrecioActualTe())) {
                //PASO 3
                if (Producto.comprobarCantidad(tipo)) {
                    //PASO 4
                    Producto p = new Producto(tipo);
                }
            } else {
                System.out.println(noHayCambio);
                Moneda.devolucionMonedas();
            }
        } else {
            System.out.println(insertaDinero);
            if (Moneda.getCajetin() != 0) {
                Moneda.devolucionMonedas();
            }
        }
    }

    private boolean menuAdministracion() {
        /**
         * Método que creará el menú de administración.
         */
        boolean exit = false;
        int opMenuAdministracion = -1;

        //Mostramos las opciones del menú.
        mostrarInfoMaquina();

        //Asignamos la opcion del menú.
        opMenuAdministracion = opcionMenuAdministracion();

        //Si seleccionamos la opción 0 salimos del menú de administración.
        if (opMenuAdministracion == 0) {
            exit = true;
        } else {
            //Sino ejecutamos el método con las opciones del menú.
            iniciarOpcionMenuAdministracion(opMenuAdministracion);
        }
        //Devolvemos al main si queremos salir o no
        return exit;
    }

    private void devolucionMonedas() {
        /**
         * Método para la devolución de las monedas de la cajetilla.
         */
        //Si la cajetilla no tiene ninguna moneda avisaremos de que no hay ninguna moneda.
        if (Moneda.getCajetin() == 0) {
            System.out.println("No has introducido ninguna moneda.");
        } else {
            //Si hay alguna moneda ejecutaremos el método de devolver las monedas introducidas.
            Moneda.devolucionMonedas();
        }
    }

    private int opcionMenuAdministracion() {
        /**
         * Método que seleccionará la opción del menu a aplicar.
         */
        int opAdmin = -1;
        boolean salirOpcionMenu, opMenuValida = true;

        do {
            salirOpcionMenu = true;
            try {
                //Se elige una opcion del menú
                opAdmin = sc.nextInt();
            } catch (Exception e) {
                //Si se introduce un caracter que no sea numerico entero saltará un error y se pedirá reintroducir opcion de nuevo.
                opMenuValida = false;
                System.out.println(caracterErroneo);
                sc.nextLine();
            }
            while (opMenuValida) {
                //Se comprueba que la opcion seleccionada es correcta.
                if (opcionMenuAdminCorrecta(opAdmin)) {
                    //Si es correcta se podrá salir de este bucle de seleccion.
                    salirOpcionMenu = true;
                    opMenuValida = false;
                } else {
                    //Si la opcion está fuera del rango del menu se mostrara mensaje de error y se pedira reintroducir de nuevo.
                    System.out.println(opcionIncorrecta);
                    //Mostramos de nuevo las opciones del menú para que no se pierda el contenido de la vista.
                    mostrarInfoMaquina();
                    salirOpcionMenu = false;
                    opMenuValida = false;
                }
            }
        } while (!salirOpcionMenu);
        return opAdmin;
    }

    private void iniciarOpcionMenuAdministracion(int opMenuAdmin) {
        /**
         *Metodo que hará la seleccion del menú de administración.
         */

        switch (opMenuAdmin) {
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


    private boolean opcionMenuCorrecta(int opMenu) {
        /**
         * Método para comprobar que la opcion del menú principal está en el rango correcto.
         */

        //Si está dentro del rango de 0 a 6 será valido.
        if (opMenu >= 0 && opMenu <= 6) {
            return true;
        } else {
            return false;
        }
    }

    private boolean opcionMenuAdminCorrecta(int opMenu) {
        /**
         * Método para comprobar que la opcion del menú de administración está en el rango correcto.
         */

        //Si está dentro del rango de 0 a 4 será valido.
        if (opMenu >= 0 && opMenu <= 4) {
            return true;
        } else {
            return false;
        }
    }

    public void mostrarMenu() {
        /**
         * Método que mostrará las opciones del menú principal.
         */
        System.out.println("\n--------------------------MENÚ-----------------------------");
        System.out.println("1 ------- Introducir Monedas");
        System.out.println("2 ------- Café Solo (" + Producto.getPrecioActualSolo() + ") €      (Unidades disponibles: " + Producto.getCantidadSolo() + ")");
        System.out.println("3 ------- Cafe con Leche (" + Producto.getPrecioActualConLeche() + ") € (Unidades disponibles: " + Producto.getCantidadConLeche() + ")");
        System.out.println("4 ------- Té (" + Producto.getPrecioActualTe() + ") €             (Unidades disponibles: " + Producto.getCantidadTe() + ")");
        System.out.println("5 ------- Menu Administracion");
        System.out.println("6 ------- Devolucion de Monedas");
        System.out.println("0 ------- Apagar la maquina \n");
    }

    private void mostrarInfoMaquina() {
        /**
         * Método que mostrará las opciones del menú de administración.
         */
        System.out.println("\n----------MENÚ ADMINISTRACION-----------");
        System.out.println("1 ------- Mostrar contenido de cajetines.");
        System.out.println("2 ------- Modificar precio productos.");
        System.out.println("3 ------- Mostrar unidades vendidas.");
        System.out.println("4 ------- Añadir productos (No implementado).");
        System.out.println("0 ------- Volver\n");
    }

    private void mostrarUnidadesVendidas() {
        /**
         * Método que mostrará las unidades vendidas.
         */
        System.out.println("\n-Unidades Vendidas-");
        System.out.println("Cafe Solo: " + Producto.getCantidadSoloVendida());
        System.out.println("Cafe con Leche: " + Producto.getCantidadConLecheVendida());
        System.out.println("Te: " + Producto.getCantidadTeVendida());
    }

}
