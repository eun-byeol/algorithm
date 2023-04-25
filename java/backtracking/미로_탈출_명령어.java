import java.util.*;
class Solution {
    int N, M, K;
    int endR, endC;
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};
    String[] dir = {"d", "l", "r", "u"};
    String answer = "";
    
    void dfs(int x, int y, String move, int depth) {
        int minDist = Math.abs(x-endR) + Math.abs(y-endC);
        if (!answer.equals("")) return;
        if (minDist + depth > K) return;
        if (depth == K) {
            if (x == endR && y == endC && answer.equals("")) {
                answer = move;
            }
            return;
        }
        for (int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 1 || nx > N || ny < 1 || ny > M) continue;
            dfs(nx, ny, move + dir[i], depth+1);
        }
    }
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        K = k;
        endR = r;
        endC = c;
        int minDist = Math.abs(x-r) + Math.abs(y-c);
        if (minDist > k || (k - minDist) % 2 != 0) {
            return "impossible";
        }
        dfs(x, y, "", 0);
        if (answer.equals("")){
            return "impossible";
        }
        return answer;
    }
}
