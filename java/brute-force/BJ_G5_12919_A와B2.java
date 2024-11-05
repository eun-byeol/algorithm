import java.io.*;
import java.util.*;

class Main {

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        recursive(S, T, S.length(), T.length());

        System.out.println(ans);
    }

    static void recursive(String s, String t, int N, int K) {
        if (N == K) {
            if (s.equals(t)) {
                ans = 1;
            }
            return;
        }

        if (t.charAt(0) == 'B') {
            recursive(s, new StringBuffer(t.substring(1, K)).reverse().toString(), N, K-1);
        }
        if (t.charAt(K-1) == 'A') {
            recursive(s, t.substring(0, K-1), N, K-1);
        }
    }
}
// StringBuffer 쓰면 reverse 편하게 가능
// T -> S 검증 로직?
// 1. 첫번째 원소가 B이면 뺀다
// 2. 마지막 원소가 A이면 뺀다
// 3. S 길이 == T 길이 -> S==T 확인
