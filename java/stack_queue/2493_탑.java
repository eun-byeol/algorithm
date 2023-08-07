import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] tops = new int[N];
		for (int i=0; i<N; i++) {
			tops[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] result = new int[N];
		boolean ansFlag = false;
		Stack<int[]> stack = new Stack<>();
		
		stack.add(new int[] {0, tops[0]});
		for (int i=1; i<N; i++) {
			while (!stack.isEmpty() && stack.peek()[1] < tops[i]) {
				stack.pop();
			}
			if (!stack.isEmpty() && stack.peek()[1] >= tops[i]) {
				result[i] = stack.peek()[0] + 1;
				ansFlag = true;
			}
			stack.add(new int[] {i, tops[i]});
		}
		
		if (ansFlag) {
			for (int i=0; i<N; i++) {
				sb.append(result[i]).append(" ");
			}
			System.out.println(sb.toString());
		}
		else {
			System.out.println(0);			
		}
	}
}
