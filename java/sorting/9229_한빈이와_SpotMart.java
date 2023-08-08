import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] snacks = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(snacks);
			
			int r = N-1;
			int l = 0;
			
			int ans = -1;
			
			while (l < r) {
				int total = snacks[l] + snacks[r];
				if (total == M) {
					ans = total;
					break;
				}
				else if (total > M) {
					r--;
				}
				else {
					l++;
					ans = Math.max(total, ans);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
}
