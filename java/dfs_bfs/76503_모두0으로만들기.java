import java.util.*;
class Solution {
    long answer = 0L;
    List<Integer>[] graph;
    int[] weight;
    int[] visited;
    
    public long solution(int[] a, int[][] edges) {
        long total = 0L;
        for (int aa : a) {
            total += aa;
        }
        if (total != 0) return -1; // 불가능한 경우
        
        makeGraph(a.length, edges);
        weight = a;
        visited = new int[weight.length];
        dfs(0, weight[0]);
    
        return answer;
    }
    
    long dfs(int cur, long total) {
        visited[cur] = 1;
        for (int i=0; i<graph[cur].size(); i++) {
            int next = graph[cur].get(i);
            if (visited[next] == 1) continue;
            total += dfs(next, weight[next]);
        }
        answer += Math.abs(total);
        return total;
    }
    
    void makeGraph(int N, int[][] edges) {
        graph = new List[N];
        for (int i=0; i<N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
    }
}
