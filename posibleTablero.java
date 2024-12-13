import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class posibleTablero {

    // Secuencias de escape ANSI para colores
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_WHITE_TEXT = "\u001B[37m";
    public static final String ANSI_BLACK_TEXT = "\u001B[30m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Crear un objeto Scanner para leer la entrada del usuario

        String posicion = ""; // Variable para almacenar la posición ingresada por el usuario
        boolean posicionValida = false; // Bandera para verificar si la posición ingresada es válida

        // Bucle para solicitar una posición válida al usuario
        while (!posicionValida) {
            System.out.println("Introduce la posición inicial (e.g., e2):");
            posicion = sc.next().toLowerCase(); // Leer la posición y convertirla a minúsculas

            posicionValida = validarPosicion(posicion); // Validar la posición
            if (!posicionValida) {
                System.out.println("Posición inválida. Asegúrate de ingresar una posición entre a1 y h8.");
            }
        }

        int[] indicesPosicion = convertirPosicion(posicion); // Convertir la posición a índices de matriz

        // Calcular los posibles movimientos de la torre
        List<String> movimientos = calcularMovimientosTorre(indicesPosicion);
        System.out.println("Movimientos posibles para la torre desde " + posicion + ": " + movimientos);

        // Imprimir el tablero con la posición de la torre y sus movimientos posibles
        imprimirTablero(indicesPosicion, movimientos);
    }

    // Método para validar si la posición ingresada es válida (entre a1 y h8)
    public static boolean validarPosicion(String posicion) {
        if (posicion.length() != 2) {
            return false; // La posición debe tener exactamente 2 caracteres
        }

        char columna = posicion.charAt(0); // Extraer la columna
        char fila = posicion.charAt(1); // Extraer la fila

        // Validar que la columna esté entre 'a' y 'h'
        if (columna < 'a' || columna > 'h') {
            return false;
        }

        // Validar que la fila esté entre '1' y '8'
        if (fila < '1' || fila > '8') {
            return false;
        }

        return true; // La posición es válida
    }

    // Método para convertir la posición ingresada (e.g., e2) en índices de matriz
    public static int[] convertirPosicion(String posicion) {
        int[] indices = new int[2];
        char columna = posicion.charAt(0); // Extraer la columna
        char fila = posicion.charAt(1); // Extraer la fila

        int row = Character.getNumericValue(fila); // Convertir la fila a un entero

        indices[0] = 8 - row; // Convertir la fila (1-8) a índice de matriz (0-7)
        indices[1] = columna - 'a'; // Convertir la columna (a-h) a índice de matriz (0-7)
        return indices; // Devolver los índices de la matriz
    }

    // Método para convertir la columna de letra a número (e.g., 'a' -> 1, 'b' -> 2, ..., 'h' -> 8)
    public static int convertirColumna(char columna) {
        return columna - 'a' + 1;
    }

    // Método para calcular los posibles movimientos de la torre
    public static List<String> calcularMovimientosTorre(int[] posicion) {
        List<String> movimientos = new ArrayList<>();
        int fila = posicion[0];
        int columna = posicion[1];

        // Movimientos verticales
        for (int i = 0; i < 8; i++) {
            if (i != fila) { // Omitir la posición actual
                movimientos.add((char) ('a' + columna) + Integer.toString(8 - i));
            }
        }

        // Movimientos horizontales
        for (int j = 0; j < 8; j++) {
            if (j != columna) { // Omitir la posición actual
                movimientos.add((char) ('a' + j) + Integer.toString(8 - fila));
            }
        }

        return movimientos;
    }

    // Método para imprimir el tablero con la posición de la torre y sus movimientos posibles
    public static void imprimirTablero(int[] posicion, List<String> movimientos) {
        String[][] tablero = new String[8][8]; // Crear un tablero de 8x8

        // Llenar el tablero con casillas blancas y negras alternas
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    tablero[i][j] = ANSI_WHITE_BACKGROUND + ANSI_BLACK_TEXT + "   " + ANSI_RESET; // Casilla blanca
                } else {
                    tablero[i][j] = ANSI_BLACK_BACKGROUND + ANSI_WHITE_TEXT + "   " + ANSI_RESET; // Casilla negra
                }
            }
        }

        int fila = posicion[0]; // Fila de la posición ingresada
        int columna = posicion[1]; // Columna de la posición ingresada
        if ((fila + columna) % 2 == 0) {
            tablero[fila][columna] = ANSI_WHITE_BACKGROUND + ANSI_BLACK_TEXT + " ♖ " + ANSI_RESET; // Torre en casilla blanca
        } else {
            tablero[fila][columna] = ANSI_BLACK_BACKGROUND + ANSI_WHITE_TEXT + " ♖ " + ANSI_RESET; // Torre en casilla negra
        }

        // Marcar los movimientos posibles
        for (String movimiento : movimientos) {
            int[] posMov = convertirPosicion(movimiento);
            int filaMov = posMov[0];
            int columnaMov = posMov[1];
            if ((filaMov + columnaMov) % 2 == 0) {
                tablero[filaMov][columnaMov] = ANSI_WHITE_BACKGROUND + ANSI_BLACK_TEXT + " * " + ANSI_RESET; // Movimiento en casilla blanca
            } else {
                tablero[filaMov][columnaMov] = ANSI_BLACK_BACKGROUND + ANSI_WHITE_TEXT + " * " + ANSI_RESET; // Movimiento en casilla negra
            }
        }

        // Imprimir las letras de las columnas (a-h) en la parte superior del tablero
        System.out.println("Tablero de Ajedrez:");
        System.out.print("    ");
        for (char c = 'a'; c <= 'h'; c++) {
            System.out.print(" " + c + " ");
        }
        System.out.println();

        // Imprimir las filas del tablero con las posiciones marcadas
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + "  "); // Imprimir el número de la fila
            for (int j = 0; j < 8; j++) {
                System.out.print(tablero[i][j]); // Imprimir la casilla
            }
            System.out.println("  " + (8 - i)); // Imprimir el número de la fila al final
        }

        // Imprimir las letras de las columnas (a-h) en la parte inferior del tablero
        System.out.print("    ");
        for (char c = 'a'; c <= 'h'; c++) {
            System.out.print(" " + c + " ");
        }
        System.out.println();
    }
}
