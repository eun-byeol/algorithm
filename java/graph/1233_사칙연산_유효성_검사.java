import java.util.*;
import java.io.*;

class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int ans = 1;
			
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken();
				char v = st.nextToken().charAt(0);
				if (st.hasMoreTokens()) { // 리프노드가 아닌 경우 -> 연산자
					if (v > '0' && v < '9')
						ans = 0;
				}
				else { // 리프노드인 경우 -> 숫자
					if (v <= '0' || v > '9')
						ans = 0;
				}
			}
			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
}
