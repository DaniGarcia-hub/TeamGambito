import java.util.InputMismatchException;
import java.util.Scanner;

public class menu {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ejecutarMenu();
    }

    public static void ejecutarMenu() {
        int opcion;
        boolean correcto = false;

        do {
            mostrarMenuInicial();

            try {
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("------------");
                        System.out.println("INFORMACIÓN");
                        System.out.println("------------");
                        correcto = true;
                        break;
                    case 2:
                        boolean salirMenuInicial = false;
                        do{
                            System.out.println("---------------");
                            System.out.println("INTRODUCE FICHA");
                            System.out.println("---------------");
                            System.out.println("Estos son los campos que tienes que rellenar:");
                            mostrarMenuFicha();
                            seleccionarFicha();
                            mostrarMenuColor();
                            seleccionarColor();

                            String respuesta;

                            do{
                                System.out.println("¿Quieres volver al menú principal?(S/N): ");
                                respuesta = sc.next().trim().toUpperCase();
                                if(respuesta.equals("S")){
                                    salirMenuInicial = true;

                                }else if(respuesta.equals("N")){
                                    System.out.println("Continúas en el programa.");
                                }else if(!respuesta.equals("S") && !respuesta.equals("N")){
                                    System.out.println("Error. Introduce una de las opciones válidas(S o N).");
                                }
                            }while(!respuesta.equals("S") && !respuesta.equals("N"));
                        }while(!salirMenuInicial);
                        break;
                    case 3:
                        System.out.println("Saliendo del programa...");
                        correcto = true;
                        break;
                    default:
                        System.out.println("Opción no válida. Introduce una opción correcta.");
                }
            } catch (InputMismatchException e) {
                System.out.println("En el programa solo puedes introducir números");
                sc.next();
            }
        } while (!correcto);
    }

    public static void mostrarMenuInicial() {
        System.out.println("1. Información");
        System.out.println("2. Introducir ficha");
        System.out.println("3. Salir");
        System.out.println("Introduce la opción:");
    }

    public static void mostrarMenuFicha() {
        System.out.println("*******");
        System.out.println("FICHAS");
        System.out.println("*******");
        System.out.print("");
        System.out.println("1. Rey");
        System.out.println("2. Dama");
        System.out.println("3. Torre");
        System.out.println("4. Caballo");
        System.out.println("5. Alfil");
        System.out.println("6. Peón");
        System.out.println("7. Salir");
        System.out.println("Elige la ficha/salir del submenú:");
    }

    public static void mostrarMenuColor() {
        System.out.println("*******");
        System.out.println("COLORES");
        System.out.println("*******");
        System.out.print("");
        System.out.println("1. Blanco");
        System.out.println("2. Negro");
        System.out.println("Elige el color de la ficha:");
    }

    public static void seleccionarFicha() {
        int ficha;
        try {
            ficha = sc.nextInt();
            switch (ficha) {
                case 1:
                    System.out.println("Has seleccionado: Rey");
                    break;
                case 2:
                    System.out.println("Has seleccionado: Dama");
                    break;
                case 3:
                    System.out.println("Has seleccionado: Torre");
                    break;
                case 4:
                    System.out.println("Has seleccionado: Caballo");
                    break;
                case 5:
                    System.out.println("Has seleccionado: Alfil");
                    break;
                case 6:
                    System.out.println("Has seleccionado: Peón");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor selecciona una opción válida.");
                    seleccionarFicha();
            }
        } catch (InputMismatchException e) {
            System.out.println("Solo puedes introducir números. Intenta nuevamente.");
            sc.next();
            seleccionarFicha();
        }
    }

    public static void seleccionarColor() {
        int color;
        try {
            color = sc.nextInt();
            switch (color) {
                case 1:
                    System.out.println("Has seleccionado: Blanco");
                    break;
                case 2:
                    System.out.println("Has seleccionado: Negro");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor selecciona una ficha válida.");
                    seleccionarColor();
            }
        } catch (InputMismatchException e) {
            System.out.println("Solo puedes introducir números. Intenta nuevamente.");
            sc.next();
            seleccionarColor();
        }
    }
}
