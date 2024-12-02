import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] blocks = new int[W];

        st = new StringTokenizer(br.readLine());

        for (int i=0; i<W; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
        }

        int total = 0;

        for (int i=0; i<H; i++) { // 현재 높이
            int pre = -1;
            int cnt = 0;
            for (int j=0; j<W; j++) {
                if (i+1 > blocks[j]) { // 벽돌 아니면 // i+1 빼먹지 않기!
                    cnt++;
                    continue;
                }
                // 벽돌이면
                if (pre != -1) total += cnt;
                pre = j;
                cnt = 0;
            }
        }

        System.out.println(total);
    }
}
/*

1. 모든 배열 훑기O(N^2)
2.
  if(벽돌 아니면) {
    cnt++
    continue;
  }
  if(벽돌이면) {
      if (pre != -1) total+=cnt;
       pre=현재 인덱스
       cnt=0;
  }

 */
