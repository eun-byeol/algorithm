import java.util.*;
import java.lang.*;

class Solution {
	static int simulByCross(int[][] board, int r, int c, int N, int M) {
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		int total = board[r][c];
		for (int i=0; i<4; i++) {
			int nr = r;
			int nc = c;
			for (int j=0; j<M-1; j++) {
				nr += dr[i];
				nc += dc[i];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
					break;
				}
				total += board[nr][nc];
			}
		}
		return total;
	}
	
	static int simulByDiag(int[][] board, int r, int c, int N, int M) {
		int[] dr = {-1, -1, 1, 1};
		int[] dc = {-1, 1, 1, -1};
		int total = board[r][c];
		for (int i=0; i<4; i++) {
			int nr = r;
			int nc = c;
			for (int j=0; j<M-1; j++) {
				nr += dr[i];
				nc += dc[i];
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
					ans = Math.max(simulByCross(board, i, j, N, M), ans);
					ans = Math.max(simulByDiag(board, i, j, N, M), ans);
				}
			}
			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
}
