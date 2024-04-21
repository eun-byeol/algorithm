import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0L;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) {
            pq.add(work);
        }
        
        while (!pq.isEmpty() && n > 0) {
            int work = pq.poll();
            if (work - 1 > 0) {
                pq.add(work - 1);
            }
            n--;
        }
        
        while (!pq.isEmpty()) {
            int work = pq.poll();
            answer += (work * work);
        }
        
        return answer;
    }
}
