import java.util.*;
import java.io.*;

public class Main {
	static int minDist = Integer.MAX_VALUE;
	static int N;
	static int[] people;
	static List<Integer>[] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		people = new int[N+1];
		graph = new List[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<N+1; i++) {
			people[i] = Integer.parseInt(st.nextToken());
			graph[i] = new ArrayList<Integer>();
		}
		
		for (int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken(); // n
			while (st.hasMoreTokens()) {
				int v = Integer.parseInt(st.nextToken());
				graph[i].add(v);
			}
		}
		
		dfs(0, 1, new int[N+1]);
		
		if (minDist == Integer.MAX_VALUE)
			minDist = -1;
		System.out.println(minDist);
	}

	private static void dfs(int size, int at, int[] rst) { // 1: g1팀 , 0: g2팀
		if (size > N/2) { // 팀  a 뽑는 조합 구하기 : 1 ~ N/2 개
			return;
		}
		
		if (size > 0) { // 공집합 뽑는 경우는 제외
			check(rst);
		}
		
		for (int i=at; i<N+1; i++) {
			rst[i] = 1;
			dfs(size+1, i+1, rst);
			rst[i] = 0;
		}
	}


	private static void check(int[] rst) {
		Set<Integer> g1 = new HashSet<>();
		Set<Integer> g2 = new HashSet<>();
		int s1 = 0;
		int s2 = 0;
		
		for (int i=1; i<N+1; i++) {
			if (rst[i] == 1) {
				g1.add(i);
				s1 = i;
			}
			else {
				g2.add(i);
				s2 = i;
			}
		}
		
		if (!bfs(g1, s1))
			return;
		
		if (!bfs(g2, s2))
			return;
		
		int totalG1 = 0;
		int totalG2 = 0;
		
		for (Integer idx : g1) {
			totalG1 += people[idx];
		}
		for (Integer idx : g2) {
			totalG2 += people[idx];
		}
		minDist = Math.min(Math.abs(totalG1 - totalG2), minDist);
	}

	private static boolean bfs(Set<Integer> g, int start) {
		int S = g.size();
		int cnt = 1;
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		
		q.add(start);
		visited[start] = true;
		
		while (!q.isEmpty()) {
			int cur = q.remove();
			if (cnt == S) {
				return true;
			}
			
			for (Integer next : graph[cur]) {
				if (!visited[next] && g.contains(next)) {
					q.add(next);
					cnt++;
					visited[next] = true;
				}
			}
		}
		
		return false;
	}
}
