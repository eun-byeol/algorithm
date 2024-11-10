import java.io.*;
import java.util.*;

class Main {
    static int N, K, M;
    static int[][] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = 2 * N;
        A = new int[M][2];
        for (int i=0; i<M; i++) {
            A[i][0] = Integer.parseInt(st.nextToken());
        }

        int turn = 0;
        int in = 0;
        int out = N-1;

        while (true) {
            turn++;
            //1
            in = (in + M - 1) % M;
            out = (out + M - 1) % M;

            if (A[out][1] > 0) { // 하차
                A[out][1] = 0;
            }

            // 2
            for (int i=1; i<N; i++) {
                int cur = (out + M - i) % M;
                int nxt = (cur + 1) % M;
                if (A[cur][1] > 0 && A[nxt][1] == 0 && A[nxt][0] > 0) {
                    A[nxt][1] = 1;
                    A[nxt][0]--;
                    A[cur][1] = 0;
                    if (A[nxt][0] == 0) {
                        K--;
                    }
                    if (A[out][1] > 0) { // 하차
                        A[out][1] = 0;
                    }
                }
            }
            //3
            if (A[in][0] > 0) {
                A[in][1] = 1;
                A[in][0]--;
                if (A[in][0] == 0) {
                    K--;
                }
            }
            if (K <= 0) {
                break;
            }
        }
        System.out.println(turn);
    }
}
