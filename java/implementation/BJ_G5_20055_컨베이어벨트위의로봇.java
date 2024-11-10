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

        while (true) {
            turn++;
            //1
            rotateA();

            if (A[N-1][1] > 0) { // 하차
                A[N-1][1] = 0;
            }

            // 2
            for (int i=N-2; i>=0; i--) {
                if (A[i][1] > 0 && A[i+1][1] == 0 && A[i+1][0] > 0) {
                    A[i+1][1] = 1;
                    A[i+1][0]--;
                    A[i][1] = 0;
                    if (A[i+1][0] == 0) {
                        K--;
                    }
                    if (A[N-1][1] > 0) { // 하차
                        A[N-1][1] = 0;
                    }
                }
            }
            //3
            if (A[0][0] > 0) {
                A[0][1] = 1;
                A[0][0]--;
                if (A[0][0] == 0) {
                    K--;
                }
            }
            if (K <= 0) {
                break;
            }
        }
        System.out.println(turn);
    }

    static void rotateA() {
        int d = A[M-1][0];
        int r = A[M-1][1];

        for (int i=M-2; i>=0; i--) {
            A[i+1][0] = A[i][0];
            A[i+1][1] = A[i][1];
        }
        A[0][0] = d;
        A[0][1] = r;
    }
}
