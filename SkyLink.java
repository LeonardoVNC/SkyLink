import java.util.*;
import java.io.*;

public class SkyLink {
    static LinkedList<int[]>[] grafo;
    static Set<Integer>[] lineas;
    static int numLineas;

    public static int tamanioGrafo() {
        return grafo.length;
    }

    static int pAbordaje = 0;
    static int pTransbordo = 0;

    public static void setTipoCliente(int type) {
        if (type == 1) {
            pAbordaje = 300;
            pTransbordo = 200;
        } else if (type == 2 || type == 3) {
            pAbordaje = 150;
            pTransbordo = 100;
        }
    }

    public static String nombEstacion(int n) {
        switch (n) {
            case 0:
                return "Río Seco";
            case 1:
                return "UPEA";
            case 2:
                return "Plaza La Paz";
            case 3:
                return "Plaza La Libertad";
            case 4:
                return "16 de Julio";
            case 5:
                return "Cementerio";
            case 6:
                return "Central";
            case 7:
                return "Armentia";
            case 8:
                return "Periférico";
            case 9:
                return "Villarroel";
            case 10:
                return "Busch";
            case 11:
                return "Triangular";
            case 12:
                return "Del Poeta";
            case 13:
                return "Las Villas";
            case 14:
                return "Prado";
            case 15:
                return "Teatro al Aire Libre";
            case 16:
                return "Libertador";
            case 17:
                return "Alto Obrajes";
            case 18:
                return "Obrajes";
            case 19:
                return "Irpavi";
            case 20:
                return "Sopocachi";
            case 21:
                return "Buenos Aires";
            case 22:
                return "Mirador";
            case 23:
                return "6 de Marzo";
            case 24:
                return "Faro Murillo";
            case 25:
                return "Obelisco";
            default:
                return "Error en el id de la estación, verifique el metodo nombEstacion en la Clase SkyLink";
        }
    }

    public static void inicializarGrafo() {
        System.out.println("wiwi");
        String ruta = SkyLink.class.getResource("input.txt").getPath();
        File input = new File(ruta);
        try {
            Scanner scanTxt = new Scanner(input);
            while (scanTxt.hasNext()) {
                int nodos = scanTxt.nextInt();
                int aristas = scanTxt.nextInt();
                numLineas = scanTxt.nextInt();
                lineas = new HashSet[nodos];
                grafo = new LinkedList[nodos];
                for (int i = 0; i < nodos; i++) {
                    lineas[i] = new HashSet<>();
                    grafo[i] = new LinkedList<int[]>();
                }
                boolean ingresoDeLineas = true;
                while (ingresoDeLineas) {
                    int nodo = scanTxt.nextInt();
                    if (nodo == -1) {
                        ingresoDeLineas = false;
                    } else {
                        lineas[nodo].add(scanTxt.nextInt());
                    }
                }
                for (int i = 0; i < aristas; i++) {
                    int nodoA = scanTxt.nextInt();
                    int nodoB = scanTxt.nextInt();
                    int peso = scanTxt.nextInt();
                    int[] arr1 = { nodoB, peso };
                    int[] arr2 = { nodoA, peso };
                    grafo[nodoA].add(arr1);
                    grafo[nodoB].add(arr2);
                }
            }
            scanTxt.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Exception: " + ex.getMessage());
        }
    }

    static int INF = Integer.MAX_VALUE;
    static int[] dist;
    static int[] padreDijkstra;

    public static void optimizacionTiempo(int nodoInicial, int nodoObjetivo) {
        dist = new int[tamanioGrafo()];
        padreDijkstra = new int[tamanioGrafo()];
        Arrays.fill(dist, INF);
        Comparator<int[]> comparator = (a, b) -> a[0] - b[0];
        PriorityQueue<int[]> pq = new PriorityQueue<>(comparator);
        int[] arr = { 0, nodoInicial };
        dist[nodoInicial] = 0;
        padreDijkstra[nodoInicial] = -1;
        pq.add(arr);
        while (!pq.isEmpty()) {
            int pesoActual = pq.peek()[0];
            int nodoActual = pq.peek()[1];
            pq.poll();
            if (pesoActual > dist[nodoActual])
                continue;
            for (int i = 0; i < grafo[nodoActual].size(); i++) {
                int siguienteNodo = grafo[nodoActual].get(i)[0];
                int peso = grafo[nodoActual].get(i)[1];
                int siguientePeso = pesoActual + peso;
                if (siguientePeso < dist[siguienteNodo]) {
                    dist[siguienteNodo] = siguientePeso;
                    padreDijkstra[siguienteNodo] = nodoActual;
                    int[] arr2 = { siguientePeso, siguienteNodo };
                    pq.add(arr2);
                }
            }
        }
        if (dist[nodoObjetivo] == INF) {
            System.out.println("No se puede llegar de " + nombEstacion(nodoInicial) + " a " + nombEstacion(nodoObjetivo));
        } else {
            System.out.println(
                    "El tiempo mínimo para llegar a " + nombEstacion(nodoObjetivo) + " es " + dist[nodoObjetivo] + " minutos.");
            System.out.print("El recorrido esta conformado por las estaciones: " + nombEstacion(nodoObjetivo));
            int i = nodoObjetivo;
            while (padreDijkstra[i] != -1) {
                System.out.print(", " + nombEstacion(padreDijkstra[i]));
                i = padreDijkstra[i];
            }
        }
    }

    // TODO mala implementación de los transbordos
    public static void optimizacionPrecio(int nodoInicial, int nodoObjetivo) {
        if (nodoInicial == nodoObjetivo) {
            System.out.println("Ya te encuentras en tu destino, el precio a pagar es 0 Bs");
        } else {
            bfs(nodoInicial, nodoObjetivo);
        }
    }

    static int[] nivel;
    static boolean[] vis;
    static int[] padre;
    static int[] costo;

    public static void bfs(int nodoInicial, int nodoObjetivo) {
        Queue<Integer> kiwi = new LinkedList<>(); // Se instancia la cola de nodos a explorar
        vis = new boolean[tamanioGrafo()]; // Se instancia el arreglo de visitados
        padre = new int[tamanioGrafo()];
        costo = new int[tamanioGrafo()];

        int nodoAnterior = 0;
        int nodoActual = nodoInicial;
        kiwi.offer(nodoInicial); // Agregamos a la cola el nodo inicial
        padre[nodoInicial] = nodoInicial;
        costo[nodoInicial] = pAbordaje; // Establecemos el nivel 0 para el nodo inicial
        while (!kiwi.isEmpty()) { // Mientras la cola no se encuentre vacia
            int ultimaArista = tatiInt(nodoActual, nodoActual);
            nodoActual = kiwi.poll(); // Se empieza2 a explorar con el frente de la cola
            nodoAnterior = padre[nodoActual];
            System.out.println("Nodo anterior es: " + nodoAnterior + " y nodo actual es: " + nodoActual);

            // if (!vis[nodoActual]) { // Comprobamos que no se esté re visitando un nodo
            vis[nodoActual] = true; // Marcamos el nodo como visitado

            if (ultimaArista != tatiInt(nodoAnterior, nodoActual)) {
                costo[nodoActual] = costo[nodoAnterior] + pTransbordo;
            } else {
                costo[nodoActual] = costo[nodoAnterior];
            }
            ultimaArista = tatiInt(nodoAnterior, nodoActual);
            System.out.println("Precio del nodo actua: " + costo[nodoActual]);
            // System.out.println("Nodo Actual es " + nodoActual);
            for (int i = 0; i < grafo[nodoActual].size(); i++) { // Se guardan a todos los nodos relacionados
                int vecino = grafo[nodoActual].get(i)[0];
                if (!vis[vecino]) {
                    kiwi.offer(vecino); // Los nodos relacionados que no se hayan visitado se agregan a la cola
                    padre[vecino] = nodoActual;
                }
            }
            // }
        }
        System.out.println(costo[nodoObjetivo]);
    }

    public static boolean tatiBoolean(int nodoA, int nodoB) {
        for (int i = 0; i < numLineas; i++) {
            if (lineas[nodoA].contains(i) && lineas[nodoB].contains(i)) {
                return true;
            }
        }
        return false;
    }

    public static int tatiInt(int nodoA, int nodoB) { // pasamos de boolean a int y comprobamos si devuelve algo bien o
                                                      // no
        for (int i = 0; i < numLineas; i++) {
            if (lineas[nodoA].contains(i) && lineas[nodoB].contains(i)) {
                return i;
            } // TODO verifica la ultima conexion para ver si cambiamos la arista de linea o
              // no
        }
        return -1;
    }

    // TEST

    public static void verificarSetLineas() {
        for (int i = 0; i < 26; i++) {
            Integer[] arr = lineas[i].toArray(new Integer[0]);
            System.out.print("El nodo " + i + " pertenece a las líneas: ");
            mostrarArreglo(arr);
        }
    }

    public static void mostrarArreglo(Integer[] arr) {
        System.out.print("[" + arr[0]);
        for (int i = 1; i < arr.length; i++) {
            System.out.print("," + arr[i]);
        }
        System.out.println("]");
    }

    public static void mostrarArreglo(int[] arr) {
        System.out.print("[" + arr[0]);
        for (int i = 1; i < arr.length; i++) {
            System.out.print("," + arr[i]);
        }
        System.out.print("]");
    }

    public static void mostrarArreglo(boolean[] arr) {
        System.out.print("[" + arr[0]);
        for (int i = 1; i < arr.length; i++) {
            System.out.print("," + arr[i]);
        }
        System.out.print("]");
    }

    public static void mostrarGrafo() {
        for (int i = 0; i < grafo.length; i++) {
            System.out.print("Nodo " + i + ": ");
            for (int j = 0; j < grafo[i].size(); j++) {
                mostrarArreglo(grafo[i].get(j));
            }
            System.out.println();
        }
    }
}

