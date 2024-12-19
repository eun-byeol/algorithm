import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        long[] minDp = calMin();

        for (int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            long min = minDp[num];
            String max = calMax(num);
            sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }

    static long[] calMin() {
        long[] dp = new long[101];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;
        dp[7] = 8;
        dp[8] = 10;
        String[] nums = {"", "", "1", "7", "4", "2", "0", "8"};

        for (int i=9; i<=100; i++) {
            for (int k=2; k<=7; k++) {
                String cur = dp[i-k] + nums[k];
                dp[i] = Math.min(Long.parseLong(cur), dp[i]);
            }
        }
        return dp;
    }

    static String calMax(int num) {
        int q = num / 2;
        int r = num - (q * 2);
        if (r == 1) {
            q--;
            r = 3;
        }
        if (r == 0) {
            return repeat("1", q);
        }
        return "7" + repeat("1", q);
    }
    
    static String repeat(String num, int cnt) {
        StringBuilder sb = new StringBuilder();
        while (cnt-- > 0) {
            sb.append(num);
        }
        return sb.toString();
    }
}
