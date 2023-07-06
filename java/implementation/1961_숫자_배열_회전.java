import java.util.*;

class Solution {
	
	static int[][] rotate(int[][] board, int N) {
		int[][] after = new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				after[i][j] = board[N-j-1][i];
			}
		}
		return after;
	}
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++){
			int N = sc.nextInt();
			int[][] board = new int[N][N];
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					board[i][j] = sc.nextInt();
				}
			}
			int[][] r90 = rotate(board, N);
			int[][] r180 = rotate(r90, N);
			int[][] r270 = rotate(r180, N);
			
			System.out.println("#" + test_case + " ");
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					System.out.print(r90[i][j]);
				}
				System.out.print(" ");
				for (int j=0; j<N; j++) {
					System.out.print(r180[i][j]);
				}
				System.out.print(" ");
				for (int j=0; j<N; j++) {
					System.out.print(r270[i][j]);
				}
				System.out.println();
			}
		}
	}
}
