import java.util.*;
import java.io.*;

public class Main {
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		char[][] map1 = new char[N][N];
		char[][] map2 = new char[N][N];
		for (int i=0; i<N; i++) {
			map1[i] = br.readLine().toCharArray();
			map2[i] = map1[i].clone();
		}
		
		int cnt1 = 0;
		int cnt2 = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map1[i][j] != '#') {
					bfs1(i, j, map1, N, map1[i][j]);
					cnt1++;
				}
				if (map2[i][j] != '#') {
					bfs2(i, j, map2, N, map2[i][j]);
					cnt2++;
				}
			}
		}
		
		System.out.println(cnt1 + " " + cnt2);
	}

	private static void bfs1(int x, int y, char[][] map, int N, int value) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {x, y});
		
		while (!q.isEmpty()) {
			int[] cur = q.remove();
			int r = cur[0];
			int c = cur[1];
			
			for (int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] != value)
					continue;
				map[nr][nc] = '#';
				q.add(new int[] {nr, nc});
			}
		}
	}
	
	private static void bfs2(int x, int y, char[][] map, int N, int value) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {x, y});
		
		while (!q.isEmpty()) {
			int[] cur = q.remove();
			int r = cur[0];
			int c = cur[1];
			
			for (int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == '#')
					continue;
				if (value == 'R' && map[nr][nc] == 'B')
					continue;
				if (value == 'G' && map[nr][nc] == 'B')
					continue;
				if (value == 'B' && map[nr][nc] != 'B')
					continue;
				map[nr][nc] = '#';
				q.add(new int[] {nr, nc});
			}
		}
	}
}
