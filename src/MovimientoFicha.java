public class MovimientoFicha {

    public static int[] convertirPosicion(String posicionInicial) {
        int fila = 8 - Character.getNumericValue(posicionInicial.charAt(1));
        int columna = posicionInicial.charAt(0) - 'A';
        return new int[]{fila, columna};
    }

    public static String[] calcularMovimientosCaballo(int[] posicionInicial) {
        String[] movimientos = new String[8];
        int fila = posicionInicial[0];
        int columna = posicionInicial[1];

        int[][] movimientosPosibles = {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        int index = 0;
        for (int[] movimientoPosible : movimientosPosibles) {
            int nuevaFila = fila + movimientoPosible[0];
            int nuevaColumna = columna + movimientoPosible[1];
            if (nuevaFila >= 0 && nuevaFila < 8 && nuevaColumna >= 0 && nuevaColumna < 8) {
                movimientos[index++] = (char) ('A' + nuevaColumna) + Integer.toString(8 - nuevaFila);
            }
        }

        return movimientos;
    }

    public static String[] calcularMovimientosTorre(int[] posicionInicial) {
        String[] movimientos = new String[14];
        int fila = posicionInicial[0];
        int columna = posicionInicial[1];

        int index = 0;

        for (int nuevaFila = 0; nuevaFila < 8; nuevaFila++) {
            if (nuevaFila != fila) {
                movimientos[index++] = (char) ('A' + columna) + Integer.toString(8 - nuevaFila);
            }
        }


        for (int nuevaColumna = 0; nuevaColumna < 8; nuevaColumna++) {
            if (nuevaColumna != columna) {
                movimientos[index++] = (char) ('A' + nuevaColumna) + Integer.toString(8 - fila);
            }
        }

        return movimientos;
    }

    public static String[] calcularMovimientosDama(int[] posicionInicial) {
        String[] movimientos = new String[27];
        int fila = posicionInicial[0];
        int columna = posicionInicial[1];

        int index = 0;

        for (int i = 1; i < 8; i++) {
            if (fila - i >= 0 && columna + i < 8) {
                movimientos[index++] = (char) ('A' + columna + i) + Integer.toString(8 - fila + i);
            }
            if (fila - i >= 0 && columna - i >= 0) {
                movimientos[index++] = (char) ('A' + columna - i) + Integer.toString(8 - fila + i);
            }
            if (fila + i < 8 && columna + i < 8) {
                movimientos[index++] = (char) ('A' + columna + i) + Integer.toString(8 - fila - i);
            }
            if (fila + i < 8 && columna - i >= 0) {
                movimientos[index++] = (char) ('A' + columna - i) + Integer.toString(8 - fila - i);
            }
        }

        for (int nuevaFila = 0; nuevaFila < 8; nuevaFila++) {
            if (nuevaFila != fila) {
                movimientos[index++] = (char) ('A' + columna) + Integer.toString(8 - nuevaFila);
            }
        }

        for (int nuevaColumna = 0; nuevaColumna < 8; nuevaColumna++) {
            if (nuevaColumna != columna) {
                movimientos[index++] = (char) ('A' + nuevaColumna) + Integer.toString(8 - fila);
            }
        }

        return movimientos;
    }

    public static String[] calcularMovimientosAlfil(int[] posicionInicial) {
        String[] movimientos = new String[27];
        int fila = posicionInicial[0];
        int columna = posicionInicial[1];

        int index = 0;

        for (int i = 1; i < 8; i++) {
            if (fila - i >= 0 && columna + i < 8) {
                movimientos[index++] = (char) ('A' + columna + i) + Integer.toString(8 - fila + i);
            }
            if (fila - i >= 0 && columna - i >= 0) {
                movimientos[index++] = (char) ('A' + columna - i) + Integer.toString(8 - fila + i);
            }
            if (fila + i < 8 && columna + i < 8) {
                movimientos[index++] = (char) ('A' + columna + i) + Integer.toString(8 - fila - i);
            }
            if (fila + i < 8 && columna - i >= 0) {
                movimientos[index++] = (char) ('A' + columna - i) + Integer.toString(8 - fila - i);
            }
        }

        return movimientos;
    }
    
    public static String[] calcularMovimientosRey(int[] posicionInicial){
        String[] movimientos = new String[8];
        int fila = posicionInicial[0];
        int columna = posicionInicial[1];
        int index = 0;

        int[][] movimientosPosibles = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1},
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        for (int[] movimientoPosible : movimientosPosibles){
            int nuevaFila = fila+movimientoPosible[0];
            int nuevaColumna = columna+movimientoPosible[1];
            if(nuevaFila >= 0 && nuevaFila<8 && nuevaColumna>=0 && nuevaColumna<8){
                movimientos[index++]=(char)('A'+ nuevaColumna)+Integer.toString(8-nuevaFila);
            }
        }
        return movimientos;
    }

    public static String[] calcularMovimientos(String tipoFicha, int[] posicionInicial) {
        if (tipoFicha == null) return new String[0];

        switch (tipoFicha.toLowerCase()) {
            case "caballo":
                return calcularMovimientosCaballo(posicionInicial);
            case "torre":
                return calcularMovimientosTorre(posicionInicial);
            case "dama":
                return calcularMovimientosDama(posicionInicial);
            case "alfil":
                return calcularMovimientosAlfil(posicionInicial);
            case "rey":
                return calcularMovimientosRey(posicionInicial);
            default:
                return new String[0];
        }
    }
}
