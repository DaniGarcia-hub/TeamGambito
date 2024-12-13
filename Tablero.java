public class Tablero {
    public static void main(String[] args) {
        final int FILAS = 8;
        final int COLUMNAS = 8;
        final char CUADROBLANCO = '\u25A1';
        final char CUADRONEGRO = '\u25A0';

        char[][] tablero = new char[FILAS][COLUMNAS];
        char color;


        for (int fila = FILAS - 1; fila >= 0; fila--) {
            System.out.print(fila + 1 + " ");
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (((fila + columna) % 2) != 0)
                    color = CUADROBLANCO;
                else
                    color = CUADRONEGRO;
                System.out.print(color + " ");
                tablero[fila][columna] = color;
            }
            System.out.println();
        }
        System.out.print("  ");
        for (char letra = 'a'; letra <= 'h'; letra++) {
            System.out.print(letra + " ");
        }
        System.out.println();
    }
}