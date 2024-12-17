import java.util.ArrayList;
import java.util.List;

public class MovimientoFicha {


    public static int[] convertirPosicion(String posicionInicial) {
        int fila = 8 - Character.getNumericValue(posicionInicial.charAt(1));
        int columna = posicionInicial.charAt(0) - 'A';
        return new int[]{fila, columna};
    }


    public static List<String> calcularMovimientosCaballo(int[] posicionInicial) {
        List<String> movimientos = new ArrayList<>();
        int fila = posicionInicial[0];
        int columna = posicionInicial[1];


        int[][] movimientosPosibles = {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        for (int[] movimientoPosibles : movimientosPosibles) {
            int nuevaFila = fila + movimientoPosibles[0];
            int nuevaColumna = columna + movimientoPosibles[1];
            if (nuevaFila >= 0 && nuevaFila < 8 && nuevaColumna >= 0 && nuevaColumna < 8) {
                movimientos.add((char) ('A' + nuevaColumna) + Integer.toString(8 - nuevaFila));
            }
        }

        return movimientos;
    }


    public static List<String> calcularMovimientosTorre(int[] posicionInicial) {
        List<String> movimientos = new ArrayList<>();
        int fila = posicionInicial[0];
        int columna = posicionInicial[1];


        for (int nuevaFila = 0; nuevaFila < 8; nuevaFila++) {
            if (nuevaFila != fila) {
                movimientos.add((char) ('A' + columna) + Integer.toString(8 - nuevaFila));
            }
        }


        for (int nuevaColumna = 0; nuevaColumna < 8; nuevaColumna++) {
            if (nuevaColumna != columna) {
                movimientos.add((char) ('A' + nuevaColumna) + Integer.toString(8 - fila));
            }
        }

        return movimientos;
    }


    public static List<String> calcularMovimientos(String tipoFicha, int[] posicionInicial) {
        if (tipoFicha == null) return new ArrayList<>();

        switch (tipoFicha.toLowerCase()) {
            case "caballo":
                return calcularMovimientosCaballo(posicionInicial);
            case "torre":
                return calcularMovimientosTorre(posicionInicial);
            default:
                return new ArrayList<>();
        }
    }
}
