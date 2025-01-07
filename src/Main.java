import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class menu {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ejecutarMenu();
    }

    public static void ejecutarMenu() {
        int opcion;
        boolean correcto = false;

        do {
            System.out.println("***************");
            System.out.println("CHESS POSITION");
            System.out.println("***************");
            System.out.println("""
                ¡BIENVENIDO AL PROGRAMA DE "Movimiento de piezas de ajedrez sobre un tablero"!
                
                El programa consiste en indicar una ficha de ajedrez, su color y su posición inicial dentro del tablero. Tras esto, se indicará según la posición y las características de dicha pieza, los posibles movimientos a realizar.
                """);
            mostrarMenuInicial();

            try {
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("""
                        
                                                       ******************************************************************
                                                       ||                    INFORMACIÓN ADICIONAL                     ||
                                                       ******************************************************************
                        
                        | Información piezas |
                        
                        En el ajedrez, existen 6 tipos de piezas diferentes, y cada uno tiene unos movimientos determinados.
                        Piezas existentes y sus movimientos:
                        
                        - REY: Se mueve 1 casilla en cualquier dirección. Puede hacer el enroque.
                        
                        - DAMA: Se mueve todas las casillas que quiera, en horizontal, vertical o diagonal.
                        
                        - TORRE: Se mueve todas las casillas que quiera, en vertical o horizontal.
                        
                        - ALFIL: Se mueve todas las casillas que quiera, en diagonal y por casillas del mismo color que la que se encuentra.
                        
                        - CABALLO: Tiene dos posibilidades de movimientos:
                        1. Puede moverse una casilla en vertical, y dos en horizontal.
                        2. Puede moverse dos casillas en vertical, y una en horizontal.
                        En cualquier opción, el caballo en cada movimiento deberá llegar a una casilla de distinto color. Puede saltar cualquier pieza. Para matar tiene que caer encima de la pieza contraria.
                        
                        - PEÓN: No se mueve para atrás ni para los lados. Avanza 1 casilla adelante. Si tiene una pieza en diagonal hacia delante de él, si se puede mover hacía esa posición y matar. Si la posición inicial es la fila 2 (Blancos) o 7 (Negras), puede moverse 2 casillas. Si llega al final, puede ser modificada por otra pieza.
                        
                        | Colores piezas, orientación y información del tablero |
                        
                        El tablero que se usa en ajedrez, se trata de un 8x8 (8 casillas en horizontal, y 8 casillas en vertical), es decir 64 casillas.
                        Las filas se nombran por números, del 1 al 8. En cambio, las columnas se nombran por letras alfabéticas, de la A a la H.
                        Ejemplos:
                             - Casilla A2.
                             - Casilla F5.
                             - Casilla H6.
                             - Casilla C8.
                        
                        Existen dos colores diferentes, "Blancas" y "Negras". Dependiendo del color, las piezas se colocarán de una forma u otra.
                        
                        - Blancas: Sus torres tendrán que estar en la parte inferior del tablero. Es decir, sus torres quedarán situadas en A1 y H1 respectivamente. A la hora de moverse, generalmente se moverán hacia arriba (sin tener en cuenta los movimientos de cada pieza).
                        - Negras: Sus torres se situarán inicialmente en la parte superior del tablero. Sus torres se encontrarán colocadas en A8 y H8 respectivamente. Sus movimientos, en general, serán hacia abajo.
                        
                        Hay que tener en cuenta la orientación del tablero, donde una casilla blanca deberá quedar a la derecha del tablero (A8 y H1).
                        """);

                        esperarConfirmacion("¿Desea volver al menú principal? (S): ");
                        break;

                    case 2:
                        boolean salirMenuInicial2 = false;
                        do {
                            System.out.println("---------------");
                            System.out.println("INTRODUCE FICHA");
                            System.out.println("---------------");

                            mostrarMenuFicha();
                            String tipoFicha = seleccionarFicha();

                            mostrarMenuColor();
                            String color = seleccionarColor();

                            boolean dejarPedir = false;
                            String posicionInicial = "0";

                            while (!dejarPedir){
                                System.out.println("Introduzca la posición inicial de la ficha (Ejemplo: A2): ");
                                posicionInicial = sc.next().trim().toUpperCase();

                                boolean posicionValida = validacionPosicion(posicionInicial);

                                if (posicionValida){
                                    dejarPedir = true;
                                }
                            }

                            // Crear tablero y ficha
                            CrearTablero tablero = new CrearTablero(8, 8);
                            CrearFicha ficha = new CrearFicha(tipoFicha, color, posicionInicial);

                            // Imprimir la información de la ficha
                            System.out.println(ficha.toString());

                            // Aquí añadimos los movimientos creados en MovimientoFicha reconociendo cada nombre.
                            switch (tipoFicha.toLowerCase()) {
                                case "torre":
                                    movimientosPosibles(MovimientoFicha.torre(ficha, tablero));
                                    break;
                                case "alfil":
                                    movimientosPosibles(MovimientoFicha.alfil(ficha, tablero));
                                    break;
                                case "dama":
                                    movimientosPosibles(MovimientoFicha.dama(ficha, tablero));
                                    break;
                                case "rey":
                                    movimientosPosibles(MovimientoFicha.rey(ficha, tablero));
                                    break;
                                case "caballo":
                                    movimientosPosibles(MovimientoFicha.caballo(ficha, tablero));
                                default:
                                    System.out.println("Los movimientos para esta ficha no están implementados.");
                                    break;
                            }

                            System.out.println(" ");
                            salirMenuInicial2 = esperarConfirmacion("¿Desea volver al menú principal? (S/N): ");
                        } while (!salirMenuInicial2);
                        break;

                    case 3:
                        System.out.println("Saliendo del programa...\nGracias por usar nuestro programa. Pase un buen día.");
                        correcto = true;
                        break;

                    default:
                        System.err.println("ERROR. Introduce una opción válida.");
                }
            } catch (InputMismatchException e) {
                System.err.println("ERROR: Solo puedes introducir números.");
                sc.next();
            }
        } while (!correcto);
    }

    public static void mostrarMenuInicial() {
        System.out.println("1. Información");
        System.out.println("2. Introducir ficha");
        System.out.println("3. Salir");
        System.out.println("Introduce una opción:");
    }

    public static void mostrarMenuFicha() {
        System.out.println("*******");
        System.out.println("FICHAS");
        System.out.println("*******");
        System.out.println("1. Rey" + " " + simboloPiezas.reyBlanco);
        System.out.println("2. Dama" + " " + simboloPiezas.damaBlanca);
        System.out.println("3. Torre" + " " + simboloPiezas.torreBlanca);
        System.out.println("4. Caballo" + " " + simboloPiezas.caballoBlanco);
        System.out.println("5. Alfil" + " " + simboloPiezas.alfilBlanco);
        System.out.println("6. Peón" + " " + simboloPiezas.peonBlanco);
        System.out.println("Escoge la ficha:");
    }

    public static void mostrarMenuColor() {
        System.out.println("*******");
        System.out.println("COLORES");
        System.out.println("*******");
        System.out.println("1. Blanco" + " " + simboloPiezas.colorBlanco);
        System.out.println("2. Negro" + " " + simboloPiezas.colorNegro);
        System.out.println("Escoge el color de la ficha:");
    }

    public static String seleccionarFicha() {
        int ficha;
        String resultado = "";
        do {
            try {
                ficha = sc.nextInt();
                switch (ficha) {
                    case 1 -> resultado = "Rey";
                    case 2 -> resultado = "Dama";
                    case 3 -> resultado = "Torre";
                    case 4 -> resultado = "Caballo";
                    case 5 -> resultado = "Alfil";
                    case 6 -> resultado = "Peón";
                    default -> System.err.println("ERROR. Introduce una opción válida.");
                }
            } catch (InputMismatchException e) {
                System.err.println("ERROR: Solo puedes introducir números.");
                sc.next();
            }
        } while (resultado.isEmpty());
        return resultado;
    }

    public static String seleccionarColor() {
        int color;
        String resultado = "";
        do {
            try {
                color = sc.nextInt();
                if (color == 1){
                    resultado = "Blanco";
                }else if (color == 2){
                    resultado = "Negro";
                }else {
                    System.err.println("ERROR. Introduce una opción válida.");
                }
            } catch (InputMismatchException e) {
                System.err.println("ERROR: Solo puedes introducir números.");
                sc.next();
            }
        } while (resultado.isEmpty());
        return resultado;
    }

    public static boolean esperarConfirmacion(String mensaje) {
        String respuesta;
        do {
            System.out.println(mensaje);
            respuesta = sc.next().trim().toUpperCase();
            if (respuesta.equals("S")) return true;
            if (respuesta.equals("N")) return false;
            System.err.println("ERROR. Introduce una opción válida.");
        } while (true);
    }

    public static boolean validacionPosicion(String posicionInicial){
        boolean resultado = true;

        if (posicionInicial.length() != 2){
            System.err.println("ERROR. La cantidad de datos introducidos en la posición inicial es incorrecto. Se debe tener 2 carácteres (Fila y columna).");
            resultado = false;
        } else {
            char primerCaracter = posicionInicial.charAt(0);
            char segundoCaracter = posicionInicial.charAt(1);

            if (!Character.isLetter(primerCaracter) || !Character.isDigit(segundoCaracter)){
                System.err.println("ERROR. Debes introducir una columna (Letra) y fila (Número), en el orden indicado.");
                resultado = false;
            } else {
                int numero = Integer.parseInt(String.valueOf(segundoCaracter));

                // Si el cod ASCII de la letra es menor que... Significa que NO es una A o mayor, y si es mayor a ... NO estará entre A y la H.
                if (primerCaracter < 65 || primerCaracter > 72){
                    System.err.println("ERROR. Solo existen las columnas desde la A hasta la H.");
                    resultado = false;
                } else if (numero < 1 || numero > 8) {
                    System.err.println("ERROR. Solo existen las filas desde la 1 a la 8.");
                    resultado = false;
                }
            }
        }
        return resultado;
    }

    public static void movimientosPosibles(String[] posiciones){
        System.out.print("Movimientos posibles: ");
        for (int i = 0; i < posiciones.length; i++){
            if (i == 0){
                System.out.print("[" + posiciones[i] + " | ");
                continue;
            } else if (i == posiciones.length-1) {
                System.out.print(posiciones[i] + "]\n");
                continue;
            }
            System.out.print(posiciones[i] + " | ");
        }
    }
}
