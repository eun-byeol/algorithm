import java.io.*;
import java.util.*;

class Main {

    static int N, M, X;
    static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new List[N+1];
        for (int i=1; i<N+1; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[v1].add(new int[]{v2, e});
        }

        int max = 0;
        for (int i=1; i<N+1; i++) {
            int go = dijkstra(i, X);
            int back = dijkstra(X, i);
            max = Math.max(go + back, max);
        }

        System.out.println(max);
    }

    static int dijkstra(int start, int target) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{start, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.remove();
            int v = cur[0];
            int e = cur[1];

            for (int[] nxt : graph[v]) {
                int nv = nxt[0];
                int ne = nxt[1];
                if (dist[nv] > e + ne) {
                    dist[nv] = e + ne;
                    pq.add(new int[]{nv, e + ne});
                }
            }
        }
        return dist[target];
    }
}
