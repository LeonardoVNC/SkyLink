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

    public static void optimizacionTiempo(int nodoInicial, int nodoObjetivo) {
        dist = new int[tamanioGrafo()];
        Arrays.fill(dist, INF);
        Comparator<int[]> comparator = (a, b) -> a[0] - b[0];
        PriorityQueue<int[]> pq = new PriorityQueue<>(comparator);
        int[] arr = { 0, nodoInicial };
        dist[nodoInicial] = 0;
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
                    int[] arr2 = { siguientePeso, siguienteNodo };
                    pq.add(arr2);
                }
            }
        }
        if (dist[nodoObjetivo] == INF) {
            System.out.println("No se puede llegar al nodo" + nodoObjetivo);
        } else {
            System.out.println("El tiempo mínimo para llegar al nodo " + nodoObjetivo + " es " + dist[nodoObjetivo] + " minutos uwu");
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

/*
 * class Edge {
 * int dest, weight;
 * 
 * public Edge(int dest, int weight) {
 * this.dest = dest;
 * this.weight = weight;
 * }
 * }
 * 
 * public class Main {
 * static final int INF = Integer.MAX_VALUE;
 * 
 * public static int dijkstra(int start, List<Edge>[] adj, int[] dist, int
 * target) {
 * PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e ->
 * e.weight));
 * pq.add(new Edge(start, 0));
 * dist[start] = 0;
 * 
 * while (!pq.isEmpty()) {
 * int current_node = pq.poll().dest;
 * int current_distance = dist[current_node];
 * 
 * if (current_node == target) {
 * return current_distance;
 * }
 * 
 * for (Edge edge : adj[current_node]) {
 * int next_node = edge.dest;
 * int weight = edge.weight;
 * int next_distance = current_distance + weight;
 * 
 * if (next_distance < dist[next_node]) {
 * dist[next_node] = next_distance;
 * pq.add(new Edge(next_node, next_distance));
 * }
 * }
 * }
 * 
 * return INF;
 * }
 * 
 * public static void main(String[] args) throws IOException {
 * BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 * PrintWriter out = new PrintWriter(System.out);
 * 
 * String[] line = br.readLine().split(" ");
 * int n = Integer.parseInt(line[0]);
 * int m = Integer.parseInt(line[1]);
 * 
 * List<Edge>[] adj = new ArrayList[n];
 * for (int i = 0; i < n; i++) {
 * adj[i] = new ArrayList<>();
 * }
 * 
 * int[] dist = new int[n];
 * Arrays.fill(dist, INF);
 * 
 * for (int i = 0; i < m; i++) {
 * line = br.readLine().split(" ");
 * int u = Integer.parseInt(line[0]);
 * int v = Integer.parseInt(line[1]);
 * int w = Integer.parseInt(line[2]);
 * 
 * adj[u].add(new Edge(v, w));
 * adj[v].add(new Edge(u, w));
 * }
 * 
 * int start = Integer.parseInt(br.readLine().trim());
 * 
 * int target = 4; // El nodo final es 4 según el ejemplo proporcionado
 * 
 * int finalCost = dijkstra(start, adj, dist, target);
 * 
 * if (finalCost == INF) {
 * out.println("No es posible llegar al nodo " + target);
 * } else {
 * out.println("Desde " + start + " deberíamos llegar a " + target +
 * " con un peso de " + finalCost);
 * }
 * 
 * out.flush();
 * }
 * }
 */