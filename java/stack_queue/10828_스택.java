import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
	
		Stack<Integer> stack = new Stack<>();
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			
			
			if (cmd.equals("push")) {
				stack.push(Integer.parseInt(st.nextToken()));
				continue;
			}
			if (cmd.equals("pop")) {
				sb.append(stack.isEmpty() ? -1 : stack.pop()).append("\n");
				continue;
			}
			if (cmd.equals("size")) {
				sb.append(stack.size()).append("\n");
				continue;
			}
			if (cmd.equals("empty")) {
				sb.append(stack.isEmpty() ? 1 : 0).append("\n");
				continue;
			}
			if (cmd.equals("top")) {
				sb.append(stack.isEmpty() ? -1 : stack.peek()).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}
