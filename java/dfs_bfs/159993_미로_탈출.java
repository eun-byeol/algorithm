import java.util.*;

class Solution {
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    int N, M;
    char[][] board;
    int sx, sy, lx, ly;
    
    public int solution(String[] maps) {
        int answer = -1;
        N = maps.length;
        M = maps[0].length();
        board = new char[N][M];
        for (int i=0; i< N; i++) {
            board[i] = maps[i].toCharArray();
            for (int j=0; j<M; j++) {
                if (board[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }
                else if (board[i][j] == 'L') {
                    lx = i;
                    ly = j;
                }
            }
        }
        int dist1 = bfs(sx, sy, 'L');
        int dist2 = bfs(lx, ly, 'E');
        if (dist1 != -1 && dist2 != -1) return dist1 + dist2;
        return answer;
    }
    
    int bfs(int x, int y, char target) {
        Queue<int[]> q = new ArrayDeque<>();
        int[][] visited = new int[N][M];
        q.add(new int[]{x, y, 0});
        visited[x][y] = 1;
        
        while(!q.isEmpty()) {
            int[] cur = q.remove();
            if (board[cur[0]][cur[1]] == target) {
                return cur[2];
            }
            for (int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (OOB(nx, ny) || visited[nx][ny] == 1 || board[nx][ny] == 'X') continue;
                q.add(new int[]{nx, ny, cur[2]+1});
                visited[nx][ny] = 1;
            }
        }
        return -1;
    }
    
    boolean OOB(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}
