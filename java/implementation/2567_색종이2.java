import java.util.*;
import java.io.*;

public class Main {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] board = new int[101][101];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for (int r=x; r<x+10; r++) {
				for (int c=y; c<y+10; c++) {
					board[r][c] = 1;
				}
			}
		}
		
		System.out.println(check(board, N));
	}

	private static int check(int[][] board, int N) {
		int total = 0;
		for (int i=1; i<101; i++) {
			for (int j=1; j<101; j++) {
				if (board[i][j] == 1) {
					for (int d=0; d<4; d++) {
						if (board[i+dx[d]][j+dy[d]] == 0)
							total++;
					}
				}
			}
		}
		return total;
	}
}
