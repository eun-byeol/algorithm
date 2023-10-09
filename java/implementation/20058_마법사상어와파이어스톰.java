import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int N, M, Q;
    static int[][] board;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        M = (int) Math.pow(2, N);
        board = new int[M][M];

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<Q; i++) {
            int L = Integer.parseInt(st.nextToken());
            simul(L);
        }

        int total = 0;
        int maxCnt = 0;
        int[][] visited = new int[M][M];
        for (int i=0; i<M; i++) {
            for (int j=0; j<M; j++) {
                if (board[i][j] == 0) continue;
                total += board[i][j];
                if (visited[i][j] == 0) {
                    maxCnt = Math.max(bfs(i, j, visited), maxCnt);
                }
            }
        }
        System.out.println(total);
        System.out.println(maxCnt);
    }

    private static int bfs(int r, int c, int[][] visited) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {r, c});
        visited[r][c] = 1;
        int cnt = 1;

        while (!q.isEmpty()) {
            int[] cur = q.remove();
            for (int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= M || ny < 0 || ny >= M || board[nx][ny] == 0 || visited[nx][ny] == 1) continue;
                cnt++;
                q.add(new int[] {nx, ny});
                visited[nx][ny] = 1;
            }
        }
        return cnt;
    }

    private static void simul(int L) {
        int[][] cp = copyBoard();
        int S = (int) Math.pow(2, L);
        for (int i=0; i<M; i+=S) {
            for (int j=0; j<M; j+=S) {
                rotate(i, j, S, cp); // 회전
            }
        }
        updateBoard(copyBoard());
    }

    private static void updateBoard(int[][] pre) {
        for (int i=0; i<M; i++) { // 검사
            for (int j=0; j<M; j++) {
                if (pre[i][j] == 0) continue;
                int cnt = 0;
                for (int d=0; d<4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx < 0 || nx >= M || ny < 0 || ny >= M || pre[nx][ny] == 0) continue;
                    cnt++;
                }
                if (cnt < 3) {
                    board[i][j]--;
                }
            }
        }
    }

    private static void rotate(int r, int c, int S, int[][] pre) {
        for (int i=0; i<S; i++) {
            for (int j=0; j<S; j++) {
                board[r+j][c+S-1-i] = pre[r+i][c+j];
            }
        }
    }

    private static int[][] copyBoard() {
        int[][] cp = new int[M][M];
        for (int i=0; i<M; i++) {
            cp[i] = board[i].clone();
        }
        return cp;
    }
}
