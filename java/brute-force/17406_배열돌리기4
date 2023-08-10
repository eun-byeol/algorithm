import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    static int N, M, K;
    static int[][] board;
    static int[][] cmd;
    static int minAns;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        board = new int[N+1][M+1];
        
        for (int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<M+1; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        cmd = new int[K][3];
        
        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            cmd[i][0] = Integer.parseInt(st.nextToken());
            cmd[i][1] = Integer.parseInt(st.nextToken());
            cmd[i][2] = Integer.parseInt(st.nextToken());
        }
        
        minAns = Integer.MAX_VALUE;
        permu(0, new int[K], new boolean[K]);
        
        System.out.println(minAns);
    }
    
    private static void permu(int depth, int[] order, boolean[] visited) {
    	if (depth == K) {
    		int[][] copy = new int[N+1][M+1];
    		for (int i=1; i<N+1; i++) {
    			copy[i] = Arrays.copyOf(board[i], M+1);
    		}

    		for (int i=0; i<K; i++) {
    			int r = cmd[order[i]][0];
    			int c = cmd[order[i]][1];
    			int s = cmd[order[i]][2];
    			rotate(r-s, c-s, r+s, c+s, copy); // 범위 : [x1, y1] ~ [x2, y2]
    		}
    		
    		minAns = Math.min(findMinTotal(copy), minAns);
    		return;
    	}
    	
    	for (int i=0; i<K; i++) {
    		if (visited[i]) continue;
    		order[depth] = i;
    		visited[i] = true;
    		permu(depth+1, order, visited); 
    		visited[i] = false;
    	}
    }

    private static void rotate(int x1, int y1, int x2, int y2, int[][] board) {
        int endIdx = Math.min(x2-x1+1, y2-y1+1) / 2;
        
        for (int i=0; i<endIdx; i++) {
            int d = 0;
            int r = x1 + i;
            int c = y1 + i;
            int tmp = board[r][c];
            
            while (true) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr == x1+i && nc == y1+i) break;
                if (nr < x1+i || nr > x2-i || nc < y1+i || nc > y2-i) {
                    d++;
                    continue;
                }
                board[r][c] = board[nr][nc];
                r = nr;
                c = nc;
            }
            board[r][c] = tmp;
        }
    }

    private static int findMinTotal(int[][] board) {
    	int ans = Integer.MAX_VALUE;
    	
    	for (int i=1; i<N+1; i++) {
    		int total = 0;
    		for (int j=1; j<M+1; j++) {
    			total += board[i][j];
    		}
    		if (total < ans)
    			ans = total;
    	}
    	return ans;
    }
}
