import java.util.Scanner;

public class Ajedrez {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String piezaSeleccionada = "";
        String colorSeleccionado = "";
        int opcion;

        Menu menu = new Menu();

        do {
            opcion = menu.mostrarMenu();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre de la pieza de ajedrez (Rey, Reina, Torre, Alfil, Caballo, Peón):");
                    String piezaInput = scanner.nextLine().trim().toLowerCase();

                    switch (piezaInput) {
                        case "rey":
                            piezaSeleccionada = "Rey";
                            break;
                        case "reina":
                            piezaSeleccionada = "Reina";
                            break;
                        case "torre":
                            piezaSeleccionada = "Torre";
                            break;
                        case "alfil":
                            piezaSeleccionada = "Alfil";
                            break;
                        case "caballo":
                            piezaSeleccionada = "Caballo";
                            break;
                        case "peón":
                            piezaSeleccionada = "Peón";
                            break;
                        default:
                            System.out.println("Pieza inválida. Intente nuevamente.");
                    }

                    if (!piezaSeleccionada.isEmpty()) {
                        System.out.println("Has seleccionado la pieza: " + piezaSeleccionada);
                    }
                    break;

                case 2:
                    System.out.println("Seleccione el color de la pieza (Blanco o Negro):");
                    String colorInput = scanner.nextLine().trim().toLowerCase();

                    switch (colorInput) {
                        case "blanco":
                            colorSeleccionado = "Blanco";
                            break;
                        case "negro":
                            colorSeleccionado = "Negro";
                            break;
                        default:
                            System.out.println("Color inválido. Intente nuevamente.");
                    }

                    if (!colorSeleccionado.isEmpty()) {
                        System.out.println("Has seleccionado el color: " + colorSeleccionado);
                    }
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 3);

        scanner.close();
    }
}
