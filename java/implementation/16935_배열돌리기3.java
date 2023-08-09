import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int [][] result = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<R; i++) {
			int cmd = Integer.parseInt(st.nextToken());
			
			if (cmd == 1) {
				result = simul1(N, M, board);
			}
			else if (cmd == 2) {
				result = simul2(N, M, board);
			}
			else if (cmd == 3) {
				result = simul3(N, M, board);
			}
			else if (cmd == 4) {
				result = simul4(N, M, board);
			}
			else if (cmd == 5) {
				result = simul5(N, M, board);
			}
			else if (cmd == 6) {
				result = simul6(N, M, board);
			}
			N = result.length;
			M = result[0].length;
			
			board = new int[N][M];
			
			for (int j = 0; j<N; j++) {
				for (int k = 0; k<M; k++) {
					board[j][k] = result[j][k];
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<result.length; i++) {
			for (int j=0; j<result[0].length; j++) {
				sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	
	/*
	 * 1<-2<-3<-4
	 */
	private static int[][] simul6(int n, int m, int[][] board) {
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		
		int jumpN = n / 2;
		int jumpM = m / 2;
		
		int[][] result = new int[n][m];
		
		int sx = 0;
		int sy = 0;
		for (int d = 0; d < 4; d++) {
			for (int x = sx; x < sx + jumpN; x++) {
				for (int y = sy; y < sy + jumpM; y++) {
					int nx = x + dx[d] * jumpN;
					int ny = y + dy[d] * jumpM;
					result[nx][ny] = board[x][y];
				}
			}
			sx += dx[d] * jumpN;
			sy += dy[d] * jumpM;
		}
		return result;
	}

	/*
	 * 1->2->3->4
	 */
	private static int[][] simul5(int n, int m, int[][] board) {
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		
		int jumpN = n / 2;
		int jumpM = m / 2;
		
		int[][] result = new int[n][m];
		
		int sx = 0;
		int sy = 0;
		for (int d = 0; d < 4; d++) {
			for (int x = sx; x < sx + jumpN; x++) {
				for (int y = sy; y < sy + jumpM; y++) {
					int nx = x + dx[d] * jumpN;
					int ny = y + dy[d] * jumpM;
					result[nx][ny] = board[x][y];
				}
			}
			sx += dx[d] * jumpN;
			sy += dy[d] * jumpM;
		}
		return result;
	}

	/*
	 * 왼쪽 90도 회전
	 */
	private static int[][] simul4(int n, int m, int[][] board) {
		int[][] result = new int[m][n];
		
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				result[i][j] = board[j][m-i-1];
			}
		}
		return result;
	}

	/*
	 * 오른쪽 90도 회전
	 */
	private static int[][] simul3(int n, int m, int[][] board) {
		int[][] result = new int[m][n];
		
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				result[i][j] = board[n-j-1][i];
			}
		}
		return result;
	}

	/*
	 * 좌우 반전
	 */
	private static int[][] simul2(int n, int m, int[][] board) {
		int[][] result = new int[n][m];
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				result[i][j] = board[i][m-j-1];
			}
		}
		return result;
	}

	/*
	 * 상하 반전
	 */
	private static int[][] simul1(int n, int m, int[][] board) {
		int[][] result = new int[n][m];
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				result[i][j] = board[n-i-1][j];
			}
		}
		return result;
	}
}
