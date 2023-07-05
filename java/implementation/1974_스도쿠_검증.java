import java.util.*;

class Solution {
	
	static int solve(int[][] board) {
		int[][] d = {{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};
		
		for (int i=0; i<9; i++) {
			Set<Integer> row = new HashSet<>();
			Set<Integer> col = new HashSet<>();
			for (int j=0; j<9; j++) {
				row.add(board[i][j]);
				col.add(board[j][i]);
			}
			if (row.size() != 9 || col.size() != 9) {
				return 0;
			}
		}
		
		for (int i=0; i<9; i+=3) {
			for (int j=0; j<9; j+=3) {
				Set<Integer> square = new HashSet<>();
				for (int k=0; k<9; k++) {
					square.add(board[i+d[k][0]][j+d[k][1]]);
				}
				if (square.size() != 9) {
					return 0;
				}
			}
		}
		return 1;
	}
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++){
			int[][] board = new int[9][9];
			for (int i=0; i<9; i++) {
				for (int j=0; j<9; j++) {
					board[i][j] = sc.nextInt();
				}
			}
			
			int ans = solve(board);
			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
}
