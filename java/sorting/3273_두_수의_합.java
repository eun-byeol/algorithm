import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		int x = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		int l = 0;
		int r = N-1;
		
		int ans = 0;
		while (l < r) {
			if (arr[l] + arr[r] == x) {
				ans++;
				l++;
				r--;
			}
			else if (arr[l] + arr[r] > x) {
				r--;
			}
			else {
				l++;
			}
		}
		
		System.out.println(ans);
	}
}
