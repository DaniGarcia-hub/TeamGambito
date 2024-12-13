import java.util.Scanner;

public class Main {

    public static int menuOpciones(){
        Scanner sc = new Scanner(System.in);

        int opcionInicioPrograma = 0;
        boolean ejecucionCorrecta = false;

        while (!ejecucionCorrecta){
            System.out.println("""
                A continuación, escoge el siguiente paso a seguir:
                
                1. Comprobar ficha.
                2. Información adicional.
                3. Salir.
                
                Recuerda, escoge una opción válida.""");

            int opcionUsuario = sc.nextInt();

            if (opcionUsuario == 1 || opcionUsuario == 2 || opcionUsuario == 3){
                opcionInicioPrograma = opcionUsuario;
                ejecucionCorrecta = true;
            } else {
                System.err.println("Opción escogida inválida. Prueba de nuevo.");
            }
        }

        return opcionInicioPrograma;
    }

    public static String eleccionFicha(){
        Scanner sc = new Scanner(System.in);

        String fichaSeleccionada = "";
        boolean ejecucionEleccion = false;

        System.out.println("""
                \nELECCIÓN DE FICHAS.
                
                Ahora, se solicita que se introduzca los siguientes datos:""");

        while (!ejecucionEleccion){
            System.out.println("""

                            FICHA DE AJEDREZ.
                            Escoge una de las siguientes:
                            
                            1) Rey.
                            2) Dama.
                            3) Torre.
                            4) Alfil.
                            5) Caballo.
                            6) Peon.
                            
                            Otras opciones:
                            7) Salir.
                            """);

            String opcionUsuario = sc.nextLine().toUpperCase();

            switch (opcionUsuario){
                case "R":
                    fichaSeleccionada = "R";
                    ejecucionEleccion = true;
                    break;
                case "D":
                    fichaSeleccionada = "D";
                    ejecucionEleccion = true;
                    break;
                case "T":
                    fichaSeleccionada = "T";
                    ejecucionEleccion = true;
                    break;
                case "A":
                    fichaSeleccionada = "A";
                    ejecucionEleccion = true;
                    break;
                case "C":
                    fichaSeleccionada = "C";
                    ejecucionEleccion = true;
                    break;
                case "P":
                    fichaSeleccionada = "P";
                    ejecucionEleccion = true;
                    break;
                case "S":
                    fichaSeleccionada = "S";
                    ejecucionEleccion = true;
                    break;
                default:
                    System.err.println("Opción seleccionada inválida. ¿Quieres volver a escoger ficha? (S)i / (N)o");
                    String reintentar = sc.nextLine();

                    if (reintentar.equals("N")){
                        System.out.println("Saliendo de la elección de fichas...");
                        ejecucionEleccion = true;
                    }
            }
        }

        return fichaSeleccionada;
    }

    public static void main(String[] args) {
        
        boolean salirPrograma = false;
        
        System.out.println("""
                ¡BIENVENIDO AL PROGRAMA DE "Movimiento de piezas de ajedrez sobre un tablero"!
                
                El programa consiste en indicar una ficha de ajedrez, su color y su posición inicial dentro del tablero. Tras esto, se indicará según la posición y las características de dicha pieza, los posibles movimientos a realizar.""");
        
        while (!salirPrograma){
            switch (menuOpciones()){
                case 1:

                    String ficha = eleccionFicha();

                    if (ficha.equals("S")){
                        System.out.println("Has seleccionado salir. ¿Quieres hacerlo?");
                    }

                    break;
                case 2:
                    System.out.println("""
                        \nINFORMACIÓN ADICIONAL.
                        
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
                        
                        - PEON: No se mueve para atrás ni para los lados. Avanza 1 casilla adelante. Si tiene una pieza en diagonal hacia delante de él, si se puede mover hacía esa posición y matar. Si la posición inicial es la fila 2 (Blancos) o 7 (Negras), puede moverse 2 casillas. Si llega al final, puede ser modificada por otra pieza.
                        
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
                    break;
                case 3:
                    System.out.println("\nSALIENDO DEL PROGRAMA...");
                    salirPrograma = true;
                    break;
            }
        }
    }
}
