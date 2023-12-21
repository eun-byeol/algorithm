import java.util.*;

class Node {
    int vtx;
    int edge;
    
    Node (int vtx, int edge) {
        this.vtx = vtx;
        this.edge = edge;
    }
    
    public String toString() {
        return "(" + vtx + "-" + edge + ")";
    }
}

class Solution {
    List<Node>[] graph;
    int N;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        N = n+1;
        makeGraph(paths); // 그래프 그리기

				// 상수시간에 파악하기 위해 따로 저장
        boolean[] isGate = new boolean[N];
        boolean[] isSummit = new boolean[N];
        for (int gate : gates) {
            isGate[gate] = true;
        }
        for (int summit : summits) {
            isSummit[summit] = true;
        }
        
        // 봉우리 -> 출입구로 간다
        // 장점? bfs 탐색을 더 빨리 끝낼 수 있다. 출입구에 도착하면 바로 종료 가능
        for (int summit : summits) {
            int intensity = bfs(summit, isGate, isSummit);
            if (intensity < answer[1] || (intensity == answer[1] && summit < answer[0])) {
                answer[0] = summit;
                answer[1] = intensity;
            }
        }
        
        return answer;
    }
    
    int bfs(int start, boolean[] isGate, boolean[] isSummit) {
        Queue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]); // intensity, vtx
        int[] visited = new int[N];
        
        for (Node nxt : graph[start]) {
            q.add(new int[]{nxt.edge, nxt.vtx});
        }
        visited[start] = 1;
        
        while(!q.isEmpty()) {
            int[] cur = q.remove();
            int e = cur[0];
            int v = cur[1];
            if (isGate[v]) return e; // 출발지에 도착하면 바로 반환
            
            for (Node nxt : graph[v]) {
                if (visited[nxt.vtx] == 1 || isSummit[nxt.vtx]) continue;
                q.add(new int[]{Math.max(nxt.edge, e), nxt.vtx});
            }
            visited[v] = 1;
        }
        
        return Integer.MAX_VALUE;
    } 
    
    void makeGraph(int[][] paths) {
        graph = new List[N];
        
        for (int i=0; i<N; i++) {
            graph[i] = new LinkedList<>();
        }
        
        for (int[] path : paths) {
            int v1 = path[0];
            int v2 = path[1];
            int e = path[2];
            graph[v1].add(new Node(v2, e));
            graph[v2].add(new Node(v1, e));
        }
    }
}
