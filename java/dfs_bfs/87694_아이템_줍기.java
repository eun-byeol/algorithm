import java.util.*;

class Solution {
    int N = 102; // 맵 2배
    int[][] map = new int[N][N];
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for (int[] idx : rectangle) {
            int x1 = idx[0] * 2;
            int y1 = idx[1] * 2;
            int x2 = idx[2] * 2;
            int y2 = idx[3] * 2;
            for (int i=x1; i<x2+1; i++) {
                for (int j=y1; j<y2+1; j++) {
                    if (x1 < i && i < x2 && y1 < j && j < y2) {
                        map[i][j] = 2; // 내부
                        continue;
                    }
                    if (map[i][j] != 2) {
                        map[i][j] = 1; // 외곽선
                    }
                }
            }
        }
        int answer = bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
        return answer;
    }
    
    int bfs(int sx, int sy, int tx, int ty) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx, sy});
        map[sx][sy] = 0;
        
        while(!q.isEmpty()) {
            int[] cur = q.remove();
            int x = cur[0];
            int y = cur[1];
            
            if (x == tx && y == ty) {
                return map[x][y] / 2; // 2배 했으므로 2로 나눠준다
            }
            
            for (int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!OOB(nx, ny) && map[nx][ny] == 1) { // 지나가지 않은 외곽선인 경우만 진행
                    q.add(new int[]{nx, ny});
                    map[nx][ny] = map[x][y] + 1;
                }
            }
        }
        return 0;
    }
    
    boolean OOB(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}

/*
1. 지형 그리기
    - x, y 반대로 그려도 무방
    - 0(초기화), 1(외곽선), 2(내부)로 구분
    - 외곽선을 그리는 조건 : 0인 곳만 1로 바꾸기
    - 내부 조건 : x1 < x < x2 && y1 < y < y2
2. bfs
*/
