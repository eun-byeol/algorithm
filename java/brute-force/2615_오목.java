import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N = 20;
	static int win = 0;
	static int[] idx = {19, 19};
	static int[] dx = {-1, 0, 1, 1}; //  / - | \
	static int[] dy = {1, 1, 0, 1};
	
	public static boolean check(int x, int y, int[][] board) {
		for (int i=0; i<4; i++) {
			int cnt = 1;
			int nx = x;
			int ny = y;
			while (true) {
				nx += dx[i];
				ny += dy[i];
				if (nx < 1 || nx >= N || ny >= N) break;
				if (board[x][y] != board[nx][ny]) break;
				cnt++;
			}
			if (cnt == 5) {
				nx = x - dx[i];
				ny = y - dy[i];
				if (nx > 0 && nx < N && ny > 0 && ny < N) {
					if (board[nx][ny] == board[x][y]) continue;
				}
				return true;
			}
		}
		return false;
	}
	
	public static void run(int[][] board) {
		for (int i=1; i<N; i++) {
			for (int j=1; j<N; j++) {
				if (board[i][j] != 0) {
					if (check(i, j, board)) {
						idx[0] = i;
						idx[1] = j;
						win = board[i][j];
						return;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] board = new int[N][N];
		for (int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		run(board);
		
		System.out.println(win);
		if (win != 0) {
			System.out.println(idx[0] + " " + idx[1]);
		}
		br.close();
	}
}
