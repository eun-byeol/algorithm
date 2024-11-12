import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(solve(N)).append("\n");
        }
        System.out.println(sb);
    }

    static long solve(int N) {
        if (N <= 2) {
            return 1;
        }
        long[] dp = new long[N+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i=3; i<=N; i++) {
            dp[i] = dp[i-3] + dp[i-2];
        }
        return dp[N];
    }
}
