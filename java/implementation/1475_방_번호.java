import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String N = sc.nextLine();
		
		int[] cnts = new int[10];
		
		int l = N.length();
		for (int i=0; i<l; i++) {
			if (N.charAt(i) == '9') {
				cnts[6]++;
			}
			else {
				cnts[N.charAt(i) - '0']++;
			}
		}
		
		cnts[6] = (cnts[6] / 2) + (cnts[6] % 2);
		int ans = 0;
		for (int i=0; i<10; i++) {
			if (cnts[i] > ans) {
				ans = cnts[i];
			}
		}
		System.out.println(ans);
	}
}
