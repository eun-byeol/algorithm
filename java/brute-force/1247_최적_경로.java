import java.util.*;
import java.io.*;

public class Solution {
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] infos = new int[N+2][2];
			st = new StringTokenizer(br.readLine());			
			for (int i=0; i<N+2; i++) {
				infos[i][0] = Integer.parseInt(st.nextToken());
				infos[i][1] = Integer.parseInt(st.nextToken());
			}
			
			ans = Integer.MAX_VALUE;
			boolean[] visited = new boolean[N+2];
			dfs(0, N, infos[0][0], infos[0][1], 0, infos, visited);
			sb.append("#")
				.append(tc)
				.append(" ")
				.append(ans)
				.append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int cnt, int N, int preX, int preY, int total, int[][] infos, boolean[] visited) {
		if (total >= ans)
			return;
		
		if (cnt == N) {
			total += Math.abs(preX - infos[1][0]) + Math.abs(preY - infos[1][1]);
			if (total < ans) {
				ans = total;
			}
			return;
		}
		
		for (int i=2; i<N+2; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			dfs(cnt+1, N, infos[i][0], infos[i][1], total + Math.abs(preX - infos[i][0]) + Math.abs(preY - infos[i][1]), infos, visited);
			visited[i] = false;
		}
	}
}
