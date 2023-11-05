import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long answer = -100001;
        answer = findMaxSubSum(sequence, 1);
        answer = Math.max(findMaxSubSum(sequence, -1), answer);
        return answer;
    }
    
    public long findMaxSubSum(int[] seq, int p) {
        long psum = seq[0] * p;
        long max = psum;
        p *= (-1);
        
        for (int i=1; i<seq.length; i++) {
            psum = Math.max(0, psum) + seq[i] * p;
            p *= (-1);
            max = Math.max(psum, max);
        }
        return max;
    }
}
