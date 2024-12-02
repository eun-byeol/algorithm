import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N+1];
        for (int i=1; i<N+1; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }

        int answer = dijkstra();
        System.out.println(answer);
    }

    static int dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        pq.add(new int[]{1, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.remove();
            int v = cur[0];
            int d = cur[1];

            if (v == N) {
                return d;
            }

            for (int[] nxt : graph[v]) {
                int node = nxt[0];
                int edge = nxt[1];

                if (d + edge < dist[node]) {
                    pq.add(new int[]{node, d + edge});
                    dist[node] = d + edge;
                }
            }
        }
        return dist[N];
    }
}
/*

1. 양방향 그래프 생성
2. 다익스트라 (1 -> N)

*/
