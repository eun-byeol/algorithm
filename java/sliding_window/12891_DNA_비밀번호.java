import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		String arr = br.readLine();
		
		int[] cnts = new int[26];
		
		st = new StringTokenizer(br.readLine());
		cnts['A'-'A'] = Integer.parseInt(st.nextToken());
		cnts['C'-'A'] = Integer.parseInt(st.nextToken());
		cnts['G'-'A'] = Integer.parseInt(st.nextToken());
		cnts['T'-'A'] = Integer.parseInt(st.nextToken());
		
		int ans = 0;
		
		// 최초 1회
		for (int i=0; i<P; i++) {
			cnts[arr.charAt(i) - 'A']--;
		}
		if (check(cnts)) {
			ans++;
		}
		
		// 2회 ~
		for (int i=0; i<S-P; i++) {
			cnts[arr.charAt(i) - 'A']++;
			cnts[arr.charAt(i+P) - 'A']--;
			if (check(cnts)) {
				ans++;
			}
		}
		System.out.println(ans);
	}
	private static boolean check(int[] tmp) {
		return tmp['A'-'A'] < 1 && tmp['C'-'A'] < 1 && tmp['G'-'A'] < 1 && tmp['T'-'A'] < 1;
	}
}
