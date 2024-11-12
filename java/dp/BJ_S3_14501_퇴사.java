import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N+2];
        int[] P = new int[N+2];
        for (int i=2; i<N+2; i++) { // idx=2가 1번
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N+2];
        for (int i=2; i<N+2; i++) {
            for (int j=1; j<=i; j++) {
                if (T[j] > i - j + 1) continue;
                dp[i] = Math.max(dp[j-1] + P[j], dp[i]); // 첫번째 dp[-1]을 피하기 위해 인덱스 2부터 시작
            }
        }
        System.out.println(dp[N+1]);
    }
}
/*

시간복잡도: O(N^2)
i일차의 최대 수익을 계산하기 위해, j일차(1일차 ~ i일차)를 순회하며 비교하여 갱신
- i일차 전에 T[j]를 끝낼 수 있으면, dp[i] = MAX(dp[j-1] + P[j], dp[i])

1일차)
T  | 3
P  | 10
dp | 0

2일차)
T  | 3  5
P  | 10 20
dp | 0  0

3일차)
T  |(3) 5 (1)
P  | 10 20 10
dp | 0  0  MAX(0+10, 0+10)

4일차)
T  | 3  5  1  1
P  | 10 20 10 20
dp | 0  0  10 MAX(0+10, 0+10, 10+20)

5일차)
T  | 3  5  1  1  2
P  | 10 20 10 20 15
dp | 0  0  10 30 MAX(0+10, 0+10, 10+20)

6일차)
T  | 3  5  1  1  2  4
P  | 10 20 10 20 15 40
dp | 0  0  10 30 30 MAX(0+10, 0+10, 10+20, 30+15)

6일차)
T  | 3  5  1  1  2  4  2
P  | 10 20 10 20 15 40 200
dp | 0  0  10 30 30 45 MAX(0+10, 0+10, 10+20, 30+15)

--

dp | 0  0  10  30  30  45  (45)

 */
