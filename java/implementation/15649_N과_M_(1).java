import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	
	static void permu(int depth, int[] nums, int[] visited) {
		if (depth == M) {
			for (int i=0; i<M; i++) {
				System.out.print(nums[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i=1; i<=N; i++) {
			if (visited[i] == 1) continue;
			nums[depth] = i;
			visited[i] = 1;
			permu(depth + 1, nums, visited);
			visited[i] = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] visited = new int[N+1];
		permu(0, new int[M], visited);
	}
}
