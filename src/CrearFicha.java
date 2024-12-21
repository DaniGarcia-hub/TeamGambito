import java.util.Arrays;

public class CrearFicha {
    private String tipoFicha;
    private String color;
    private String posicionInicial;

    public CrearFicha(String tipoFicha, String color, String posicionInicial) {
        this.tipoFicha = tipoFicha;
        this.color = color;
        this.posicionInicial = posicionInicial;
    }

    public String getTipoFicha() {
        return tipoFicha;
    }

    public void setTipoFicha(String tipoFicha) {
        this.tipoFicha = tipoFicha;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPosicionInicial() {
        return posicionInicial;
    }

    public void setPosicionInicial(String posicionInicial) {
        this.posicionInicial = posicionInicial;
    }

    @Override
    public String toString() {
        return "Información ficha: " +
                "[ Tipo de ficha: " + tipoFicha + " | " +
                "Color: " + color + " | " +
                "Posicion inicial: " + posicionInicial + " ]";
    }
}