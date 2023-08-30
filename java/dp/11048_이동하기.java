import java.io.*;
import java.util.*;

public class Main_11048_이동하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N+1][M+1];
		
		for (int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<M+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				int max = Math.max(map[i-1][j], map[i][j-1]);
				max = Math.max(map[i-1][j-1], max);
				
				map[i][j] += max;
			}
		}
		
		System.out.println(map[N][M]);
	}
}
