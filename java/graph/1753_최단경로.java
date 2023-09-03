import java.util.*;
import java.io.*;

class Node {
	int v;
	int e;
	
	public Node(int v, int e) {
		super();
		this.v = v;
		this.e = e;
	}
}

public class Main_1753_최단경로 {
	static int V, E;
	static List<Node>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		int K = Integer.parseInt(br.readLine());
		
		graph = new List[V+1];
		
		for (int i=1; i<V+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[u].add(new Node(v, w)); // u-v 연결하는 간선 w가 여러개일 수 있음 -> pq로 정렬하기 때문에 따로 처리X
		}
		
		int[] dist = bfs(K);
		
		for (int i=1; i<V+1; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				sb.append("INF\n");
			}
			else {
				sb.append(dist[i]).append("\n");
			}
		}
		
		System.out.println(sb);
	}

	private static int[] bfs(int start) {
		int[] dist = new int[V+1];
		int[] visited = new int[V+1];
		
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
			return o1.e - o2.e;
		});
		
		pq.add(new Node(start, 0));
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.remove();
			visited[cur.v] = 1;
			
			for (Node next : graph[cur.v]) {
				if (visited[next.v] == 1) continue;
				if (dist[next.v] > dist[cur.v] + next.e) {
					dist[next.v] = dist[cur.v] + next.e;
					next.e = dist[next.v]; // 노드의 edge 값을 갱신해줘야 함!
					pq.add(next);
				}
			}
		}
		
 		return dist;
	}
}

/*

5 6
1
5 1 1
1 2 2
1 3 3
2 3 4
2 4 5
3 4 6

*/
