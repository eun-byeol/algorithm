import java.util.*;
import java.lang.*;

class Solution {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static int bfs(int[][] board, int sx, int sy, int tx, int ty, int N) {
        int[][] dist = new int[N][N];
        for (int[] d : dist) {
        	Arrays.fill(d, Integer.MAX_VALUE);
        }
        dist[sx][sy] = 0;
        
        Queue<List<Integer>> q = new LinkedList<>();
        q.add(Arrays.asList(sx, sy));
        
        while (!q.isEmpty()) {
            List<Integer> cur = q.poll();
            int r = cur.get(0);
            int c = cur.get(1);
            if (r == tx && c == ty) {
            	return dist[tx][ty];
            }
            for (int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] == 1) {
                    continue;
                }
                if (board[nr][nc] == 0) {
                    if (dist[r][c] + 1 < dist[nr][nc]) {
                        dist[nr][nc] = dist[r][c] + 1;
                        q.add(Arrays.asList(nr, nc));
                    }
                }
                else { // 2
                    int nxt = dist[r][c] + (3 - dist[r][c] % 3);
                    if (nxt < dist[nr][nc]) {
                        dist[nr][nc] = nxt;
                        q.add(Arrays.asList(nr, nc));
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        
        for(int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int[][] board = new int[N][N];
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    board[i][j] = sc.nextInt();
                }
            }
            int sx = sc.nextInt();
            int sy = sc.nextInt();
            int tx = sc.nextInt();
            int ty = sc.nextInt();
            int ans = bfs(board, sx, sy, tx, ty, N);
            System.out.printf("#%d %d\n", test_case, ans);
        }
    }
}
