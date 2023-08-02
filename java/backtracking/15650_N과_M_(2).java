import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] result = new int[M];
		combi(0, 1, N, M, result);
	}

	private static void combi(int depth, int at, int n, int m, int[] result) {
		if (depth == m) {
			for (int i=0; i<m; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i=at; i<=n; i++) {
			result[depth] = i;
			combi(depth + 1, i+1, n, m, result);
		}
	}
}
