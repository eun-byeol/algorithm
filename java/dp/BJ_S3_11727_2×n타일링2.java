import java.io.*;
import java.util.*;

class Main {
    static int MOD = 10007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.print(solve(n));
    }

    static int solve(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 3;

        for (int i=3; i<=n; i++) {
            dp[i] = (dp[i-1] + dp[i-2] * 2) % MOD;
        }
        return dp[n];
    }
}
