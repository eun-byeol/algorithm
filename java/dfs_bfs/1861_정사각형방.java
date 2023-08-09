import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] board = new int[N][N];
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int ansNum = 0;
			int ansCnt = 0;
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					int cnt = bfs(i, j, N, board);
					if (cnt > ansCnt || (cnt == ansCnt && board[i][j] < ansNum)) {
						ansNum = board[i][j];
						ansCnt = cnt;
					}
				}
			}
			
			sb.append("#")
				.append(tc)
				.append(" ")
				.append(ansNum)
				.append(" ")
				.append(ansCnt)
				.append("\n");
		}
		System.out.println(sb.toString());
		
	}

	private static int bfs(int x, int y, int N, int[][] board) {
		int cnt = 0;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			cnt++;
			
			for (int i=0; i<4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || board[nx][ny] != board[cx][cy] + 1) {
					continue;
				}
				q.add(new int[] {nx, ny});
				break;
			}
		}
		
		return cnt;
	}
}
