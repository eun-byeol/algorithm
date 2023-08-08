import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		Deque<Integer> q = new ArrayDeque<>();
		
		for (int i=1; i<N+1; i++) {
			q.offer(i);
		}

		sb.append("<");
		int cnt = 1;
		while (q.size() > 1) {
			if (cnt % K == 0) {
				sb.append(q.pollFirst()).append(", ");
			}
			else {
				q.addLast(q.pollFirst());
			}
			cnt++;
		}
		sb.append(q.poll()).append(">");
		System.out.println(sb.toString());
	}
}
