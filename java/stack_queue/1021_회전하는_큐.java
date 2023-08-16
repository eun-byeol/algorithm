import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        Deque<Integer> deq = new ArrayDeque<>();
        
        for (int i=1; i<N+1; i++) {
            deq.addLast(i);
        }
        
        st = new StringTokenizer(br.readLine());
        
        int ansCnt = 0;
        for (int i=0; i<M; i++) {
            int findIdx = Integer.parseInt(st.nextToken());
            
            int target = 0;
            for (Integer num : deq) {
                if (num == findIdx) {
                    break;
                }
                target++;
            }
            
            if (target == 0) { // 1. 삭제
                deq.removeFirst();
            }
            
            else if (target < deq.size() - target) { // 2. 왼쪽 이동 
                ansCnt += target;
                for (int j=0; j<target; j++) {
                	deq.addLast(deq.removeFirst());
                }
                deq.removeFirst();
            }
            
            else { // 3. 오른쪽 이동
                ansCnt += deq.size() - target;
                for (int j=0; j<deq.size() - target; j++) {
                	deq.addFirst(deq.removeLast());
                }
                deq.removeFirst();
            }
        }
        System.out.println(ansCnt);
    }
}
