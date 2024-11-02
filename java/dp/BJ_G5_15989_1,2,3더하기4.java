import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<T; i++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(calculate(num))
                    .append("\n");
        }

        System.out.println(sb);
    }

    static int calculate(int num) {
        int[][] dp = new int[4][10001];
        dp[1][1] = 1; // 1
        dp[1][2] = 2; // 1+1 / 2
        dp[2][2] = 1;
        dp[1][3] = 3; // 1+1+1 / 2+1 / 3
        dp[3][3] = 1;

        for (int i=4; i<=num; i++) {
            dp[1][i] += dp[1][i-1];
            dp[2][i] += dp[2][i-2] + dp[3][i-2];
            dp[3][i] += dp[3][i-3];

            dp[1][i] += dp[2][i] + dp[3][i];
        }
        return dp[1][num];
    }
}
