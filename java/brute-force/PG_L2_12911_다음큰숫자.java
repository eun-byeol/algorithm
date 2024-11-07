import java.util.*;

class Solution {
    
    public int solution(int n) {
        int cntN = Integer.bitCount(n);
        
        int answer = n+1;
        int cntA = Integer.bitCount(answer);
        while (cntA != cntN) {
            cntA = Integer.bitCount(++answer);
        }        
        return answer;
    }
}
