import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        return kruskal(n, costs);
    }
    
    int kruskal(int n, int[][] costs) {
        int[] parents = initParents(n);
        int total = 0;
        
        for (int[] cost : costs) {
            int v1 = cost[0];
            int v2 = cost[1];
            int e = cost[2];
            
            int p1 = find(v1, parents);
            int p2 = find(v2, parents);
            if (p1 == p2) continue;
            if (p1 < p2) { // union
                parents[p2] = p1; // x == parents[x] 이므로
            }
            else {
                parents[p1] = p2;
            }
            total += e;
        }
        return total;
    }
    
    int find(int x, int[] parents) {
        if (x == parents[x]) {
            return x;
        }
        return find(parents[x], parents);
    }
    
    int[] initParents(int n) {
        int[] parents = new int[n];
        for (int i=0; i<n; i++) {
            parents[i] = i;
        }
        return parents;
    }
}
