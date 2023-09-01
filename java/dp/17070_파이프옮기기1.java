import java.io.*;
import java.util.*;

public class Main_17070_파이프옮기기1 {
    static int[][] map;
    static int N;
    static int[] dx = {0, -1, -1}; // →, ↘, ↓
    static int[] dy = {-1, -1, 0};
    static int[][] preCases = {{0,1}, {0,1,2}, {1,2}};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        map = new int[N+1][N+1];
        
        for (int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<N+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int[][][] dp = new int[N+2][N+2][3];
        dp[1][2][0] = 1;
        
        for (int x=1; x<N+1; x++) {
            for (int y=1; y<N+1; y++) {
            	
            	if (map[x][y] != 0) continue; // 놓을 수 없는 자리
            	
            	for (int d=0; d<3; d++) {
            		
            		if (d == 1) {
            			if (map[x-1][y] != 0 || map[x][y-1] != 0) continue; // 대각선으로 놓는 경우 - 놓을 수 없는 자리
            		}
            		
            		for (int preDir : preCases[d]) {
            			dp[x][y][d] += dp[x+dx[d]][y+dy[d]][preDir];
            		}
            	}
            }
        }
        
        System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
    }
}
