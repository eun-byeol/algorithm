import java.io.*;
import java.util.*;

class Main {
    static int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N+1][10];

        for (int i=1; i<10; i++) {
            dp[1][i] = 1;
        }

        for (int i=2; i<=N; i++) {
            for (int j=0; j<10; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i-1][j+1];
                    continue;
                }
                if (j == 9) {
                    dp[i][j] = dp[i-1][j-1];
                    continue;
                }
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % MOD;
            }
        }
        int ans = 0;
        for (int cnt : dp[N]) {
            ans = (ans + cnt) % MOD;
        }
        System.out.println(ans);
    }
}
/*

시간복잡도 : O(N^2)
dp[i][j] : i번째 자리수의 j로 끝나는 계산 수 개수
- j = 2 ~ 8 ) dp[i][j] = dp[i-1][j-1] + dp[i][j+1]
- j = 0 ) dp[i][j] = dp[i-1][j+1]
- j = 9 ) dp[i][j] = dp[i-1][j-1]

--

N=1
1 2 3 4 5 6 7 8 9

N=2
12 23 34 45 56 67 78 89
21 32 43 54 65 76 87 98 10

 */
