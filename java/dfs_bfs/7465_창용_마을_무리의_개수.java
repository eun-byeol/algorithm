import java.util.*;
import java.lang.*;

class Solution {
	static void bfs(int start, boolean[][] info, int N, boolean[] visited) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int next=1; next<N+1; next++) {
				if (info[cur][next] && !visited[next]) {
					q.add(next);
					visited[next] = true;
				}
			}
		}
	}
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			boolean[][] info = new boolean[N+1][N+1];
			for(int i=0; i<M; i++) {
				int p1 = sc.nextInt();
				int p2 = sc.nextInt();
				info[p1][p2] = true;
				info[p2][p1] = true;
			}
			int ans = 0;
			boolean[] visited = new boolean[N+1];
			for (int i=1; i<N+1; i++) {
				if (!visited[i]) {
					bfs(i, info, N, visited);
					ans++;
				}
			}
			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
}
