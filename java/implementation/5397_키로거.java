import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		
		for (int i=0; i<L; i++) {
			String password = br.readLine();
			
			Deque<Character> result = new ArrayDeque<>();
			Deque<Character> tmp = new ArrayDeque<>();
			
			for (int j=0; j<password.length(); j++) {
				char v = password.charAt(j); 
				if (v == '-') {
					if (!result.isEmpty()) {
						result.pollLast();						
					}
				}
				else if (v == '<') {
					if (!result.isEmpty()) {
						tmp.offerFirst(result.pollLast());
					}
				}
				else if (v == '>') {
					if (!tmp.isEmpty()) {
						result.offerLast(tmp.pollFirst());
					}
				}
				else {
					result.offerLast(v);
				}
			}
			
			StringBuilder sb = new StringBuilder();
			
			while (!result.isEmpty()) {
				sb.append(result.pollFirst());
			}			
			
			while (!tmp.isEmpty()) {
				sb.append(tmp.pollFirst());
			}
			
			System.out.println(sb.toString());
		}
	}
}
