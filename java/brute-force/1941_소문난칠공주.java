import java.util.*;
import java.io.*;

class Idx {
    int x, y;

    public Idx(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Idx idx = (Idx) o;
        return x == idx.x && y == idx.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "{" + x +
                ", " + y +
                '}';
    }
}

public class Main {
    static int N = 5;
    static char[][] classroom = new char[N][N];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int ansCnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i=0; i<N; i++) {
            classroom[i] = br.readLine().toCharArray();
        }

        combi(0, 0, 0, 0, new Idx[7], new int[N][N]);
        System.out.println(ansCnt);
    }

    private static void combi(int r, int c, int num, int numY, Idx[] seven, int[][] visited) {
        if (numY > 3) {
            return;
        }
        if (num == 7) {
            bfs(seven);
            return;
        }
        for (int i=r; i<N; i++) {
            if (i != r) c = 0;
            for (int j=c; j<N; j++) {
                if (visited[i][j] == 1) continue;
                seven[num] = new Idx(i, j);
                visited[i][j] = 1;
                if (classroom[i][j] == 'Y') {
                    combi(i, j+1, num+1, numY+1, seven, visited);
                }
                else {
                    combi(i, j + 1, num + 1, numY, seven, visited);
                }
                visited[i][j] = 0;
            }
        }
    }

    private static void bfs(Idx[] seven) {
        int[][] visited = new int[N][N];
        Queue<Idx> q = new ArrayDeque<>();
        Set<Idx> check = new HashSet<>();
        check.addAll(Arrays.asList(seven));

        q.add(seven[0]);
        check.remove(seven[0]);

        while (!q.isEmpty()) {
            if (check.isEmpty()) { // 모두 인접
                ansCnt++;
                return;
            }

            Idx cur = q.remove();

            for (int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] == 1) continue;
                Idx nxt = new Idx(nx, ny);
                if (check.contains(nxt)) {
                    check.remove(nxt);
                    q.add(nxt);
                    visited[nx][ny] = 1;
                }
            }
        }
    }
}
