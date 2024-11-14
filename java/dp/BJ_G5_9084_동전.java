import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc=0; tc<T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] coins = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i=0; i<N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int M = Integer.parseInt(br.readLine());

            sb.append(solve(N, coins, M)).append("\n");
        }
        System.out.println(sb);
    }

    static long solve(int N, int[] coins, int M) {
        long[][] dp = new long[N+1][M+1];
        for (int i=1; i<=N; i++) {
            dp[i][0] = 1;
        }

        for (int i=1; i<=N; i++) {
            int coin = coins[i-1];
            for (int j=1; j<=M; j++) { // coin부터 시작하면 1~coin 구간의 값이 누적되지 않기 때문에, 1부터 시작!
                if (j-coin >= 0) {
                    dp[i][j] += dp[i][j-coin];
                }
                dp[i][j] += dp[i-1][j];
            }
        }
        return dp[N][M];
    }
}
