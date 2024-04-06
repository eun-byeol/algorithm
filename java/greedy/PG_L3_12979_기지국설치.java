import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int start = 1;
        int covered = w * 2 + 1;
        
        for (int station : stations) {
            int end = station - w - 1;
            if (start <= end) {
                answer += cal(start, end, covered);
            }
            start = station + w + 1;
        }
        if (start <= n) {
            answer += cal(start, n, covered);
        }
        return answer;
    }
    
    int cal(int start, int end, int covered) {
        int len = end - start + 1;
        double cnt = len / covered;
        if (len % covered > 0) cnt++;
        return (int) cnt;
    }
}
