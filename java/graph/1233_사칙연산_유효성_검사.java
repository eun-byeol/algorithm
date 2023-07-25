import java.util.*;
import java.io.*;

class Solution {
	static int N;
	static String[] graph;
	static List<String> result;
	
	public static boolean isOperator(String s) {
		return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
	}
	
	public static void inorder(int cur) {
		if (graph[cur] == null)
			return;
		inorder(cur * 2);
		result.add(graph[cur]);
		inorder(cur * 2 + 1);
	}
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			graph = new String[2 * (N+1)];
			result = new ArrayList<>();
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				graph[num] = st.nextToken();
			}
			inorder(1);
			int ans = 1;
			for (int i=1; i<result.size()-1; i++) {
				if (!(isOperator(result.get(i)) ^ isOperator(result.get(i+1)))) {
					ans = 0;
					break;
				}
			}
			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
}
