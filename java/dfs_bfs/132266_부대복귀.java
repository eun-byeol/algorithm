import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int m = sources.length;
        int[] answer = new int[m];
        List<Integer>[] graph = makeGraph(n, roads, sources);
        int[] dist = bfs(n, sources, destination, graph);
        
        for (int i=0; i<m; i++) {
            answer[i] = dist[sources[i]];
        }
        return answer;
    }
    
    public int[] bfs(int n, int[] sources, int destination, List<Integer>[] graph) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new ArrayDeque<>();
        q.add(destination);
        dist[destination] = 0;
        
        while (!q.isEmpty()) {
            int cur = q.remove();
            
            for (int next : graph[cur]) {
                if (dist[next] != -1) continue; // visited 체크
                dist[next] = dist[cur] + 1;
                q.add(next);
            }
        }
        return dist;
    }
    
    public List<Integer>[] makeGraph(int n, int[][] roads, int[] sources) {
        List<Integer>[] graph = new List[n+1];
        for (int i=1; i<n+1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] road : roads) {
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }
        return graph;
    }
    
}

/*

1. 양방향 그래프 그리기
2. bfs
    - destination에서 출발 ~ 모든 노드 까지의 최단거리 dist 배열 구하기

*/
