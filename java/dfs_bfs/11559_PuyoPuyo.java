import java.io.*;
import java.util.*;

public class Main_11559_PuyoPuyo {
    static int N = 12;
    static int M = 6;
    static char[][] board = new char[N][M];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i=0; i<N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int ans = 0;

        while (true) {
            if (chaining()) {
                ans++;
                gravity();
            }
            else {
                break;
            }
        }

        System.out.println(ans);
    }

    private static void gravity() {
        for (int c=0; c<M; c++) {
            int to = N-1; // from ----> to

            while (true) {
                while (to >= 0 && board[to][c] != '.') { // 빈칸일 때까지 반복
                    to--;
                }

                int from = to - 1;
                while (from >= 0 && board[from][c] == '.') { // 빈칸이 아닐 때까지 반복
                    from--;
                }

                if (to < 0 || from < 0) break;

                board[to][c] = board[from][c];
                board[from][c] = '.';
            }
        }
    }

    private static boolean chaining() {
        int[][] visited = new int[N][M];
        boolean isSuccess = false;

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (board[i][j] == '.' || visited[i][j] == 1) continue;
                if (bfs(i, j, visited)) {
                    isSuccess = true;
                }
            }
        }
        return isSuccess;
    }

    private static boolean bfs(int sx, int sy, int[][] visited) {
        Queue<int[]> q = new ArrayDeque<>(); // 탐색 큐
        Queue<int[]> rstQ = new ArrayDeque<>(); // 결과 큐
        int[] start = {sx, sy};
        q.add(start); // x, y
        rstQ.add(start);
        visited[sx][sy] = 1;

        // 같은 색 뿌요 찾기
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            int x = cur[0];
            int y = cur[1];

            for (int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (OOB(nx, ny) || visited[nx][ny] == 1 || board[x][y] != board[nx][ny]) continue;
                visited[nx][ny] = 1;
                int[] nxt = {nx, ny};
                q.add(nxt);
                rstQ.add(nxt);
            }
        }

        // 4개 이상이면 뿌요 없애기
        if (rstQ.size() >= 4) { // 성공
            while (!rstQ.isEmpty()) {
                int[] cur = rstQ.remove();
                board[cur[0]][cur[1]] = '.';
            }
            return true;
        }
        else  { // 실패
            return false;
        }
    }

    private static boolean OOB(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}
