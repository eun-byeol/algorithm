import java.util.*;

class Solution {
    int N, M;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = { 0, 1, 0, -1};
    
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        int num = 1;
        int[] values = new int[N * M + 2];
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (land[i][j] == 1) {
                    values[++num] = bfs(i, j, land, num);
                }
            }
        }
        
        int answer = 0;
        for (int c=0; c<M; c++) {
            Set<Integer> idxs = new HashSet<>();
            for (int r=0; r<N; r++) {
                if (land[r][c] > 0) {
                    idxs.add(land[r][c]);
                }
            }
            int total = 0;
            for (Integer idx : idxs) {
                total += values[idx];
            }
            answer = Math.max(total, answer);
        }
        
        return answer;
    }
    
    int bfs(int sr, int sc, int[][] land, int num) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc});
        land[sr][sc] = num;
        int cnt = 1;
        
        while(!q.isEmpty()) {
            int[] cur = q.remove();
            int r = cur[0];
            int c = cur[1];
            
            for (int i=0; i<4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];
                
                if (OOB(nr, nc) || land[nr][nc] != 1) continue;
                q.add(new int[]{nr, nc});
                land[nr][nc] = num;
                cnt++;
            }
        }
        return cnt;
    }
    
    boolean OOB(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}
