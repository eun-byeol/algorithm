import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] A1 = br.readLine().toCharArray();
        char[] A2 = br.readLine().toCharArray();
        int N1 = A1.length;
        int N2 = A2.length;

        int[][] dp = new int[N1+1][N2+1];

        for (int i=1; i<=N1; i++) {
            for (int j=1; j<=N2; j++) {
                if (A1[i-1] == A2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    continue;
                }
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        System.out.println(dp[N1][N2]);
    }
}
/*

LCS(Longest Common Subsequence): 최장 공통 부분 수열 - O(N^2)
if (A[i] == B[j])
    LCS[i][j] = LCS[i-1][j-1] + 1
else
    LCS[i][j] = MAX(LCS[i][j-1], LCS[i-1][j])

--

cf) 최장 공통 문자열(Longest Common Substring)
if (A[i] == B[j])
    LCS[i][j] = LCS[i-1][j-1] + 1
else
    LCS[i][j] = 0

 */
