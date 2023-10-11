import java.util.*;
import java.io.*;

public class Solution_14510_나무높이 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] trees = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int maxH = -1;
			for (int i=0; i<N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				maxH = Math.max(trees[i], maxH);
			}
			
			int even = 0;
			int odd = 0;
			
			for (int tree : trees) {
				int dist = maxH - tree;
				even += dist / 2;
				odd += dist % 2;
			}
			
			int day = 2 * Math.min(even, odd);

			if (even > odd) {
				int tmp = 2*(even-odd); 
				day += (tmp/3) * 2 + (tmp%3);
			}
			if (even < odd) {
				day += 2*(odd-even) - 1;
			}
			
			sb.append("#").append(tc).append(" ").append(day).append("\n");
		}
		
		System.out.println(sb);
	}

}
