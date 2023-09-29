import java.util.*;
import java.io.*;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

public class Main {
    static int N; // 스도쿠 크기
    static int[][] map; // 스도쿠 맵
    static List<Pair> idxes; // 채워지지 않은 칸 인덱스 리스트
    static int[] sx = {0, 0, 0, 3, 3, 3, 6, 6, 6}; // 사각형 그룹 시작 x좌표
    static int[] sy = {0, 3, 6, 0, 3, 6, 0, 3, 6}; // 사각형 그룹 시작 y좌표
    static boolean ansFlag = false;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = 9;
        map = new int[N][N];
        idxes = new ArrayList<>();

        for (int i=0; i<N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = line[j] - '0';
                if (map[i][j] == 0) {
                    idxes.add(new Pair(i, j)); // 0이면 인덱스리스트에 추가
                }
            }
        }

        int[][][] visited = new int[10][N][N];
        dfs(0, idxes.size(), visited); // 백트래킹

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int depth, int M, int[][][] visited) {
        if (ansFlag) { // 이미 답을 찾았으면 더는 탐색X
            return;
        }

        if (depth == M) { // M : 스도쿠 빈칸 수
            ansFlag = true;
            return;
        }

        Pair cur = idxes.get(depth);

        for (int n=1; n<10; n++) {
            // 가로, 세로, 사각형 중복 검사
            if (visited[n][cur.x][cur.y] == 0 && isValidCol(cur, n) && isValidRow(cur, n) && isValidSqu(cur, n)) {
                visited[n][cur.x][cur.y] = 1;
                map[cur.x][cur.y] = n;
                dfs(depth+1, M, visited);
                visited[n][cur.x][cur.y] = 0;
                if (!ansFlag) { // 답이 구해졌는데 맵을 지우면 안 됨
                    map[cur.x][cur.y] = 0;
                }
            }
        }
    }

    private static boolean isValidSqu(Pair cur, int n) { // 사각형 그룹 내 중복 원소 검사
        int x = 0; // 그룹 내 시작 x 좌표 초기화 
        int y = 0; // 그룹 내 시작 y 좌표 초기화

        for (int i=8; i>-1; i--) { // 시작점 찾기
            if (sx[i] <= cur.x && sy[i] <= cur.y) {
                x = sx[i];
                y = sy[i];
                break;
            }
        }

        for (int r=0; r<3; r++) { // 중복 검사
            for (int c=0; c<3; c++) {
                if (map[x+r][y+c] == n) return false;
            }
        }
        return true;
    }

    private static boolean isValidRow(Pair cur, int n) { // 세로 중복 검사
        for (int r=0; r<N; r++) {
            if (map[r][cur.y] == n) return false;
        }
        return true;
    }

    private static boolean isValidCol(Pair cur, int n) { // 가로 중복 검사
        for (int c=0; c<N; c++) {
            if (map[cur.x][c] == n)  {
                return false;
            }
        }
        return true;
    }

}
