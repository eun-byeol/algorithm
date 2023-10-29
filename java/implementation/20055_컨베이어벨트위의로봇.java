import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, M;
    static int[][] A;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = 2 * N;
        A = new int[M][2]; // 내구도, 로봇
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<2*N; i++) {
            A[i] = new int[] {Integer.parseInt(st.nextToken()), 0};
        }

        int step = 0;
        int zeroCnt = 0;

        while (zeroCnt < K) {
            step++;

            // 1
            rotate();
            unload();

            // 2
            move();
            unload();

            // 3
            load();

            // 4
            zeroCnt = countZero();
        }

        System.out.println(step);
    }

    private static int countZero() {
        int cnt = 0;
        for (int i=0; i<M; i++) {
            if (A[i][0] <= 0) {
                cnt++;
            }
        }
        return cnt;
    }

    private static void load() {
        if (A[0][0] > 0) {
            A[0][0]--;
            A[0][1] = 1;
        }
    }

    private static void move() {
        for (int i=N-2; i>-1; i--) {
            if (A[i][1] > 0 && A[i+1][0] > 0 && A[i+1][1] == 0) {
                A[i+1][0]--;
                A[i+1][1] = 1;
                A[i][1] = 0;
            }
        }
    }

    private static void unload() {
        A[N-1][1] = 0;
    }

    private static void rotate() {
        int[] tmp = A[M-1].clone();
        for (int i=M-2; i>-1; i--) {
            A[i+1][0] = A[i][0];
            A[i+1][1] = A[i][1];
        }
        A[0][0] = tmp[0];
        A[0][1] = tmp[1];
    }
}
