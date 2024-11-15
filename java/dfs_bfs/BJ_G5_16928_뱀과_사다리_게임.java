import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] ladder = new int[101];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            ladder[s] = t;
        }
        int[] snake = new int[101];
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            snake[s] = t;
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        int[] visited = new int[101];
        visited[1] = 0;

        int ans = 0;
        while(!q.isEmpty()) {
            int num = q.remove();
            if (num == 100) {
                ans = visited[100];
                break;
            }

            for (int i=1; i<=6; i++) {
                int nxt = num + i;

                if (nxt > 100 || visited[nxt] > 0) continue;

                if (ladder[nxt] > 0) {
                    nxt = ladder[nxt];
                }
                else if (snake[nxt] > 0) {
                    nxt = snake[nxt];
                }
                if (visited[nxt] == 0) {
                    q.add(nxt);
                    visited[nxt] = visited[num] + 1;
                }
            }
        }
        System.out.println(ans);
    }
}
