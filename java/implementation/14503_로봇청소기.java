import java.io.*;
import java.util.*;

public class Main_14503_로봇청소기 {
    static int N, M;
    static int[][] room;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        room = new int[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(run(r, c, d));
    }

    private static int run(int r, int c, int d) {
        int done = 0;

        while (true) {
            // 1
            if (room[r][c] == 0) {
                done++;
                room[r][c] = 2;
            }

            // 2
            if (!isDirtyArea(r, c)) {
                int backR = r - dx[d];
                int backC = c - dy[d];
                if (room[backR][backC] != 1) {
                    r = backR;
                    c = backC;
                    continue;
                }
                return done; // 작동 중지
            }

            // 3
            else {
                d = ((d+3) % 4);
                int nr = r + dx[d];
                int nc = c + dy[d];
                if (room[nr][nc] == 0) {
                    r = nr;
                    c = nc;
                }
            }
        }
    }

    private static boolean isDirtyArea(int r, int c) {
        for (int i=0; i<4; i++) {
            if (room[r + dx[i]][c + dy[i]] == 0) {
                return true;
            }
        }
        return false;
    }
}


/*

1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
    0 -> 2
2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
    1) 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
    2) 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
    1) 반시계 방향으로 90도 회전한다.
        - d = ((d+3) % 4)
    2) 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
    3) 1번으로 돌아간다.
 */
