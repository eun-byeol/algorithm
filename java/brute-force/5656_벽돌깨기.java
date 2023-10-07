import java.util.*;
import java.io.*;

class Block {
    int x, y, weight;

    public Block(int x, int y, int weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Block{" +
                "x=" + x +
                ", y=" + y +
                ", weight=" + weight +
                '}';
    }
}

public class Solution {
    static int N, W, H;
    static int minAns;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int[][] board = new int[H][W];
            for (int i=0; i<H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<W; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            minAns = Integer.MAX_VALUE;
            permu(0, new int[N], board);

            if (minAns == Integer.MAX_VALUE) {
                minAns = 0;
            }
            sb.append("#")
                    .append(tc)
                    .append(" ")
                    .append(minAns)
                    .append("\n");
        }
        System.out.print(sb);
    }

    private static void permu(int idx, int[] colOrder, int[][] board) {
        if (idx == N) {
            simul(colOrder, copy(board));
            return;
        }

        for (int i=0; i<W; i++) {
            colOrder[idx] = i;
            permu( idx+1, colOrder, board);
        }
    }

    private static int[][] copy(int[][] board) {
        int[][] cp = new int[H][W];

        for (int i=0; i<H; i++) {
            cp[i] = board[i].clone();
        }
        return cp;
    }

    private static void simul(int[] colOrder, int[][] board) {
        Queue<Block> q = new ArrayDeque<>();

        for (int i=0; i<N; i++) {
            int c = colOrder[i];
            int r = findRow(c, board);
            if (r == H) return; // 더이상 깰 벽돌이 없는 경우 -> 시뮬레이션 중단
            q.add(new Block(r, c, board[r][c]));

            while(!q.isEmpty()) {
                Block cur = q.remove();
                board[cur.x][cur.y] = 0;
                //연쇄효과
                for (int d=0; d<4; d++) {
                    for (int k=1; k<cur.weight; k++) {
                        int nx = cur.x + (dx[d]*k);
                        int ny = cur.y + (dy[d]*k);
                        if (nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
                        if (board[nx][ny] > 1) {
                            q.add(new Block(nx, ny, board[nx][ny])); // 1보다 큰 벽돌만 큐에 넣기
                        }
                        board[nx][ny] = 0;
                    }
                }
            }
            //중력작용
            gravity(board);
        }

        // 남은 벽돌 세기
        minAns = Math.min(countRemain(board), minAns);
    }

    private static int countRemain(int[][] board) {
        int cnt = 0;
        for (int i=0; i<W; i++) {
            for (int j=H-1; j>-1; j--) {
                if (board[j][i] == 0) break;
                cnt++;
            }
        }
        return cnt;
    }

    private static void gravity(int[][] board) {
        for (int i=0; i<W; i++) {
            int to = H-1;
            int from = H-2;
            while(true) {
                while (to > -1 && board[to][i] > 0) to--; //0일떄까지 탐색
                from = to - 1;
                while (from > -1 && board[from][i] == 0) from--; //0이 아닐때까지 탐색
                if (to < 0 || from < 0) break;
                board[to][i] = board[from][i];
                board[from][i] = 0;
            }
        }
    }

    private static int findRow(int c, int[][] board) {
        for (int r=H-1; r>-1; r--) {
            if (board[r][c] == 0) return ++r;
        }
        return 0;
    }
}
