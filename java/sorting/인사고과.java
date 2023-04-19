import java.util.*;
class Solution {
    void prioritySort(int[][] total) {
        Arrays.sort(total, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                if (o1[1] == o2[1]) return o2[2] - o1[2];
                return o2[1] - o1[1];
            }
            return o2[0] - o1[0];
        });
    }
    
    int findRank(int[][] total, int N) {
        int[] ranks = new int[N];
        int[] wanho = total[0];
        int r = 1;
        prioritySort(total);
        
        for (int i=0; i<N; i++) {
            int[] emp = total[i];
            ranks[i] = r++;
            
            for (int j=i-1; j>-1; j--) {
                int[] pre = total[j];
                if (emp[0] == pre[0] && ranks[j] != -1) {
                    ranks[i] = ranks[j];
                }
                if (pre[0] > emp[0]) {
                    if (pre[1] > emp[1] && pre[2] > emp[2]) { // 1번만 체크
                        ranks[i] = -1;
                        r--;
                        break;
                    }
                }
            }
            
            if (emp[0] == wanho[0] && emp[1] == wanho[1] && emp[2] == wanho[2]) {
                return ranks[i];
            }
        }
        return 0;
    }
    
    public int solution(int[][] scores) {
        int N = scores.length;
        int[][] total = new int[N][3];
        
        for (int i=0; i<N; i++) {
            int a = scores[i][0];
            int b = scores[i][1];
            total[i][0] = a + b;
            total[i][1] = a;
            total[i][2] = b;
        }       
        int answer = findRank(total, N);
        return answer;
    }
}
