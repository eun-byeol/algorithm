import java.io.*;
import java.util.*;

public class Main_2636_치즈 {
	static int N, M;
	static int[][] board;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		
		int cnt = 0;
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 1) cnt++;
			}
		}
		
		int time = 0;
		while (cnt > 0) {
			time++;
			int remove = bfs(0, 0);
			if (cnt - remove <= 0) break;
			cnt -= remove;
		}
		
		System.out.println(time);
		System.out.println(cnt);
	}

	private static int bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		int[][] visited = new int[N][M];
		q.add(new int[] {r, c});
		visited[r][c] = 1;
		int removeCnt = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.remove();
			
			for (int i=0; i<4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] == 1) continue;
				
				if (board[nx][ny] == 0) { // 녹은 곳인 경우
					q.add(new int[] {nx, ny});
				}
				if (board[nx][ny] == 1) { // 곧 녹아 없어지는 칸
					board[nx][ny] = 0;
					removeCnt++;
				}
				visited[nx][ny] = 1;
			}
		}
		
		return removeCnt;
	}

}

/*

실수한 포인트 : 녹은 부분을 bfs한다!

*/
