import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        try {
            inicio();
        } catch (java.util.InputMismatchException ex) { // Si se ingresan datos que no son compatibles con los solicitados:
            scan.nextLine(); // Se descarta la línea entera para evitar problemas
            main(args); // Entonces, se reinicia el programa
        }
    }

    static int tamanioGrafo;
    static Scanner scan = new Scanner(System.in);

    // Método que prepara toda la estructura del grafo e inicia el menú
    public static void inicio() throws java.util.InputMismatchException {
        SkyLink.inicializarGrafo();
        tamanioGrafo = SkyLink.tamanioGrafo(); // Una vez preparado el grafo, se guarda su tamaño
        primerMenu();
    }

    // Menú principal, se presentan las opciones más básicas
    public static void primerMenu() {
        clc();
        System.out.println("\n\033[33mBienvenidos al Menú de SkyLink, elija una de las siguientes opciones:");
        System.out.println("1 - Optimizar tiempo");
        System.out.println("2 - Optimizar precio");
        System.out.println("3 - Terminar Programa");
        System.out.println("4 - DevTest");
        int entrada = scan.nextInt();
        switch (entrada) {
            case 1:
                menuTiempo();
                break;
            case 2:
                menuPrecio();
                break;
            case 3:
                clc();
                System.exit(0); // Termina la ejecución del programa
                break;
            case 4:
                menuDev();
                break;
            default: // Entrada no admitida, reinicia el menú
                primerMenu();
                break;
        }
    }

    // Menú que se encarga de administrar el uso de la opción de optimización de tiempo de viaje
    public static void menuTiempo() {
        clc();
        System.out.println("\nIngrese las estaciones de partida y llegada:");
        mostrarEstaciones();
        int nodoInicial = scan.nextInt();
        int nodoObjetivo = scan.nextInt();
        if (nodoInicial < 0 || nodoInicial > tamanioGrafo || nodoObjetivo < 0 || nodoObjetivo > tamanioGrafo) {
            menuTiempo(); // Entradas fuera de los límites reinician el menú
        } else {
            SkyLink.optimizacionTiempo(nodoInicial, nodoObjetivo);
            back();
            primerMenu();
        }
    }

    // Menú que se encarga de administrar el uso de la opción de optmización de costo
    public static void menuPrecio() {
        clc();
        // Se define el tipo, el cual define el costo de cada abordo y transbordo
        System.out.println("\n¿Qué tipo de cliente es?");
        System.out.println("1 - Estándar");
        System.out.println("2 - Estudiante");
        System.out.println("3 - Adulto mayor");
        int type = scan.nextInt();
        if (type < 1 || type > 3) {
            menuPrecio(); // Una entrada no admitida reinicia el menú
        } else {
            SkyLink.setTipoCliente(type);
        }
        menuPrecioA(); // Una vez definido el tipo, se ejecuta el segundo menú de precio
    }

    // Menú encargado de seguir el proceso de optimización del costo
    public static void menuPrecioA() {
        clc();
        System.out.println("\nIngrese las estaciones de partida y llegada:");
        mostrarEstaciones();
        int nodoInicial = scan.nextInt();
        int nodoObjetivo = scan.nextInt();
        if (nodoInicial < 0 || nodoInicial > tamanioGrafo || nodoObjetivo < 0 || nodoObjetivo > tamanioGrafo) {
            menuPrecioA(); // Entradas fuera de los límites reinician el menú
        } else {
            SkyLink.optimizacionPrecio(nodoInicial, nodoObjetivo);
            back();
            primerMenu();
        }
    }

    // Menú para ver el estado del grafo, ayuda al desarrollo
    public static void menuDev() {
        clc();
        System.out.println("\nMenu de Desarrollo, elija una opción:");
        System.out.println("1 - Mostrar Grafo");
        System.out.println("2 - Mostrar Líneas");
        System.out.println("3 - Volver atrás");
        int entrada = scan.nextInt();
        switch (entrada) {
            case 1:
                clc();
                SkyLink.mostrarGrafo();
                back();
                menuDev();
                break;
            case 2:
                clc();
                SkyLink.verificarSetLineas();
                back();
                menuDev();
                break;
            case 3:
                primerMenu();
                break;
            default: // Entrada no admitida reinicia el menú
                menuDev();
                break;
        }
    }

    // Método que imprime los nodos con sus respectivas estaciones
    public static void mostrarEstaciones() {
        for (int i = 0; i < tamanioGrafo; i++) {
            System.out.println(i + " - " + SkyLink.estNomb(i));
        }
    }

    //Método empleado para regresar de un menú a otro
    public static void back() {
        System.out.println("\nIngrese cualquier caracter para volver");
        scan.next();
    }

    //Método que limpia la terminal
    public static void clc() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
