package com.ody;

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
        dp[0] = 1;

        for (int i=1; i<N; i++) {
            dp[i] = 1;
            for (int j=0; j<i; j++) {
                if (A[i] <= A[j]) continue;
                dp[i] = Math.max(dp[j]+1, dp[i]);
            }
        }

        int max = 0;

        for (int cnt : dp) {
            max = Math.max(cnt, max);
        }

        System.out.println(max);
    }
}
/*

LTS: 최장 증가 부분 수열 - O(N^2)
1. i 번째 원소가 포함된 LTS
2. i 번째 원소와 j 번째 원소 비교
    - A[i] <= A[j] 이면 건너뛰기
    - A[i] > A[j] 일 때만, MAX(dp[j]+1, dp[i]) 비교

 A[i] | 10  20  10  30  20  50
-----------------------------------------
i=1, j=0)
 LIS  | 1  MAX(1, 1+1=2)
i=2, j=0-1)
 LIS  | 1  2  MAX(1)
i=3, j=0-2)
 LIS  | 1  2  1  MAX(1, 1+1=2, 2+1=3, 1+1=2)
i=4, j=0-3)
 LIS  | 1  2  1  3  MAX(1, 1+1=2, 1+1=2)
i=5, j=0-4)
 LIS  | 1  2  1  3  2  MAX(1, 1+1=2, 2+1=3, 1+1=2, 3+1=4, 2+1=3)
---
LIS  | 1  2  1  3  2  (4)

 */
