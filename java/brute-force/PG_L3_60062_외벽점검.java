import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    int N, W, D;
    int[] weakCircle;
    List<int[]> distPermu = new ArrayList<>();
    
    public int solution(int n, int[] weak, int[] dist) {
        N = n;
        W = weak.length;
        D = dist.length;
        weakCircle = new int[2*W];
        
        System.arraycopy(weak, 0, weakCircle, 0, W); // 확장된 weak 배열 구하기
        for (int i=0; i<W; i++) {
            weakCircle[i+W] = weakCircle[i] + N;
        }
        
        permu(0, new int[D], new int[D], dist); // 투입될 친구 순서 모두 구하기
        
        for (int i=0; i<W; i++) { // weakCircle 중 시작할 지점부터, 시뮬레이션
            for (int[] p : distPermu) {
                int cnt = simul(p, i);
                answer = Math.min(cnt, answer);
            }
        }
        
        if (answer == Integer.MAX_VALUE) {
            return -1;
        }
        return answer;
    }
    
    void permu(int depth, int[] order, int[] visited, int[] dist) {
        if (depth == D) {
            distPermu.add(order.clone());
            return;
        }
        for (int i=0; i<D; i++)  {
            if (visited[i] == 1) continue;
            order[depth] = dist[i];
            visited[i] = 1;
            permu(depth+1, order, visited, dist);
            visited[i] = 0;
        }
    }
    
    int simul(int[] order, int idx) {
        int cnt = 1;
        int pre = weakCircle[idx];
        int done = 1;
        int orderIdx = 0;
        
        for (int i=idx+1; i<idx+W; i++) {
            if (order[orderIdx] < weakCircle[i] - pre) {
                cnt++;
                done++;
                orderIdx++;
                pre = weakCircle[i];
            }
            if (orderIdx >= D) {
                return Integer.MAX_VALUE;
            }
        }
        return cnt;
    }
}
/*

1. 확장된 배열 구하기 : weakCircle
    - 1, 5, 6, 10, 13, 17, 18, 22
2. 투입할 친구 순서 순열로 정하기 : distPermu
3. 시작할 취약 지점 정하기(0 ~ W-1), 시뮬레이션
    - weak[i] - pre > dist[j] : 새로운 친구 투입, cnt++
    - weak[i] - pre <= dist[j] : 기존 친구 투입

*/
