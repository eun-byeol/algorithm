import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] board;
	
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1}; // ←, ↖, ↑, ↗, →, ↘, ↓, ↙ 
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

	static int[] diaX = {-1, -1, 1, 1}; // 대각선 
	static int[] diaY = {-1, 1, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N+1][N+1];
		
		for (int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<N+1; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<int[]> cloud = new ArrayDeque<>();
		cloud.add(new int[] {N, 1});
		cloud.add(new int[] {N, 2});
		cloud.add(new int[] {N-1, 1});
		cloud.add(new int[] {N-1, 2});

		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			cloud = simul(d-1, s, cloud);
		}
		

		int total = 0;
		for (int i=1; i<N+1; i++) {
			for (int j=1; j<N+1; j++) {
				total += board[i][j];
			}
		}
		
		System.out.println(total);
	}

	private static Queue<int[]> simul(int d, int move, Queue<int[]> cloud) {
		Queue<int[]> tmp = new ArrayDeque<>();
		boolean[][] visited = new boolean[N+1][N+1];
		
		// 이동한 구름  + 1
		while (!cloud.isEmpty()) {
			int[] cur = cloud.remove();
			
			int nx = (cur[0] + move * dx[d]) % N;
			int ny = (cur[1] + move * dy[d]) % N; 
			
			if (nx < 1) {
				nx += N;
			}
			if (ny < 1) {
				ny += N;
			}
			
			board[nx][ny]++;
			visited[nx][ny] = true;
			tmp.add(new int[] {nx, ny});
		}
		
		// 이동한 구름 대각선 개수 +
		while (!tmp.isEmpty()) {
			int[] cur = tmp.remove();
		
			for (int i=0; i<4; i++) {
				int nx = cur[0] + diaX[i];
				int ny = cur[1] + diaY[i];
				
				if (nx < 1 || nx > N || ny < 1 || ny > N || board[nx][ny] == 0) continue;
				board[cur[0]][cur[1]]++;
			}
		}
		
		// 다음 구름 찾기
		for (int i=1; i<N+1; i++) {
			for (int j=1; j<N+1; j++) {
				if (board[i][j] >= 2 && !visited[i][j]) {
					tmp.add(new int[] {i, j});
					board[i][j] -= 2;
				}
			}
		}
		
		return tmp;
	}

}

/*

<포인트>
1. 맵의 인덱스는 순환
	- (% N) 해주고 음수이면 (+ N)
2. 물의 양 더하는 2 연산 분리
	-> 모든 구름을 다 증가시킨 이후, 대각선 체크해야 함 -> 디버깅 오래 걸린 포인트임


<로직>
1. 구름 이동 (d 방향으로 s 이동)
	- 큐를 빼면서, +1 증가 갱신
	- visited 표시 

2. 대각선 체크
	- 물이 증가한 칸(r,c) += 대각선 1칸에 있는 물 바구니 수
	- 경계를 넘어가면 무시
	
3. 다음 구름 찾기
	- 해당 인덱스의 값이 2 이상이면 , visited 체크 -> 큐에 넣기
	- 물 양 2 감소

*/
