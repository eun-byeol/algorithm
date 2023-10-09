import java.io.*;
import java.util.*;

public class Solution_5643_키순서 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc=1; tc<=T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());

            List<Integer>[] in = new List[N+1];
            List<Integer>[] out = new List[N+1];
            for (int i=1; i<N+1; i++) {
                in[i] = new ArrayList<>();
                out[i] = new ArrayList<>();
            }

            for (int i=0; i<M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                in[v2].add(v1);
                out[v1].add(v2);
            }

            int ans = 0;
            for (int i=1; i<N+1; i++) {
                if (bfs(i, in, N) + bfs(i, out, N) == N-1) { // 자신의 키 위치 판별 기준 : 진입차수 + 진출차수 = N-1
                    ans++;
                }
            }

            sb.append("#")
                    .append(tc)
                    .append(" ")
                    .append(ans)
                    .append("\n");
        }
        System.out.print(sb);
    }

    private static int bfs(int start, List<Integer>[] graph, int N) {
        int degree = 0;
        int[] visited = new int[N+1];
        Queue<Integer> q = new ArrayDeque<>();
        visited[start] = 1;
        q.add(start);

        while (!q.isEmpty()) {
            int cur = q.remove();
            for (int nxt : graph[cur]) {
                if (visited[nxt] == 0) {
                    degree++;
                    visited[nxt] = 1;
                    q.add(nxt);
                }
            }
        }
        return degree;
    }
}
