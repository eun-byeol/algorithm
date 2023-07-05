import java.io.FileInputStream;
import java.util.*;

class Solution {
	
	static int solve(int[] arr1, int[] arr2, int N, int M) {
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i<M-N+1; i++) {
			int result = 0;
			for (int j = 0; j<N; j++) {
				result += arr1[j] * arr2[i+j];
			}
			if (result > max) {
				max = result;
			}
		}
		return max;	
	}
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++){
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			int[] arr1 = new int[N];
			int[] arr2 = new int[M];
			
			for (int i=0; i<N; i++) {
				arr1[i] = sc.nextInt();
			}
			for (int i=0; i<M; i++) {
				arr2[i] = sc.nextInt();
			}
			
			int ans = 0;
			if (N < M) {
				ans = solve(arr1, arr2, N, M);
			}
			else {
				ans = solve(arr2, arr1, M, N);
			}

			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
}
