import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
			if (Math.abs(o1) == Math.abs(o2))
				return o1 - o2;
			return Math.abs(o1) - Math.abs(o2);
		});
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num != 0) {
				pq.add(num);
				continue;
			}
			if (pq.isEmpty()) {
				sb.append("0\n");
				continue;
			}
			sb.append(pq.poll()).append("\n");
		}
		System.out.println(sb.toString());
	}
}
