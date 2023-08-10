import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int ans;
	static int N;
	static int[][] sngy;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			sngy = new int[N][N];
			
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					sngy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = Integer.MAX_VALUE;
			
			combi(0, 0, N/2, new int[N]);
				
			sb.append("#")
				.append(tc)
				.append(" ")
				.append(ans)
				.append("\n");
		}
		System.out.println(sb);
	}

	private static void combi(int depth, int at, int K, int[] share) {
		if (depth == K) {
			int rst = calculate(share);
			if (rst < ans)
				ans = rst;
			return;
		}
		
		for (int i=at; i<N; i++) {
			share[i] = 1;
			combi(depth+1, i+1, K, share);
			share[i] = 0;
		}
	}

	private static int calculate(int[] share) {
		int[] total = new int[2];
	
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (share[i] == share[j]) {
					total[share[i]] += sngy[i][j];
				}
			}
		}
		return Math.abs(total[0] - total[1]);
	}
}
