import java.util.*;

public class Main {
    private static Scanner sc =new Scanner(System.in);
    private static Edge[] edges;
    private static int INF = 0x3f3f3f3f;
    private static int n;
    private static int m;
    private static Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
    public static void main(String[] args) throws Exception {
        n = sc.nextInt();
        m = sc.nextInt();
        edges = new Edge[m];
        for(int i = 0; i < m ; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int z = sc.nextInt();
            Map<Integer, Integer> map = graph.getOrDefault(a, new HashMap<>());
            map.put(b, Math.min(z, map.getOrDefault(b, INF)));
            graph.put(a, map);
            edges[i] = new Edge(a, b, z);
        }

        boolean res = spfa(1);
        if(res) System.out.println("Yes");
        else System.out.println("No");
    }

    private static boolean spfa(int src) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[src] = 0;
        Queue<Integer> que = new LinkedList<>();
        que.offer(src);
        int[] count = new int[n + 1];
        while(!que.isEmpty()) {
            int size = que.size();
            for(int k = 0; k < size; k++) {
                int a = que.poll();
                for(Map.Entry<Integer, Integer> entry : graph.getOrDefault(a, new HashMap<>()).entrySet()) {
                    int b = entry.getKey();
                    int z = entry.getValue();
                    if(dist[a] != INF && dist[b] > dist[a] + z) {
                        count[b]++;
                        if(count[b] > n) return true;
                        dist[b] = dist[a] + z;
                        que.offer(b);
                    }
                }
            }
        }
        return false;
    }
}

class Edge {
    int a;
    int b;
    int z;
    public Edge (int a, int b, int z) {
        this.a = a;
        this.b = b;
        this.z = z;
    }
}