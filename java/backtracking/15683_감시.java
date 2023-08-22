import java.util.*;
import java.io.*;

public class Main {
	static int[] dx = {0, 1, 0, -1}; // 오른, 아래, 왼, 위
	static int[] dy = {1, 0, -1, 0};
	
	static int[][][] cctvCases = { 
			{{}},
			{{0}, {1}, {2}, {3}}, 				// cctvCases[1]
			{{0,2}, {1,3}},						// cctvCases[2]
			{{0,3}, {0,1}, {1,2}, {2,3}}, 		// cctvCases[3]
			{{2,3,0}, {3,0,1}, {0,1,2}, {1,2,3}}, // cctvCases[4]
			{{0,1,2,3}} 						// cctvCases[5]
	};
	
	static int minAns = 64;
	static int N, M;
	static List<int[]> cctvs = new ArrayList<>(); // cctv 좌표를 담는 리스트
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				
				if (board[i][j] > 0 && board[i][j] < 6) // cctv이면 리스트에 담는다
					cctvs.add(new int[] {i, j});
			}
		}
		
		dfs(0, board); // 백트래킹
		
		System.out.println(minAns);
	}
	
	private static void dfs(int cnt, int[][] origin) {
		if (cnt == cctvs.size()) { // 기저조건 : 모든 cctv를 체크해 줬을 때
			minAns = Math.min(check(origin), minAns);
			return;
		}
		
		int x = cctvs.get(cnt)[0];
		int y = cctvs.get(cnt)[1];
		
		int[][] cases = cctvCases[origin[x][y]]; // 1번 cctv이면      combi = {{0}, {1}, {2}, {3}}
		
		for (int[] dir : cases) { // 방향 경우의 수들
			
			int[][] board = copy(origin); // 2차원 맵 복사 ***중요***
			
			for (int d : dir) {
				int nx = x;
				int ny = y;
				
				while (true) {
					nx += dx[d];
					ny += dy[d];
					if (nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] == 6) break; // 범위를 벗어나거나, 벽을 만나면 멈추기
					if (board[nx][ny] == 0)
						board[nx][ny] = -1; // # -> -1로 표시
				}
			}
			
			dfs(cnt+1, board); // 다음 cctv 체크
		}
	}

	private static int[][] copy(int[][] origin) { // 2차원 배열 복사 메서드
		int[][] tmp = new int[N][M];

		for (int i=0; i<N; i++) {
			tmp[i] = origin[i].clone();
		}
		return tmp;
	}
	
	private static int check(int[][] board) { // 사각지대 개수 세주는 메서드
		int num = 0;
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (board[i][j] == 0)
					num++;
			}
		}
		return num;
	}
}
