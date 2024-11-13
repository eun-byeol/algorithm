import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];
        Arrays.fill(dp, 100001);
        dp[0] = 0;
        dp[1] = 1;

        for (int i=2; i<=N; i++) {
            int sqrt = (int) Math.sqrt(i);
            for (int j=sqrt; j>0; j--) {
                int pre = i - j * j;
                dp[i] = Math.min(dp[pre] + 1, dp[i]);
            }
        }
        System.out.println(dp[N]);
    }
}
/*

시간복잡도 : O(NlogN)
헷갈렸던 부분 - 그리디로 풀 수 없다! 항상 루트값을 선택하는 것이 최선이 아니다.

 */
