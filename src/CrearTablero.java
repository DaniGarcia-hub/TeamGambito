import java.util.Arrays;

public class CrearTablero {
    private int cantidadFilas;
    private int cantidadColumnas;
    String[][] tamanoTablero;

    public CrearTablero(int cantidadFilas, int cantidadColumnas) {
        this.cantidadFilas = cantidadFilas;
        this.cantidadColumnas = cantidadColumnas;

        tamanoTablero = new String[cantidadFilas][cantidadColumnas];
    }

    public int getCantidadFilas() {
        return cantidadFilas;
    }

    public void setCantidadFilas(int cantidadFilas) {
        this.cantidadFilas = cantidadFilas;
    }

    public int getCantidadColumnas() {
        return cantidadColumnas;
    }

    public void setCantidadColumnas(int cantidadColumnas) {
        this.cantidadColumnas = cantidadColumnas;
    }

    @Override
    public String toString() {
        return "Información tablero: " +
                "[ Cantidad filas: " + cantidadFilas  + " | " +
                "Cantidad columnas: " + cantidadColumnas + " | "  +
                "Tamaño tablero: " + cantidadFilas + " x " + cantidadColumnas + " ]";
    }
}
