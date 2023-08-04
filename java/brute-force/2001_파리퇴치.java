import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc < T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] board = new int[N+1][N+1];
			
			for (int i=1; i<N+1; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=1; j<N+1; j++) {
					board[i][j] = board[i][j-1] + Integer.parseInt(st.nextToken());
				}
			}
			
			int max_v = 0;
			
			for (int i=1; i<N-M+2; i++) {
				for (int j=1; j<N-M+2; j++) {
					int total = 0;
					for (int k=i; k<i+M; k++) {
						total += board[k][j+M-1] - board[k][j-1];
					}
					max_v = Math.max(total, max_v);
				}
			}
			System.out.printf("#%d %d\n", tc, max_v);
		}
	}
}
