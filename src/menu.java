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
            mostrarMenuInicial();

            try {
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("----------------------");
                        System.out.println("INFORMACIÓN ADICIONAL");
                        System.out.println("----------------------");

                        System.out.println("""
                        \\nINFORMACIÓN ADICIONAL.
                                                                                                                                                  \
                        | Información piezas |
                                                                                                                                                  \
                        En el ajedrez, existen 6 tipos de piezas diferentes, y cada uno tiene unos movimientos determinados.
                        Piezas existentes y sus movimientos:
                        - REY: Se mueve 1 casilla en cualquier dirección. Puede hacer el enroque.
                                                                                                                                                  \
                        - DAMA: Se mueve todas las casillas que quiera, en horizontal, vertical o diagonal.
                                                                                                                                                  \
                        - TORRE: Se mueve todas las casillas que quiera, en vertical o horizontal.
                                                                                                                                                  \
                        - ALFIL: Se mueve todas las casillas que quiera, en diagonal y por casillas del mismo color que la que se encuentra.
                                                                                                                                                  \
                        - CABALLO: Tiene dos posibilidades de movimientos:
                        1. Puede moverse una casilla en vertical, y dos en horizontal.
                        2. Puede moverse dos casillas en vertical, y una en horizontal.
                        En cualquier opción, el caballo en cada movimiento deberá llegar a una casilla de distinto color. Puede saltar cualquier pieza. Para matar tiene que caer encima de la pieza contraria.
                                                                                                                                                  \
                        - PEÓN: No se mueve para atrás ni para los lados. Avanza 1 casilla adelante. Si tiene una pieza en diagonal hacia delante de él, si se puede mover hacía esa posición y matar. Si la posición inicial es la fila 2 (Blancos) o 7 (Negras), puede moverse 2 casillas. Si llega al final, puede ser modificada por otra pieza.
                                                                                                                                                  \
                        | Colores piezas, orientación y información del tablero |
                                                                                                                                                  \
                        El tablero que se usa en ajedrez, se trata de un 8x8 (8 casillas en horizontal, y 8 casillas en vertical), es decir 64 casillas.
                        Las filas se nombran por números, del 1 al 8. En cambio, las columnas se nombran por letras alfabéticas, de la A a la H.
                        Ejemplos:
                             - Casilla A2.
                             - Casilla F5.
                             - Casilla H6.
                             - Casilla C8.
                                                                                                                                                  \
                        Existen dos colores diferentes, "Blancas" y "Negras". Dependiendo del color, las piezas se colocarán de una forma u otra.
                                                                                                                                                  \
                        - Blancas: Sus torres tendrán que estar en la parte inferior del tablero. Es decir, sus torres quedarán situadas en A1 y H1 respectivamente. A la hora de moverse, generalmente se moverán hacia arriba (sin tener en cuenta los movimientos de cada pieza).
                        - Negras: Sus torres se situarán inicialmente en la parte superior del tablero. Sus torres se encontrarán colocadas en A8 y H8 respectivamente. Sus movimientos, en general, serán hacia abajo.
                                                                                                                                                  \
                        Hay que tener en cuenta la orientación del tablero, donde una casilla blanca deberá quedar a la derecha del tablero (A8 y H1).
                        """);

                        esperarConfirmacion("¿Quieres volver al menú principal? (S): ");
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

                            System.out.println("Introduce la posición inicial de la ficha (ejemplo: A2): ");
                            String posicionInicial = sc.next().trim().toUpperCase();

                            CrearFicha ficha = new CrearFicha(tipoFicha, color, posicionInicial);
                            System.out.println(ficha);

                            int[] posicion = MovimientoFicha.convertirPosicion(posicionInicial);
                            boolean esBlanco = color.equalsIgnoreCase("Blanco");
                            String[] movimientosPosibles = MovimientoFicha.calcularMovimientos(tipoFicha, posicion, esBlanco);

                            if (movimientosPosibles == null || movimientosPosibles.length == 0) {
                                System.out.println("No hay movimientos posibles o la ficha no está implementada.");
                            } else {
                                System.out.println("Movimientos posibles:");
                                for (String movimiento : movimientosPosibles) {
                                    if (movimiento != null) {
                                        System.out.print("[" + movimiento + "] ");
                                    }
                                }
                            }

                            System.out.println(" ");
                            salirMenuInicial2 = esperarConfirmacion("¿Quieres volver al menú principal? (S/N): ");
                        } while (!salirMenuInicial2);
                        break;

                    case 3:
                        System.out.println("Saliendo del programa...");
                        correcto = true;
                        break;

                    default:
                        System.out.println("Opción no válida. Introduce una opción correcta.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Solo puedes introducir números.");
                sc.next();
            }
        } while (!correcto);
    }

    public static void mostrarMenuInicial() {
        System.out.println("1. Información");
        System.out.println("2. Introducir ficha");
        System.out.println("3. Salir");
        System.out.println("Introduce la opción:");
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
        System.out.println("Elige la ficha:");
    }

    public static void mostrarMenuColor() {
        System.out.println("*******");
        System.out.println("COLORES");
        System.out.println("*******");
        System.out.println("1. Blanco" + " " + simboloPiezas.colorBlanco);
        System.out.println("2. Negro" + " " + simboloPiezas.colorNegro);
        System.out.println("Elige el color de la ficha:");
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
                    default -> System.out.println("Opción no válida. Vuelve a introducir una opción: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Solo puedes introducir números.");
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
                    System.out.println("Opción no válida. Vuelve a introdicir una opción: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Solo puedes introducir números.");
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
            System.out.println("Error. Introduce un valor válido.");
        } while (true);
    }
}