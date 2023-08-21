import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int MAX_SIZE = 100001;
		int[] dp = new int[MAX_SIZE];
		
		Queue<Integer> q = new ArrayDeque<>();
		
		q.add(N);
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			if (cur == K) break;
			if (cur+1 < MAX_SIZE && dp[cur+1] == 0) {
				dp[cur+1] = dp[cur] + 1;
				q.add(cur+1);
			}
			if (cur-1 >= 0 && dp[cur-1] == 0) {
				dp[cur-1] = dp[cur] + 1;
				q.add(cur-1);
			}
			if (cur*2 < MAX_SIZE && dp[cur*2] == 0) {
				dp[cur*2] = dp[cur] + 1;
				q.add(cur*2);
			}
		}
		System.out.println(dp[K]);
	}
}
