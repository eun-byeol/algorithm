import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] paper;
    static int whiteCnt;
    static int blueCnt;

    public static void main(String[]args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        whiteCnt = 0;
        blueCnt = 0;

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cut(0, 0, N);

        System.out.println(whiteCnt);
        System.out.print(blueCnt);
    }

    private static void cut(int r, int c, int size) {
        int color = paper[r][c];

        if (size == 1) { // 더 이상 자를 수 없는 경우 카운트
            if (color == 0) whiteCnt++;
            else blueCnt++;
            return;
        }

        for (int i=r; i<r+size; i++) {
            for (int j=c; j<c+size; j++) {
                if (color != paper[i][j]) { // 색이 일치하지 않는 경우만 재귀 진행
                    size /= 2;
                    cut(r, c, size);
                    cut(r, c+size, size);
                    cut(r+size, c, size);
                    cut(r+size, c+size, size);
                    return;
                }
            }
        }
        if (color == 0) whiteCnt++; // 색이 모두 일치하는 경우 카운트
        else blueCnt++;
    }

}
