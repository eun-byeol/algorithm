import java.util.*;
import java.io.*;

public class Main {
	static int N, M, K;
	static int[][] board;
	static int[][] score;
	static int[] dx = {0, 1, 0, -1}; // 동 남 서 북 
	static int[] dy = {1, 0, -1, 0};
	static int dir;
	static int totalScore;
	static int[][] updateDirInfo = {
			{0, 2, 3, 5, 4, 1}, // east
			{2, 1, 4, 3, 5, 0}, // south
			{0, 5, 1, 2, 4, 3}, // west
			{5, 1, 0, 3, 2, 4} // north
	};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		score = new int[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				bfs(i, j);
			}
		}
		
		dir = 0; // 동
		totalScore = 0;
		int[] cur = {0, 0};
		int[] dice = {2, 4, 1, 3, 5, 6}; // 마지막이 A
		
		for (int i=0; i<K; i++) {
			roll(cur, dice);
		}
		
		System.out.println(totalScore);
	}

	
	private static void roll(int[] cur, int[] dice) {
		// 한 칸 이동 
		int nx = cur[0] + dx[dir];
		int ny = cur[1] + dy[dir];

		if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
			dir = (dir + 2) % 4; // 반대 방향 이동
			nx = cur[0] + dx[dir];
			ny = cur[1] + dy[dir];
		}
		
		cur[0] = nx; // 현재 위치값 갱신 
		cur[1] = ny;
		updateDice(dice); // 주사위 갱신
		
		// 점수 획득 
		totalScore += score[nx][ny];
		
		// 이동 방향 갱신 
		int a = dice[5];
		int b = board[nx][ny];
		
		if (a > b) {
			dir = (dir+1) % 4;
		}
		else if (a < b) {
			dir--;
			if (dir < 0) {
				dir = 3;
			}
		}
	}

	
	private static void updateDice(int[] dice) {
		int[] tmp = dice.clone();
		
		for (int i=0; i<6; i++) {
			dice[updateDirInfo[dir][i]] = tmp[i];
		}
	}

	
	private static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		q.add(new int[] {r, c});
		visited[r][c] = true;
		
		int point = 0;
		int value = board[r][c];
		
		while (!q.isEmpty()) {
			int[] cur = q.remove();
			int x = cur[0];
			int y = cur[1];
			point += board[x][y];
			
			for (int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || board[nx][ny] != value)
					continue;
				visited[nx][ny] = true;
				q.add(new int[] {nx, ny});
			}
		}
		
		score[r][c] = point;
	}
}
