import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
	int v1, v2;
	int edge;
	
	public Node(int v1, int v2, int edge) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.edge = edge;
	}

	@Override
	public int compareTo(Node o) {
		return this.edge - o.edge;
	}

	@Override
	public String toString() {
		return "Node [v1=" + v1 + ", v2=" + v2 + ", edge=" + edge + "]";
	}
	
}

public class Main {
	static int N, M;
	static int[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0; // 섬 개수
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j] == 1) { // 신대륙 발견!!
					bfs(i, j, ++cnt);
				}
			}
		}
		
		
		int[][] graph = new int[cnt+2][cnt+2];
		for (int[] g : graph) {
			Arrays.fill(g, Integer.MAX_VALUE);
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j] == 0) continue;
				// 가로 찾기
				for (int c=j+1; c<M; c++) {
					if (map[i][j] == map[i][c]) break; // 같은 땅인 경우
					if (map[i][c] != 0) {
						int dist = c - j -1;
						int g1 = map[i][j];
						int g2 = map[i][c];
						if (dist < 2) break;
						if (graph[g1][g2] > dist) {
							graph[g1][g2] = dist;
							graph[g2][g1] = dist;
							break;
						}
					}
				}
				
				// 세로 찾기
				for (int r=i+1; r<N; r++) {
					if (map[i][j] == map[r][j]) break;
					if (map[r][j] != 0) {
						int dist = r - i -1;
						int g1 = map[i][j];
						int g2 = map[r][j];
						if (dist < 2) break;
						if (graph[g1][g2] > dist) {
							graph[g1][g2] = dist;
							graph[g2][g1] = dist;
							break;
						}
					}
				}
			}
		}
		
		// 최소신장 트리 구하기
		int ans = mst(graph, cnt);
		System.out.println(ans);
	}

	private static int mst(int[][] graph, int cnt) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for (int i=2; i<cnt+2; i++) {
			for (int j=2; j<cnt+2; j++) {
				if (graph[i][j] < Integer.MAX_VALUE) {
					pq.add(new Node(i-1, j-1, graph[i][j]));
				}
			}
		}
		
		int total = 0;
		int[] parent = new int[cnt+1];
		for (int i=1; i<cnt+1; i++) {
			parent[i] = i;
		}
		
		int connected = 0;
		while (!pq.isEmpty()) {
			if (connected == cnt-1) return total;
			Node cur = pq.remove();
			
			int p1 = find(cur.v1, parent);
			int p2 = find(cur.v2, parent);
			
			if (p1 == p2) continue;
			
			if (p1 < p2) {
				parent[p2] = p1;
			}
			else {
				parent[p1] = p2;
			}
			
			total += cur.edge;
			connected++;
		}
		
		return -1;
	}

	private static int find(int x, int[] parent) {
		if (parent[x] == x) return x;
		return find(parent[x], parent);
	}

	private static void bfs(int r, int c, int cnt) {
		Queue<int[]> q = new ArrayDeque<>();
		int[][] visited = new int[N][M];
		q.add(new int[] {r, c});
		visited[r][c] = 1;
		map[r][c] = cnt+1;
		
		while (!q.isEmpty()) {
			int[] cur = q.remove();
			for (int i=0; i<4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] == 1) continue;
				if (map[nx][ny] == 1) {
					q.add(new int[] {nx, ny});
					visited[nx][ny] = 1;
					map[nx][ny] = cnt+1;
				}
			}
		}
	}
}
