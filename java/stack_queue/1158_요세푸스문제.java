import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		List<Integer> nums = new LinkedList<>();
		for (int i=0; i<N; i++) {
			nums.add(i+1);
		}
		
		int start = 0;
		sb.append("<");
		while (nums.size() > 1) {
			int nextIdx = (start + K - 1) % nums.size();
			sb.append(nums.remove(nextIdx)).append(", ");
			start = nextIdx;
		}
		sb.append(nums.remove(0)).append(">");
		
		System.out.println(sb.toString());
	}
}
