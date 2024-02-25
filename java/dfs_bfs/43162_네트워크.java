import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        int[] visited = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                dfs(i, n, computers, visited);
                answer++;
            }
        }
        return answer;
    }
    
    void dfs(int start, int n, int[][] computers, int[] visited) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = 1;
        
        while(!q.isEmpty()) {
            int cur = q.remove();
            
            for (int next=0; next<n; next++) {
                if (computers[cur][next] == 0 || visited[next] == 1) continue;
                q.add(next);
                visited[next] = 1;
            }
        }
    }
}
