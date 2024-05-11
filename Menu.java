import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        try {
            inicio();
        } catch (java.util.InputMismatchException ex) {
            scan.nextLine();
            main(args);
        }
    }

    static int tamanioGrafo = 0;
    static Scanner scan = new Scanner(System.in);

    public static void inicio() throws java.util.InputMismatchException {
        SkyLink.inicializarGrafo();
        tamanioGrafo = SkyLink.tamanioGrafo();
        primerMenu();
    }

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
                System.exit(0);
                break;
            case 4:
                menuDev();
                break;
            default:
                System.out.println("Entrada no admitida");
                primerMenu();
                break;
        }
    }

    public static void menuTiempo() {
        clc();
        System.out.println("\nIngrese las estaciones de partida y llegada:");
        mostrarEstaciones();
        int nodoInicial = scan.nextInt();
        int nodoObjetivo = scan.nextInt();
        if (nodoInicial < 0 || nodoInicial > tamanioGrafo || nodoObjetivo < 0 || nodoObjetivo > tamanioGrafo) {
            menuTiempo();
        } else {
            SkyLink.optimizacionTiempo(nodoInicial, nodoObjetivo);
            back();
            primerMenu();
        }
    }

    public static void menuPrecio() {
        clc();
        System.out.println("\n¿Qué tipo de cliente es?");
        System.out.println("1 - Estándar");
        System.out.println("2 - Estudiante");
        System.out.println("3 - Adulto mayor");
        int type = scan.nextInt();
        if (type < 1 || type > 3) {
            menuPrecio();
        } else {
            SkyLink.setTipoCliente(type);
        }
        menuPrecioA();
    }

    public static void menuPrecioA() {
        clc();
        System.out.println("\nIngrese las estaciones de partida y llegada:");
        mostrarEstaciones();
        int nodoInicial = scan.nextInt();
        int nodoObjetivo = scan.nextInt();
        if (nodoInicial < 0 || nodoInicial > tamanioGrafo || nodoObjetivo < 0 || nodoObjetivo > tamanioGrafo) {
            menuPrecioA();
        } else {
            SkyLink.optimizacionPrecio(nodoInicial, nodoObjetivo);
            back();
            primerMenu();
        }
    }

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
            default:
                System.out.println("Entrada no admitida");
                menuDev();
                break;
        }
    }

    public static void mostrarEstaciones() {
        System.out.println(
                "0 - Río Seco\n1 - UPEA\n2 - Plaza La Paz\n3 - Plaza La Libertad\n4 - 16 de Julio\n5 - Cementerio\n6 - Central\n7 - Armentia\n8 - Periférico\n9 - Villarroel\n10 - Busch\n11 - Triangular\n12 - Del Poeta\n13 - Las Villas\n14 - Prado\n15 - Teatro al Aire Libre\n16 - Libertador\n17 - Alto Obrajes\n18 - Obrajes\n19 - Irpavi\n20 - Sopocachi\n21 - Buenos Aires\n22 - Mirador\n23 - 6 de Marzo\n24 - Faro Murillo\n25 - Obelisco");
    }

    public static void back() {
        System.out.println("\nIngrese cualquier caracter para volver");
        scan.next();
    }

    public static void clc() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
