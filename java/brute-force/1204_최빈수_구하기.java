import java.util.*;

class Solution {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++){
			int tc = sc.nextInt();
			int[] scoreCnt = new int[101];
			for (int i=0; i<1000; i++) {
				int score = sc.nextInt();
				scoreCnt[score]++;
			}
			int ans = 0;
			int max = -1;
			for (int i=100; i>0; i--) {
				if (scoreCnt[i] > max) {
					ans = i;
					max = scoreCnt[i];
				}
			}
			System.out.printf("#%d %d\n",tc, ans);
		}
	}
}
