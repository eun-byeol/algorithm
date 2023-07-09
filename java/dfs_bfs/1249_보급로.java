import java.util.*;

class Solution {
	static int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	
	static int[][] initDist(int N) {
		int[][] dist = new int[N][N];
		for (int i=0; i<N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		return dist;
	}
	
	static int bfs(int[][] map, int N) {
		int[][] dist = initDist(N);
		dist[0][0] = 0;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0, 0});
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			for (int i=0; i<4; i++) {
				int nr = r + dir[i][0];
				int nc = c + dir[i][1];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if (dist[r][c] + map[nr][nc] < dist[nr][nc]) {
					dist[nr][nc] = dist[r][c] + map[nr][nc];
					q.add(new int[] {nr, nc});
				}
			}
		}
		return dist[N-1][N-1];
	}
	
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        
        for(int test_case = 1; test_case <= T; test_case++) {
        	int N = sc.nextInt();
        	sc.nextLine();
        	int[][] map = new int[N][N];
        	
        	for (int i=0; i<N; i++) {
        		String inputs = sc.nextLine();
        		for (int j=0; j<N; j++) {
        			map[i][j] = inputs.charAt(j) - '0';
        		}
        	}
        	
        	int ans = bfs(map, N);
        	System.out.printf("#%d %d\n", test_case, ans);
        }
    }
}
