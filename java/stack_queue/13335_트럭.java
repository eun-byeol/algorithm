import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_13335_트럭 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] trucks = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        int time = 0;
        int idx = 0;
        int totalL = 0;
        Queue<int[]> bridge = new ArrayDeque<>();

        while (idx < N) { // 마지막 트럭을 넣을 때 까지만 반복
            time++;
            // 다리에서 나오기
            if (!bridge.isEmpty()) {
                int[] truck = bridge.peek();
                if (time - truck[1] == W) {
                    bridge.remove();
                    totalL -= truck[0]; // 무게 빼기
                }
            }
            // 다리에 진입하기
            if (totalL + trucks[idx] <= L) {
                totalL += trucks[idx];
                bridge.add(new int[] {trucks[idx], time}); // (무게, 진입시간)
                idx++;
            }
        }
        time += W; // 몇 개의 트럭이 다리에 있든 마지막 트럭만 보면 된다.

        System.out.println(time);
    }
}

/*

1. 순서 유의! 다리에서 빼고, 이후에 다리에 넣어야 함 (total 무게 조건)
2. 모든 트럭을 다리에 넣는 순간까지 time을 구하고, 마지막에 +W를 더하면 됨
    -> 마지막 트럭이 나오는 시간만 보면 되기 때문 

 */
