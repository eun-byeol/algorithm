import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] graph;
	static int[] done;
	static int[] visited;
	static int cycle;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=0; tc<T; tc++) {
			N = Integer.parseInt(br.readLine());
			graph = new int[N+1];
			done = new int[N+1];
			visited = new int[N+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i=1; i<N+1; i++) {
				graph[i] = Integer.parseInt(st.nextToken());
			}
			
			cycle = 0;
			for (int i=1; i<N+1; i++) {
				dfs(i);
			}
			
			sb.append(N-cycle).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int cur) {
		if (done[cur] == 1) { // 이미 검증이 끝난 노드면 진행하지 않음
			return;
		}
		visited[cur] = 1;
		
		int next = graph[cur];
		if (visited[next] == 0) { // 사이클이 없으면 진행
			dfs(next);
		}
		else { // 사이클이 있으면 체크 
			if (done[next] == 0) { // 체크되지 않은 노드이면 추가
				cycle++;
				for (int i=next; i!=cur; i=graph[i]) {
					cycle++;
				}
			}
		}
		
		done[cur] = 1;
	}

}
