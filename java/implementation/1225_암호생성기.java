import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] arr = new int[8];
		
		for (int tc = 1; tc <= 10; tc++) {
			br.readLine();
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<8; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int start = 0;
			int end = start + 7;
			int cnt = 1;
			while(arr[end] > 0) {
				arr[start] -= cnt;
				cnt = cnt % 5 + 1;
				if (arr[start] < 0)
					arr[start] = 0;
				start = (start + 1) % 8;
				end = (start + 7) % 8;
			}
			
			System.out.print("#" + tc);
			int idx = start;
			while (true) {
				System.out.print(" " + arr[idx]);
				idx = (idx+1) % 8;
				if (idx == start)
					break;
			}
			System.out.println();
		}
	}
}
