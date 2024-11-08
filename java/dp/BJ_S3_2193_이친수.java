import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.print(solve(N));
    }

    static long solve(int N) {
        long[][] dp = new long[N+1][2];
        dp[1][1] = 1;

        for (int i=2; i<=N; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-1][0];
        }

        return dp[N][0] + dp[N][1];
    }
}
