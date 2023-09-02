import java.io.*;
import java.util.*;

public class Main_1010_다리놓기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] dp = new int[M+1][N+1];
			
			sb.append(combi(M, N, dp))
				.append("\n");
		}
		
		System.out.println(sb);
	}

	private static int combi(int M, int N, int[][] dp) {
		if (N == M || N == 0) { // 1대1, 서쪽에 사이트가 없는 경우
			return 1;
		}
		
		if (dp[M][N] > 0) { // 이미 계산했을 경우 
			return dp[M][N];
		}
	
		return dp[M][N] = combi(M-1, N, dp) + combi(M-1, N-1, dp); // M-1에서 N개를 모두 선택하는 경우 + M-1에서 N-1개만 선택하는 경우(마지막 N이 M을 선택)
	}

}
