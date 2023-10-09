import java.io.*;
import java.util.*;

public class Main_6603_로또 {
    static int K;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] lotto = new int[6];

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            if (K == 0) break;

            nums = new int[K];
            for (int i = 0; i < K; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            combi(0, 0, lotto, sb);
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void combi(int idx, int at, int[] lotto, StringBuilder sb) {
        if (idx == 6) {
            for (int i = 0; i < 6; i++) {
                sb.append(lotto[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = at; i < K; i++) {
            lotto[idx] = nums[i];
            combi(idx + 1, i + 1, lotto, sb);
        }
    }
}
