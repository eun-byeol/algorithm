import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N+1];
        int[] P = new int[N+1];
        for (int i=1; i<N+1; i++) { // idx=2가 1번
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        long[] dp = new long[N+2];
        for (int i=N; i>0; i--) {
            int pre = i + T[i];
            if (pre <= N+1) {
                dp[i] = Math.max(dp[pre] + P[i], dp[i+1]);
                continue;
            }
            dp[i] = dp[i+1];
        }
        System.out.println(dp[1]);
    }
}
/*

시간복잡도: O(N)
- 핵심: top-down
- T[i] 가능 -> dp[i] = MAX(dp[i+T[i]] + P[i], dp[i+1])
- T[i] 불가능 -> dp[i] = dp[i+1]

i=10)
T  | 5  4  3  2  1  1  2  3  4  (5)
P  | 50 40 30 20 10 10 20 30 40 50
--
dp | 0  0  0  0  0  0  0  0  0  0

i=9)
T  | 5  4  3  2  1  1  2  3  (4) 5
P  | 50 40 30 20 10 10 20 30 40 50
--
dp | 0  0  0  0  0  0  0  0  0  0

i=8)
T  | 5  4  3  2  1  1  2  (3)  4  5
P  | 50 40 30 20 10 10 20 30 40 50
--
dp | 0  0  0  0  0  0  0  MAX(0+30, 0) 0 0

i=7)
T  | 5  4  3  2  1  1  (2)  3  4  5
P  | 50 40 30 20 10 10 20 30 40 50
--
dp | 0  0  0  0  0  0  MAX(0+20, 30) 30 0 0

i=6)
T  | 5  4  3  2  1  (1)  2  3  4  5
P  | 50 40 30 20 10 10 20 30 40 50
--
dp | 0  0  0  0  0  MAX(30+10, 30) 30 30 0 0

i=5)
T  | 5  4  3  2  (1)  1  2  3  4  5
P  | 50 40 30 20 10 10 20 30 40 50
--
dp | 0  0  0  0  MAX(40+10, 40) 40 30 30 0 0

i=4)
T  | 5  4  3  (2) 1  1  2  3  4  5
P  | 50 40 30 20 10 10 20 30 40 50
--
dp | 0  0  0  MAX(40+20, 50) 50 40 30 30 0 0

i=3)
T  | 5  4  (3) 2 1  1  2  3  4  5
P  | 50 40 30 20 10 10 20 30 40 50
--
dp | 0  0  MAX(40+30, 60) 60 50 40 30 30 0 0

i=2)
T  | 5  (4) 3 2 1  1  2  3  4  5
P  | 50 40 30 20 10 10 20 30 40 50
--
dp | 0  MAX(40+40, 70) 70 60 50 40 30 30 0 0

i=1)
T  | (5) 4 3 2 1  1  2  3  4  5
P  | 50 40 30 20 10 10 20 30 40 50
--
dp | MAX(40+50, 80) 80 70 60 50 40 30 30 0 0

-----
dp | (90) 80 70 60 50 40 30 30 0 0

 */
