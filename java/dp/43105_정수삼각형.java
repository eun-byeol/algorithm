import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int N = triangle.length;
        int[][] dp = new int[N][N];
        dp[0][0] = triangle[0][0];
        
        for (int i=0; i<N-1; i++) {
            for (int j=0; j<triangle[i].length; j++) {
                int parent = dp[i][j];
                int leftChild = triangle[i+1][j];
                int rightChild = triangle[i+1][j+1];
                
                dp[i+1][j] = Math.max(leftChild + parent, dp[i+1][j]);
                dp[i+1][j+1] = Math.max(rightChild + parent, dp[i+1][j+1]);
            }
        }
        for (int total : dp[N-1]) {
            answer = Math.max(total, answer);
        }
        return answer;
    }
}
