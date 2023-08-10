import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int winIY, winGY;
	static final int N = 18;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int M = N/2;
			
			int[] GY = new int[M];		
			boolean[] visited = new boolean[N+1];
			
			for (int i=0; i<M; i++) {
				GY[i] = Integer.parseInt(st.nextToken()); 
				visited[GY[i]] = true;
			}
			
			winIY = 0;
			winGY = 0;
			
			permu(0, M, new int[M], GY, visited);
			
			sb.append("#")
				.append(tc)
				.append(" ")
				.append(winGY)
				.append(" ")
				.append(winIY)
				.append("\n");
		}
		
		System.out.println(sb);
	}

	private static void permu(int idx, int M, int[] IY, int[] GY, boolean[] visited) {
		if (idx == M) {
			int scoreG = 0;
			int scoreI = 0;
			
			for (int i=0; i<M; i++) {
				if (GY[i] > IY[i])
					scoreG += GY[i] + IY[i];
				else if (GY[i] < IY[i])
					scoreI += GY[i] + IY[i];
			}
			
			if (scoreG > scoreI)
				winGY++;
			else if (scoreG < scoreI)
				winIY++;
			return;
		}
		
		for (int i=1; i<N+1; i++) {
			if (visited[i]) continue;
			IY[idx] = i;
			visited[i] = true;
			permu(idx+1, M, IY, GY, visited);
			visited[i] = false;
		}
	}
}
