import java.util.*;

class Solution {
    int[][] matrix;
    int N;
    
    public int solution(int[][] matrix_sizes) {
        int answer = 0;
        matrix = matrix_sizes;
        N = matrix.length;
        
        int[][] dp = new int[N][N];
        for (int i=0; i<N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }
        
        for (int i = 0; i<N; i++) {
            for (int j=0; j<N-i; j++) {
                int a = j;
                int b = j + i;
                for (int k=a; k<b; k++) {
                    int tmp = dp[a][k] + dp[k+1][b] + (matrix[a][0] * matrix[k][1] * matrix[b][1]);
                    dp[a][b] = Math.min(dp[a][b], tmp);
                }
            }
        }
        return dp[0][N-1];
    }
}
