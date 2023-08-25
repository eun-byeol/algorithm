import java.util.*;
import java.io.*;

public class Solution {
	static int N, K;
	static int maxCnt, minDist;
	static int[] dx = {-1, 0, 1, 0}; // 위, 왼, 아래, 오
	static int[] dy = {0, -1, 0, 1};


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			int[][] board = new int[N][N];
			List<int[]> cores = new ArrayList<>(); // 연결되지 않은 코어 리스트
			int connected = 0; // 연결된 코어 수
			
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j=0; j<N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					
					if (board[i][j] == 1) {
						if (i == 0 || i == N-1 || j == 0 || j == N-1) { // 벽에 붙은 코어는 추가하지 않기
							connected++;
							continue; 
						}
						
						if (isPossible(i, j, board, cores)) { // 사방이 코어로 막혀있는 경우 제외
							cores.add(new int[] {i, j});
						}
					}
				}
			}
			
			maxCnt = 0; // 초기화 유의
			minDist = Integer.MAX_VALUE;
			
			K = cores.size();
			dfs(0, connected, 0, board, cores);
			
			sb.append("#")
				.append(tc)
				.append(" ")
				.append(minDist)
				.append("\n");
		}
		
		System.out.println(sb);
	}

	/*
	 * 사방이 코어로 막혀있는 경우 제외
	 */
	private static boolean isPossible(int x, int y, int[][] board, List<int[]> cores) {
		boolean left = false;
		boolean right = false;
		boolean up = false;
		boolean down = false;
		
		for (int[] core : cores) {
			int r = core[0];
			int c = core[1];
			if (r == x && c == y) continue;
			if (r == x && c > y) right = true;
			if (r == x && c < y) left = true;
			if (c == y && r > x) down = true;
			if (c == y && r < x) up = true;
			if (left && right && up && down) return false;
		}
		return true;
	}

	/*
	 * 백트래킹
	 */
	private static void dfs(int depth, int cnt, int dist, int[][] board, List<int[]> cores) {
		if (maxCnt > cnt + (K-depth)) { 	// 가지치기 1 ) 더 많은 연결 cnt가 나올 수 없는 경우   
			return;
		}
		if (maxCnt == cnt + (K-depth) && (dist >= minDist)) {	// 가지치기2 ) 더 적은 dist가 나올 수 없는 경우
			return;
		}

		if (depth == K) { // ***모든 코어를 다 돌아봐야 함!!!!!
			if (cnt > maxCnt) {
				minDist = dist;
				maxCnt = cnt;
			}
			
			else if (cnt == maxCnt && dist < minDist) {
				minDist = dist;
			}
			return;
		}
		
		int x = cores.get(depth)[0];
		int y = cores.get(depth)[1];
		
		for (int d=0; d<4; d++) { // 4방향 다 돌아주기 -> 모든 경우의 수
			int lineCnt = countLine(d, x, y, board, cores);
			
			if (lineCnt != -1) { // 전선을 놓는 경우
				putLine(d, board, x, y, 2); // 전선 놓기
				dfs(depth + 1, cnt + 1, dist + lineCnt, board, cores);
				putLine(d, board, x, y, 0); // 전선 빼기
			}
			
			else { // 전선 못 놓는 경우
				dfs(depth + 1, cnt, dist, board, cores);				
			}
		}
	}

	/**
	 * 
	 * @param d : 방향 정보
	 * @param value = 2(놓기), 0(빼기)
	 */
	private static void putLine(int d, int[][] board, int x, int y, int value) {
		int nx = x + dx[d];
		int ny = y + dy[d];
		
		while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
			board[nx][ny] = value;
			nx += dx[d];
			ny += dy[d];
		}
	}

	/*
	 * 전선 수 세는 메서드
	 */
	private static int countLine(int d, int x, int y, int[][] board, List<int[]> cores) {
		int nx = x;
		int ny = y;
		int length = 0;
		
		while (true) {
			nx += dx[d];
			ny += dy[d];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
				break;
			}
			
			if (board[nx][ny] != 0) {
				return -1; // 실패
			}
			
			length++;
		}
		return length;
	}
}


/*

<dfs>
기저조건
- depth == K // 코어 개수
	- maxCnt, minDist 갱신
가지치기
- 더 많은 연결 cnt가 나올 수 없는 경우 
- 더 적은 dist가 나올 수 없는 경우


재귀조건
- 연결하기 (4방향)
	- 성공: dfs(depth+1, cnt+1, dist+line)
	- 실패: dfs(depth+1, cnt, dist)

<main>
- 이미 연결된 코어는 백트래킹에 포함하지 않는다!!
- 사방이 코어로 막혀있는 경우 제외한다!!

*/
