import java.util.*;
class Solution {
    long getMaxSum(int[] seq, int N) {
        long psum = 0;
        long max = 0;
        for (int i=0; i<N; i++) {
            psum = Math.max(psum, 0) + seq[i];
            max = Math.max(psum, max);
        }
        return max;
    }
    public long solution(int[] sequence) {
        long answer = 0;
        int N = sequence.length;
        int[] seq1 = sequence.clone();
        int[] seq2 = sequence.clone();
        for (int i=0; i<N; i+=2) {
            seq1[i] *= -1;
        }
        for (int i=1; i<N; i+=2) {
            seq2[i] *= -1;
        }
        answer = Math.max(getMaxSum(seq1, N), answer);
        answer = Math.max(getMaxSum(seq2, N), answer);
        return answer;
    }
}
