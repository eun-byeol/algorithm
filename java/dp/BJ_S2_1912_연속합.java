import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = -1001;
        int pre = 0;

        for (int i=0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken());
            pre = Math.max(pre + num, num);
            max = Math.max(pre, max);
        }

        System.out.print(max);
    }
}
