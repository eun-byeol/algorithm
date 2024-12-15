import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());
    }

    static int solve() {
        int[][] dp = new int[N][M];
        dp[0][0] = map[0][0];

        // 첫줄
        for (int j=1; j<M; j++) {
            dp[0][j] = dp[0][j-1] + map[0][j];
        }

        for (int i=1; i<N; i++) {
            int[] right = new int[M];
            int[] left = new int[M];

            //오른쪽 아래
            right[0] = dp[i-1][0] + map[i][0];
            for (int j=1; j<M; j++) {
                right[j] = Math.max(dp[i-1][j], right[j-1]) + map[i][j];
            }

            //왼쪽 아래
            left[M-1] = dp[i-1][M-1] + map[i][M-1]; // N 아닌 M 유의!!
            for (int j=M-2; j>=0; j--) {
                left[j] = Math.max(dp[i-1][j], left[j+1]) + map[i][j];
            }

            for (int j=0; j<M; j++) {
                dp[i][j] = Math.max(right[j], left[j]);
            }
        }

        return dp[N-1][M-1];
    }
}
