import java.util.*;
class Solution {
    int N = 0;
    int M = 0;
    char[][] graph;

    List<Integer> findNextIndex(int r, int c, int i) {
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        while (true) {
            r += dr[i];
            c += dc[i];
            if (r < 0 || r >= N || c < 0 || c >=M || graph[r][c] == 'D') {
                r -= dr[i];
                c -= dc[i];
                break;
            }
        }
        return List.of(r, c);
    }

    int bfs(int x, int y) {
        int[][] visited = new int[N][M];
        Queue<List<Integer>> q = new LinkedList<>();
        q.add(List.of(x, y, 0));
        visited[x][y] = 1;

        while (!q.isEmpty()) {
            List<Integer> cur = q.poll();
            int r = cur.get(0);
            int c = cur.get(1);
            int dist = cur.get(2);

            if (graph[r][c] == 'G') {
                return dist;
            }

            for (int i=0; i<4; i++) {
                List<Integer> next = findNextIndex(r, c, i);
                int nr = next.get(0);
                int nc = next.get(1);
                if (visited[nr][nc] == 1) continue;
                q.add(List.of(nr, nc, dist + 1));
                visited[nr][nc] = 1;
            }
        }
        return -1;
    }
    public int solution(String[] board) {
        N = board.length;
        M = board[0].length();
        graph = new char[N][M];
        int x = 0, y = 0;

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                graph[i][j] = board[i].charAt(j);
                if (graph[i][j] == 'R') {
                    x = i;
                    y = j;
                }
            }
        }
        int answer = bfs(x, y);
        return answer;
    }
}
