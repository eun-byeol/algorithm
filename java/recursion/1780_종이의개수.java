import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] paper;
    static int[] cnts;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        cnts = new int[3]; // -1, 0, 1

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cut(0, 0, N);
        System.out.println(cnts[0]);
        System.out.println(cnts[1]);
        System.out.print(cnts[2]);
    }

    private static void cut(int r, int c, int size) {
        int num = paper[r][c];

        if (size == 1) {
            cnts[num+1]++;
            return;
        }

        for (int i=r; i<r+size; i++) {
            for (int j=c; j<c+size; j++) {
                if (num != paper[i][j]) {
                    size /= 3;
                    cut(r, c, size);
                    cut(r, c+size, size);
                    cut(r, c+2*size, size);
                    cut(r+size, c, size);
                    cut(r+size, c+size, size);
                    cut(r+size, c+2*size, size);
                    cut(r+2*size, c, size);
                    cut(r+2*size, c+size, size);
                    cut(r+2*size, c+2*size, size);
                    return;
                }
            }
        }
        cnts[num+1]++;
    }
}
