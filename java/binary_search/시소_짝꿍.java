import java.util.*;
class Solution {
    int[][] rates = {{1,1}, {3,2}, {2,1}, {4,3}};
    
    int upperSearch(int value, int[] weights, int l, int r) {
        while (l < r) {
            int mid = (l + r) / 2;
            if (value < weights[mid]) {
                r = mid;
            } 
            else {
                l = mid + 1;
            }
        }
        return l;
    }

    int lowerSearch(int value, int[] weights, int l, int r) {
        while (l < r) {
            int mid = (l + r) / 2;

            if (value <= weights[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    
    public long solution(int[] weights) {
        long answer = 0;
        int N = weights.length;
        Arrays.sort(weights);
        
        for (int[] rate : rates) {
            for (int i=0; i<N; i++) {
                int x = weights[i];
                if ((rate[0] * x) % rate[1] != 0) {
                    continue;
                }
                int y = (rate[0] * x) / rate[1];
                int upper = upperSearch(y, weights, i+1, N);
                int lower = lowerSearch(y, weights, i+1, upper);
                answer += upper-lower;
            }
        }
        return answer;
    }
}
