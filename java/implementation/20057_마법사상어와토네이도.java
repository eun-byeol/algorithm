import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] board;
    static int[] dx = {0, 1, 0, -1}; // 왼아오위
    static int[] dy = {-1, 0, 1, 0};
    static int[][][] sand = { // persent, x, y
        { {10,-1,-1}, {10,1,-1}, {7,-1,0}, {7,1,0}, {5,0,-2}, {2,-2,0}, {2,2,0}, {1,-1,1}, {1,1,1}, {-1,0,-1} }, // 마지막은 a
        { {10,1,-1}, {10,1,1}, {7,0,-1}, {7,0,1}, {5,2,0}, {2,0,-2}, {2,0,2}, {1,-1,-1}, {1,-1,1}, {-1,1,0} },
        { {10,-1,1}, {10,1,1}, {7,-1,0}, {7,1,0}, {5,0,2}, {2,-2,0}, {2,2,0}, {1,-1,-1}, {1,1,-1}, {-1,0,1} }, // d=0과 y좌표 반대
        { {10,-1,-1}, {10,-1,1}, {7,0,-1}, {7,0,1}, {5,-2,0}, {2,0,-2}, {2,0,2}, {1,1,-1}, {1,1,1}, {-1,-1,0} } // d=1과 x좌표 반대
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(simul());
    }

    private static int simul() {
        int x = N/2;
        int y = N/2;
        int d = 0;
        int move = 1;
        int total = 0;

        while (true) {
            for (int i = 0; i < 2; i++) {
                for (int k = 0; k < move; k++) {
                    // 모래 퍼뜨리기
                    total += spread(x, y, d);

                    // 토네이도 이동
                    x += dx[d];
                    y += dy[d];
                    if (x == 0 && y == 0) { // 0, 0에 도착하면 종료
                        return total;
                    }
                }
                d = (d + 1) % 4;
            }
            move++;
        }
    }

    private static int spread(int r, int c, int d) {
        int out = 0; // 밖으로 나간 모래 총합
        int x = r + dx[d];
        int y = c + dy[d];
        int a = board[x][y]; // 뿌리고 남은 모래

        for (int i=0; i<9; i++) { // 마지막 전(a)까지 진행
            int persent = sand[d][i][0];
            int amount = (int)(board[x][y] * (persent / 100.0));
            int nx = x + sand[d][i][1]; // x좌표
            int ny = y + sand[d][i][2]; // y좌표
            if (OOB(nx, ny)) { // 좌표 밖
                out += amount;
            }
            else { // 좌표 안
                board[nx][ny] += amount;
            }
            a -= amount;
        }

        // a 처리
        int nx = x + sand[d][9][1]; // x좌표
        int ny = y + sand[d][9][2]; // y좌표
        if (OOB(nx, ny)) { // 좌표 밖
            out += a;
        }
        else { // 좌표 안
            board[nx][ny] += a;
        }
        board[x][y] = 0; // 초기화
        return out;
    }

    private static boolean OOB(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}
/*

1. 모래 퍼뜨리기
    - 방향별 퍼뜨리는 퍼센트, 좌표값 저장 (퍼센트, x좌표, y좌표)
    - 왼아오위 순

2. 토네이도 이동
    - 왼아오위 순
    - 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, ...
    - move만큼 2번씩 이동

 */
