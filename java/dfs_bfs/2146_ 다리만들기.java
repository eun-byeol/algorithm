import java.util.*;
import java.io.*;

public class Main {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int N;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		int ans = Integer.MAX_VALUE;
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j] == 1) { // 방문하지 않은 대륙이면 (방문한 대륙은 2로 변경)
					int minDist = bfs(i, j); 
					if (ans > minDist) {
						ans = minDist;
					}
				}
			}
		}
		
		System.out.println(ans);
	}

	private static int bfs(int r, int c) {
		Queue<int[]> land = new ArrayDeque<>();
		Queue<int[]> ocean = new ArrayDeque<>();
		int[][] dist = new int[N][N];

		land.add(new int[] {r, c});
		map[r][c] = 2; // 대륙 방문 표시 
		for (int[] d : dist) {
			Arrays.fill(d, Integer.MAX_VALUE);
		}
		dist[r][c] = 0;
		
		// 해안선 찾기 
		while (!land.isEmpty()) {
			int[] cur = land.remove();
			boolean isCoast = false;
			
			for (int i=0; i<4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 2) continue;
				
				if (map[nx][ny] == 1) { // 내 땅이면 
					land.add(new int[] {nx, ny});
					map[nx][ny] = 2;
					dist[nx][ny] = 0;
				}
				
				else if (map[nx][ny] == 0) { // 해안선이면 
					isCoast = true;
				}
			}
			if (isCoast) {
				ocean.add(cur);
			}
 		}
		
		// 다리 놓기
		while (!ocean.isEmpty()) {
			int[] cur = ocean.remove();
			int x = cur[0];
			int y = cur[1];
			
			for (int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 2) continue;
				
				if (map[nx][ny] == 1) { // 새로운 대륙을 만나면 종료  
					return dist[x][y];
				}
				
				if (dist[nx][ny] > dist[x][y]+1) {
					dist[nx][ny] = dist[x][y]+1;
					ocean.add(new int[] {nx, ny});
				}
			}
		}
		return Integer.MAX_VALUE;
	}
}

/*

<bfs 내부 로직>
1.해안선 찾기
	-> 내 대륙인 경우 land 큐에 저장하고, 바다를 만나면 ocean 큐에 저장
2.다리 놓기
	-> ocean 큐에 바다를 추가하면서, 새로운 대륙을 만나면 바로 반환 -> 최단거리 보장
	- 자신의 대륙과 다른 대륙을 구분하기 위해, 방문한 대륙을 2로 표시
		=> 이미 다리 놓기가 끝난 다른 대륙은 새로운 대륙으로 보지 않음

*/
