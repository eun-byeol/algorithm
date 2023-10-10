import java.io.*;
import java.util.*;

public class Solution_4013_특이한자석 {
	static int[][] board;
	static int N = 4;
	static int M = 8;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			int K = Integer.parseInt(br.readLine());
			board = new int[N][M];
			
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<M; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i=0; i<K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				int[] dir = new int[N];
				dir[num-1] = d;
				checkDir(num-1, dir);
				
				for (int j=0; j<N; j++) {
					if (dir[j] != 0) {
						rotate(j, dir[j]);
					}
				}
			}
			
			int score = 0;
			for (int i=0; i<N; i++) {
				if (board[i][0] == 1) {
					score += Math.pow(2, i);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(score).append("\n");
		}
		System.out.print(sb);
	}

	private static void checkDir(int i, int[] dir) {
		if (i+1 < N && dir[i+1] == 0 && board[i][2] != board[i+1][6]) { // 오른쪽
			dir[i+1] = dir[i] * (-1);
			checkDir(i+1, dir);
		}
		if (i-1 >= 0 && dir[i-1] == 0 && board[i-1][2] != board[i][6]) { // 왼쪽
			dir[i-1] = dir[i] * (-1);
			checkDir(i-1, dir);
		}
	}

	private static void rotate(int idx, int d) {
		if (d == -1) {
			int tmp = board[idx][0];
			for (int i=0; i<M-1; i++) {
				board[idx][i] = board[idx][i+1];
			}
			board[idx][M-1] = tmp;
			return;
		}
		if (d == 1) {
			int tmp = board[idx][M-1];
			for (int i=M-1; i>0; i--) {
				board[idx][i] = board[idx][i-1];
			}
			board[idx][0] = tmp;
			return;
		}
	}

}
