import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
        
        int cctv = -30001;
        for (int[] route : routes) {
            int exit = route[1];
            int enter = route[0];
            if (cctv < enter) {
                answer++;
                cctv = exit;
            }
        }
        return answer;
    }
}

/*        
(-20,-15) / (-18,-13) / (-14,-5) / (-5,-3)
      1                       1   
1. 출차 기준, 입차 기준으로 오름차순 정렬
2. pre < cur[0] -> count++, pre = cur[1]
*/
