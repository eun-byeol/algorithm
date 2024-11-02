import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int K = Integer.parseInt(line[1]);

        int min = bfs(N, K);

        System.out.println(min);
    }

    static int bfs(int N, int K) {
        int min = Math.abs(K - N);
        int M = Math.max(N, K) * 2;
        int[] dist = new int[M];
        Arrays.fill(dist, 100000);

        Queue<Integer> q = new ArrayDeque<>();
        dist[N] = 0;
        q.add(N);

        while (!q.isEmpty()) {
            int n = q.remove();
            int d = dist[n];

            if (min <= d) continue;

            if (2*n < M && dist[2*n] > d) {
                dist[2*n] = d;
                q.add(2*n);
            }

            if (n-1 > 0 && dist[n-1] > d+1) {
                dist[n-1] = d+1;
                q.add(n-1);
            }

            if (n+1 < M && dist[n+1] > d+1) {
                dist[n+1] = d+1;
                q.add(n+1);
            }

            min = Math.min(min, dist[K]);
        }
        return min;
    }
}
