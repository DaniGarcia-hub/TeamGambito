import java.util.InputMismatchException;
import java.util.Scanner;

public class MovimientoFicha {

    static final int letrasAscii = 65; // Letra A.

    public static int[] convertirPosicion(String posicionInicial){
        char columnaInicial = posicionInicial.charAt(0);
        String filaInicial = String.valueOf(posicionInicial.charAt(1));

        int columnaConvertida = -1;
        int filaConvertida = (8 - Integer.parseInt(filaInicial)); // El -1 para que, si mete el 1, que para ajustar a las mediciones del tablero en Java.

        switch (columnaInicial){
            case 'A' -> columnaConvertida = 0;
            case 'B' -> columnaConvertida = 1;
            case 'C' -> columnaConvertida = 2;
            case 'D' -> columnaConvertida = 3;
            case 'E' -> columnaConvertida = 4;
            case 'F' -> columnaConvertida = 5;
            case 'G' -> columnaConvertida = 6;
            case 'H' -> columnaConvertida = 7;
        }

        int[] posicionConvertida = {filaConvertida, columnaConvertida};
        return posicionConvertida;
    }

    public static String[] recorrerDatosArray(String[] datos){
        String[] datosFinales = new String[datos.length];
        for (int i = 0; i < datos.length; i++){
            datosFinales[i] = datos[i];
        }

        // Recortar el array para eliminar posiciones vacías
        String[] movimientosFinales = new String[datos.length];
        System.arraycopy(datosFinales, 0, movimientosFinales, 0, datos.length);

        return datosFinales;
    }

    public static String[] torre(CrearFicha ficha, CrearTablero tablero){
        int[] posicionConvertida = convertirPosicion(ficha.getPosicionInicial());

        String[] movimientos = new String[14];

        int posicionOcupar = 0;

        // Movimientos por abajo.
        for (int i = 0; i < (tablero.getCantidadFilas() - posicionConvertida[0]) - 1; i++){
            movimientos[posicionOcupar] = Character.toString(letrasAscii+posicionConvertida[1]) + (i+1);
            posicionOcupar++;
        }

        // Movimientos por arriba.
        for (int i = (tablero.getCantidadFilas() - posicionConvertida[0]) + 1; i <= tablero.getCantidadFilas(); i++){
            movimientos[posicionOcupar] = Character.toString(letrasAscii+posicionConvertida[1]) + i;
            posicionOcupar++;
        }

        int asciiModificable = letrasAscii;

        //Movimientos por izquierda.
        for (int i = 0; i < posicionConvertida[1]; i++){
            movimientos[posicionOcupar] = Character.toString(asciiModificable) + ficha.getPosicionInicial().charAt(1);
            asciiModificable++;
            posicionOcupar++;
        }

        asciiModificable++; // Para saltarse la letra de la posicion inicial.

        //Movimientos por derecha.
        for (int i = tablero.getCantidadColumnas()-1; i > posicionConvertida[1]; i--){
            movimientos[posicionOcupar] = Character.toString(asciiModificable) + ficha.getPosicionInicial().charAt(1);
            asciiModificable++;
            posicionOcupar++;
        }

        return movimientos;
    }

    public static String[] alfil(CrearFicha ficha, CrearTablero tablero){
        int[] posicionConvertida = convertirPosicion(ficha.getPosicionInicial());

        String[] movimientos = new String[32];
        int posicionOcupar = 0;

        // Diagonal arriba-derecha
        for (int i = 1; posicionConvertida[0] - i >= 0 && posicionConvertida[1] + i < tablero.getCantidadColumnas(); i++) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + posicionConvertida[1] + i) + (8 - (posicionConvertida[0] - i));
            posicionOcupar++;
        }

        // Diagonal arriba-izquierda
        for (int i = 1; posicionConvertida[0] - i >= 0 && posicionConvertida[1] - i >= 0; i++) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + posicionConvertida[1] - i) + (8 - (posicionConvertida[0] - i));
            posicionOcupar++;
        }

        // Diagonal abajo-derecha
        for (int i = 1; posicionConvertida[0] + i < tablero.getCantidadFilas() && posicionConvertida[1] + i < tablero.getCantidadColumnas(); i++) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + posicionConvertida[1] + i) + (8 - (posicionConvertida[0] + i));
            posicionOcupar++;
        }

        // Diagonal abajo-izquierda
        for (int i = 1; posicionConvertida[0] + i < tablero.getCantidadFilas() && posicionConvertida[1] - i >= 0; i++) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + posicionConvertida[1] - i) + (8 - (posicionConvertida[0] + i));
            posicionOcupar++;
        }

        // Recortar el array para eliminar posiciones vacías
        String[] movimientosFinales = new String[posicionOcupar];
        System.arraycopy(movimientos, 0, movimientosFinales, 0, posicionOcupar);

        return movimientosFinales;
    }

    public static String[] dama(CrearFicha ficha, CrearTablero tablero) {
        String[] movimientosAlfil = alfil(ficha, tablero);
        String[] movimientosTorre = torre(ficha, tablero);

        String[] movimientos = new String[movimientosAlfil.length + movimientosTorre.length];

        System.arraycopy(movimientosTorre, 0, movimientos, 0, movimientosTorre.length);
        System.arraycopy(movimientosAlfil, 0, movimientos, movimientosTorre.length, movimientosAlfil.length);

        return movimientos;
    }

    public static String[] rey(CrearFicha ficha, CrearTablero tablero) {
        int[] posicionConvertida = convertirPosicion(ficha.getPosicionInicial());

        String[] movimientos = new String[8];
        int posicionOcupar = 0;

        // Hacia arriba
        if (posicionConvertida[0] > 0) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + posicionConvertida[1]) + (8 - (posicionConvertida[0] - 1));
            posicionOcupar++;
        }

        // Hacia abajo
        if (posicionConvertida[0] < tablero.getCantidadFilas() - 1) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + posicionConvertida[1]) + (8 - (posicionConvertida[0] + 1));
            posicionOcupar++;
        }

        // Hacia la izquierda
        if (posicionConvertida[1] > 0) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + (posicionConvertida[1] - 1)) + (8 - posicionConvertida[0]);
            posicionOcupar++;
        }

        // Hacia la derecha
        if (posicionConvertida[1] < tablero.getCantidadColumnas() - 1) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + (posicionConvertida[1] + 1)) + (8 - posicionConvertida[0]);
            posicionOcupar++;
        }

        // Hacia la diagonal arriba-izquierda
        if (posicionConvertida[0] > 0 && posicionConvertida[1] > 0) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + (posicionConvertida[1] - 1)) + (8 - (posicionConvertida[0] - 1));
            posicionOcupar++;
        }

        // Hacia la diagonal arriba-derecha
        if (posicionConvertida[0] > 0 && posicionConvertida[1] < tablero.getCantidadColumnas() - 1) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + (posicionConvertida[1] + 1)) + (8 - (posicionConvertida[0] - 1));
            posicionOcupar++;
        }

        // Hacia la diagonal abajo-izquierda
        if (posicionConvertida[0] < tablero.getCantidadFilas() - 1 && posicionConvertida[1] > 0) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + (posicionConvertida[1] - 1)) + (8 - (posicionConvertida[0] + 1));
            posicionOcupar++;
        }

        // Hacia la diagonal abajo-derecha
        if (posicionConvertida[0] < tablero.getCantidadFilas() - 1 && posicionConvertida[1] < tablero.getCantidadColumnas() - 1) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + (posicionConvertida[1] + 1)) + (8 - (posicionConvertida[0] + 1));
            posicionOcupar++;
        }

        // Recortar el array para eliminar posiciones vacías
        String[] movimientosFinales = new String[posicionOcupar];
        System.arraycopy(movimientos, 0, movimientosFinales, 0, posicionOcupar);

        return movimientosFinales;
    }

    public static String[] caballo(CrearFicha ficha, CrearTablero tablero) {
        int[] posicionConvertida = convertirPosicion(ficha.getPosicionInicial());

        String[] movimientos = new String[8];
        int posicionOcupar = 0;

        // Movimiento 1: 2 hacia adelante, 1 hacia la derecha
        if (posicionConvertida[0] + 2 < tablero.getCantidadFilas() && posicionConvertida[1] + 1 < tablero.getCantidadColumnas()) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + posicionConvertida[1] + 1) + (8 - (posicionConvertida[0] + 2));
            posicionOcupar++;
        }

        // Movimiento 2: 2 hacia adelante, 1 hacia la izquierda
        if (posicionConvertida[0] + 2 < tablero.getCantidadFilas() && posicionConvertida[1] - 1 >= 0) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + posicionConvertida[1] - 1) + (8 - (posicionConvertida[0] + 2));
            posicionOcupar++;
        }

        // Movimiento 3: 2 hacia atrás, 1 hacia la derecha
        if (posicionConvertida[0] - 2 >= 0 && posicionConvertida[1] + 1 < tablero.getCantidadColumnas()) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + posicionConvertida[1] + 1) + (8 - (posicionConvertida[0] - 2));
            posicionOcupar++;
        }

        // Movimiento 4: 2 hacia atrás, 1 hacia la izquierda
        if (posicionConvertida[0] - 2 >= 0 && posicionConvertida[1] - 1 >= 0) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + posicionConvertida[1] - 1) + (8 - (posicionConvertida[0] - 2));
            posicionOcupar++;
        }

        // Movimiento 5: 1 hacia adelante, 2 hacia la derecha
        if (posicionConvertida[0] + 1 < tablero.getCantidadFilas() && posicionConvertida[1] + 2 < tablero.getCantidadColumnas()) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + posicionConvertida[1] + 2) + (8 - (posicionConvertida[0] + 1));
            posicionOcupar++;
        }

        // Movimiento 6: 1 hacia adelante, 2 hacia la izquierda
        if (posicionConvertida[0] + 1 < tablero.getCantidadFilas() && posicionConvertida[1] - 2 >= 0) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + posicionConvertida[1] - 2) + (8 - (posicionConvertida[0] + 1));
            posicionOcupar++;
        }

        // Movimiento 7: 1 hacia atrás, 2 hacia la derecha
        if (posicionConvertida[0] - 1 >= 0 && posicionConvertida[1] + 2 < tablero.getCantidadColumnas()) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + posicionConvertida[1] + 2) + (8 - (posicionConvertida[0] - 1));
            posicionOcupar++;
        }

        // Movimiento 8: 1 hacia atrás, 2 hacia la izquierda
        if (posicionConvertida[0] - 1 >= 0 && posicionConvertida[1] - 2 >= 0) {
            movimientos[posicionOcupar] = Character.toString(letrasAscii + posicionConvertida[1] - 2) + (8 - (posicionConvertida[0] - 1));
            posicionOcupar++;
        }

        // Recortar el array para eliminar posiciones vacías
        String[] movimientosFinales = new String[posicionOcupar];
        System.arraycopy(movimientos, 0, movimientosFinales, 0, posicionOcupar);

        return movimientosFinales;
    }

    public static String[] peon (CrearFicha ficha, CrearTablero tablero){

        Scanner sc = new Scanner(System.in);

        int[] posicionConvertida = convertirPosicion(ficha.getPosicionInicial());
        int posicionOcupar = 0;

        String[] movimientos = new String[2];

        if (ficha.getColor().equals("Blanco")){
            if (posicionConvertida[0] == 0) {
                System.out.println("El peón ha promocionado.");
                Main.mostrarMenuPromocion();
                try {
                    int cambioFicha = sc.nextInt();
                    switch (cambioFicha) {
                        case 1:
                            ficha.setTipoFicha("Dama");
                            System.out.println("Has cambiado a la ficha: " + ficha.getTipoFicha());
                            return dama(ficha, tablero);
                        case 2:
                            ficha.setTipoFicha("Torre");
                            System.out.println("Has cambiado a la ficha: " + ficha.getTipoFicha());
                            return torre(ficha, tablero);
                        case 3:
                            ficha.setTipoFicha("Caballo");
                            System.out.println("Has cambiado a la ficha: " + ficha.getTipoFicha());
                            return caballo(ficha, tablero);
                        case 4:
                            ficha.setTipoFicha("Alfil");
                            System.out.println("Has cambiado a la ficha: " + ficha.getTipoFicha());
                            return alfil(ficha, tablero);
                        default:
                            System.err.println("ERROR. Introduce una opción válida.");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("ERROR: Solo puedes introducir los números indicados.");
                    sc.next();
                }
            } else {
                // Movimiento de una casilla hacia arriba
                if (posicionConvertida[0] > 0) {
                    movimientos[posicionOcupar] = Character.toString((char) (letrasAscii + posicionConvertida[1])) + (8 - (posicionConvertida[0] - 1));
                    if (movimientos[posicionOcupar].charAt(1) == 6){
                        System.out.println("Limite del tablero.");
                    }
                    posicionOcupar++;
                }
                // Movimiento de dos casillas hacia abajo si la posición inicial es la segunda fila del blanco
                if (posicionConvertida[0] == 6) {
                    movimientos[posicionOcupar] = Character.toString((char) (letrasAscii + posicionConvertida[1])) + (8 - (posicionConvertida[0] - 2));
                    posicionOcupar++;
                }

                // Recortar el array para eliminar posiciones vacías
                String[] movimientosFinales = new String[posicionOcupar];
                System.arraycopy(movimientos, 0, movimientosFinales, 0, posicionOcupar);

                return movimientosFinales;
            }
        } else
            if (posicionConvertida[0] == 7) {
                System.out.println("El peón ha promocionado.");
                Main.mostrarMenuPromocion();
                try {
                    int cambioFicha = sc.nextInt();
                    switch (cambioFicha) {
                        case 1:
                            ficha.setTipoFicha("Dama");
                            System.out.println("Has cambiado a la ficha: " + ficha.getTipoFicha());
                            return dama(ficha, tablero);
                        case 2:
                            ficha.setTipoFicha("Torre");
                            System.out.println("Has cambiado a la ficha: " + ficha.getTipoFicha());
                            return torre(ficha, tablero);
                        case 3:
                            ficha.setTipoFicha("Caballo");
                            System.out.println("Has cambiado a la ficha: " + ficha.getTipoFicha());
                            return caballo(ficha, tablero);
                        case 4:
                            ficha.setTipoFicha("Alfil");
                            System.out.println("Has cambiado a la ficha: " + ficha.getTipoFicha());
                            return alfil(ficha, tablero);
                        default:
                            System.err.println("ERROR. Introduce una opción válida.");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("ERROR: Solo puedes introducir los números indicados.");
                    sc.next();
                }
            } else {
                // Movimiento de una casilla hacia abajo
                if (posicionConvertida[0] < tablero.getCantidadFilas() - 1) {
                    movimientos[posicionOcupar] = Character.toString((char) (letrasAscii + posicionConvertida[1])) + (8 - (posicionConvertida[0] + 1));
                    if (movimientos[posicionOcupar].charAt(1) == 6){
                        System.out.println("Limite del tablero.");
                    }
                    posicionOcupar++;
                }
                // Movimiento de dos casillas hacia abajo si la posición inicial es la segunda fila del negro
                if (posicionConvertida[0] == 1) {
                    movimientos[posicionOcupar] = Character.toString((char) (letrasAscii + posicionConvertida[1])) + (8 - (posicionConvertida[0] + 2));
                    posicionOcupar++;
                }

                // Recortar el array para eliminar posiciones vacías
                String[] movimientosFinales = new String[posicionOcupar];
                System.arraycopy(movimientos, 0, movimientosFinales, 0, posicionOcupar);

                return movimientosFinales;
            }

            return movimientos;
    }
}