import java.util.*;
import java.io.*;

public class Main_1463_1로만들기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1] = 0;
		
		for (int i=2; i<N+1; i++) {
			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i/2] + 1);
			}
			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i/3] + 1);
			}
			dp[i] = Math.min(dp[i], dp[i-1] + 1);
		}
		
		System.out.println(dp[N]);
	}
}
