import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] graph;
    static int minDist;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        minDist = Integer.MAX_VALUE;
        
        dfs(0, 0, 0, 0, new boolean[N]);
        
        System.out.println(minDist);
    }

    private static void dfs(int size, int total, int pre, int start, boolean[] visited) {
        if (total >= minDist) {
            return;
        }
        
        if (size == N) {
            if (graph[pre][start] == 0) return;
            
            total += graph[pre][start];
            
            if (total < minDist) {
                minDist = total;
            }
            return;
        }
        
        for (int i=0; i<N; i++) {
        	if (visited[i]) continue;
        	if (size == 0) {
        		visited[i] = true;
        		dfs(size+1, 0, i, i, visited);
        		visited[i] = false;
        	}
        	else {
        		if (graph[pre][i] == 0) continue; //연결 안 됨
        		visited[i] = true;
        		dfs(size+1, total + graph[pre][i], i, start, visited);
        		visited[i] = false;
        	}
        }
    }
}
