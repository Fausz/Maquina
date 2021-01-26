import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Moneda {

    private static int cant5=2;
    private static int cant10=0;
    private static int cant20=3;
    private static int cant50=2;
    private static int cant1=0;
    private static int cant2=0;

    private static double cajetin=0;
    private static double totalCaja=0;
    private static double cant5Insertada=0;
    private static double cant10Insertada=0;
    private static double cant20Insertada=0;
    private static double cant50Insertada=0;
    private static double cant1Insertada=0;
    private static double cant2Insertada=0;



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

    public static double getCant5Insertada() {
        return cant5Insertada;
    }

    public static void setCant5Insertada(double cant5Insertada) {
        Moneda.cant5Insertada = cant5Insertada;
    }

    public static double getCant10Insertada() {
        return cant10Insertada;
    }

    public static void setCant10Insertada(double cant10Insertada) {
        Moneda.cant10Insertada = cant10Insertada;
    }

    public static double getCant20Insertada() {
        return cant20Insertada;
    }

    public static void setCant20Insertada(double cant20Insertada) {
        Moneda.cant20Insertada = cant20Insertada;
    }

    public static double getCant50Insertada() {
        return cant50Insertada;
    }

    public static void setCant50Insertada(double cant50Insertada) {
        Moneda.cant50Insertada = cant50Insertada;
    }

    public static double getCant1Insertada() {
        return cant1Insertada;
    }

    public static void setCant1Insertada(double cant1Insertada) {
        Moneda.cant1Insertada = cant1Insertada;
    }

    public static double getCant2Insertada() {
        return cant2Insertada;
    }

    public static void setCant2Insertada(double cant2Insertada) {
        Moneda.cant2Insertada = cant2Insertada;
    }

    public static double getCajetin() {
        return cajetin;
    }

    public static void setCajetin(double cajetin) {
        Moneda.cajetin = cajetin;
    }


    public static void mostrarMonedas(){
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

    public static boolean esMonedaValida(int moneda){
        if(moneda==5 || moneda==10 || moneda==20 || moneda==50 || moneda==1 || moneda==2){
            return true;
        }else{
            return false;
        }

    }


    public static void totalCajetin(int num){
       cantidadMonedasInsertadas(num);
        cajetin=calcularCajetin();
    }
    private static double calcularCajetin(){
        double cant;
        return cant=(cant1Insertada*1.00)+(cant2Insertada*2.00)+(cant10Insertada*0.10)+(cant20Insertada*0.20)+(cant50Insertada*0.50)+(cant5Insertada*0.05);
    }
    private static void cantidadMonedasInsertadas(int num){

        switch (num){
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
    /*private static double transformarMoneda(int num){
        double moneda=-1;
        switch (num){
            case 1:
                moneda=1.00;
                cant1Insertada++;
                break;
            case 2:
                moneda=2.00;
                cant2Insertada++;
                break;
            case 5:
                moneda=0.05;
                cant5Insertada++;
                break;
            case 10:
                moneda=0.10;
                cant10Insertada++;
                break;
            case 20:
                moneda=0.20;
                cant20Insertada++;
                break;
            case 50:
                moneda=0.50;
                cant50Insertada++;
                break;
        }
        return moneda;
    }*/

    public static boolean comprobarPrecio(Tipo t){
        boolean resp=false;
        switch (t){
            case SOLO:
                if(cajetin>=Producto.getPrecioActualSolo()){
                    resp = true;
                }else{
                    resp = false;
                }
                break;
            case CONLECHE:
                if(cajetin>=Producto.getPrecioActualConLeche()){
                    resp = true;
                }else{
                    resp = false;
                }
                break;
            case TE:
                if(cajetin>=Producto.getPrecioActualTe()){
                    resp = true;
                }else{
                    resp = false;
                }
                break;
        }
        return resp;
    }


    public static void restarCajetin(double precioProducto){
        //cajetin=cajetin-precioProducto;
        //if(cajetin!=0) {
            int centimos=calcularCambioCajetin(precioProducto);
            devolucionCambio(centimos);
            vaciarCajetin();
            cajetin=calcularCajetin();
        //}
    }

    private static int calcularCambioCajetin(double precioProducto){
        //Preparamos a trozos el cajetin para poder transformarlo a int
        String unionCaja;
        int centimos;

        String str = String.valueOf(calcularCajetin());
        int intNumberCaja = Integer.parseInt(str.substring(0, str.indexOf('.')));
        int decNumberIntCaja = Integer.parseInt(str.substring(str.indexOf('.') + 1));



        //Si no termina en 5 le añadimos un 0 pues java lo omite
        if(decNumberIntCaja%10!=5){
            unionCaja = intNumberCaja+""+(decNumberIntCaja+""+0);
            centimos = Integer.parseInt(unionCaja);
        }else{
            unionCaja = intNumberCaja+""+decNumberIntCaja;
            centimos = Integer.parseInt(unionCaja);
        }

        //Pareparamos a trozos el precio del producto para poder transformarlo a int
        String unionPrecio;
        int precio;
        String str2 = String.valueOf(precioProducto);
        int intNumberPrecio = Integer.parseInt(str2.substring(0, str2.indexOf('.')));
        int decNumberIntPrecio = Integer.parseInt(str2.substring(str2.indexOf('.') + 1));

        //Si no termina en 5 le añadimos un 0 pues java lo omite
        //if(decNumberIntPrecio%10!=5){
            unionPrecio = intNumberPrecio+""+(decNumberIntPrecio+""+0);
            precio = Integer.parseInt(unionPrecio);
        //}else{
           // unionPrecio = intNumberPrecio+""+decNumberIntPrecio;
           // precio = Integer.parseInt(unionPrecio);
        //}

        //Restamos el precio del producto a la caja
        return centimos-=precio;
    }
    private static void devolucionCambio(int centimos){

        /**
         * Metodo para restar el precio del producto de la caja
         */

            do {
                System.out.println("El cambio a devolver es el siguiente:"+centimos+" cént.");
                if (centimos / 200 > 0) {

                    System.out.printf("%nMonedas de 2€: %d", centimos / 200);
                    //System.out.println("\nMonedas de 2 euros: "+String.format("%.0f", centimos/200));
                    centimos %= 200;
                    cant2--;
                }
                if (centimos / 100 > 0) {

                    System.out.printf("%nMonedas de 1€: %d", centimos / 100);
                    //System.out.println("Monedas de 1 euro: "+String.format("%.0f", centimos/100));
                    centimos %= 100;
                    cant1--;
                }
                if (centimos / 50 > 0) {

                    System.out.printf("%nMonedas de 50 cént: %d", centimos / 50);
                    //System.out.println("Monedas de 50 cent: "+String.format("%.0f", centimos/50));
                    centimos %= 50;
                    cant50--;
                }
                if (centimos / 20 > 0) {

                    System.out.printf("%nMonedas de 20 cént: %d", centimos / 20);
                    //System.out.println("Monedas de 20 cent: "+String.format("%.0f", centimos/20));
                    centimos %= 20;
                    cant20--;
                }
                if (centimos / 10 > 0) {

                    System.out.printf("%nMonedas de 10 cént: %d", centimos / 10);
                    //System.out.println("Monedas de 10 cent: "+String.format("%.0f", centimos/10));
                    centimos %= 10;
                    cant10--;
                }
                if (centimos / 5 > 0) {

                    System.out.printf("%nMonedas de 5 cént: %d", centimos / 5);
                    //System.out.println("Monedas de 5 cent: "+String.format("%.0f", centimos/5));
                    centimos %= 5;
                    cant5--;
                }
                System.out.println();
            }while(centimos!=0);
        }

        private static void vaciarCajetin(){
            cant2+=cant2Insertada;
            cant2Insertada=0;
            cant1+=cant1Insertada;
            cant1Insertada=0;
            cant50+=cant50Insertada;
            cant50Insertada=0;
            cant20+=cant20Insertada;
            cant20Insertada=0;
            cant10+=cant10Insertada;
            cant10Insertada=0;
            cant5+=cant5Insertada;
            cant5Insertada=0;
        }

        public static void devolucionMonedas(){
            System.out.println("Se han devuelto: ");
            if(cant2Insertada!=0) {
                int cant=(int)cant2Insertada;
                System.out.println("Monedas de 2 €: "+cant+".");
                cant2Insertada=0;
            }
            if(cant1Insertada!=0) {
                int cant=(int)cant1Insertada;
                System.out.println("Monedas de 1 €: "+cant+".");
                cant1Insertada=0;
            }
            if(cant50Insertada!=0) {
                int cant=(int)cant50Insertada;
                System.out.println("Monedas de 50 cént: "+cant+".");
                cant50Insertada=0;
            }
            if(cant20Insertada!=0) {
                int cant=(int)cant20Insertada;
                System.out.println("Monedas de 20 cént: "+cant+".");
                cant20Insertada=0;
            }
            if(cant10Insertada!=0) {
                int cant=(int)cant10Insertada;
                System.out.println("Monedas de 10 cént: "+cant+".");
                cant10Insertada=0;
            }
            if(cant5Insertada!=0) {
                int cant=(int)cant5Insertada;
                System.out.println("Monedas de 5 cént: "+cant+".");
                cant5Insertada=0;
            }
            cajetin=calcularCajetin();
        }

        public static void mostrarCambioInterno(){
            double total=calcularPrecioTotalMonedas();
            System.out.println("\nEl cajetin contiene un total de: "+String.format("%.2f", total)+" €");
            System.out.println("Monedas de 2 €: "+cant2+".");
            System.out.println("Monedas de 1 €: "+cant1+".");
            System.out.println("Monedas de 50 cént: "+cant50+".");
            System.out.println("Monedas de 20 cént: "+cant20+".");
            System.out.println("Monedas de 10 cént: "+cant10+".");
            System.out.println("Monedas de 5 cént: "+cant5+".");
        }

        private static double calcularPrecioTotalMonedas(){
            double calc;
            calc= (cant1*1.00)+(cant2*2.00)+(cant50*0.50)+(cant20*0.20)+(cant10*0.10)+(cant5*0.05);
            return calc;
        }
        public static boolean hayCambio(double precioProducto){
        //obtenemos todo el cambio de la caja en int
            int cambio=calcularTotalMonedas();

            //calculamos el resto
            int centimos=calcularCambioCajetin(precioProducto);

            //comparamos el total del cambio en int(centimos) con la resta del total de la cajetilla con el precio del producto


            if(centimos<=cambio && comprobarStockMonedas(centimos)){
                return true;
            }else{
                return false;
            }

        }

        private static int calcularTotalMonedas(){
            String total;
            double calc;
            calc= (cant1*1.00)+(cant2*2.00)+(cant50*0.50)+(cant20*0.20)+(cant10*0.10)+(cant5*0.05);

            DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
            separadoresPersonalizados.setDecimalSeparator('.');

            DecimalFormat formato1 = new DecimalFormat("#.00", separadoresPersonalizados);
            total=formato1.format(calc); // Resultado => 3.33


            //String str = String.valueOf(calc);

            String unionCaja;

            int centimos;


            int intNumberCaja = Integer.parseInt(total.substring(0, total.indexOf('.')));
            int decNumberIntCaja = Integer.parseInt(total.substring(total.indexOf('.') + 1));




            //Si no termina en 5 le añadimos un 0 pues java lo omite
            if(decNumberIntCaja%10!=5){
                unionCaja = intNumberCaja+""+(decNumberIntCaja+""+0);
                centimos = Integer.parseInt(unionCaja);
            }else{
                unionCaja = intNumberCaja+""+decNumberIntCaja;
                centimos = Integer.parseInt(unionCaja);
            }

            return centimos;
    }

        public static boolean comprobarStockMonedas(int centimos){

            //do {
                //System.out.println("El cambio a devolver es el siguiente:"+centimos+" cént.");
                if (centimos / 200 > 0) {

                    //System.out.printf("%nMonedas de 2€: %d", centimos / 200);
                    //System.out.println("\nMonedas de 2 euros: "+String.format("%.0f", centimos/200));
                    //centimos %= 200;
                    if((cant2>centimos/200)){
                        centimos-=((centimos/200)*2.00);
                    }

                }
                if (centimos / 100 > 0) {

                    //System.out.printf("%nMonedas de 1€: %d", centimos / 100);
                    //System.out.println("Monedas de 1 euro: "+String.format("%.0f", centimos/100));
                    //centimos %= 100;
                    if((cant1>centimos/100)){
                        centimos-=((centimos/100)*1.00);
                    }

                }
                if (centimos / 50 > 0) {
                    if(cant50==0){

                    }else {
                        //System.out.printf("%nMonedas de 50 cént: %d", centimos / 50);
                        //System.out.println("Monedas de 50 cent: "+String.format("%.0f", centimos/50));
                        //centimos %= 50;
                        if((cant50>centimos/50)){
                            centimos-=((centimos/50)*0.50);
                        }
                    }
                }
                if (centimos / 20 > 0) {
                    if(cant20==0){

                    }else {
                        //System.out.printf("%nMonedas de 20 cént: %d", centimos / 20);
                        //System.out.println("Monedas de 20 cent: "+String.format("%.0f", centimos/20));
                        //centimos %= 20;
                        if((cant20>centimos/20)){
                            centimos-=((centimos/20)*0.20);
                        }
                    }
                }
                if (centimos / 10 > 0) {
                    if(cant10==0){

                    }else {
                        //System.out.printf("%nMonedas de 10 cént: %d", centimos / 10);
                        //System.out.println("Monedas de 10 cent: "+String.format("%.0f", centimos/10));
                        //centimos %= 10;
                        if((cant10>centimos/10)){
                            centimos-=((centimos/10)*0.10);
                        }
                    }
                }
                if (centimos / 5 > 0) {
                    if(cant5==0){

                    }else {
                        //System.out.printf("%nMonedas de 5 cént: %d", centimos / 5);
                        //System.out.println("Monedas de 5 cent: "+String.format("%.0f", centimos/5));
                        //centimos %= 5;
                        if((cant5>centimos/5)){
                            centimos-=((centimos/5)*0.05);
                        }
                    }
                }

            //}while(centimos!=0);
            if(centimos!=0){
                return false;
            }else {
                return true;
            }
        }

    private static int calcularCambioInterno(double precioProducto){
        //Preparamos a trozos el cajetin para poder transformarlo a int
        String unionCaja;
        int centimos;
        totalCaja=calcularTotalMonedas();
        String str = String.valueOf(totalCaja);
        int intNumberCaja = Integer.parseInt(str.substring(0, str.indexOf('.')));
        int decNumberIntCaja = Integer.parseInt(str.substring(str.indexOf('.') + 1));



        //Si no termina en 5 le añadimos un 0 pues java lo omite
        if(decNumberIntCaja%10!=5){
            unionCaja = intNumberCaja+""+(decNumberIntCaja+""+0);
            centimos = Integer.parseInt(unionCaja);
        }else{
            unionCaja = intNumberCaja+""+decNumberIntCaja;
            centimos = Integer.parseInt(unionCaja);
        }

        //Pareparamos a trozos el precio del producto para poder transformarlo a int
        String unionPrecio;
        int precio;
        String str2 = String.valueOf(precioProducto);
        int intNumberPrecio = Integer.parseInt(str2.substring(0, str2.indexOf('.')));
        int decNumberIntPrecio = Integer.parseInt(str2.substring(str2.indexOf('.') + 1));

        //Si no termina en 5 le añadimos un 0 pues java lo omite
        if(decNumberIntPrecio%10!=5){
            unionPrecio = intNumberPrecio+""+(decNumberIntPrecio+""+0);
            precio = Integer.parseInt(unionPrecio);
        }else{
            unionPrecio = intNumberPrecio+""+decNumberIntPrecio;
            precio = Integer.parseInt(unionPrecio);
        }

        //Restamos el precio del producto a la caja
        return centimos-=precio;
    }

}
