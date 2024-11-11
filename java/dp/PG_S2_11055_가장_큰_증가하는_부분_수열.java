import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];

        for (int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];

        dp[0] = A[0];
        for (int i=1; i<N; i++) {
            dp[i] = A[i];
            for (int j=0; j<i; j++) {
                if (A[i] <= A[j]) {
                    continue;
                }
                dp[i] = Math.max(dp[j] + A[i], dp[i]);
            }
        }

        int max = 0;

        for (int total : dp) {
            max = Math.max(total, max);
        }

        System.out.println(max);
    }
}
/*

LIS(Longest Increasing Subsequence): 최장 증가 부분 수열 - O(N^2)
1. i 번째 원소까지의 LIS
2. i 번째 원소와 j 번째 원소 비교
    - A[i] <= A[j] 이면 건너뛰기
    - A[i] > A[j] 일 때만, MAX(LTS[j]+A[i], LTS[i]) 비교

 A[i] | 1  100  2  50  60  3  5  6  7  8
-----------------------------------------
i=1, j=0)
 LIS  | 1  MAX(100, 1+100=101)
i=2, j=0-1)
 LIS  | 1  101  MAX(2, 1+2=3)
i=3, j=0-2)
 LIS  | 1  101  3  MAX(50, 1+50=51, 3+50=53)
i=4, j=0-3)
 LIS  | 1  101  3  53  MAX(60, 1+60=61, 3+60=63, 53+60=113)
i=5, j=0-4)
 LIS  | 1  101  3  53  113  MAX(3, 1+3=4, 3+3=6)
i=6, j=0-5)
 LIS  | 1  101  3  53  113  6  MAX(5, 1+5=6, 3+5=8, 6+5=11)
i=7, j=0-6)
 LIS  | 1  101  3  53  113  6  11  MAX(6, 1+6=7, 3+6=9, 6+6=12, 11+6=17)
i=8, j=0-7)
 LIS  | 1  101  3  53  113  6  11  17  MAX(7, 1+7=8, 3+7=10, 6+7=13, 11+7=18, 17+7=24)
i=9, j=0-8)
 LIS  | 1  101  3  53  113  6  11  17  24  MAX(8, 1+8=9, 3+8=11, 6+8=14, 11+8=19, 17+8=25, 24+8=32)
---
LIS  | 1  101  3  53  (113)  6  11  17  24  32

 */
