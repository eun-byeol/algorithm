import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] S = new int[N];
		int[] B = new int[N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		long minDiff = Long.MAX_VALUE;
		for (int i=1; i<Math.pow(2, N); i++) {
			long totalS = 1;
			long totalB = 0;
			for (int j=0; j<N; j++) {
				if ((i & (1 << j)) == (1 << j)) {
					totalS *= S[j];
					totalB += B[j];
				}
			}
			long diff = Math.abs(totalS - totalB);
			if (diff < minDiff) {
				minDiff = diff;
			}
		}
		System.out.println(minDiff);
	}
}
