import java.util.*;
class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    int rx = -1, ry = -1;
    int N, M;
    
    public int solution(String[] board) {
        int answer = 0;
        N = board.length;
        M = board[0].length();
        for (int i=0; i<N; i++) {
            if (rx != -1) break;
            for (int j=0; j<M; j++) {
                if (board[i].charAt(j) == 'R') {
                    rx = i;
                    ry = j;
                    break;
                }
            }
        }
        
        answer = bfs(board);
        return answer;
    }
    
    public int bfs(String[] board) {
        Queue<int[]> q = new ArrayDeque<>();
        int[][] visited = new int[N][M];
        q.add(new int[]{rx, ry, 0}); // x, y, dist
        
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            // G 위치이면 종료
            if (board[cur[0]].charAt(cur[1]) == 'G') {
                return cur[2];
            }
            // 상하좌우 이동
            for (int i=0; i<4; i++) {
                int[] next = move(cur, i, board);
                if (visited[next[0]][next[1]] == 1) continue;
                q.add(next);
            }
            visited[cur[0]][cur[1]] = 1;
        }
        return -1; // 도달할 수 없는 경우
    }
    
    public int[] move(int[] cur, int d, String[] board) {
        int nx = cur[0];
        int ny = cur[1];
        int dist = cur[2];
        while (true) {
            nx += dx[d];
            ny += dy[d];
            if (OOB(nx, ny) || (board[nx].charAt(ny) == 'D')) { // 범위를 벗어나거나 장애물에 부딪히는 순간까지
                return new int[]{nx-dx[d], ny-dy[d], dist+1};
            }
        }
    }
    
    public boolean OOB(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}
