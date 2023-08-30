import java.io.*;
import java.util.*;

class Pair {
	int x;
	int y;
	int dist;
	
	public Pair(int x, int y, int dist) {
		super();
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}

public class Main {
	static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=0; tc<T; tc++) {
			int I = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int tx = Integer.parseInt(st.nextToken());
			int ty = Integer.parseInt(st.nextToken());
			
			int ans = bfs(sx, sy, tx, ty, I);
			sb.append(ans).append("\n");
		}

		System.out.println(sb);
	}

	private static int bfs(int sx, int sy, int tx, int ty, int I) {
		Queue<Pair> q = new ArrayDeque<>();
		int[][] visited = new int[I][I];
		q.add(new Pair(sx, sy, 0));
		visited[sx][sy] = 1;
		
		while(!q.isEmpty()) {
			Pair cur = q.remove();
			
			if (cur.x == tx && cur.y == ty) {
				return cur.dist;
			}
			
			for (int i=0; i<8; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (nx < 0 || nx >= I || ny < 0 || ny >= I || visited[nx][ny] == 1) continue;
				
				q.add(new Pair(nx, ny, cur.dist + 1));
				visited[nx][ny] = 1;
			}
		}
		
		return -1;
	}
}
