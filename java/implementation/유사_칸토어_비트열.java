import java.util.*;
class Solution {
    int[] base = {1, 1, 0, 1, 1};

    long solve(int n, long end) {
        if (n == 1) {
            int cnt = 0;
            for (int i=0; i<end; i++) {
                cnt += base[i];
            }
            return cnt;
        }
        
        long len = (long)Math.pow(5, n-1);
        long range = 0L;
        // 1 ~ 5구간
        for (int i=1; i<6; i++) {
            range += len;
            if (range >= end) {
                int cnt = (int)Math.pow(4, n-1);
                if (i == 3) {
                    return cnt * (i-1);
                }
                if (i < 3) {
                    return cnt * (i-1) + solve(n-1, end-((i-1)*len));
                }
                return cnt * (i-2) + solve(n-1, end-((i-1)*len));
            }
        }
        return 0;
    }
    
    public int solution(int n, long l, long r) {
        long R = solve(n, r);
        long L = solve(n, l-1);
        int answer = (int)(R-L);
        return answer;
    }
}
