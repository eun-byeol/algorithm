import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            char[] W = br.readLine().toCharArray();
            int K = Integer.parseInt(br.readLine());
            solve(W, K, sb);
        }

        System.out.println(sb);
    }

    static void solve(char[] W, int K, StringBuilder sb) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        int L = W.length;

        for (int i = 0; i < L; i++) {
            int cnt = 1;
            int length = 1;
            for (int j = i + 1; j < L; j++) {
                if (cnt == K) break; // 엣지 케이스: K = 1
                if (W[i] == W[j]) {
                    cnt++;
                }
                length++;
            }
            if (cnt == K) {
                max = Math.max(length, max);
                min = Math.min(length, min);
            }
        }
        if (min == Integer.MAX_VALUE) {
            sb.append(-1).append("\n");
            return;
        }
        sb.append(min).append(" ").append(max).append("\n");
    }
}
