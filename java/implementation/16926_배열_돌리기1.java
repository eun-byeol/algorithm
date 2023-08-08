import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int[][] board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < R; i++) {
			rotate(board, N, M);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void rotate(int[][] board, int N, int M) {
		int endIdx = Math.min(N / 2, M / 2);
		
		for (int i=0; i<endIdx; i++) {
			int cx = i;
			int cy = i;
			int d = 0;
			int tmp = board[cx][cy];
			while (true) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				if (nx == i && ny == i) break;
				if (nx < 0+i || nx >= N-i || ny < 0+i || ny >= M-i) {
					d++;
					continue;
				}
				board[cx][cy] = board[nx][ny];
				cx = nx;
				cy = ny;
			}
			board[cx][cy] = tmp;
		}
	}
}
