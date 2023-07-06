import java.util.*;
import java.lang.*;

class Solution {
	static int simulByDir(int[][] board, int r, int c, int N, int M, int[][] dir) {
		int total = board[r][c];
		for (int i=0; i<4; i++) {
			int nr = r;
			int nc = c;
			for (int j=0; j<M-1; j++) {
				nr += dir[i][0];
				nc += dir[i][1];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
					break;
				}
				total += board[nr][nc];
			}
		}
		return total;
	}
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		int[][] crossDir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		int[][] diagDir = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

		for(int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[][] board = new int[N][N];
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					board[i][j] = sc.nextInt();
				}
			}
			int ans = 0;
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					ans = Math.max(simulByDir(board, i, j, N, M, crossDir), ans);
					ans = Math.max(simulByDir(board, i, j, N, M, diagDir), ans);
				}
			}
			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
}
