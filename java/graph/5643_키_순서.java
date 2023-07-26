import java.util.*;
import java.io.*;

class Solution {
    
    public static int bfs(int num, List<Integer>[] graph, int N) {
    	int cnt = 0;
    	Queue<Integer> q = new LinkedList<>();
    	int[] visited = new int[N+1];
    	visited[num] = 1;
    	q.add(num);
    	
    	while (!q.isEmpty()) {
    		int cur = q.poll();
    		for (int next : graph[cur]) {
    			if (visited[next] != 1) {
    				cnt++;
    				q.add(next);
    				visited[next] = 1;
    			}
    		}
    	}
    	return cnt;
    }
    
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
            
            List<Integer>[] in = new LinkedList[N+1];
            List<Integer>[] out = new LinkedList[N+1];
            
            for (int i=0; i<N+1; i++) {
                in[i] = new LinkedList<>();
                out[i] = new LinkedList<>();
            }
            
            for (int i=0; i<M; i++) {
            	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                out[a].add(b);
                in[b].add(a);
            }
            
            int ans = 0;
            
            for (int i=1; i<N+1; i++) {
            	int total = 0;
            	total += bfs(i, in, N);
            	total += bfs(i, out, N);
            	if (total == N-1) {
            		ans++;
            	}
            }
            
            System.out.printf("#%d %d\n", test_case, ans);
        }
    } 
}
