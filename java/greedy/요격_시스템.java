import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, (o1, o2) -> o1[1]-o2[1]);
        
        int pre = 0;
        for (int[] attack : targets) {
            int s = attack[0];
            int e = attack[1];
            if (s >= pre) {
                answer++;
                pre = e;
            }
        }
        return answer;
    }
}
