import java.util.*;

class Solution {
    int MOD = 1_000_000_007;
    
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1];
        
        for (int[] puddle : puddles) {
            if (puddle.length == 0) continue;
            dp[puddle[1]][puddle[0]] = -1;
        }
        
        dp[1][1] = 1;
        
        for (int i=1; i<n+1; i++) {
            for (int j=1; j<m+1; j++) {
                if (dp[i][j] == -1) continue;
                if (dp[i-1][j] != -1) {
                    dp[i][j] = (dp[i-1][j] + dp[i][j]) % MOD;
                }
                if (dp[i][j-1] != -1) {
                    dp[i][j] = (dp[i][j-1] + dp[i][j]) % MOD;
                }
            }
        }
        return dp[n][m] % MOD;
    }
}
// m과 n이 반대
// dp[i][j] = dp[i-1][j] + dp[i][j-1]
