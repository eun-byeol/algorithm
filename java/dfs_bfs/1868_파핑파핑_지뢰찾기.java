import java.util.*;
import java.lang.*;

class Solution {
	static int[][] dir8 = {{0,1}, {0,-1}, {1,0}, {-1,0}, {-1,-1}, {-1,1}, {1,-1}, {1,1}};
	
	static void counter(int r, int c, String[] inputs, int N, int[][] board) {
		if (inputs[r].charAt(c) == '*') {
			board[r][c] = -1;
			return;
		}
		for (int i=0; i<8; i++) {
			int nr = r + dir8[i][0];
			int nc = c + dir8[i][1];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if (inputs[nr].charAt(nc) == '*') {
				board[r][c]++;
			}
		}
	}
	
	static void bfs(int x, int y, int[][] board, int N, int[][] visited, int cnt) {
		Queue<List<Integer>> q = new LinkedList<>();
		q.add(Arrays.asList(x, y));
		while (!q.isEmpty()) {
			List<Integer> cur = q.poll();
			int r = cur.get(0);
			int c = cur.get(1);
			for (int i=0; i<8; i++) {
				int nr = r + dir8[i][0];
				int nc = c + dir8[i][1];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] != 0 || board[nr][nc] == -1) continue;
				if (board[nr][nc] == 0) {
					q.add(Arrays.asList(nr, nc));
				}
				visited[nr][nc] = cnt;
			}
		}
	}

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        
        for(int test_case = 1; test_case <= T; test_case++) {
        	int N = sc.nextInt();
        	sc.nextLine();
        	String[] inputs = new String[N];
        	for (int i=0; i<N; i++) {
        		inputs[i] = sc.nextLine();
        	}
        	
        	int[][] board = new int[N][N];
        	Queue<List<Integer>> zeroQ = new LinkedList<>(); 
        	for (int i=0; i<N; i++) {
        		for (int j=0; j<N; j++) {
        			counter(i, j, inputs, N, board);
        			if (board[i][j] == 0) {
        				zeroQ.add(Arrays.asList(i, j));
        			}
        		}
        	}
        	
        	int ans = 0;
        	int[][] visited = new int[N][N];
        	
        	while (!zeroQ.isEmpty()) {
        		List<Integer> indexes = zeroQ.poll();
        		int r = indexes.get(0);
        		int c = indexes.get(1);
        		if (visited[r][c] == 0) {
        			visited[r][c] = ++ans;
        			bfs(r, c, board, N, visited, ans);
        		}
        	}
        	
        	for (int i=0; i<N; i++) {
        		for (int j=0; j<N; j++) {
        			if (board[i][j] > 0 && visited[i][j] == 0) {
        				visited[i][j] = ++ans;
        			}
        		}
        	}
        	System.out.printf("#%d %d\n", test_case, ans);
        }
    }
}
