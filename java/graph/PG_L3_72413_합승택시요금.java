import java.util.*;

class Solution {
    int N;
    int INF = 20_000_000;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        int[][] dist = floyd(fares);
        
        int answer = dist[s][a] + dist[s][b]; // 합승X
        
        for (int i=1; i<N+1; i++) { // s->i 까지 합승
            int total = dist[s][i] + dist[i][a] + dist[i][b];
            answer = Math.min(total, answer);
        }
        
        return answer;
    }
    
    int[][] floyd(int[][] fares) {
        int[][] dist = new int[N+1][N+1];
        for (int i=1; i<N+1; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        
        for (int[] fare : fares) {
            int v1 = fare[0];
            int v2 = fare[1];
            int e = fare[2];
            dist[v1][v2] = e;
            dist[v2][v1] = e;
        }

        for (int k=1; k<N+1; k++) { // 거쳐가는 노드
            for (int s=1; s<N+1; s++) { // 출발
                if (k == s) continue;
                for (int t=1; t<N+1; t++) { // 도착
                    if (k == t || s == t) continue;
                    int total = dist[k][s] + dist[k][t];
                    if (dist[s][t] > total) {
                        dist[s][t] = total;
                        dist[t][s] = total;
                    }
                }
            }
        }
        return dist;
    }
}
