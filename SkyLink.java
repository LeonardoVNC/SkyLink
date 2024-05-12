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

    public static String estNomb(int n) {
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
        dist = new int[grafo.length];
        padreDijkstra = new int[grafo.length];
        Arrays.fill(dist, INF);
        Comparator<int[]> comparator = (a, b) -> a[0] - b[0];
        PriorityQueue<int[]> pq = new PriorityQueue<>(comparator);
        dist[nodoInicial] = 0;
        padreDijkstra[nodoInicial] = -1;
        pq.add(new int[] { 0, nodoInicial });
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
                    pq.add(new int[] { siguientePeso, siguienteNodo });
                }
            }
        }
        if (dist[nodoObjetivo] == INF) {
            System.out.println("No se puede llegar de " + estNomb(nodoInicial) + " a " + estNomb(nodoObjetivo));
        } else {
            System.out.println("El tiempo mínimo para llegar a " + estNomb(nodoObjetivo) + " es " + dist[nodoObjetivo]
                    + " minutos.");
            System.out.print("El recorrido esta conformado por las estaciones: " + estNomb(nodoObjetivo));
            int i = nodoObjetivo;
            while (padreDijkstra[i] != -1) {
                System.out.print(", " + estNomb(padreDijkstra[i]));
                i = padreDijkstra[i];
            }
            System.out.println();
        }
    }

    static int[] nivel;
    static boolean[] vis;
    static int[] padre;

    public static void optimizacionPrecio(int nodoInicial, int nodoObjetivo) {
        if (nodoInicial == nodoObjetivo) {
            System.out.println("Ya te encuentras en tu destino, el precio a pagar es 0 Bs");
        } else {
            Queue<Integer> kiwi = new LinkedList<>();
            nivel = new int[grafo.length];
            vis = new boolean[grafo.length];
            kiwi.offer(nodoInicial);
            nivel[nodoInicial] = 1;
            padre = new int[grafo.length];
            boolean nodoEncontrado = false;
            while (!kiwi.isEmpty() && !nodoEncontrado) {
                int nodoActual = kiwi.poll();
                if (nodoActual == nodoObjetivo) {
                    nodoEncontrado = true;
                    vis[nodoActual] = true;
                    break;
                }
                if (!vis[nodoActual]) {
                    vis[nodoActual] = true;
                    for (int i = 0; i < grafo[nodoActual].size(); i++) {
                        int vecino = grafo[nodoActual].get(i)[0];
                        if (!vis[vecino]) {
                            kiwi.offer(vecino);
                            if (nivel[vecino] == 0) {
                                nivel[vecino] = nivel[nodoActual] + 1;
                                padre[vecino] = nodoActual;
                            }
                        }
                    }
                }
            }
            if (!vis[nodoObjetivo]) {
                System.out.println("No se puede llegar de  " + estNomb(nodoInicial) + " a " + estNomb(nodoObjetivo));
            } else {
                Set<Integer> set = new HashSet<>();
                System.out.print("El camino comprende las estaciones " + estNomb(nodoObjetivo));
                int nodoActual = nodoObjetivo;
                for (int i = 1; i < nivel[nodoObjetivo]; i++) {
                    set.add(mismaLinea(nodoActual, padre[nodoActual]));
                    nodoActual = padre[nodoActual];
                    System.out.print(", " + estNomb(nodoActual));
                }
                int n = set.size();
                int costo = 0;
                for (int i = 0; i < n; i++) {
                    if (i != 0) {
                        costo += pTransbordo;
                    } else {
                        costo += pAbordaje;
                    }
                }
                System.out.println(". Con un precio total de " + ((double) (costo) / 100) + "Bs");
            }
        }
    }

    public static int mismaLinea(int nodoA, int nodoB) {
        for (int i = 0; i < numLineas; i++) {
            if (lineas[nodoA].contains(i) && lineas[nodoB].contains(i)) {
                return i;
            }
        }
        return -1;
    }

    // TEST

    public static void verificarSetLineas() {
        for (int i = 0; i < grafo.length; i++) {
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
