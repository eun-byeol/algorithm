import java.util.*;
class Solution {
    int N, M;
    int[] dr = {0, 0, 1, -1};
    int[] dc = {1, -1, 0, 0};

    int bfs(int x, int y, char[][] graph, int[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = 1;
        int total = graph[x][y] - '0';

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (graph[nr][nc] != 'X' && visited[nr][nc] == 0) {
                    q.add(new int[]{nr, nc});
                    visited[nr][nc] = 1;
                    total += graph[nr][nc] - '0';
                }
            }
        }
        return total;
    }

    int[] solve(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        char[][] graph = new char[N][];
        for (int i=0; i<N; i++) {
            graph[i] = maps[i].toCharArray();
        }
        int[][] visited = new int[N][M];
        int[] answer = new int[N*M+1];
        int index = 0;

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (graph[i][j] != 'X' && visited[i][j] == 0) {
                    answer[index++] = bfs(i, j, graph, visited);
                }
            }
        }
        int[] result = Arrays.copyOfRange(answer, 0, index);
        Arrays.sort(result);
        if (index == 0) {
            return new int[]{-1};
        }
        return result;
    }

    public int[] solution(String[] maps) {
        int[] answer = solve(maps);
        return answer;
    }
}
