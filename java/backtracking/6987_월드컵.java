import java.util.*;
import java.io.*;

public class Main {
	static int ans;
	static int[] t1 = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
	static int[] t2 = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int tc=0; tc<4; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int[][] result = new int[6][3];
			for (int i=0; i<6; i++) {
				result[i][0] = Integer.parseInt(st.nextToken());
				result[i][1] = Integer.parseInt(st.nextToken());
				result[i][2] = Integer.parseInt(st.nextToken());
			}
			
			ans = 0;
			dfs(0, result);
			sb.append(ans).append(" ");
		}
		System.out.println(sb);
	}

	private static void dfs(int game, int[][] result) {
		if (game == 15) {
			for (int i=0; i<6; i++) {
				if (result[i][0] != 0 || result[i][1] != 0 || result[i][2] != 0)
					return;
			}
			ans = 1;
			return;
		}
		
		result[t1[game]][0]--;
		result[t2[game]][2]--;
		dfs(game+1, result);
		result[t1[game]][0]++;
		result[t2[game]][2]++;
		
		result[t1[game]][1]--;
		result[t2[game]][1]--;
		dfs(game+1, result);
		result[t1[game]][1]++;
		result[t2[game]][1]++;
		
		result[t1[game]][2]--;
		result[t2[game]][0]--;
		dfs(game+1, result);
		result[t1[game]][2]++;
		result[t2[game]][0]++;
	}
}
