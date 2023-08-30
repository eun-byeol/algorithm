import java.io.*;
import java.util.*;

class Move {
	int x;
	int y;
	int cnt;
	
	public Move(int x, int y, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}

public class Main {
	static int[] dxH = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dyH = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dxM = {0, 1, 0, -1};
	static int[] dyM = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int[][] board = new int[H][W];
		
		for (int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<W; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = bfs(K, W, H, board);
		
		System.out.println(ans);
	}

	private static int bfs(int K, int W, int H, int[][] board) {
		Queue<Move> que = new ArrayDeque<>();
		que.add(new Move(0, 0, 0)); // x, y, 말 점프 카운트
		int[][][] dist = new int[H][W][K+1];
		
		while (!que.isEmpty()) {
			Move cur = que.remove();
			
			if (cur.x == H-1 && cur.y == W-1) {
				return dist[cur.x][cur.y][cur.cnt];
			}
			
			if (cur.cnt < K) { // 말 점프
				for (int i=0; i<8; i++) {
					int nx = cur.x + dxH[i];
					int ny = cur.y + dyH[i];
					
					if (nx < 0 || nx >= H || ny < 0 || ny >= W || board[nx][ny] == 1 || dist[nx][ny][cur.cnt+1] > 0) continue;
					
					dist[nx][ny][cur.cnt+1] = dist[cur.x][cur.y][cur.cnt] + 1;
					que.add(new Move(nx, ny, cur.cnt+1));
				}
			}
			
			for (int i=0; i<4; i++) { // 원숭이 걸음
				int nx = cur.x + dxM[i];
				int ny = cur.y + dyM[i];
				
				if (nx < 0 || nx >= H || ny < 0 || ny >= W || board[nx][ny] == 1 || dist[nx][ny][cur.cnt] > 0) continue;

				dist[nx][ny][cur.cnt] = dist[cur.x][cur.y][cur.cnt] + 1;
				que.add(new Move(nx, ny, cur.cnt));
			}
		}
		
		return -1;
	}
}
