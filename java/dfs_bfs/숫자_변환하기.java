import java.util.*;
class Solution {
    int solve(int x, int y, int n) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, 0});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cy = cur[0];
            int cnt = cur[1];
            
            if (cy == x) {
                return cnt;
            }
            if (cy < x) {
                continue;
            }
            if (cy-n == x) return cnt+1;
            q.add(new int[]{cy-n, cnt+1});
            
            if (cy % 2 == 0) {
                if (cy/2 == x) return cnt+1;
                q.add(new int[]{cy/2, cnt+1});
            } 

            if (cy % 3 == 0) {
                if (cy/3 == x) return cnt+1;
                q.add(new int[]{cy/3, cnt+1});
            }
        }
        return -1;
    }
    public int solution(int x, int y, int n) {
        int answer = solve(x, y, n);
        return answer;
    }
}
