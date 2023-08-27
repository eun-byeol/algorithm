import java.util.*;
import java.io.*;

public class Main {
	static int W, H;
	static char[][] board;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			board = new char[H][W];
			
			Queue<int[]> fire = new ArrayDeque<>();
			Queue<int[]> sg = new ArrayDeque<>();
			
			for (int i=0; i<H; i++) {
				board[i] = br.readLine().toCharArray();
				
				for (int j=0; j<W; j++) {
					if (board[i][j] == '@') {
						sg.add(new int[] {i, j});
					}
					else if (board[i][j] == '*') {
						fire.add(new int[] {i, j});
					}
				}
			}
			
			int minTime = bfs(fire, sg);
			
			if (minTime == -1) {
				sb.append("IMPOSSIBLE\n");
			}
			else {
				sb.append(minTime).append("\n");				
			}
		}
		
		System.out.println(sb);
	}

	private static int bfs(Queue<int[]> fire, Queue<int[]> sg) {
		int time = 0;
		int fireSize = fire.size();
		int sgSize = sg.size();
		
		while (!sg.isEmpty()) {
			time++;
			int nextFireSize = 0;
			int nextSgSize = 0;

			// 불 전파 
			for (int i=0; i<fireSize; i++) {
				int[] fireIdx = fire.remove();
				int x = fireIdx[0];
				int y = fireIdx[1];
				
				for (int d=0; d<4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];

					if (OOB(nx, ny) || board[nx][ny] == '#' || board[nx][ny] == '*') continue;
					
					fire.add(new int[] {nx, ny});
					board[nx][ny] = '*';
					nextFireSize++;
				}
			}
			fireSize = nextFireSize;
			
			// 상근 이동
			for (int i=0; i<sgSize; i++) {
				int[] sgIdx = sg.remove();
				int x = sgIdx[0];
				int y = sgIdx[1];
				
				for (int d=0; d<4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if (OOB(nx, ny)) { // 탈출한 경우
						return time;
					}
					if (board[nx][ny] == '.') {
						sg.add(new int[] {nx, ny});
						board[nx][ny] = '@';
						nextSgSize++;
					}
				}
			}
			sgSize = nextSgSize;
			
		}
		
		return -1;
	}

	private static boolean OOB(int x, int y) {
		return x < 0 || x >= H || y < 0 || y >= W;
	}
}
