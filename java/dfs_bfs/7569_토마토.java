import java.util.*;
import java.io.*;

public class Main {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[] dz = {1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		Queue<int[]> q = new ArrayDeque<>();
		int[][][] box = new int[H][M][N];
		
		for (int i=0; i<H; i++) {
			for (int j=0; j<M; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k=0; k<N; k++) {
					box[i][j][k] = Integer.parseInt(st.nextToken());
					if (box[i][j][k] == 1)
						q.add(new int[] {i, j, k});
				}
			}
		}
		
		System.out.println(bfs(box, N, M, H, q));
	}

	private static int bfs(int[][][] box, int N, int M, int H, Queue<int[]> q) {
		while (!q.isEmpty()) {
			int[] cur = q.remove();
			int z = cur[0];
			int x = cur[1];
			int y = cur[2];
			
			for (int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || nx >= M || ny < 0 || ny >= N || box[z][nx][ny] != 0)
					continue;
				box[z][nx][ny] = box[z][x][y]+1;
				q.add(new int[] {z, nx, ny});
			}
			
			for (int i=0; i<2; i++) {
				int nz = z + dz[i];
				
				if (nz < 0 || nz >= H || box[nz][x][y] != 0)
					continue;
				box[nz][x][y] = box[z][x][y]+1;
				q.add(new int[] {nz, x, y});
			}
		}
		
		int ans = 0;
		for (int i=0; i<H; i++) {
			for (int j=0; j<M; j++) {
				for (int k=0; k<N; k++) {
					if (box[i][j][k] > ans)
						ans = box[i][j][k];
					if (box[i][j][k] == 0)
						return -1;
				}
			}
		}
		return --ans;
	}
}
