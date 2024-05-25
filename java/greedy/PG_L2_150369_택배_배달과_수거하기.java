import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        long d = 0;
        long p = 0;
        
        for (int i=n-1; i>=0; i--) {    
            long cnt = 0;
            
            d -= deliveries[i]; // 잉여분 포함, 누적해서 빼주는 것이 핵심
            p -= pickups[i];
            
            while (d < 0 || p < 0) {
                d += cap;
                p += cap;
                cnt++;
            }
            
            answer += (i+1) * 2 * cnt;
        }
        return answer;
    }
}
