import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int[][] board;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for(int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            simul(N);
            
            sb.append("#")
            	.append(test_case)
            	.append("\n");
            
            for (int i=0; i<N; i++) {
            	for (int j=0; j<N; j++) {
            		sb.append(board[i][j]).append(" ");
            	}
            	sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

	private static void simul(int N) {
		int num = 1;
		int r = 0;
		int c = -1;
		int d = 0;
		while (num < N*N+1) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] != 0) {
				d = (d+1) % 4;
				continue;
			}
			board[nr][nc] = num++;
			r = nr;
			c = nc;
		}
	}
}
