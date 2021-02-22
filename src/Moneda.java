import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Moneda {

    //Variable que contendrá eñ calculo total del cajetin de monedas.
    private static double cajetin = 0;
    //Variable que contendrá eñ calculo total de la caja de cambio de las monedas.
    private static double totalCaja = 0;

    //Variables que contendrán el numero de cada tipo de moneada del cambio interno de la máquina.
    private static int cant5 = 2;//2
    private static int cant10 = 0;
    private static int cant20 = 0;//3
    private static int cant50 = 1;
    private static int cant1 = 1;
    private static int cant2 = 0;

    //Variables que contendrán el numero de cada tipo de moneada del cajetin de monedas de la máquina.
    private static int cant5Insertada = 0;
    private static int cant10Insertada = 0;
    private static int cant20Insertada = 0;
    private static int cant50Insertada = 0;
    private static int cant1Insertada = 0;
    private static int cant2Insertada = 0;

    public static int getCant5() {
        return cant5;
    }

    public static void setCant5(int cant5) {
        Moneda.cant5 = cant5;
    }

    public static int getCant10() {
        return cant10;
    }

    public static void setCant10(int cant10) {
        Moneda.cant10 = cant10;
    }

    public static int getCant20() {
        return cant20;
    }

    public static void setCant20(int cant20) {
        Moneda.cant20 = cant20;
    }

    public static int getCant50() {
        return cant50;
    }

    public static void setCant50(int cant50) {
        Moneda.cant50 = cant50;
    }

    public static int getCant1() {
        return cant1;
    }

    public static void setCant1(int cant1) {
        Moneda.cant1 = cant1;
    }

    public static int getCant2() {
        return cant2;
    }

    public static void setCant2(int cant2) {
        Moneda.cant2 = cant2;
    }

    public static double getCajetin() {
        return cajetin;
    }

    public static void setCajetin(double cajetin) {
        Moneda.cajetin = cajetin;
    }

    public static double getTotalCaja() {
        return totalCaja;
    }

    public static void setTotalCaja(double totalCaja) {
        Moneda.totalCaja = totalCaja;
    }

    public static int getCant5Insertada() {
        return cant5Insertada;
    }

    public static void setCant5Insertada(int cant5Insertada) {
        Moneda.cant5Insertada = cant5Insertada;
    }

    public static int getCant10Insertada() {
        return cant10Insertada;
    }

    public static void setCant10Insertada(int cant10Insertada) {
        Moneda.cant10Insertada = cant10Insertada;
    }

    public static int getCant20Insertada() {
        return cant20Insertada;
    }

    public static void setCant20Insertada(int cant20Insertada) {
        Moneda.cant20Insertada = cant20Insertada;
    }

    public static int getCant50Insertada() {
        return cant50Insertada;
    }

    public static void setCant50Insertada(int cant50Insertada) {
        Moneda.cant50Insertada = cant50Insertada;
    }

    public static int getCant1Insertada() {
        return cant1Insertada;
    }

    public static void setCant1Insertada(int cant1Insertada) {
        Moneda.cant1Insertada = cant1Insertada;
    }

    public static int getCant2Insertada() {
        return cant2Insertada;
    }

    public static void setCant2Insertada(int cant2Insertada) {
        Moneda.cant2Insertada = cant2Insertada;
    }

    public static void mostrarMonedas() {
        /**
         * Método que mostrará las monedas admitidas.
         */
        System.out.println("\n-INTRODUCIR MONEDAS-");
        System.out.println(" Monedas aceptadas:");
        System.out.println("    5 ->  5 Cts.");
        System.out.println("   10 -> 10 Cts.");
        System.out.println("   20 -> 10 Cts.");
        System.out.println("   50 -> 50 Cts.");
        System.out.println("    1 ->  1 €.");
        System.out.println("    2 ->  2 €.");
        System.out.println("   0 -> Finalizar.");
    }

    public static boolean esMonedaValida(int moneda) {
        /**
         * Método que comprobará si la moneda introducida es válida (todas menos las de 1 cent y 2 cent.
         */

        if (moneda == 5 || moneda == 10 || moneda == 20 || moneda == 50 || moneda == 1 || moneda == 2) {
            return true;
        } else {
            return false;
        }
    }


    public static void totalCajetin(int num) {
        /**
         * Método que sumará las monedas introducidas en el cajetín una a una y sumandose en el cajetín de monedas de la máquina.
         */

        //Enviamos la moneda que nos llega a este método para que sume el contador de la moneda introducida.
        cantidadMonedasInsertadas(num);

        //Actualizamos el valor del cajetin sumando las monedas que contiene.
        cajetin = calcularCajetin();
    }

    private static double calcularCajetin() {
        /**
         * Método que calcula la suma total de las monedas del cajetín de monedas.
         */

        double cant= (cant1Insertada * 1.00) + (cant2Insertada * 2.00) + (cant10Insertada * 0.10) + (cant20Insertada * 0.20) + (cant50Insertada * 0.50) + (cant5Insertada * 0.05);
        return cant ;
    }

    private static void cantidadMonedasInsertadas(int num) {

        /**
         * Método que incrementa el contador de monedas del cajetín sobre la moneda que le llega.
         */

        switch (num) {
            case 1:
                cant1Insertada++;
                break;
            case 2:
                cant2Insertada++;
                break;
            case 5:
                cant5Insertada++;
                break;
            case 10:
                cant10Insertada++;
                break;
            case 20:
                cant20Insertada++;
                break;
            case 50:
                cant50Insertada++;
                break;
        }
    }

    public static boolean comprobarPrecio(Tipo t) {
        /**
         * Método que comprueba que se haya introducido el precio correspondiente o superior para comprar el producto que se ha elegido.
         */

        boolean resp = false;
        switch (t) {
            case SOLO:
                if (cajetin >= Producto.getPrecioActualSolo()) {
                    resp = true;
                } else {
                    resp = false;
                }
                break;
            case CONLECHE:
                if (cajetin >= Producto.getPrecioActualConLeche()) {
                    resp = true;
                } else {
                    resp = false;
                }
                break;
            case TE:
                if (cajetin >= Producto.getPrecioActualTe()) {
                    resp = true;
                } else {
                    resp = false;
                }
                break;
        }
        return resp;
    }

    public static void actualizarCambioMaquina(){
        /**
         * Método para actualizar las monedas de cambio de la máquina.
         */
            int aux2,aux1,aux50,aux20,aux10,aux5;

            aux2=cant2;
            aux1=cant1;
            aux50=cant50;
            aux20=cant20;
            aux10=cant10;
            aux5=cant5;

            cant2=aux2;
            cant1=aux1;
            cant50=aux50;
            cant20=aux20;
            cant10=aux10;
            cant5=aux5;
    }
    public static void restarCajetin(double precioProducto) {
        /**
         * Método que hará el proceso de cambio con la devolucion del producto y reseteará el cajetin.
         */

        //Obtenemos el cambio que hay que devolver en numero entero (cajetin - precioProducto).
        int centimos = calcularCambioCajetin(precioProducto);

        //Llamamos al método para hacer la devolución de monedas.
        devolucionCambio(centimos);

        //Vaciamos el cajetín de monedas.
        vaciarCajetin();

        //Actualizamos el valor del cajetin sumando las monedas que contiene.
        cajetin = calcularCajetin();

    }

    private static int calcularCambioCajetin(double precioProducto) {
        /**
         * Método para calcular el resto de cajetin-precioProducto para obtener el total en centimos para los calculos de devolución de moneda.
         */

        //Obtenemos el total del cajetin en centimos.
        double cajetinMonedas = calcularCajetin() * 100;

        //Pasamos el calculo anterior a int.
        int centimos = (int) cajetinMonedas;

        //Obtenemos el precio del producto en centimos.
        double precio = precioProducto * 100;

        //Pasamos el calculo anterior a int.
        int precioProductoEntero = (int) precio;

        //Obtenemos el cambio de monedas que tendremos que aplicar posteriormente.
        centimos -= precioProductoEntero;
        return centimos;
    }

    private static void vaciarCajetin() {
        /**
         * Método para vaciar el cajetín de monedas.
         */
        cant2 += cant2Insertada;
        cant2Insertada = 0;
        cant1 += cant1Insertada;
        cant1Insertada = 0;
        cant50 += cant50Insertada;
        cant50Insertada = 0;
        cant20 += cant20Insertada;
        cant20Insertada = 0;
        cant10 += cant10Insertada;
        cant10Insertada = 0;
        cant5 += cant5Insertada;
        cant5Insertada = 0;
    }

    public static void devolucionMonedas() {
        /**
         * Método para la devolución de las monedas insertadas.
         */
        System.out.println("Se han devuelto: ");

        //Si hay alguna moneda, muestra el texto y la cantidad devuelta y se resetea a 0.
        if (cant2Insertada != 0) {
            System.out.println("Monedas de 2 €: " + cant2Insertada + ".");
            cant2Insertada = 0;
        }
        if (cant1Insertada != 0) {
            System.out.println("Monedas de 1 €: " + cant1Insertada + ".");
            cant1Insertada = 0;
        }
        if (cant50Insertada != 0) {
            System.out.println("Monedas de 50 cént: " + cant50Insertada + ".");
            cant50Insertada = 0;
        }
        if (cant20Insertada != 0) {
            System.out.println("Monedas de 20 cént: " + cant20Insertada + ".");
            cant20Insertada = 0;
        }
        if (cant10Insertada != 0) {
            System.out.println("Monedas de 10 cént: " + cant10Insertada + ".");
            cant10Insertada = 0;
        }
        if (cant5Insertada != 0) {
            System.out.println("Monedas de 5 cént: " + cant5Insertada + ".");
            cant5Insertada = 0;
        }

        //Se calcula el cajetin para actualizarlo.
        cajetin = calcularCajetin();
    }

    public static void mostrarCambioInterno() {
        /**
         * Método que muestra la cantidad de monedas de cambio que contiene la máquina.
         */

        //Se calcula el total de las monedas de cambio.
        double total = calcularPrecioTotalMonedas();

        //Se muestra la suma del total.
        System.out.println("\nEl cajetin contiene un total de: " + String.format("%.2f", total) + " €");

        //Se muestran las monedas con sus cantidades.
        System.out.println("Monedas de 2 €: " + cant2 + ".");
        System.out.println("Monedas de 1 €: " + cant1 + ".");
        System.out.println("Monedas de 50 cént: " + cant50 + ".");
        System.out.println("Monedas de 20 cént: " + cant20 + ".");
        System.out.println("Monedas de 10 cént: " + cant10 + ".");
        System.out.println("Monedas de 5 cént: " + cant5 + ".");
    }

    private static double calcularPrecioTotalMonedas() {
        /**
         * Método que calcula la suma de la cantidad de monedas de cambio que contiene la máquina.
         */

        double calc = (cant1 * 1.00) + (cant2 * 2.00) + (cant50 * 0.50) + (cant20 * 0.20) + (cant10 * 0.10) + (cant5 * 0.05);
        return calc;
    }

    public static boolean hayCambio(double precioProducto) {
        /**
         *Método que comprueba si hay cambio para devolver al cliente.
         */

        //Obtenemos todo el cambio de la caja en int.
        double cambioDecimal = calcularPrecioTotalMonedas()*100;
        int cambio=(int)cambioDecimal;

        //pasar a entero el cambio

        //Calculamos el resto de cajetin-precio.
        int centimos = calcularCambioCajetin(precioProducto);

        //Comparamos que el cambio a devolver de la cajetilla sea inferior o igual que el cambio de la máquina y que haya la cantidad de monedas de cambio suficientes.
        if (centimos <= cambio && comprobarStockMonedas(centimos)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean comprobarStockMonedas(int centimos) {
        /**
         * Método que comprueba si hay la cantidad de monedas necesarias para proporcionar el cambio del producto.
         */

        //Variables auxiliares que harán de contador de las veces que devuelve una cantidad concreta de moneda.
        int aux2 = 0;
        int aux1 = 0;
        int aux50 = 0;
        int aux20 = 0;
        int aux10 = 0;
        int aux5 = 0;

        //Si el cambio está dentro del rango de la cantidad de la moneda se comprobara si...
        if (centimos / 200 > 0) {
            //Si hay la misma cantidad de monedas de cambio del tipo que es o mayor cantidad...
            if ((cant2 >= centimos / 200)) {

                //Se restaran directamente la cantidad de monedas del tipo que es.
                cant2 -= (centimos / 200);
                //Al contador se le dirá cuantas monedas hay de este tipo.
                aux2 += (centimos / 200);
                //Se resta el cambio por completo pues hay cambio de esta moneda.
                centimos %= 200;

            } else if (cant2 > 0) {
                //Si no hay cambio total de esta moneda pues se hará vía devolucion de lo que se pueda.

                //Se resta la cantidad del tipo de moneda en la que está.
                centimos -= 200;
                //Se resta un contador de la cantidad.
                cant2--;
                //Se suma un contador del tipo de moneda que es para saber cuantas monedas ha restado.
                aux2++;

                //Mientras que quede alguna moneda se repite el bucle.
                while (centimos != 0) {
                    //Si se gastan las monedas de este tipo y queda aun por devolver se romperá el ciclo y se pasará a comprobar con la moneda inferior a esta.
                    if ((cant2 * 100) != centimos) {
                        break;
                    }
                    //Si siguen quedando monedas se hará el mismo proceso de antes del bucle mientras que queden monedas.
                    centimos -= 200;
                    cant2--;
                    aux2++;
                }
            }
        }

        if (centimos / 100 > 0) {

            if ((cant1 >= centimos / 100)) {

                cant1 -= (centimos / 100);
                aux1 += (centimos / 100);
                centimos %= 100;
            } else if (cant1 > 0) {

                centimos -= 100;
                cant1--;
                aux1++;
                while (centimos != 0) {
                    if ((cant1 * 100) != centimos) {
                        break;
                    }
                    centimos -= 100;
                    cant1--;
                    aux1++;
                }
            }
        }
        if ((centimos / 50) > 0) {

            if (cant50 >= (centimos / 50)) {
                cant50 -= (centimos / 50);
                aux50 += (centimos / 50);
                centimos %= 50;

            } else if (cant50 > 0) {

                centimos -= 50;
                cant50--;
                aux50++;
                while (centimos != 0) {
                    if ((cant50 * 10) != centimos) {
                        break;
                    }
                    centimos -= 50;
                    cant50--;
                    aux50++;
                }
            }
        }

        if (centimos / 20 > 0) {

            if ((cant20 >= (centimos / 20))) {

                cant20 -= (centimos / 20);
                aux20 += (centimos / 20);
                centimos %= 20;
            } else if (cant20 > 0) {

                centimos -= 20;
                cant20--;
                aux20++;
                while (centimos != 0) {
                    if ((cant20 * 10) != centimos) {
                        break;
                    }
                    centimos -= 20;
                    cant20--;
                    aux20++;
                }
            }
        }

        if (centimos / 10 > 0) {

            if ((cant10 >= (centimos / 10))) {

                cant10 -= (centimos / 10);
                aux10 += (centimos / 10);
                centimos %= 10;
            }
            else if (cant10 > 0) {

                centimos -= 10;
                cant10--;
                aux10++;
                while (centimos != 0) {
                    if (cant10 == 0) {
                        break;
                    }
                    centimos -= 10;
                    cant10--;
                    aux10++;
                }
            }
        }

        if (centimos / 5 > 0) {

            if ((cant5 >= (centimos / 5))) {

                cant5 -= (centimos / 5);
                aux5 += (centimos / 5);
                centimos %= 5;
            } else if (cant5 > 0) {

                centimos -= 5;
                cant5--;
                aux5++;
                while (centimos != 0) {
                    if (cant5 == 0) {
                        break;
                    }
                    centimos -= 5;
                    cant5--;
                    aux5++;
                }
            }
        }

        //Dado que este método es de comprobacion y no de aplicar pues se restauran los valores de las cantidades a como estaban inicialmente con los auxiliares que contienen las cantidades restadas.
        cant2 += aux2;
        cant1 += aux1;
        cant50 += aux50;
        cant20 += aux20;
        cant10 += aux10;
        cant5 += aux5;

        //Si se ha podido restar todo el cambio significa que hay cambio, si ha quedado algun centimo por falta de stock de monedas significará que no hay cambio disponible.
        if (centimos != 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void devolucionCambio(int centimos) {
        /**
         *Misma lógica que el método de comprobarStockMonedas() solo que esta vez se mostrarán las monedas que devuelve, la cantidad de las mismas y se restarán las monedas que se devuelven.
         */

        //Variables que representan a las cantidades de los tipos de monedas que se restan completamente de este tipo
        int aux2 = 0;
        int aux1 = 0;
        int aux50 = 0;
        int aux20 = 0;
        int aux10 = 0;
        int aux5 = 0;

        //Variables que representan al stock de monedas de los tipos de monedas que se restan completamente de este tipo, en vez de descontar del contador de la clase se hará con el auxiliar.
        int auxCant2 = cant2;
        int auxCant1 = cant1;
        int auxCant50 = cant50;
        int auxCant20 = cant20;
        int auxCant10 = cant10;
        int auxCant5 = cant5;

        //Mostramos la cantidad que tendremos que devolver.
        System.out.println("El cambio a devolver es el siguiente:" + centimos + " cént.");

        if (centimos / 200 > 0) {

            if ((cant2 >= centimos / 200)) {

                aux2 = centimos / 200;
                centimos %= 200;

            } else if (cant2 > 0) {

                centimos -= 200;
                auxCant2--;
                aux2++;
                while (centimos != 0) {
                    if (auxCant2 == 0) {
                        break;
                    }
                    centimos -= 200;
                    auxCant2--;
                    aux2++;
                }
            }
            //Si hay alguna moneda de este tipo se mostrará la cantidad, sino no se mostrará pues entrar entra dentro de esta condicional.
            if (aux2 > 0) {
                System.out.printf("%nMonedas de 2€: %d", aux2);
            }
        }

        if (centimos / 100 > 0) {


            if ((cant1 >= centimos / 100)) {

                aux1 = centimos / 100;
                centimos %= 100;
            } else if (cant1 > 0) {

                centimos -= 100;
                auxCant1--;
                aux1++;
                while (centimos != 0) {
                    if (auxCant1 == 0) {
                        break;
                    }
                    centimos -= 100;
                    auxCant1--;
                    aux1++;
                }
            }
            if (aux1 > 0) {
                System.out.printf("%nMonedas de 1€: %d", aux1);
            }
        }

        if ((centimos / 50) > 0) {

            if (cant50 >= (centimos / 50)) {
                aux50 = centimos / 50;
                centimos %= 50;

            } else if (cant50 > 0) {

                centimos -= 50;
                auxCant50--;
                aux50++;
                while (centimos != 0) {
                    if (auxCant50 == 0) {
                        break;
                    }
                    centimos -= 50;
                    auxCant50--;
                    aux50++;
                }
            }
            if (aux50 > 0) {
                System.out.printf("%nMonedas de 50 cent: %d", aux50);
            }

        }

        if (centimos / 20 > 0) {

            if ((cant20 >= (centimos / 20))) {

                aux20 = centimos / 20;
                centimos %= 20;

            } else if (cant20 > 0) {

                centimos -= 20;
                auxCant20--;
                aux20++;
                while (centimos != 0) {
                    if (auxCant20 == 0) {
                        break;
                    }
                    centimos -= 20;
                    auxCant20--;
                    aux20++;
                }
            }

            if (aux20 > 0) {
                System.out.printf("%nMonedas de 20 cent: %d", aux20);
            }
        }

        if (centimos / 10 > 0) {

            if ((cant10 >= (centimos / 10))) {

                aux10 = centimos / 10;
                centimos %= 10;
            }
            else if (cant10 > 0) {

                centimos -= 10;
                auxCant10--;
                aux10++;
                while (centimos != 0) {
                    if (auxCant10 == 0) {
                        break;
                    }
                    centimos -= 10;
                    auxCant10--;
                    aux10++;
                }
            }
            if (aux10 > 0) {
                System.out.printf("%nMonedas de 10 cent: %d", aux10);
            }
        }

        if (centimos / 5 > 0) {

            if ((cant5 >= (centimos / 5))) {

                aux5 = centimos / 5;
                centimos %= 5;

            } else if (cant5 > 0) {

                centimos -= 5;
                auxCant5--;
                aux5++;
                while (centimos != 0) {
                    if (auxCant5 == 0) {
                        break;
                    }
                    centimos -= 5;
                    auxCant5--;
                    aux5++;
                }
            }
            if (aux5 > 0) {
                System.out.printf("%nMonedas de 5 cent: %d", aux5);
            }
        }

        //Se restan las cantidades de los contadores auxiliares que han ido acumulando las devoluciones de las monedas.
        cant2 -= aux2;
        cant1 -= aux1;
        cant50 -= aux50;
        cant20 -= aux20;
        cant10 -= aux10;
        cant5 -= aux5;

    }


    public static void comprobarMonedas() {
        /**
         * Método que comprueba si falta stock en alguna moneda de cambio.
         */

        System.out.println();
        if (cant2 == 0) {
            System.out.println("Se han agotado las monedas de 2€, por favor introduzca en la caja de cambio, gracias.");
        }
        if (cant1 == 0) {
            System.out.println("Se han agotado las monedas de 1€, por favor introduzca en la caja de cambio, gracias.");
        }
        if (cant50 == 0) {
            System.out.println("Se han agotado las monedas de 50 cent, por favor introduzca en la caja de cambio, gracias.");
        }
        if (cant20 == 0) {
            System.out.println("Se han agotado las monedas de 20 cent, por favor introduzca en la caja de cambio, gracias.");
        }
        if (cant10 == 0) {
            System.out.println("Se han agotado las monedas de 10 cent, por favor introduzca en la caja de cambio, gracias.");
        }
        if (cant5 == 0) {
            System.out.println("Se han agotado las monedas de 5 cent, por favor introduzca en la caja de cambio, gracias.");
        }
    }
}


