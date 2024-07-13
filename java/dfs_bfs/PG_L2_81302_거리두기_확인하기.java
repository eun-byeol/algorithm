import java.util.*;

class Solution {
    int N;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    
    public int[] solution(String[][] places) {
        N = places.length;
        int[] answer = new int[N];
        
        for (int i=0; i<N; i++) {
            String[] place = places[i];
            char[][] room = new char[5][5];
            for (int j=0; j<5; j++) {
                room[j] = place[j].toCharArray();
            }
            if (valid(room)) {
                answer[i] = 1;
                continue;
            }
            answer[i] = 0;
        }
        return answer;
    }
    
    boolean valid(char[][] room) {
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                if (room[i][j] == 'P' && !bfs(room, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    boolean bfs(char[][] room, int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        int[][] visited = new int[5][5];
        q.add(new int[]{x, y, 0});
        visited[x][y] = 1;
        
        while(!q.isEmpty()) {
            int[] cur = q.remove();
            x = cur[0];
            y = cur[1];
            if (room[x][y] == 'P' && (cur[2] == 1 || cur[2] == 2)) {
                return false;
            }
            if (room[x][y] == 'O' && cur[2] >= 2) {
                continue;
            }
            for (int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (OOB(nx, ny) || visited[nx][ny] == 1 || room[nx][ny] == 'X') {
                    continue;
                }
                q.add(new int[]{nx, ny, cur[2] + 1});
                visited[nx][ny] = 1;
            }
        }
        return true;
    }
    
    boolean OOB(int x, int y) {
        return x < 0 || x >= 5 || y < 0 || y >= 5;
    }
}
/*

모든 노드에서 다른 노드까지 거리가 2이하이면 실패

1. 배열 분할
2. 모든 배열을 돌면서 P이면 모든 노드까지 bfs 돌리기
3. 다 가능하면 0

*/
