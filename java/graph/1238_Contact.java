import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc<11; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			Set<Integer>[] graph = new Set[101]; // 노드 최대 크기로 초기화 (노드 번호가 순차적으로 나오지 않을 수 있기 때문)
												 // 중복된 간선이 나올 수 있으므로 Set 자료구조 사용 
			
			for (int i=1; i<101; i++) {
				graph[i] = new HashSet<Integer>();
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from].add(to);
			}
			
			int ans = bfs(graph, start);
			
			sb.append("#")
				.append(tc)
				.append(" ")
				.append(ans)
				.append("\n");
		}
		
		System.out.println(sb);
	}

	private static int bfs(Set<Integer>[] graph, int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		int[] dist = new int[101]; // 모든 거리를 0으로 초기화 -> 연결되지 않은 노드의 거리는 0으로 남도록 함 
		dist[start] = 1; // 출발지점의 노드 거리 1로 설정 
		
		while (!q.isEmpty()) {
			int cur = q.remove();
			
			for (Integer next : graph[cur]) {
				if (dist[next] > 0)
					continue;
				dist[next] = dist[cur]+1;
				q.add(next);
			}
		}
		
		int maxV = 0; // 노드 번호 저장 
		for (int i=100; i>0; i--) { // 큰 숫자가 우선순위 높음
			if (dist[i] > dist[maxV])
				maxV = i;
		}
		return maxV;
	}
}
