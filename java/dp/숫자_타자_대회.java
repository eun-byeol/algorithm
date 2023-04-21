import java.util.*;
class Solution {
    int[][][] dp;
    int N;
    char[] nums;
    int[][] weight = {
        { 1, 7, 6, 7, 5, 4, 5, 3, 2, 3 },
        { 7, 1, 2, 4, 2, 3, 5, 4, 5, 6 },
        { 6, 2, 1, 2, 3, 2, 3, 5, 4, 5 },
        { 7, 4, 2, 1, 5, 3, 2, 6, 5, 4 },
        { 5, 2, 3, 5, 1, 2, 4, 2, 3, 5 },
        { 4, 3, 2, 3, 2, 1, 2, 3, 2, 3 },
        { 5, 5, 3, 2, 4, 2, 1, 5, 3, 2 },
        { 3, 4, 5, 6, 2, 3, 5, 1, 2, 4 },
        { 2, 5, 4, 5, 3, 2, 3, 2, 1, 2 },
        { 3, 6, 5, 4, 5, 3, 2, 4, 2, 1 }
    };

    int dfs(int depth, int L, int R) {
        if (depth == N) {
            return 0;
        }
        if (dp[depth][L][R] != -1) {
            return dp[depth][L][R];
        }

        int num = nums[depth] - '0';
        int result = Integer.MAX_VALUE;

        if (num != R) {
            result = Math.min(dfs(depth+1, num, R) + weight[L][num], result);
        }

        if (num != L) {
            result = Math.min(dfs(depth+1, L, num) + weight[R][num], result);
        }
        return dp[depth][L][R] = result;
    }

    public int solution(String numbers) {
        nums = numbers.toCharArray();
        N = numbers.length();

        dp = new int[N + 1][10][10];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < 10; j++)
                Arrays.fill(dp[i][j], -1);
        }
        return dfs(0, 4, 6);
    }
}
