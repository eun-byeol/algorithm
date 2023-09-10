import java.io.*;
import java.util.*;

class Group {
	List<int[]> idxList = new LinkedList<>();
	int[] repre = {Integer.MAX_VALUE, Integer.MAX_VALUE};
	int cnt = 0, rainbowCnt = 0;
	
	public void addBlock(int r, int c, int color) {
		idxList.add(new int[] {r, c});
		cnt++;
		if (color == 0) rainbowCnt++;
		else {
			if (repre[0] > r) {
				repre[0] = r;
				repre[1] = c;
			}
			else if (repre[0] == r && repre[1] > c) {
				repre[0] = r;
				repre[1] = c;
			}
		}
	}
}

public class Main {
	static int N, M;
	static int[][] map;
	static Group max;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int totalScore = 0;
		while (findMaxGroup()) {
			if (max.cnt < 2) break;
			totalScore += countScore();
			gravity();
			rotate();
			gravity();
		}
		
		System.out.println(totalScore);
		
	}

	private static void rotate() {
		int[][] copy = new int[N][N];
		
		for (int i=0; i<N; i++) {
			copy[i] = map[i].clone();
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				map[i][j] = copy[j][N-i-1];
			}
		}
	}

	private static void gravity() {
		for (int i=0; i<N; i++) {
			int to = N-1;
			int from = to - 1;
			
			while (true) {
				while (to > -1 && map[to][i] != -2) to--;
				from = to-1;
				while (from > -1 && map[from][i] == -2) from--;
				if (to < 0 || from < 0) break;
				
				if (map[from][i] == -1) {
					to = from - 1;
					continue;
				}
				
				map[to][i] = map[from][i];
				map[from][i] = -2;
			}
		}
	}

	private static int countScore() {
		for (int[] idx : max.idxList) {
			map[idx[0]][idx[1]] = -2;
		}
		return max.cnt * max.cnt;
	}

	private static boolean findMaxGroup() {
		max = new Group();
		max.cnt = -1;
		max.rainbowCnt = -1;
		max.repre[0] = -1;
		max.repre[1] = -1;
		
		int[][] visited = new int[N][N]; 
		int num = 1;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j] > 0 && visited[i][j] == 0) {
					bfs(i, j, num++, visited);
				}
			}
		}
		return max.cnt > 0;
	}

	private static void bfs(int r, int c, int groupNum, int[][] visited) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {r, c});
		visited[r][c] = groupNum;
		Group group = new Group();
		group.addBlock(r, c, map[r][c]);
		
		while(!q.isEmpty()) {
			int[] cur = q.remove();
			
			for(int i=0; i<4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] == groupNum) continue;
				if (map[nx][ny] < 0) continue;
				if (map[nx][ny] > 0 && map[nx][ny] != map[r][c]) continue;
				
				visited[nx][ny] = groupNum;
				q.add(new int[] {nx, ny});
				group.addBlock(nx, ny, map[nx][ny]);
			}
		}
		
		if (group.cnt > max.cnt) {
			max = group;
		}
		else if (group.cnt == max.cnt) {
			if (group.rainbowCnt > max.rainbowCnt) {
				max = group;
			}
			else if (group.rainbowCnt == max.rainbowCnt) {
				if (group.repre[0] > max.repre[0]) {
					max = group;
				}
				else if (group.repre[0] == max.repre[0] && group.repre[1] > max.repre[1]) {
					max = group;
				}
			}
		}
	}

}
