import java.util.*;
class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        for (int x=1; x<=r2; x++) {
            int max = (int)Math.floor(Math.sqrt((long)r2*r2 - (long)x*x));
            int min = (int)Math.ceil(Math.sqrt((long)r1*r1 - (long)x*x));
            answer += max-min+1;
        }
        return answer * 4;
    }
}
