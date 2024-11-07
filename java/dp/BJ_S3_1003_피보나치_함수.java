import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc <T; tc++) {
            int N = Integer.parseInt(br.readLine());
            calculate(N, sb);
        }
        System.out.print(sb);
    }
    static void calculate(int N, StringBuilder sb) {
        int[][] dp = new int[2][N+1];
        dp[0][0] = 1;
        if (N > 0) {
            dp[1][1] = 1;
        }

        for (int num=2; num<=N; num++) {
            dp[0][num] = dp[0][num-1] + dp[0][num-2];
            dp[1][num] = dp[1][num-1] + dp[1][num-2];
        }
        sb.append(dp[0][N])
                .append(" ")
                .append(dp[1][N])
                .append("\n");
    }
}
