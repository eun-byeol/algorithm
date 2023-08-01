import java.io.*;
import java.util.*;

public class Solution {
	static final int N = 100;
	static int[][] board = new int[N][N];
	static int[] dx = {0, 0, -1};
	static int[] dy = {-1, 1, 0};
	
	static int simul(int x, int y) {
		while (x > 0) {
			for (int i=0; i<3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (board[nx][ny] == 0) continue;
				board[nx][ny] = 0;
				x = nx;
				y = ny;
				break;
			}
		}
		return y;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int tc =Integer.parseInt(br.readLine()); //입력
			
			int targetX = 0;
			int targetY = 0;
			
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (board[i][j] == 2) {
						targetX = i;
						targetY = j;
					}
				}
			}
			
			int ans = simul(targetX, targetY);
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
}
