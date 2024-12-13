import java.util.Scanner;

public class Menu {

    public int mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("========================================");
        System.out.println("          MOVIMIENTOS DE AJEDREZ");
        System.out.println("========================================");
        System.out.println("1. Seleccionar una pieza de ajedrez");
        System.out.println("2. Seleccionar el color de la pieza");
        System.out.println("3. Salir");
        System.out.println("========================================");
        System.out.print("Ingrese su opción: ");
        opcion = scanner.nextInt();
        scanner.nextLine();

        return opcion;
    }
}
