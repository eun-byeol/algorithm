import java.util.Scanner;

public class Solution {
	static char[] managers;
	static int[][] dp;
	static int N;
	static final int MOD = 1000000007;
	
	static int solve() {
		// 첫날
		for (int i=1; i<16; i++) {
			if ((i & 1) == 0)
				continue;
			if ((i & (1 << managers[0] - 'A')) == 0)
				continue;
			dp[0][i] = 1;
		}
		// 그 외
		for (int i=1; i<N; i++) {
			for (int j=1; j<16; j++) { // 오늘
				for (int l=1; l<16; l++) { // 어제 
					if ((j & (1 << managers[i] - 'A')) == 0)
						continue;
					if ((j & l) == 0)
						continue;
					dp[i][j] += dp[i-1][l];
					dp[i][j] %= MOD;
				}
			}
		}
		int ans = 0;
		for (int i=1; i<16; i++) {
			ans += dp[N-1][i];
			ans %= MOD;
		}
		return ans;
	}
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		sc.nextLine();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			managers = sc.nextLine().toCharArray();
			N = managers.length;
			dp = new int[N+1][16];
			
			int ans = solve();
			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
}
