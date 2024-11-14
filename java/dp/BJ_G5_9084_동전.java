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

    static int solve(int N, int[] coins, int M) {
        int[] dp = new int[M+1];
        dp[0] = 1;

        for (int i=0; i<N; i++) {
            int coin = coins[i];
            for (int j=coin; j<=M; j++) {
                dp[j] += dp[j-coin];
            }
        }
        return dp[M];
    }
}
