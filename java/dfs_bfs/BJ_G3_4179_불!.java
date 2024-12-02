import java.io.*;
import java.util.*;

class Main {
    static int R, C;
    static char[][] board;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];

        Queue<int[]> jihun = new ArrayDeque<>();
        Queue<int[]> fire = new ArrayDeque<>();

        for (int i=0; i<R; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j=0; j<C; j++) {
                if (board[i][j] == 'F') {
                    fire.add(new int[]{i, j});
                    continue;
                }
                if (board[i][j] == 'J') {
                    jihun.add(new int[]{i, j});
                }
            }
        }

        String answer = bfs(jihun, fire);
        System.out.println(answer);
    }

    static String bfs(Queue<int[]> jihun, Queue<int[]> fire) {
        int time = 0;
        while (true) {
            time++;

            // 지훈 이동
            Queue<int[]> nxtJihun = new ArrayDeque<>();

            while (!jihun.isEmpty()) {
                int[] jh = jihun.remove();
                int jx = jh[0];
                int jy = jh[1];
                if (board[jx][jy] == 'F') { // !! 지훈이 이동 후, 불이 붙으면 더 이동 불가
                    continue;
                }
                for (int d=0; d<4; d++) {
                    int nx = jx + dx[d];
                    int ny = jy + dy[d];
                    if (OOB(nx, ny)) { // 탈출
                        return Integer.toString(time);
                    }
                    if (board[nx][ny] == '.') {
                        nxtJihun.add(new int[]{nx, ny});
                        board[nx][ny] = 'J';
                    }
                }
            }

            if (nxtJihun.isEmpty()) { // 이동 불가한 경우
                return "IMPOSSIBLE";
            }
            jihun = nxtJihun;

            // 불 이동
            Queue<int[]> nxtFire = new ArrayDeque<>();

            while (!fire.isEmpty()) {
                int[] f = fire.remove();
                int fx = f[0];
                int fy = f[1];
                for (int d=0; d<4; d++) {
                    int nx = fx + dx[d];
                    int ny = fy + dy[d];
                    if (OOB(nx, ny) || board[nx][ny] == 'F' || board[nx][ny] == '#') { // 벗어났거나 불이 이미 났거나 벽인 경우
                        continue;
                    }
                    nxtFire.add(new int[]{nx, ny});
                    board[nx][ny] = 'F'; // 확산
                }
            }

            fire = nxtFire;
        }
    }

    static boolean OOB(int x, int y) {
        return x < 0 || x >= R || y < 0 || y >= C;
    }
}
/*

bfs
1. 지훈 이동
  - 이동 후 불이 붙었으면 실패
  - OOB이면 탈출
  - 다음 이동할 공간이 없으면 실패
2. 불 이동

*/
