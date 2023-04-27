import java.util.*;
class Solution {
    int[] answer = new int[2];
    int N, M;
    int[][] usr;
    int[] emt;
    int[] percents = {10, 20, 30, 40};
    
    int[] simul(int[] discount) {
        int[] result = new int[2];
        int[] price = new int[M];
        int[] cost = new int[N];
        for (int i=0; i<M; i++) {
            price[i] = (int)(emt[i] * ((100 - discount[i]) * 0.01)); 
        }
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (discount[j] >= usr[i][0]) {
                    cost[i] += price[j];
                }
                if (usr[i][1] <= cost[i]) {
                    cost[i] = 0;
                    result[0]++;
                    break;
                }
            }
            result[1] += cost[i];
        }
        return result;
    }
    
    void dfs(int depth, int[] discount) {
        if (depth == M) {
            int[] result = simul(discount);
            if (answer[0] < result[0]) {
                answer = result.clone();
            }
            else if (answer[0] == result[0] && answer[1] < result[1]) {
                answer = result.clone();
            }
            return;
        }
        
        for (int i=0; i<4; i++) {
            discount[depth] = percents[i];
            dfs(depth+1, discount);
            discount[depth] = 0;
        }
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        N = users.length;
        M = emoticons.length;
        usr = users;
        emt = emoticons;
        int[] discount = new int[M];
        dfs(0, discount);
        return answer;
    }
}
