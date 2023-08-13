import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		
		List<Integer>[] graph = new List[N+1];
		for (int i=0; i<N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			graph[v2].add(v1);
			graph[v1].add(v2);
		}
		
		int[][] dp = new int[Y+1][N+1];
		
		dp[0][X] = 1;
		for (int i=1; i<Y+1; i++) {
			for (int cur=1; cur<N+1; cur++) {
				if (dp[i-1][cur] == 1) {
					for (Integer nxt : graph[cur]) {
						dp[i][nxt] = 1;
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<N+1; i++) {
			if (dp[Y][i] == 1) {
				sb.append(i).append(" ");
			}
		}
		
		if (sb.length() == 0) {
			System.out.println(-1);
		}
		else 
			System.out.println(sb);
	}
}
