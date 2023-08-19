import java.io.*;
import java.util.*;

public class Main {
	static int maxDist;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] board = new char[R][C];
		for (int i=0; i<R; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		maxDist = 0;
		boolean[] visited = new boolean[26];
		visited[board[0][0] - 'A'] = true;
		dfs(0, 0, 1, board, R, C, visited);
		System.out.println(maxDist);
	}

	private static void dfs(int x, int y, int dist, char[][] board, int R, int C, boolean[] visited) {
		if (dist > maxDist) {
			maxDist = dist;
		}
		
		for (int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[board[nx][ny] - 'A'])
				continue;
			visited[board[nx][ny] - 'A'] = true;
			dfs(nx, ny, dist+1, board, R, C, visited);
			visited[board[nx][ny] - 'A'] = false;
		}
	}
}
