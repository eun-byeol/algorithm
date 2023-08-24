import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] inning;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		inning = new int[N][9];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<9; j++) {
				inning[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		boolean[] visited = new boolean[9];
		visited[0] = true;
		dfs(0, new int[9], visited);
		
		System.out.println(ans);
	}
	
	private static void dfs(int depth, int[] playerOrder, boolean[] visited) {
		if (depth == 9) {
			int rst = play(playerOrder);
			if (ans < rst) {
				ans = rst;
			}
			return;
		}
		
		if (depth == 3) { // 4번 주자는 고정
			dfs(depth+1, playerOrder, visited);
			return;
		}
		
		for (int i=1; i<9; i++) { // 첫번째 주자는 이미 결정됨 
			if (visited[i]) continue;
			playerOrder[depth] = i;
			visited[i] = true;
			dfs(depth+1, playerOrder, visited);
			visited[i] = false;
		}
	}

	private static int play(int[] playerOrder) {
		int score = 0;
		int idx = 0;
		
		for (int i=0; i<N; i++) {
			int roo = 0;
			int out = 0;
			while (out < 3) {
				int player = playerOrder[idx];
				int rst = inning[i][player];
				if (rst == 0) {
					out++;
				}
				else if (rst == 4) {
					score++; // 타자
					if ((1 & roo) > 0) {
						score++;
					}
					if ((2 & roo) > 0) {
						score++;
					}
					if ((4 & roo) > 0) {
						score++;
					}
					roo = 0;
				}
				else {
					roo = (roo << rst) + (1 << (rst-1)); // 111001
					if ((8 & roo) > 0) {
						score++;
					}
					if ((16 & roo) > 0) {
						score++;
					}
					if ((32 & roo) > 0) {
						score++;
					}
					
					int tmp = 0;
					if ((1 & roo) > 0) {
						tmp += 1;
					}
					if ((2 & roo) > 0) {
						tmp += 2;
					}
					if ((4 & roo) > 0) {
						tmp += 4;
					}
					roo = tmp;
				}
				idx = (idx+1) % 9;
			}
		}
		return score;
	}
}
