import java.util.*;

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    int N, M;
    
    public int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        N = maps.length;
        M = maps[0].length();
        int[][] visited = new int[N][M];
        
        char[][] board = new char[N][M];
        for (int i=0; i<N; i++) {
            board[i] = maps[i].toCharArray();
        }
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (board[i][j] == 'X') continue;
                answer.add(bfs(i, j, board));
            }
        }
        
        if (answer.size() == 0) answer.add(-1);
        return answer.stream()
                    .sorted()
                    .mapToInt(Integer::intValue)
                    .toArray();
    }
    
    int bfs(int sx, int sy, char[][] board) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx, sy});
        int total = board[sx][sy] - '0';
        board[sx][sy] = 'X';
        
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            
            for (int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (OOB(nx, ny) || board[nx][ny] == 'X') continue;
                q.add(new int[]{nx, ny});
                total += board[nx][ny] - '0';
                board[nx][ny] = 'X';
            }
        }
        return total;
    }
    
    boolean OOB(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}
