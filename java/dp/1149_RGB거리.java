import java.util.*;
import java.io.*;

public class Main_1149_RGB거리 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[N+1][3]; // 빨 초 파
		
		for (int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int G = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + R;
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + G;
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + B;
		}
		
		int ans = dp[N][0];
		
		for (int i=1; i<3; i++) {
			if (dp[N][i] < ans) {
				ans = dp[N][i];
			}
		}
		
		System.out.println(ans);
	}
}
