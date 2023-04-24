import java.util.*;
class Solution {
    int N, M;
    char[][] graph;
    int[] dr = {0, 0, 1, -1};
    int[] dc = {1, -1, 0, 0};
    
    int bfs(int startX, int startY, int endX, int endY) {
        Queue<int[]> q = new LinkedList<>();
        int[][] visited = new int[N][M];
        q.add(new int[]{startX, startY, 0});
        visited[startX][startY] = 1;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int dist = cur[2];
            if (r == endX && c == endY) {
                return dist;
            }
            for (int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (visited[nr][nc] == 1 || graph[nr][nc] == 'X') continue;
                q.add(new int[]{nr, nc, dist+1});
                visited[nr][nc] = 1;
            }
        }
        return Integer.MIN_VALUE;
    }
    public int solution(String[] maps) {
        int answer = 0;
        N = maps.length;
        M = maps[0].length();
        
        graph = new char[N][];
        int sx=0, sy=0, lx=0, ly=0, ex=0, ey=0;
        for (int i=0; i<N; i++) {
            graph[i] = maps[i].toCharArray();
            for (int j=0; j<M; j++) {
                if (graph[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }
                else if (graph[i][j] == 'L') {
                    lx = i;
                    ly = j;
                }
                else if (graph[i][j] == 'E') {
                    ex = i;
                    ey = j;
                }
            }
        }
        answer += bfs(sx, sy, lx, ly);
        if (answer < 0) 
            return -1;
        answer += bfs(lx, ly, ex, ey);
        if (answer < 0) 
            return -1;
        return answer;
    }
}
