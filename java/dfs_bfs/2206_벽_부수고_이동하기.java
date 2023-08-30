import java.util.*;
import java.io.*;

public class Main {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] map = new char[N][M];
		for (int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int ans = bfs(N, M, map);
		System.out.println(ans);
	}

	private static int bfs(int N, int M, char[][] map) {
		Queue<int[]> q = new ArrayDeque<>();
		int[][][] dist = new int[N][M][2];
		q.add(new int[] {0, 0, 0}); // x, y, crush
		
		dist[0][0][0] = 1;
		
		while (!q.isEmpty()) {
			int[] cur = q.remove();
			int x = cur[0];
			int y = cur[1];
			int crush = cur[2];
			
			if (x == N-1 && y == M-1) {
				return dist[N-1][M-1][crush];
			}
			
			for (int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || dist[nx][ny][crush] > 0) continue;
				
				if (map[nx][ny] == '0') { // 벽이 아닐 때
					q.add(new int[] {nx, ny, crush});
					dist[nx][ny][crush] = dist[x][y][crush] + 1;
				}
				
				else if (crush == 0 && map[nx][ny] == '1') { // 벽일 때, 처음 부수는 경우
					q.add(new int[] {nx, ny, 1});
					dist[nx][ny][crush+1] = dist[x][y][crush] + 1;
				}
			}
		}
		
		return -1;
	}

}
