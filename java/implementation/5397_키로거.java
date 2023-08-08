import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		
		for (int i=0; i<L; i++) {
			String password = br.readLine();
			List<Character> result = new LinkedList<>();
			
			int idx = 0;
			for (int j=0; j<password.length(); j++) {
				if (password.charAt(j) == '-') {
					if (idx != 0) {
						result.remove(idx-1);
						idx--;
					}
				}
				else if (password.charAt(j) == '>') {
					if (idx != result.size()) {
						idx++;
					}
				}
				else if (password.charAt(j) == '<') {
					if (idx != 0) {
						idx--;
					}
				}
				else {
					result.add(idx++, password.charAt(j));
				}
			}
			
			StringBuilder sb = new StringBuilder();
			
			for (Character c : result) {
				sb.append(c);
			}
			System.out.println(sb.toString());
		}
	}
}
