import java.io.*;
import java.util.*;

class Move {
	int x;
	int y;
	int cnt;
	int dist;
	
	public Move(int x, int y, int cnt, int dist) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.dist = dist;
	}
}

public class Main_1600_말이되고픈원숭이 {
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
		que.add(new Move(0, 0, 0, 0));
		int[][][] visited = new int[H][W][K+1];
		
		while (!que.isEmpty()) {
			Move cur = que.remove();
			
			if (cur.x == H-1 && cur.y == W-1) {
				return cur.dist;
			}
			
			if (cur.cnt < K) {
				for (int i=0; i<8; i++) {
					int nx = cur.x + dxH[i];
					int ny = cur.y + dyH[i];
					
					if (nx < 0 || nx >= H || ny < 0 || ny >= W || board[nx][ny] == 1) continue;
					if (visited[nx][ny][cur.cnt+1] == 1) continue;
					
					visited[nx][ny][cur.cnt+1] = 1;
					que.add(new Move(nx, ny, cur.cnt+1, cur.dist+1));
				}
			}
			
			for (int i=0; i<4; i++) {
				int nx = cur.x + dxM[i];
				int ny = cur.y + dyM[i];
				
				if (nx < 0 || nx >= H || ny < 0 || ny >= W || board[nx][ny] == 1) continue;
				if (visited[nx][ny][cur.cnt] == 1) continue;
				
				visited[nx][ny][cur.cnt] = 1;
				que.add(new Move(nx, ny, cur.cnt, cur.dist+1));
			}
		}
		return -1;
	}
}
