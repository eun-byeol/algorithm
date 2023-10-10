import java.io.*;
import java.util.*;

public class Solution_7465_창용마을무리의개수 {
	static int N, K;
	static List<Integer> graph[];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			graph = new List[N+1];
			for (int i=1; i<N+1; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				graph[n1].add(n2);
				graph[n2].add(n1);
			}
			
			int totalCnt = 0;
			int[] visited = new int[N+1];
			for (int i=1; i<N+1; i++) {
				if (visited[i] == 0) {
					bfs(i, visited);
					totalCnt++;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(totalCnt).append("\n");
		}
		System.out.print(sb);
	}

	private static void bfs(int num, int[] visited) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(num);
		visited[num] = 1;
		
		while(!q.isEmpty()) {
			int cur = q.remove();
			for (int nxt : graph[cur]) {
				if (visited[nxt] == 0) {
					q.add(nxt);
					visited[nxt] = 1;
				}
			}
		}
	}

}
