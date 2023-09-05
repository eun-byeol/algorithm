import java.util.*;
import java.io.*;

public class Main {
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int year = -1;
		int cnt = 0;
		while (cnt < 2) { // 2 덩어리 이상으로 분리되면 종료
			int[][] next = new int[N][M]; // 다음 사이클의 빙산 배열
			boolean[][] visited = new boolean[N][M];
			year++;
			cnt = 0;
			
			for (int i=1; i<N-1; i++) {
				for (int j=1; j<M-1; j++) {
					if (map[i][j] == 0 || visited[i][j]) continue;
					visited[i][j] = true;
					bfs(i, j, map, N, M, next, visited);
					cnt++;
				}
			}
			map = next;
			if (cnt == 0) { // 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않는 경우
				year = 0;
				break;
			}
		}
		
		System.out.println(year);
	}

	private static void bfs(int r, int c, int[][] map, int n, int m, int[][] next, boolean[][] visited) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {r, c});
		
		while (!q.isEmpty()) {
			int[] cur = q.remove();
			int x = cur[0];
			int y = cur[1];
			int water = 0;
			
			for (int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (map[nx][ny] == 0) { // 바닷물 만나면 
					water++;
				}
				else if (!visited[nx][ny]) { // 인접한 빙산
					q.add(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
			}
			next[x][y] = map[x][y] - water;
			if (next[x][y] < 0) {
				next[x][y] = 0;
			}
		}
	}

}

/*

<추가 테케>
10 10
0 0 0 0 0 0 0 0 0 0
0 5 5 5 5 5 5 5 5 0
0 5 5 5 5 5 5 5 5 0
0 5 5 5 5 5 5 5 5 0
0 5 5 5 5 5 5 5 5 0
0 5 5 5 5 5 5 5 5 0
0 5 5 5 5 5 5 5 5 0
0 5 5 5 5 5 5 5 5 0
0 5 5 5 5 5 5 5 5 0
0 0 0 0 0 0 0 0 0 0
=> 모든 빙산이 사라짐
=> 답 : 0

-----------------------------------------

<틀린 이유>
1. 모든 빙산이 사라졌을때 반복문 탈출 안 함 -> 시간초과
	-> 한 사이클에 bfs를 하나도 안 했으면, 남아있는 빙산이 없다는 의미이므로 탈출
2. 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 프로그램은 0을 출력해야 함 -> 오답
	-> year = 0으로 갱

-----------------------------------------

<로직>
1. 한 사이클(year) 동안 2차원 배열을 돌면서 bfs
	- bfs 한 개수를 cnt에 저장(인접한 빙산의 그룹 수)
	- 한 사이클 동안엔 visited 배열을 공유한다
	- 빙산이 모두 녹을 때까지 or 2개 이상의 그룹으로 나뉠 때까지 반
2. bfs
	- 인접한 빙산을 돌면서, 높이 감소시키기
	- 이때, next 빙산 배열에 높이를 감소시킨다
	-> 빙산이 0으로 바꼈는데 bfs 돌면서 해당 경우가 카운트 될 수 있기 때문
	- visited 체


*/
