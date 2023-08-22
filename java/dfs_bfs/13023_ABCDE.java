import java.io.*;
import java.util.*;

public class Main {
	static int ans = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] graph = new ArrayList[N];
		
		for (int i=0; i<N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int p1 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			
			graph[p1].add(p2);
			graph[p2].add(p1);
		}
		
		
		for (int i=0; i<N; i++) {
			boolean[] visited = new boolean[N];
			
			visited[i] = true;
			dfs(0, i, graph, visited);
			if (ans == 1)
				break;
		}
		
		System.out.println(ans);
	}

	private static void dfs(int dist, int cur, List<Integer>[] graph, boolean[] visited) {
		if (dist == 4) {
			ans = 1;
			return;
		}
		
		for (Integer next : graph[cur]) {
			if (visited[next])
				continue;
			visited[next] = true;
			dfs(dist+1, next, graph, visited);
			visited[next] = false;
		}
	}
}
