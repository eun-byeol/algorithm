import java.util.*;

class Solution {
    List<Integer>[] graph;
    int N;
    int answer = 0;
    
    public int solution(int[] info, int[][] edges) {
        N = info.length;
        graph = new List[N];
        // 그래프 생성
        for (int i=0; i<N; i++) {
            graph[i] = new LinkedList<>();
        }
        
        for (int[] edge : edges) {
            int p = edge[0];
            int c = edge[1];
            graph[p].add(c);
        }
        
        // 그래프 탐색
        Set<Integer> nextNode = new HashSet<>();
        nextNode.add(0);
        dfs(0, nextNode, 1, 0, info);
        
        return answer;
    }
    
    void dfs(int cur, Set<Integer> nextNode, int sheep, int wolf, int[] info) {
        if (sheep <= wolf) {
            return;
        }
        answer = Math.max(sheep, answer);
        nextNode.remove(cur);
        nextNode.addAll(graph[cur]);
        
        
        for(int next : nextNode) {
            if (info[next] == 0) { // 양
                dfs(next, new HashSet<>(nextNode), sheep+1, wolf, info);
            }
            else { // 늑대
                dfs(next, new HashSet<>(nextNode), sheep, wolf+1, info);
            }
        }
    }
}

/*

1. 그래프 그리기
2. 그래프 탐색

*/
