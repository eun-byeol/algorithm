import java.util.*;
import java.io.*;

public class Main {
    static int R, C;
    static char[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        Queue<int[]> que = new ArrayDeque<>();
        int sx = 0;
        int sy = 0;

        for (int i=0; i<R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j=0; j<C; j++) {
                if (map[i][j] == '*') {
                    que.add(new int[]{i, j, 0, 1});
                }
                else if (map[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }
            }
        }

        int ans = bfs(que, sx, sy);
        if (ans == -1) {
            System.out.print("KAKTUS");
        }
        else {
            System.out.print(ans);
        }
    }

    private static int bfs(Queue<int[]> turn, int sx, int sy) {
        Queue<int[]> nextTurn = new ArrayDeque<>();
        turn.add(new int[] {sx, sy, 0, 2});
        while (!turn.isEmpty()) {
            while (!turn.isEmpty()) {
                int[] cur = turn.remove();
                int x = cur[0];
                int y = cur[1];
                int time = cur[2];
                int player = cur[3]; // 1: 물, 2: 고슴도치

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;

                    if (player == 1) { // 물 먼저 이동
                        if (map[nx][ny] == 'S' || map[nx][ny] == '.') {
                            map[nx][ny] = '*';
                            nextTurn.add(new int[]{nx, ny, time + 1, 1}); // x, y, time, 물
                        }
                        continue;
                    }
                    if (player == 2) { // 고슴도치 이동
                        if (map[nx][ny] == 'D') { // 탈출
                            return time + 1;
                        }
                        if (map[nx][ny] == '.') {
                            map[nx][ny] = 'S';
                            nextTurn.add(new int[]{nx, ny, time + 1, 2}); // x, y, time, 고슴도치
                        }
                    }
                }
            }

            for (int[] v : nextTurn) {
                turn.add(v.clone());
            }
            nextTurn.clear();
        }

        return -1;
    }
}
