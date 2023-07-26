import java.util.*;
import java.io.*;

class Solution {
	static List<Integer>[] in;
	static List<Integer>[] out;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] guncho = new int[N];
			int total = 0;
			for (int i=0; i<N; i++) {
				guncho[i] = Integer.parseInt(br.readLine());
				total += guncho[i];
			}
			int avg = total / N;
			
			int ans = 0;
			for (int g : guncho) {
				if (g < avg)
					ans += avg - g;
			}
			System.out.printf("#%d %d\n", test_case, ans);
		}
	} 
}
